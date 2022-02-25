package com.jsp.command;

import com.jsp.exception.NotNumberException;

public class SearchCriteria extends Criteria {
	
	private String searchType = "";
	private String searchWord = "";
	
	public SearchCriteria() {}
	public SearchCriteria(String pageStr, String perPageNumStr, String searchType, String searchWord) throws NotNumberException {
		super(pageStr, perPageNumStr);
		this.searchType = searchType;
		this.searchWord = searchWord;
	}
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
}
