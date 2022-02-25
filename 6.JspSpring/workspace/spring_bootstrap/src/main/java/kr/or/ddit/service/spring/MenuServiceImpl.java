package kr.or.ddit.service.spring;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.MenuVO;
import com.jsp.service.MenuService;

import kr.or.ddit.dao.spring.MenuDAOBean;

public class MenuServiceImpl implements MenuService {
	
	private MenuDAOBean menuDAOBean;
	public void setMenuDAOBean(MenuDAOBean menuDAOBean) {
		this.menuDAOBean = menuDAOBean;
	}
	
	//메인메뉴
	@Override
	public List<MenuVO> getMainMenuList() throws SQLException {
		List<MenuVO> menuList = menuDAOBean.selectMainMenu();
		return menuList;
	}

	//서브메뉴
	@Override
	public List<MenuVO> getSubMenuList(String mCode) throws SQLException {
		List<MenuVO> menuList = menuDAOBean.selectSubMenu(mCode);
		return menuList;
	}

	//메뉴정보
	@Override
	public MenuVO getMenuByMcode(String mCode) throws SQLException {
		MenuVO menu = menuDAOBean.selectMenuByMcode(mCode);
		return menu;
	}
	
	//메뉴정보
	@Override
	public MenuVO getMenuByMname(String mName) throws SQLException {
		MenuVO menu = menuDAOBean.selectMenuByMname(mName);
		return menu;
	}
}
