package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.MenuDAO;
import com.jsp.dto.MenuVO;

public class MenuServiceImpl implements MenuService {
	
	private MenuDAO menuDAO;// = new MenuDAOImpl();
	public void setMenuDAO(MenuDAO menuDAO) {
		this.menuDAO = menuDAO;
	}

	private SqlSessionFactory sqlSessionFactory;// = new OracleMyBatisSqlSessionFactory();
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}

	//메인메뉴
	@Override
	public List<MenuVO> getMainMenuList() throws SQLException {
		List<MenuVO> menuList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			menuList = menuDAO.selectMainMenu(session);
		} finally {
			session.close();
		}
		return menuList;
	}

	//서브메뉴
	@Override
	public List<MenuVO> getSubMenuList(String mCode) throws SQLException {
		List<MenuVO> menuList = null;
		SqlSession session = sqlSessionFactory.openSession();
		try {
			menuList = menuDAO.selectSubMenu(session, mCode);
		} finally {
			session.close();
		}
		return menuList;
	}

	//메뉴정보
	@Override
	public MenuVO getMenuByMcode(String mCode) throws SQLException {
		MenuVO menu = null;

		SqlSession session = sqlSessionFactory.openSession();
		try {
			menu = menuDAO.selectMenuByMcode(session, mCode);
		} finally {
			session.close();
		}
		return menu;
	}
	
	//메뉴정보
	@Override
	public MenuVO getMenuByMname(String mName) throws SQLException {
		MenuVO menu = null;

		SqlSession session = sqlSessionFactory.openSession();
		try {
			menu = menuDAO.selectMenuByMname(session, mName);
		} finally {
			session.close();
		}
		return menu;
	}
}
