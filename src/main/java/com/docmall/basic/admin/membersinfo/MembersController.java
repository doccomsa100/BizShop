package com.docmall.basic.admin.membersinfo;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.docmall.basic.common.constants.Constants;
import com.docmall.basic.common.dto.Criteria;
import com.docmall.basic.common.dto.PageDTO;
import com.docmall.basic.common.util.FileManagerUtils;
import com.docmall.basic.mail.EmailService;
import com.docmall.basic.user.UserService;
import com.docmall.basic.user.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/members/*")
public class MembersController {

	private final MembersService membersService;
	
	private final UserService userService;
	
	private final PasswordEncoder passwordEncoder;
	
	private final EmailService emailService;
	
	//CKeditor 파일업로드 경로
	@Value("${file.ckdir}")
	private String uploadCKPath;
	
	
	@GetMapping("/member_list")
	public void member_list(Criteria cri, Model model) {
		
		cri.setAmount(20);
		
		List<UserVo> list = membersService.member_list(cri);
		log.info("회원목록 데이터: " + list);
		
		// 게시물 목록 20건
		model.addAttribute("member_list", list);
		
		// 전체데이터 개수
		int total = membersService.getTotalCount(cri);
		PageDTO pageDTO = new PageDTO(cri, total);
		
		log.info("페이징 기능데이터: " + pageDTO);
		model.addAttribute("pageMaker", pageDTO);
	}
	
	@PostMapping("/member_delete")
	public String member_delete(Criteria cri, String user_id) {
		membersService.member_delete(user_id);
		
		return "redirect:/admin/members/member_list" + cri.getListLink();
	}
	
	@GetMapping("/member_insert")
	public void member_insert() {
		
	}
	
	// 아이디중복체크
	@GetMapping("idCheck")
	public ResponseEntity<String> idCheck(String user_id) throws Exception {
		ResponseEntity<String> entity = null;
		
		String idUse = "";
		// 사용자가 입력한 아이디를 비교해서 있으면 null이 아니여서 true 사용불가
		// 사용자가 입력한 아이디를 비교해서 없으면 null이기 떄문에 false 사용가능
		if(userService.idCheck(user_id) != null) {
			idUse = "no";  // 사용불가능
		}else {
			idUse = "yes"; // 사용가능
		}
		
		entity = new ResponseEntity<String>(idUse, HttpStatus.OK);
		
		return entity;
	}
	
	// 회원가입 등록
	@PostMapping("member_join")
	public String member_joinOk(UserVo vo) throws Exception {
		
		vo.setUser_pwd(passwordEncoder.encode(vo.getUser_pwd()));
		
		log.info("회원정보: " + vo);
		
		userService.join(vo);
		
		return "redirect:/admin/members/member_list";
	}
	
	
	// 메일발송목록
	@GetMapping("/mem_mail_list")
	public void mem_mail_list(Criteria cri, String mtitle,Model model) {
		
		List<MembersVO> maillist = membersService.mem_mail_list(cri, mtitle);
		
		int totalcount = membersService.getMailListCount(mtitle);
		PageDTO pageDTO = new PageDTO(cri, totalcount);
		
		model.addAttribute("maillist", maillist);
		model.addAttribute("pageMaker", pageDTO);
		
		
	}
	
	
	// 메일폼
	@GetMapping("/mem_mail_form")
	public void mem_mail_form(@ModelAttribute("vo") MembersVO vo) {
		
	}
	
	// 메일저장
	@PostMapping("mem_mail_save")
	public String mem_mail_save(@ModelAttribute("vo")MembersVO vo, Model model) {
		log.info("메일내용: " + vo);
		
		// 메일정보 DB저장
		membersService.mem_mail_save(vo);
		
		log.info("시퀸스: " + vo.getIdx());
		
		model.addAttribute("idx", vo.getIdx()); // 메일보내기 횟수작업 사용
		model.addAttribute("msg", "mem_save");
		return "/admin/members/mem_mail_form";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// CKEditor 상품설명 이미지 업로드
	// MultipartFile upload : CKeditor의 업로드탭에서 나온 파일첨부 <input type="file" name="upload"> 을 참조함.
	// HttpServletRequest request : 클라이언트의 요청정보를 가지고 있는 객체.
	// HttpServletResponse response : 서버에서 클라이언트에게 보낼 정보를 응답하는 객체
	@PostMapping("/imageupload")
	public void imageupload(HttpServletRequest request, HttpServletResponse response, MultipartFile upload) {
		
		OutputStream out = null;
		PrintWriter printWriter = null; // 서버에서 클라이언트에게 응답정보를 보낼때 사용.(업로드한 이미지정보를 브라우저에게 보내는 작업용도)
		
		try {
			//1)CKeditor를 이용한 파일업로드 처리.
			String fileName = upload.getOriginalFilename(); // 업로드 할 클라이언트 파일이름
			byte[] bytes = upload.getBytes(); // 업로드 할 파일의 바이트배열
			
			String ckUploadPath = uploadCKPath + fileName; // "C:\\Dev\\upload\\ckeditor\\" + "abc.gif"
			
			out = new FileOutputStream(ckUploadPath); // "C:\\Dev\\upload\\ckeditor\\abc.gif" 파일생성. 0 byte
			
			out.write(bytes); // 빨대(스트림)의 공간에 업로드할 파일의 바이트배열을 채운상태.
			out.flush(); // "C:\\Dev\\upload\\ckeditor\\abc.gif" 0 byte -> 크기가 채워진 정상적인 파일로 인식.
			
			//2)업로드한 이미지파일에 대한 정보를 클라이언트에게 보내는 작업
			
			printWriter = response.getWriter();
			
			// 한글파일 인코딩 설정문제 발생.  Constants.ROOR_URL: 이메일에 이미지 안깨지게 하는 설정
			String fileUrl = Constants.ROOR_URL + "/admin/product/display/" + fileName; // 매핑주소/이미지파일명
//						String fileUrl = fileName;
			
			
			// Ckeditor 4.12에서는 파일정보를 다음과 같이 구성하여 보내야 한다.
			// {"filename" : "abc.gif", "uploaded":1, "url":"/ckupload/abc.gif"}
			// {"filename" : 변수, "uploaded":1, "url":변수}
			printWriter.println("{\"filename\" :\"" + fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}"); // 스트림에 채움.
			printWriter.flush();
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			if(out != null) {
				try {
					out.close();
				}catch(Exception ex) {
					ex.printStackTrace();
				}
			}
			if(printWriter != null) printWriter.close();
		}
	}
	
	@GetMapping("/display/{fileName}")
	public ResponseEntity<byte[]> getFile(@PathVariable("fileName") String fileName) {
		
		log.info("파일이미지: " + fileName);
		
		
		ResponseEntity<byte[]> entity = null;
		
		try {
			entity = FileManagerUtils.getFile(uploadCKPath, fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return entity;
		
	}
	
}
