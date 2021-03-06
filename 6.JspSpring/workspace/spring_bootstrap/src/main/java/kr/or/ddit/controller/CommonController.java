package kr.or.ddit.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jsp.dto.MenuVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;
import com.jsp.service.MemberServiceForModify;
import com.jsp.service.MenuService;

@Controller
public class CommonController {
	
	@Autowired
	private MemberServiceForModify memberService;
	
	@Autowired
	private MenuService menuService;
	
	@RequestMapping(value="/common/loginForm", method=RequestMethod.GET)
	public String loginForm(@RequestParam(defaultValue="") String error, HttpServletResponse response) {
		String url = "common/login.open";
		
		if(error.equals("1")) {
			response.setStatus(302);
		}
		return url;
	}
	
	@RequestMapping("/security/accessDenied")
	public void accessDenied() {}
	
	@RequestMapping("/common/loginTimeOut")
	public String loginTimeOut(Model model) throws Exception {
		String url = "security/sessionOut";
		
		model.addAttribute("message", "세션이 만료되었습니다.\\n다시 로그인하세요.");
		return url;
	}
	
	//SessionExpiredCheckFilter를 만들었기 때문에 더이상 필요없음
	/*@RequestMapping("/common/loginExpired")
	public String loginExpired(Model model) throws Exception {
		String url = "security/sessionOut";
		
		model.addAttribute("message", "중복 로그인이 확인되었습니다.\\n다시 로그인하면 다른 장치의 로그인이 취소됩니다.");
		return url;
	}*/
	
	/*@RequestMapping(value="/common/login", method=RequestMethod.POST)
	public String login(String id, String pwd, HttpSession session, RedirectAttributes rttr, Model model) throws Exception {
		String url = "redirect:/index.do";
		
		try {
			memberService.login(id, pwd);
			session.setAttribute("loginUser", memberService.getMember(id));
		} catch (NotFoundIDException | InvalidPasswordException e) {
			//model.addAttribute("message", e.getMessage());
			//url = "common/login_fail";
			rttr.addFlashAttribute("message", e.getMessage());
			url = "redirect:/common/loginForm.do";
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return url;
	}
	
	@RequestMapping(value="/common/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		String url = "redirect:/";
		session.invalidate();
		return url;
	}*/
	
	@RequestMapping("/index")
	public String index(@RequestParam(defaultValue="M000000") String mCode, Model model) throws SQLException {
		String url = "common/indexPage.page";
		
		try {
			List<MenuVO> menuList = menuService.getMainMenuList();
			MenuVO menu = menuService.getMenuByMcode(mCode);
			
			model.addAttribute("menuList", menuList);
			model.addAttribute("menu", menu);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		return url;
	}
	
	@RequestMapping("/subMenu")
	@ResponseBody
	public ResponseEntity<List<MenuVO>> subMenuToJSON(String mCode) throws Exception {
		ResponseEntity<List<MenuVO>> entity = null;
		List<MenuVO> subMenu = null;
		
		try {
			subMenu = menuService.getSubMenuList(mCode);
			
			entity = new ResponseEntity<List<MenuVO>>(subMenu, HttpStatus.OK);
		} catch (Exception e) {
			entity = new ResponseEntity<List<MenuVO>>(HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		return entity;
	}
	
	@RequestMapping("/main")
	public String main() throws Exception {
		String url = "common/main.open";
		return url;
	}
}
