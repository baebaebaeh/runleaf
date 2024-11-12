package kr.kro.runleaf.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.kro.runleaf.service.BoardService;

@CrossOrigin
@RestController
@RequestMapping("/api/boardImg")
public class BoardImgController {
	private final BoardService boardService;

	public BoardImgController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@PostMapping
	public ResponseEntity<Object> addBoard(@RequestParam List<MultipartFile> file) {
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		// 저장할 디렉토리 경로 설정
		for (int index = 0; index < file.size(); index++) {

		String subDir = new SimpleDateFormat("/yyyy/MM/dd/HH/").format(new Date());
		System.out.println(subDir);
//		String uploadDir = "/Users/baehanjin/SSAFY/";
		String uploadDir = "c:/SSAFY/uploads" + subDir;
		// 폴더생성
		File dir = new File(uploadDir);
		dir.mkdirs();
		// 원본 파일명 가져오기
		String originalFilename = file.get(index).getOriginalFilename();
		String systemName = UUID.randomUUID().toString() + "_" + originalFilename;
		// 저장할 파일 객체 생성
		File destFile = new File(dir, systemName);
		try {
			// 파일 저장
			file.get(index).transferTo(destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		return responseEntity;
	}
}
