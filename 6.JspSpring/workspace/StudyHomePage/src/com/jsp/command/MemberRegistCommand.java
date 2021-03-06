package com.jsp.command;

import java.util.Date;

import com.jsp.dto.MemberVO;

public class MemberRegistCommand {
	private String id;			//아이디
	private String pwd;			//패스워드
	private String email;		//이메일
	private String name;		//이름
	private String[] phone;		//전화번호
	private String address;		//주소
	private String authority;	//권한
	private String picture;		//사진파일경로, 파일명
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getPhone() {
		return phone;
	}
	public void setPhone(String[] phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}

	public MemberVO toMemberVO() {
		String phone = "";
		for(String data : this.phone) {
			phone += data;
		}
		
		//MemberVO 세팅
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPwd(pwd);
		member.setEmail(email);
		member.setName(name);
		member.setPhone(phone);
		member.setAddress(address);
		member.setAuthority(authority);
		member.setRegDate(new Date());
		member.setEnabled(1);
		member.setPicture(picture);
		
		return member;
	}
}
