package kr.or.ddit.dao.spring;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dao.MenuDAO;
import com.jsp.dto.MenuVO;

public class MenuDAOImplTemplate implements MenuDAOBean {
	
	private SqlSession session;
	public void setSession(SqlSession session) {
		this.session = session;
	}

	private MenuDAO menuDAO;
	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}
	
	//메인메뉴
	@Override
	public List<MenuVO> selectMainMenu() throws SQLException {
		return menuDAO.selectMainMenu(session);
	}
	
	//서브메뉴
	@Override
	public List<MenuVO> selectSubMenu(String mCode) throws SQLException {
		return menuDAO.selectSubMenu(session, mCode);
	}
	
	//메뉴정보
	@Override
	public MenuVO selectMenuByMcode(String mCode) throws SQLException {
		return menuDAO.selectMenuByMcode(session, mCode);
	}
	
	//메뉴정보
	@Override
	public MenuVO selectMenuByMname(String mName) throws SQLException {
		return menuDAO.selectMenuByMname(session, mName);
	}
}
