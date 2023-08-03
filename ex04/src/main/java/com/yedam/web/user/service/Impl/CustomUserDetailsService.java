package com.yedam.web.user.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.yedam.web.user.mapper.UserMapper;
import com.yedam.web.user.service.CustomUser;
import com.yedam.web.user.service.MemberVO;


public class CustomUserDetailsService implements UserDetailsService {
	
	//정보 처리할 때 mapper사용
	@Autowired
	UserMapper userMapper;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		MemberVO vo = userMapper.getMember(username);
		//로그인 시도한 사람의 정보를 가져옴
		
		//필요에 따라 있는지 없는지 체크
		
		//그리고 그 결과를 반환 ( null을 돌려주면 저쪽에서 알아서 처리함. null이 아니면 memberVO를 그대로 보내면 안됨. memberVO는 userDetails와는 상관없어서 반환이 안됨. CustomUser로 감싼 후 리턴해야함)
		return vo == null ? null : new CustomUser(vo);
	}
	
	//내장된 userDetails 사용하지말고 원하는 형태로 사용해달라고 알려야 함 -> security-context.xml에 등록

}
