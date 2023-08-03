package com.yedam.web.board.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
//페이지 요청안하면 restController
public class UploadController {
	
	//스프링은 외부값(properties 포함) 가져올 때 어노테이션 사용(롬복말고 스프링프레임워크로 import)
	@Value("${file.upload.path}")	//$를 이용해서 키값을 가져옴. 즉 중괄호 안에는 키값을 넣어줘야 한다
	private String uploadPath; //value기준으로 값 가져온 후 해당 필드에 넣어줌

	// 페이지(업로드 받을 때) 불러옴(미디어 파일 넘기는 건 페이지 없이는 불가능)
	@GetMapping("upload")
	public void getUploadPage() {
		
	}
	
	// 처리 컨트롤러
	@PostMapping("/uploadAjax")
	@ResponseBody
	public List<String> uploadFile(@RequestPart MultipartFile[] uploadFiles) {
	    //MultipartFile[]는 multipart로 파일 보낼 때 사용. 배열 = 다수를 받을 수 있음. 하나만 받아야 한다면 배열 안써도 상관 없음
		//@RequestPart는 multipartresolver가 있어야 정상 작동함
		List<String> imageList = new ArrayList<>();
		//imageList = 내부에서 처리된 파일들을 저장
		
	    for(MultipartFile uploadFile : uploadFiles){
	    	//다중 값을 처리하기 위한 배열 처리 향상된for 사용
	    	if(uploadFile.getContentType().startsWith("image") == false){
	    		//이미지인지 아닌지 확인. 이미지든 뭐든 다받겠다 하면 이 코드는 빠지면 됨
	    		System.err.println("this file is not image type");
	    		return null;
	        }
	    	
	    	//경로 생성 시작
	    	//사용자가 파일 업로드 해달라고 하면서 보내온 정보를 끌고 들어오는 것
	        String originalName = uploadFile.getOriginalFilename();
	        //오리지널 fileName을 얻기 위해 중간 경로가 있다면 제외시켜 버리는 것
	        String fileName = originalName.substring(originalName.lastIndexOf("//")+1);
	        
	        //보내온 정보 중에서 정확하기 파일 이름이 뭔지
	        System.out.println("fileName : " + fileName);
	    
	        //날짜 폴더 생성 - 데이터를 관리할 때는 하나의 폴더에 다 때려넣을 순 없음.사용자가 입력한 데이터를 관리하기 위해서는 폴더 필요// 한 폴더 사용 시 문제점 : 사용자가 업로드 했을 때 파일이름 충돌
	        String folderPath = makeFolder();	//몇년도 몇월 파일 폴더 안에 데이터 집어 넣음. makeFolder라는 메소드는 오늘 날짜를 가져옴. 특정한 포맷을 기준으로해서 문자로 변환시킴(yyyy/MM/dd). 슬러시를 사용해서 폴더 구분하면 되지 않을까 생각하지만 자바는 폴더기준으로 슬러시를 인식하지 않음. 윈도우 같은 경우에는 슬러시로 인지함. 
	        //UUID
	        String uuid = UUID.randomUUID().toString();	//UUID = 중복되는 값 제거하기 위해서 시간을 이용해서 유니크한 값 끌고 오는 것. 전세계에서 유니크한 값은 초 기준 시간밖에 없음
	        //저장할 파일 이름 중간에 "_"를 이용하여 구분
	        
	        String uploadFileName = folderPath +File.separator + uuid + "_" + fileName;	//슬러시를 전체 경로 안에서 폴더를 인식시키는 기준(File.separator)으로 change 시킴. 그 후 file 클래스를 이용해서 실질적인 경로를 만듦
	        //해당 폴더 밑에 uuid를 기준으로 하는 어떤 파일 이름을 완성시킴
	        
	        String saveName = uploadPath + File.separator + uploadFileName;
	        //파일 경로를 기준으로 해서 실제 Name. saveName을 기반으로해서 자바가 인식할 수 있는 경로 만듦. 자바는 지금 그냥 문자로 인식하고 있음
	        
	        Path savePath = Paths.get(saveName);
	        //path = 자바가 인식할 수 있는 경로 타입.
	        //Paths.get() 메서드는 특정 경로의 파일 정보를 가져옵니다.(경로 정의하기)
	        
	        //경로 생성 끝
	        System.out.println("path : " + saveName);
	        
	        
	        try{
	        	uploadFile.transferTo(savePath);
	        	//transferTo를 사용할 때는 경로로 인식시키기 위해서 path타입으로 값을 가지고 진행해야 한다
	            //uploadFile에 파일을 업로드 하는 메서드 transferTo(file)
	        	//원래는 stream을 열고 직접 해당 경로에 write 해야 하는데, 그걸 전부 내부에서 처리하는 메소드. multipartFile[]을 쓰는 이유가 transferTo때문에 편해서임. 메소드 하나로 파일 업로드 시켜줌. 문제는 경로
	        } catch (IOException e) {
	             e.printStackTrace();	             
	        }
	        // try, catch 끝난 후 DB에 저장 등 후속처리
	        
	        //DB에 저장
	        //주의 : full name을 사용하면 안됨.(savaName X)
	        // - uploadFileName
	        //가공 필요
	        imageList.add(setImagePath(uploadFileName));	//file.separator는 자바가 인식하는 경로. 다시 운영체제가 인식할 수 있는 경로로 역으로 바꿔야함)
	     }
	    
	    return imageList;
	}
	
	//경로에 대한 메소드(파일이 업로드 되야하는데 그 공간이 있는지 check)
	private String makeFolder() {
		String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
		// LocalDate를 문자열로 포멧
		String folderPath = str.replace("/", File.separator);
		File uploadPathFoler = new File(uploadPath, folderPath);	//file클래스를 이용해서 경로만들 때 기준을 만드는 것. app.properties 내의 경로 포함하는 두개를 붙여서 파일클래스 정보 가져옴. 운영체제에서 폴더를 만든 게아님 -> 자바에는 거기로 넣고싶지만 운영체제에는 없을 수도 있음(예. 8월 1일 폴더가 없을 수도 있음 -> file클래스 자체가 해당 경로 기준으로 해서 directory 만들어줌
		// File newFile= new File(dir,"파일명");
		if (uploadPathFoler.exists() == false) {	//해당 폴더 존재하는 지 확인. 
			uploadPathFoler.mkdirs();	//없으면 경로 기준으로 디렉토리 만들어 달라는 메소드
			// 만약 uploadPathFolder가 존재하지않는다면 makeDirectory하라는 의미입니다.
			// mkdir(): 디렉토리에 상위 디렉토리가 존재하지 않을경우에는 생성이 불가능한 함수
			// mkdirs(): 디렉토리의 상위 디렉토리가 존재하지 않을 경우에는 상위 디렉토리까지 모두 생성하는 함수
		}
		return folderPath;
		//생성 완료되면 해당 경로를 반환
	}
	
	private String setImagePath(String uploadFileName) {
		return uploadFileName.replace(File.separator, "/");
				//이렇게 해야 src속성에서 값을 읽어들일 수 있음
	}
}
