package com.yedam.app.tx.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yedam.app.tx.mapper.AaaMapper;
import com.yedam.app.tx.service.AaaService;


@Service
//이게 있어야 동작함
public class AaaServiceImpl implements AaaService {
	
	@Autowired
	AaaMapper aaaMapper;
	
	
	@Transactional
	//해당 메소드 내의 sql문이 같은 트랜잭션으로 묶이게 됨
	@Override
	public void insert() {
		//트랜잭셔널 어노테이션 안쓰고 insert라는 한 메소드 안에서 sql문 두개를 돌림 -> 하나의 기능
		aaaMapper.insert("101");

		aaaMapper.insert("987");
	}

}
