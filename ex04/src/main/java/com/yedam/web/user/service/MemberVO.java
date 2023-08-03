package com.yedam.web.user.service;

import lombok.Data;

@Data
public class MemberVO {
	//회원정보를 가짐
	
	private String id;
	private String pwd;
	private String role;
	
	//시큐리티에서 사용하는 UserDetails 인터페이스와 연결할건지, 구현클래스 따로 만들고 내부에 들어가는 형태로 할지 애매함
	
	//UserDetails를 구현하게 되면 정해진 메소드가 따로 있음. security에서 사용되는 부분들이 있는데, 보안관련 정보들이 있어서 회원에 대한 정보 넘길 때 얘를 넘겨서 보안 유출 일어날 수도 있음
	//View <-> Server 통신할때 문제됨(jSon으로 주고받을 때). 아니면 jSon 어노테이션(잭슨 데이터바인드 어노테이션) 사용해서 내용 제외시킬 수 있음
	
	//내부에 회원정보 VO따로 만들고, Security동작시키는 내부클래스에 집어넣음
	
	//추가정보
	
}
