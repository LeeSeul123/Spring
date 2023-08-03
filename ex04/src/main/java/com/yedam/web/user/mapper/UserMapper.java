package com.yedam.web.user.mapper;

import com.yedam.web.user.service.MemberVO;

public interface UserMapper {
	//유저 정보 가져오기
	public MemberVO getMember(String username);
}
