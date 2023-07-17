package com.yedam.web.board.service;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class BoardVO {
	//DateTimeFormat은 입력에 관한 것만(setter가 값을 입력받을 때 정해진 format으로만 입력받겠다고 덮어쓰는거). 내부에 존재하는 건 따로 있음 -> 그래서 출력과는 전혀 상관없음. 출력할 때 주는 포맷이 아님. 입력방식만 설정함
	//DateTimeFormat을 썼을 때 jsp페이지에서 input에 2023/07/17 형식으로 입력하면 오류남. dateTime은 커맨드 객체를 만드는 데에 관여.
	//입력할 때 날짜를 어떤 형식으로 입력하겠다 정하는 거.
	
    private int bno;			//게시글 번호 - primary key
	private String title;		//게시글 제목 - NOT NULL
	private String contents;	//게시글 내용
	private String writer;		//게시글 작성자 - NOT NULL
	@DateTimeFormat(pattern = "yyyy-MM-dd")	//regdate를 이런 형식으로 입력받겠다(값 입력할 때 setter의 문자열 포맷 정함. 이 방식으로만 받을 수 있음)
	private Date regdate;		//게시글 등록일자 - NOT NULL
	// default : yyyy/MM/dd -> input[type=date] : yyyy-MM-dd         값을 입력하는 포맷 default : yyyy/MM/dd(이 포맷을 유지해준다면 DateTime포맷 사용할 필요X. input의 Date타입 사용할 때는 -(하이픈)이기때문에 사용해야함.
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedate;	//게시글 수정날짜
	private String image;		//게시글 첨부파일
}
