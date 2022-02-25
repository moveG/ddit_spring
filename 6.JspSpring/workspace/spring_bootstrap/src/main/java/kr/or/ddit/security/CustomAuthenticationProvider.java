package kr.or.ddit.security;

import java.sql.SQLException;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String login_id = (String) auth.getPrincipal();		//로그인을 시도한 ID를 가져옮
		String login_pwd = (String) auth.getCredentials();	//로그인을 시도한 PWD를 가져옮
		
		MemberVO member = null;
		try {
			member = memberService.getMember(login_id);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BadCredentialsException("서버 장애로 서비스가 불가능합니다.");
		}
		
		if(member != null) {
			if(login_pwd.equals(member.getPwd())) {	//아이디, 패스워드가 일치
				UserDetails authUser = new User(member);
				boolean invalidCheck = authUser.isAccountNonExpired()
									&& authUser.isAccountNonLocked()
									&& authUser.isCredentialsNonExpired()
									&& authUser.isEnabled();
				if(invalidCheck) {
					//스프링 시큐리티 내부 클래스로 인증 토큰을 생성한다.

					//authentication.getPrincipal() : id
					//authentication.getCredentials() : pwd
					UsernamePasswordAuthenticationToken result 
						= new UsernamePasswordAuthenticationToken(authUser.getUsername()
																, authUser.getPassword()
																, authUser.getAuthorities());
					
					//전달할 내용을 설정한 후
					result.setDetails(authUser);
					
					//리턴함, successHandler로 전송됨
					return result;
				}
				
				throw new BadCredentialsException("상태변경으로 로그인이 불가능합니다.");
			}else {	//패스워드 불일치
				throw new BadCredentialsException("패스워드가 일치하지 않습니다.");
			}
		}else {		//존재하지 않는 아이디
			throw new BadCredentialsException("존재하지 않는 아이디입니다.");
		}
	}
	
	@Override
	public boolean supports(Class<?> auth) {	//authentication의 자손객체가 무엇인지 모르기 때문에 wildCard 사용
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}
}
