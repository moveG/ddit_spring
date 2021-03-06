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
import com.jsp.service.MemberService;
import com.jsp.utils.MakeFileName;

import kr.or.ddit.command.MemberModifyCommand;
import kr.or.ddit.utils.ExceptionLoggerHelper;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/main")
	public void main() {}
	
	@RequestMapping("/list")
	public ModelAndView list(SearchCriteria cri, HttpServletRequest request, ModelAndView mnv) throws SQLException {
		String url = "member/list";
		
		Map<String, Object> dataMap = null;
		
		try {
			dataMap = memberService.getMemberListPage(cri);
		} catch (SQLException e) {
			e.printStackTrace();
			ExceptionLoggerHelper.write(request, e, memberService);
			throw new SQLException();
		} catch (Exception e) {
			e.printStackTrace();
			ExceptionLoggerHelper.write(request, e, memberService);
		}
		mnv.addAllObjects(dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value="/registForm", method=RequestMethod.GET)
	public String registForm() {
		String url = "member/regist";
		return url;
	}
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String regist(MemberRegistCommand memberReq) throws Exception {
		String url = "member/regist_success";
		
		MemberVO memberVO = memberReq.toMemberVO();
		memberService.registMember(memberVO);
		
		return url;
	}
	
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public String detail(@ModelAttribute("id") String id, Model model) throws Exception {
		String url = "member/detail";
		
		MemberVO memberVO = memberService.getMember(id);
		model.addAttribute("memberVO", memberVO);
		
		return url;
	}
	
	@RequestMapping(value="/modifyForm", method=RequestMethod.GET)
	public String modifyForm(String id, Model model) throws Exception {
		String url = "member/modify";
		
		MemberVO memberVO = memberService.getMember(id);
		model.addAttribute("memberVO", memberVO);
		
		return url;
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(MemberModifyCommand modData, HttpSession session, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/member/detail.do";
		
		MemberVO memberVO = modData.toParseMember();
		
		//???????????? ?????? ??? ???????????? ??????
		String fileName = savePicture(modData.getOldPicture(), modData.getPicture());
		memberVO.setPicture(fileName);
		
		//???????????? ????????? ?????? ????????? ??????
		if(modData.getPicture().isEmpty()) {
			memberVO.setPicture(modData.getOldPicture());
		}
		
		//DB?????? ??????
		memberService.modifyMember(memberVO);
		
		rttr.addFlashAttribute("parentReload", false);
		
		//???????????? ???????????? ??????, ????????? ????????? session ?????????
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if(loginUser != null && memberVO.getId().equals(loginUser.getId())) {
			session.setAttribute("loginUser", memberVO);
			rttr.addFlashAttribute("parentReload", true);
		}
		
		rttr.addAttribute("id", memberVO.getId());
		rttr.addAttribute("from", "modify");
		
		return url;
	}
	
	@Resource(name="picturePath")
	private String picturePath;
	
	private String savePicture(String oldPicture, MultipartFile multi) throws Exception {
		String fileName = null;
		
		//???????????? ??????
		if(!(multi == null || multi.isEmpty() || multi.getSize() > 1024 * 1024 * 5)) {
			//?????????????????? ??????
			String uploadPath = picturePath;
			fileName = MakeFileName.toUUIDFileName(multi.getOriginalFilename(), "$$");
			File storeFile = new File(uploadPath, fileName);
			
			storeFile.mkdirs();
			
			//local HDD??? ??????
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
		
		MemberVO memberVO = null;
		
		//????????? ?????? ??????
		memberVO = memberService.getMember(id);
		String savePath = this.picturePath;
		File imageFile = new File(savePath, memberVO.getPicture());
		if(imageFile.exists()) {
			imageFile.delete();
		}
		
		//DB??????
		memberService.removeMember(id);
		
		//???????????? ????????? ???????????? ????????? ??????, ???????????? ?????????
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		if(loginUser.getId().equals(memberVO.getId())) {
			session.invalidate();
		}
		
		rttr.addFlashAttribute("removeMember", memberVO);
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
