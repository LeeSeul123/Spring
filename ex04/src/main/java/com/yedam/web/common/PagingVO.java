package com.yedam.web.common;

import lombok.Getter;

//값을 변경할 수 있으면 Setter도 사용함
@Getter
//생성자를 제외하고는 내부값을 변경하지 못함. 생성자를 통해서만 값을 넘기고 그리고 그걸 기반으로 필드값을 구성하기 때문에 Setter는 필요없음
//쿼리문에 사용할 데이터를 가지고 있기 때문에 일종의 VO취급을 하긴 함
public class PagingVO {
	private static final int defaultVal = 10;	//프로젝트를 시작 전에 코드 변경할 수 있지만, 동작할 때는 수정시키지 못하도록 할 때 많이 사용하는 방법
	
	//많은 필드를 가짐
	//총
	private int totalData;			// 현재 총 데이터 수
	
	//하단
	private int nowPage;			// 현재 페이지(외부에서 들어와야 하는 값)
	private int cntPage = 10;		// 한 View 안에서 보여줄 페이지 수 (시작페이지와 끝페이지가 영향받음). 이건 프로젝트 구상할 때 미리 정해둬야 하는 값임(외부에서 들어오지 않고 Paging VO 안에서 결정하는 값) => 1,2,3,4,5,6,7,8,9,10
	private int startPage;			// 현재 보여지는 페이지(1,2,3,4,5,6,7,8,9,10)의 시작 페이지
	private int endPage;			// 현재 보여지는 페이지의 끝 페이지
	
	//중간
	private int cntPerPage;			// 한 페이지 안에 보여줄 데이터 수 
	private int lastPage;			// 마지막 페이지(현재 총 데이터 수 기반으로 구함)
	private int start;				// 현재 페이지 안에 보여줄 첫번째 데이터
	private int end;				// 현재 페이지 안에 보여줄 마지막 데이터
	
	//생성자가 많음
	public PagingVO(int totalData, int nowPage, int cntPerPage) {
		//현재 5줄보기, 10줄보기, 15줄보기 변경가능한 페이지는 데이터를 받아와서 처리해야하기 때문에 생성자가 3개의 데이터 받을 수 있도록 만듦
		
		//1. 넘어온 데이터를 필드에 집어 넣는다
		this.totalData = totalData;
		this.nowPage = nowPage;
		this.cntPerPage = cntPerPage;
		calcLastPage();
		calcStartEndPage();
		calcStartEnd();
	}
	
	public PagingVO(int totalData, int nowPage) {
		//cntPerPage의 값을 내부에서 정할 경우
		this(totalData, nowPage, defaultVal);	//지정하지 않는 경우 디폴트로 10개씩 데이터를 보여주겠다
		
	}
	
	
	//PagingVO는 생성하는 순간 내부 필드에 모든 값이 들어가야 함 => 숨겨진 메서드를 이용해서 연산해서 채움
	private void calcLastPage() {
		this.lastPage = (int)Math.ceil((double)this.totalData / (double)this.cntPerPage); 	//연산은 실수로 처리하고 연산 결과만 정수로 처리할 수 있도록 만들어야 함
		
	}
	
	//시작과 끝 페이지 계산 필요 -> 필수는 아니고, 데이터가 계속 쌓이는 경우
	private void calcStartEndPage() {
		this.endPage = (int)Math.ceil((double)this.nowPage/(double)this.cntPage) * this.cntPage;			//마지막 페이지 구한 뒤 시작 페이지 구하는 게 편함
		
		//위에서 구한 endPage가 lastPage보다 크면 안되므로, 확인 해야함
		if(this.endPage > this.lastPage){
			this.endPage = this.lastPage;
		}
		
		//시작페이지 구하기
		this.startPage = (this.endPage - this.cntPage) + 1;
		//혹시라도 연산이 잘못일어나서 -가 나올까봐
		if(this.startPage < 1) {
			this.startPage = 1;
		}
	}
	
	//현제 페이지 안에 보여질 첫번째 데이터와 마지막 데이터(이건 필수) -> DB 쿼리 안에 사용할 start, end
	private void calcStartEnd() {
		//start 구한 후 end

		//이전 페이지가 보여준 마지막 데이터, nowPage는 무조건 1이상의 값을 가짐
		this.start = ((this.nowPage - 1) * this.cntPerPage) + 1;
		this.end = this.nowPage * this.cntPerPage;
		if(this.end > this.totalData) {
			this.end = this.totalData;
		}
	}
}
