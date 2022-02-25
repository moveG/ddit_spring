package kr.or.ddit.dao.spring;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.MenuVO;

public interface MenuDAOBean {
	
	//메인메뉴
	public List<MenuVO> selectMainMenu() throws SQLException;
	
	//서브메뉴
	public List<MenuVO> selectSubMenu(String mCode) throws SQLException;
	
	//메뉴정보
	public MenuVO selectMenuByMcode(String mCode) throws SQLException;
	public MenuVO selectMenuByMname(String mName) throws SQLException;
}
