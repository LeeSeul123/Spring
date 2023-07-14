package com.yedam.web.board.mapper;

import java.util.List;

import com.yedam.web.board.service.BoardVO;

public interface BoardMapper {
	//전체조회
	public List<BoardVO> selectBoardAllList();
	
	//단건조회
	public BoardVO selectBoard(BoardVO boardVO);
	
	//등록
	// 1) 게시글 번호는 자동생성
	// 2) 테이블을 참조해서 필수값과 옵션값(없을수도있는 거)을 구분해서 등록 -> Dynamic SQL
	public int insertBoard(BoardVO boardVO);
	
	//수정
	// 1) 수정대상 - 제목 || 내용 || 이미지 || 수정날짜
	// 2) updatedate : 수정날짜 (업데이트 쿼리문이 실행되면 항상 그 날짜가 등록되어야함). 수정날짜는 사용자가 지정할수도, 안할수도있음
	public int updateBoard(BoardVO boardVO);
	
	//삭제
	public int deleteBoard(int bno);
}
