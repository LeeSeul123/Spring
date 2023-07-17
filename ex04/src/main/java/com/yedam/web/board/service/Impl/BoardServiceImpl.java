package com.yedam.web.board.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.web.board.mapper.BoardMapper;
import com.yedam.web.board.service.BoardService;
import com.yedam.web.board.service.BoardVO;


@Service
//Impl은 무조건 @Service
public class BoardServiceImpl implements BoardService {
	//클래스를 상속할 때는 내가 선택하는 게 큼. 부모꺼를 그대로 쓰든가 오버라이딩 해서 써도됨.
	//반면에 인터페이스는 내부 메소드가 다 추상메소드. 상속도 다형성 가지고 인터페이스도 다형성 가지는데, 인터페이스를 쓰는 이유는 선택사항을 없애고 강제하는 것.
	//클래스 상속과 인터페이스의 가장 큰 차이 : 강제성(인터페이스는 틀을 벗어날 수 없음)
	
	@Autowired
	BoardMapper boardMapper;
	//Service의 기능이 Mapper를 요구할 땐 mapper선언함. Mapper를 가져올 땐 모든 mapper를 가져올 수 있어서 꼭 명칭을 붙여줘야함(BoardMapper)
	
	@Override
	public List<BoardVO> getBoardList() {
		
		return boardMapper.selectBoardAllList();
	}

	@Override
	public BoardVO getBoardInfo(BoardVO boardVO) {
		// TODO Auto-generated method stub
		return boardMapper.selectBoard(boardVO);
	}

	@Override
	public int insertBoardInfo(BoardVO boardVO) {
		int result = boardMapper.insertBoard(boardVO);
		//selectKey로 Bno를 가져옴
		if(result == 1) {
			return boardVO.getBno();
		} else {
			return -1;
		}
		
	}

	@Override
	public int updateBoardInfo(BoardVO boardVO) {
		int result = boardMapper.updateBoard(boardVO);
		//원래 매개변수의 필드로 Bno를 가져옴
		if(result > 0) {
			return boardVO.getBno();
		} else {
			return -1;
		}
	}

	@Override
	public int deleteBoardInfo(int boardNo) {
		int result = boardMapper.deleteBoard(boardNo);
		
		if(result > 0) {
			return boardNo;
		} else {
			return -1;
		}
	}

}
