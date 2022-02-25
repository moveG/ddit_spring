package kr.or.ddit.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.command.MemberRegistCommand;
import com.jsp.command.SearchCriteria;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberServiceForModify;
import com.jsp.utils.MakeFileName;

import kr.or.ddit.command.MemberModifyCommand;
import kr.or.ddit.utils.ExceptionLoggerHelper;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberServiceForModify memberService;
	
	@RequestMapping("/main")
	public String main() throws Exception {
		return "member/main.open";
	}
	
	@RequestMapping("/list")
	public ModelAndView list(SearchCriteria cri
						   , HttpServletRequest request
						   , ModelAndView mnv) throws SQLException {
		String url = "member/list.open";
		
		//if(true) throw new SQLException();
		Map<String, Object> dataMap = null;
		
		try {
			//if(true) throw new SQLException();
			dataMap = memberService.getMemberListPage(cri);
		} catch (SQLException e) {
			e.printStackTrace();
			ExceptionLoggerHelper.write(request, e, memberService);
			throw new SQLException();
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionLoggerHelper.write(request, e, memberService);
		}
		//mnv.addObject("dataMap", dataMap);
		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value="/registForm", method=RequestMethod.GET)
	public String registForm() {
		String url = "member/regist.open";
		return url;
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String regist(MemberRegistCommand memberReq) throws Exception {
		String url = "member/regist_success";
		
		MemberVO member = memberReq.toMemberVO();
		memberService.registMember(member);
		
		return url;
	}
		
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String detail(@ModelAttribute("id") String id, Model model) throws Exception {
		//@ModelAttribute("id") String id : id를 Model에 "id"로 심어줌, redirect때 많이 사용함
		String url = "member/detail.open";
		
		MemberVO member = memberService.getMember(id);
		model.addAttribute("member", member);
		
		return url;
	}
	
	@RequestMapping(value="/modifyForm", method=RequestMethod.GET)
	public String modifyForm(String id, Model model) throws Exception {
		String url = "member/modify.open";
		
		MemberVO member = memberService.getMember(id);
		model.addAttribute("member", member);
		
		return url;
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(MemberModifyCommand modifyReq, HttpSession session, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/member/detail.do";
		
		MemberVO member = modifyReq.toParseMember();
		
		//신규파일 변경 및 기존파일 삭제
		String fileName = savePicture(modifyReq.getOldPicture(), modifyReq.getPicture());
		member.setPicture(fileName);
		
		//파일변경 없을시 기존 파일명 유지
		if(modifyReq.getPicture().isEmpty()) {
			member.setPicture(modifyReq.getOldPicture());
		}
		
		//DB내용 수정
		memberService.modifyMember(member);
		
		rttr.addFlashAttribute("parentReload", false);
		
		//로그인한 사용자의 경우, 수정된 정보로 session 업로드
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if(loginUser != null && member.getId().equals(loginUser.getId())) {
			session.setAttribute("loginUser", member);
			rttr.addFlashAttribute("parentReload", true);
		}
		
		rttr.addAttribute("id", member.getId());
		rttr.addAttribute("from", "modify");
		
		return url;
	}
	
	@Resource(name="picturePath")
	private String picturePath;
	
	private String savePicture(String oldPicture, MultipartFile multi) throws Exception {
		String fileName = null;
		
		//파일유무 확인
		if(!(multi == null || multi.isEmpty() || multi.getSize() > 1024 * 1024 * 5)) {
			//파일저장폴더 설정
			String uploadPath = picturePath;
			fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
			File storeFile = new File(uploadPath, fileName);
			
			storeFile.mkdirs();
			
			//local HDD에 저장
			multi.transferTo(storeFile);
			
			if(oldPicture != null && !oldPicture.isEmpty()) {
				File oldFile = new File(uploadPath, oldPicture);
				if(oldFile.exists()) {
					oldFile.delete();
				}
			}
		}
		return fileName;
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.GET)
	public String remove(String id, HttpSession session, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/member/detail.do";
		
		MemberVO member = null;
		
		//이미지 파일 삭제
		member = memberService.getMember(id);
		String savePath = this.picturePath;
		File imageFile = new File(savePath, member.getPicture());
		if(imageFile.exists()) {
			imageFile.delete();
		}
		
		//DB처리
		memberService.removeMember(id);
		
		//삭제되는 회원이 로그인한 회원인 경우, 로그아웃 해야함
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if(loginUser.getId().equals(member.getId())) {
			session.invalidate();
		}
		
		rttr.addFlashAttribute("removeMember", member);
		rttr.addAttribute("from", "remove");
		rttr.addAttribute("id", id);
		
		return url;
	}
	
	@RequestMapping(value="/enabled", method=RequestMethod.GET)
	public String enabled(String id, int enabled, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/member/detail.do";
		
		memberService.enabledMember(id, enabled);
		
		rttr.addAttribute("from", "enabled");
		rttr.addAttribute("id", id);
		
		return url;
	}
}
