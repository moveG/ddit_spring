package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MenuVO;

public interface MenuDAO {
	
	//메인메뉴
	public List<MenuVO> selectMainMenu(SqlSession session) throws SQLException;
	
	//서브메뉴
	public List<MenuVO> selectSubMenu(SqlSession session, String mCode) throws SQLException;
	
	//메뉴정보
	public MenuVO selectMenuByMcode(SqlSession session, String mCode) throws SQLException;
	public MenuVO selectMenuByMname(SqlSession session, String mName) throws SQLException;
}
