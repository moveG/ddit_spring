package kr.or.ddit.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.josephoconnell.html.HTMLInputFilter;
import com.jsp.command.NoticeModifyCommand;
import com.jsp.command.NoticeRegistCommand;
import com.jsp.command.SearchCriteria;
import com.jsp.controller.XSSHttpRequestParameterAdapter;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping("/main")
	public String main() throws Exception {
		return "notice/main";
	}
	
	@RequestMapping("/list")
	public void list(SearchCriteria cri, Model model) throws Exception {
		Map<String, Object> dataMap = noticeService.getNoticeList(cri);
		List<NoticeVO> pointList = noticeService.getPointList();
		
		model.addAllAttributes(dataMap);
		model.addAttribute("pointList", pointList);
	}
	
	@RequestMapping("/registForm")
	public String registForm() throws Exception {
		String url = "notice/regist";
				
		return url;
	}
	
	@RequestMapping("/regist")
	public String regist(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/notice/list.do";
		
		NoticeRegistCommand regData = (NoticeRegistCommand) XSSHttpRequestParameterAdapter.execute(request, NoticeRegistCommand.class, true);
		
		NoticeVO noticeVO = regData.toNoticeVO();
		noticeVO.setContent(request.getParameter("content"));
		
		noticeService.registNotice(noticeVO);
		
		rttr.addFlashAttribute("from", "regist");
		
		return url;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(int nno
					   , @RequestParam(defaultValue="") String from
					   , HttpServletRequest request
					   , ModelAndView mnv) throws Exception {
		String url = "notice/detail";
		
		NoticeVO noticeVO = null;
		
		if(!from.equals("list")) {
			noticeVO = noticeService.getNoticeForModify(nno);
		}else {
			noticeVO = noticeService.getNotice(nno);
			url = "redirect:/notice/detail.do?nno=" + nno;
		}
		
		mnv.addObject("noticeVO", noticeVO);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(int nno, ModelAndView mnv) throws Exception {
		String url = "notice/modify";
		
		NoticeVO noticeVO = noticeService.getNoticeForModify(nno);
		
		mnv.addObject("noticeVO", noticeVO);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/notice/detail.do";
		
		NoticeModifyCommand modData = (NoticeModifyCommand) XSSHttpRequestParameterAdapter.execute(request, NoticeModifyCommand.class, true);
		
		NoticeVO noticeVO = modData.toNoticeVO();
		noticeVO.setContent(request.getParameter("content"));
		
		noticeService.modifyNotice(noticeVO);
		
		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("nno", noticeVO.getNno());
		
		return url;
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(int nno, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/notice/detail.do";
		
		noticeService.removeNotice(nno);
		
		rttr.addFlashAttribute("from", "remove");
		rttr.addAttribute("nno", nno);
		
		return url;
	}
}
