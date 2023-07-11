package com.yedam.app.tx.mapper;

import org.apache.ibatis.annotations.Insert;

public interface AaaMapper {
	//xml을 사용하지 않더라도 mapper만들 수 있음 -> mybatis가 어노테이션 지원해서
	//어노테이션으로 매퍼 만들기
	@Insert("INSERT INTO aaa VALUES ( #{value} )")
	public void insert(String value);
	
	
}
