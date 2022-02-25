package kr.or.ddit.command;

import org.springframework.web.multipart.MultipartFile;

import com.jsp.dto.MemberVO;

public class MemberModifyCommand {
	private String id;				//아이디
	private String pwd;				//패스워드
	private String name;			//이름
	private String phone;			//전화
	private String email;			//이메일
	private String address;			//주소
	private String authority;		//권한
	private String enabled;			//상태
	private MultipartFile picture;	//사진파일
	private String oldPicture;		//과거(변경 전) 사진파일명
	private String uploadPicture;	//현재(변경 후) 사진파일명
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	public MultipartFile getPicture() {
		return picture;
	}

	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}
	public String getOldPicture() {
		return oldPicture;
	}
	public void setOldPicture(String oldPicture) {
		this.oldPicture = oldPicture;
	}
	public String getUploadPicture() {
		return uploadPicture;
	}
	public void setUploadPicture(String uploadPicture) {
		this.uploadPicture = uploadPicture;
	}

	public MemberVO toParseMember() {
		MemberVO memberVO = new MemberVO();
		
		memberVO.setId(this.id);
		memberVO.setPwd(this.pwd);
		memberVO.setName(this.name);
		memberVO.setPhone(this.phone.replace("-", ""));
		memberVO.setEmail(this.email);
		memberVO.setAddress(this.address);
		memberVO.setAuthority(this.authority);
		memberVO.setEnabled(Integer.parseInt(this.enabled));
		memberVO.setPicture(picture.getOriginalFilename());
		
		return memberVO;
	}
}
