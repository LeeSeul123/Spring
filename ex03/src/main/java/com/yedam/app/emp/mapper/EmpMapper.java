package com.yedam.app.emp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.yedam.app.emp.service.EmpVO;

public interface EmpMapper {
	//전체조회
	public List<EmpVO> selectEmpAllList();
	
	//단건조회
	public EmpVO selectEmpInfo(EmpVO empVO);
	
	//등록(DML은 무조건 int로 정해짐)
	public int insertEmpInfo(EmpVO empVO);
	
	
	//수정 1.param 2.empVO
	//수정 - 급여를 정해진 비율로 인상
	//비율 -> 기존처럼 VO클래스 사용X
	//매개변수에 값이 2개 이상 -> 값에 대해서 쿼리문에서 제대로 적용X(mybatis에서 2개이상 매개변수 쓸때 @Param쓰는 걸 권장함)
	//@Param() = mapper에서 사용. xml에서 사용할 이름을 정해달라는 뜻. 인터페이스 매퍼에서만 사용. 둘중에 어느 위치에 들어갈 지 명확하게 하기 위해서 @Param사용.
	//@Param뒤에 객체가 들어갈 경우 이름.필드명으로 접근
	public int updateEmpSal(@Param("emp") EmpVO empVO,@Param("raise") int raise);
	
	
	//값을 2개 이상 받아올 때 VO가 아니라 @Param을 쓸 수도 있음
	
	//수정 - 사원정보를 수정
	public int updateEmpInfo(EmpVO empVO);
	
	//삭제
	//값이 하나면 @param이 붙을 필요가없음 = 이름에 대해 신경 쓸 필요도 없음
	public int deleteEmpInfo(int employeeId);
}
