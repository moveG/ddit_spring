package kr.or.ddit.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.command.BoardModifyCommand;
import com.jsp.command.SearchCriteria;
import com.jsp.controller.XSSHttpRequestParameterAdapter;
import com.jsp.dto.BoardVO;
import com.jsp.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/main")
	public String main() throws Exception {
		return "board/main";
	}
	
	@RequestMapping("/list")
	public ModelAndView list(SearchCriteria cri, ModelAndView mnv) throws Exception {
		String url = "board/list";
		
		Map<String, Object> dataMap = boardService.getBoardList(cri);
		mnv.addObject("dataMap", dataMap);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/registForm")
	public String registForm() throws Exception {
		String url = "board/regist";
		
		return url;
	}
	
	@RequestMapping("/regist")
	public String regist(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/board/list.do";
		
		BoardVO boardVO = (BoardVO) XSSHttpRequestParameterAdapter.execute(request, BoardVO.class, true);
		boardVO.setContent(request.getParameter("content"));
		boardService.registBoard(boardVO);
		
		rttr.addFlashAttribute("from", "regist");
		
		return url;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(int bno, String from, ModelAndView mnv) throws Exception {
		String url = "board/detail";
		
		BoardVO boardVO = null;
		if(from != null && from.equals("list")) {
			boardVO = boardService.getBoard(bno);
			url = "redirect:/board/detail.do?bno=" + bno;
		}else {
			boardVO = boardService.getBoardForModify(bno);
		}
		
		mnv.addObject("boardVO", boardVO);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping("/modifyForm")
	public ModelAndView modifyForm(int bno, ModelAndView mnv) throws Exception {
		String url = "board/modify";
		
		BoardVO boardVO = boardService.getBoardForModify(bno);
		
		mnv.addObject("boardVO", boardVO);
		mnv.setViewName(url);
		
		return mnv;
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(HttpServletRequest request, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/board/detail.do";
		
		BoardModifyCommand modData = (BoardModifyCommand) XSSHttpRequestParameterAdapter.execute(request, BoardModifyCommand.class, true);
		
		BoardVO boardVO = modData.toBoardVO();
		boardVO.setContent(request.getParameter("content"));
		
		boardService.modifyBoard(boardVO);
		
		rttr.addFlashAttribute("from", "modify");
		rttr.addAttribute("bno", boardVO.getBno());
		
		return url;
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(int bno, RedirectAttributes rttr) throws Exception {
		String url = "redirect:/board/detail.do";
		
		boardService.removeBoard(bno);
		
		rttr.addAttribute("bno", bno);
		rttr.addFlashAttribute("from", "remove");
		
		return url;
	}
}
