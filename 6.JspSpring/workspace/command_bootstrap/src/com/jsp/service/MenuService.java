package com.jsp.service;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.MenuVO;

public interface MenuService {
	//메인메뉴
	List<MenuVO> getMainMenuList() throws SQLException;
	
	//서브메뉴
	List<MenuVO> getSubMenuList(String mCode) throws SQLException;
	
	//메뉴정보
	MenuVO getMenuByMcode(String mCode) throws SQLException;
	MenuVO getMenuByMname(String mName) throws SQLException;
}
