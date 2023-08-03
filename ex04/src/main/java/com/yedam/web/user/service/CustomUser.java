package com.yedam.web.user.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUser implements UserDetails {
	private MemberVO member;
	//CustomUser은 그자체로 정보X. 조회한 결과를 가지고 있는 member를 내부값으로 가질것

	public CustomUser(MemberVO member) {
		this.member = member;
	}
	//덮어쓰는 wrapper형태로 만드는 것
	//override = 시큐리티에서 사용하는 것
	//get머시기 = 정보 리턴
	
	//override말고 별도의 메소드를 만듦
	public MemberVO getMemberInfo() {
		return this.member;
		//시큐리티안에서는 커스텀 정보로, 바깥에서는 memberVO에서 정보를 끄집어내면 정보 분리됨
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//컬렉션 하위 List. 얘는 디폴트가 배열임
		List<GrantedAuthority> auth = new ArrayList<>();
		
		//GrantedAuthority. 권한 체크할 때 원래는 리터럴로 하는 게 맞는데, 권한 처리하는 클래스가 따로 있음. 그래서문자로 권한 정보를 가져왔더라도 형태는 권한 처리하는 클래스로 만들어야함
		auth.add(new SimpleGrantedAuthority(member.getRole()));
		//정보를 집어넣는데 권한에 대해서 감쌈. 시큐리티 내에서 처리할 때 String 말고 Autority라는 클래스가 있음 부여된 권한을 처리하는 클래스. 그걸로 형태를 만들어서 집어넣어야 함
		//가지는 권한이 여러개라면 개별개별 끄집어내기위해 반복문을 돌려야 함
		return auth;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.member.getPwd();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.member.getId();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
