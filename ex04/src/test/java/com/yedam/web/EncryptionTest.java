package com.yedam.web;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/*-context.xml")
public class EncryptionTest {
	
	@Autowired
	StandardPBEStringEncryptor encryptor;
	
	@Test
	public void encryptionTest() {
		String[] dataList = {
							 "+F60ZcUPuQATMd9u6+VzYQluAp44kT//+iNsc0W7fxi/w7DQweJB4bOvZhDN03GN"
							 , "y5Mnsr4xRPj0RI2HBQ0Bg79KA0r4WXr1nLLqzsqHfMkB6hUiFGrq75CFE+pcdZ152Scp9j/hRZw="
							 , "mfi/x0OdC+i/VJLnlxKvtw=="
							 , "LrQiFF8/03l5HL4as4ptIA=="
							};
		
		for(String data : dataList) {
			//빈이 가진 메소드 중 암호화 하는 메소드
			//String encData = encryptor.encrypt(data);
			//System.out.println(encData);
			//사용하고 나서 배포할 때는 해당 파일 삭제. 배열의 값이 비어있음(암호화 하기 전의 값을 배열에 담아서 암호화를 하는 것.그래서 원래 값 다 삭제하거나 따로 관리)
			
			
			//빈이 가진 메소드 중 암호화 된 걸 해석하는 메소드
			String encData = encryptor.decrypt(data);
			System.out.println(encData);
		}
	}
}
