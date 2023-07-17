package com.yedam.web.board.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yedam.web.board.service.BoardService;
import com.yedam.web.board.service.BoardVO;

@Controller
public class BoardController {
	//forward : 사용자가 모르게 옮겨다니는 거. 보이는 화면과 URL이 미스매치 날 수도 있음(경로는 search인데 페이지의 내용은 login이 될 수 있음)
	//redirect : 사용자가 앉아있는 게아니라 사용자한테 계속 시킴(사용자한테 보여주려고. 니가 요청한건 등록이지만 보는건 목록에서 봐야해) 나는 검색했는데 로그인 창이 뜸(결과가 안나오고 생뚱맞은 기능이나옴). 사용자에게 보여주고자하는 페이지의 내용이 경로와 틀어지게 될 때는 redirect를 사용해야함
	
	//forward : 현재 default로 하고 있는 작업들. controller로 요청이 들어가고, 반환하는 건 페이지를 반환(페이지도 굳이 따지면 하나의 경로. controller와 페이지는 같은 개념이 아님)
	// 사용자가 컨트롤러에게 요구했을 때, 데이터가 필요한거랑 뿌려줄 페이지가 필요한 건 각각의 요청임. 데이터 가져오는건 DB, 페이지 생성하는 건 따로 있음.
	//forward : 하나의 경로로 요청했지만 내부에서 동작하는 건 두가지(empList의 경우 데이터와 페이지 둘 다 필요)
	//내부에서 필요하면 페이지를 돌려줌. forward는 사용자가 몇단계를 거쳐서 클라이언트에게 오는지 모름. 계속 request, response 유지하면서 controller를 거침. 사용자는 한번 요청했다고 생각하지만 서버쪽에서는 여러 컨트롤러를 거치고 거쳐서 반환됨
	
	//redirect : 재요청이 들어감. 클라이언트에서 요청한 것을 서버에서 빽시킴. 분명히 나는 insert로 했는데, 나는 요청하지 않았는데 List로 브라우저가 바뀜. redirect는 강제성을 가짐. redirect는 클라이언트가 몇번을 거치는지 앎. 내입장에서 완료가 되진않았지만 클라이언트한테 response, request가 돌아왔음 -> 그럼 깨짐
	//요청하고 응답주면 response와 request 깨짐. 일회성임
	//redirect : 계속 돌아와서 request, response 유지가 안됨 -> get방식만 가능하다(재요청할 때 데이터가 같이 들어가기 때문에)
	//redirectAttribute -> 계속 깨지는 것 때문에 스프링이 지원. flash : response가 깨지기 전에 session(임시저장소 역할)에 일시적으로 담았다가 새로 생기는 request에 옮겨담아줌. 주의)사용자가 새로고침했을 때 생기는 request에는 옮겨가지 못함. 이미 한번 주면 session에는 값 사라짐
	
	//F5는 redirect가 아니라 그냥 새로고침임
	
	
	@Autowired
	BoardService boardService;
	//기능은 Service가 수행
	
	//전체조회 : URI - boardList, RETURN - board/boardList
	//								    : 모든 데이터의 게시글 번호, 제목, 작성자, 작성일(2023년07월17일)만 출력 & 단건 조회로 넘어갈 수 있도록
	@GetMapping("boardList")	//조회는 GetMapping
	public String BoardList(Model model) {	//페이지를 요구하면 return String, 보내는 거 담을 땐 Model, 받아야되는건 매개변수
		model.addAttribute("boardList", boardService.getBoardList());	//데이터를 보낼 땐 이름이 필요
		
		return "board/boardList";	// 슬러시가 있다는 건 기존의 views밑에 다른 폴더를 만들겠다
	}
	
	//단건조회 : URI - boardInfo, RETURN - board/boardInfo
	//									: 번호, 제목, 작성자, 내용, 첨부이미지, 작성일(2023/07/17)
	//									: 첨부이미지 - webapp/resources/ 안에 파일있다고 가정(resources쪽은 현재 매핑되어있기 때문에 경로에 webapp/resources/이미지.jpg하면 나옴.) 첨부이미지에 그 파일명을 주면됨
	//단건조회 - 커맨드 객체
	@GetMapping("boardInfo")
	public String BoardInfo(BoardVO boardVO, Model model) { 	//데이터를 받을 때 RequestParam을 써도 됨. 단, service에 넘길 때는 객체로 만들어서 넘겨야함
		BoardVO findVO = boardService.getBoardInfo(boardVO);
		model.addAttribute("boardInfo", findVO);
		
		return "board/boardInfo";
	}
	
	//단건조회 - @RequestParam
	/* public String BoardInfo(@RequestParam int bno, Model model) {
		//컨트롤러와 서비스는 관계없음. 서비스가 원하는 형태로 변환만 해주면 됨
		
		//RequestParam : 필수요건으로 들어감. 커맨드 객체 : 값이 없어도 실행이 됨 ----> RequestParam의 장점
		BoardVO boardVO = new BoardVO();
		boardVO.setBno(bno);
		
		BoardVO findVO = boardService.getBoardInfo(boardVO);
		model.addAttribute("boardInfo", findVO);
		
		return "board/boardInfo";
	} */
	
	
	
	//등록 - 페이지 : URI - boardInsert, RETURN - board/boardInsert
	//받을 거도 없고 넘겨줄 거도 없어서 매개변수 빔
	@GetMapping("boardInsert")
	public String boardInsertForm() {
		
		return "board/boardInsert";
	}
	
	//등록 - 처리 : URI - boardInsert, RETURN - 전체조회 다시 호출
	@PostMapping("boardInsert")		//get방식으로 데이터를 받아오면 보안 취약
	public String boardInsertProcess(BoardVO boardVO) {	//넘어오는 대상이 하나의 게시글에 대한 정보라서 커맨드 객체를 사용
		boardService.insertBoardInfo(boardVO);

		return "redirect:boardList";
		//forward와 redirect의 차이점은 처리 한 후 재요청을 하느냐 차이
	}
	
	//수정 - 페이지 : URI - boardUpdate, RETURN - board/boardUpdate
	@GetMapping("boardUpdate")
	public String boardUpdateForm(BoardVO boardVO, Model model) {	//수정은 기존 데이터를 보여줘야 해서 어떤 걸 수정원하는지 데이터를 받아오고, 기존 데이터를 찾아서 화면에 뿌려줘야 함
		BoardVO findVO = boardService.getBoardInfo(boardVO);
		model.addAttribute("boardInfo", findVO);
		return "board/boardUpdate";
	}
	
	
	//수정 - 처리 : URI - boardUpdate, RETURN - 수정대상, 성공여부 반환
	@PostMapping("boardUpdate")
	@ResponseBody		//반환이 페이지가 아님
	public Map<String, Object> boardUpdateProcess(BoardVO boardVO){	//매개변수로 반드시 json이 오지는 않음. String은 2개의 정보를 한꺼번에 담을 때 사용 안함
		boolean result = false;
		
		int boardNo = boardService.updateBoardInfo(boardVO);
		if(boardNo > -1) {
			result = true;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("result", result);
		map.put("boardInfo", boardVO);
		
		return map;
	}
	
	//삭제 - 처리 : URI - boardDelete, RETURN - 전체조회 다시 호출
	@GetMapping("boardDelete")
	public String boardDelete(@RequestParam(name = "bno", defaultValue ="0") int boardNO) {	//int 타입은 defaultValue를 지정해주는 게 좋음. int는 공백처리를 못해서
		boardService.deleteBoardInfo(boardNO);
		
	
		return "redirect:boardList";	//redirect는 뿌리는 것 까지 연결해줌
		//return "board/boardList" -> 빈페이지를 요청하는 거
	}
	
	//ajax를 쓰면 'get', 'post'만 알려주면 ajax가 알아서 get일 때 header에 데이터를 넣고, post면 body에 데이터를 넣어줌(post도 queryString이 가능하다)
}
