package kr.kro.runleaf.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.SpringApplication.Running;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.kro.runleaf.domain.Board;
import kr.kro.runleaf.domain.BoardSearch;
import kr.kro.runleaf.domain.RunningDataImage;
import kr.kro.runleaf.domain.Location;
import kr.kro.runleaf.domain.RunningData;
import kr.kro.runleaf.service.RunningDataService;

@CrossOrigin
@RestController
@RequestMapping("/running")
public class RunningDataController {
	
	private final RunningDataService runningDataService;
	public RunningDataController (RunningDataService runningDataService) {
		this.runningDataService = runningDataService;
	}
	
	@GetMapping
	public ResponseEntity<List<RunningData>> getMethodName(BoardSearch boardSearch) {
		ResponseEntity<List<RunningData>> responseEntity;
		List<RunningData> list = runningDataService.getRunningDataList(boardSearch);
		responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
		return responseEntity;
	}
	
	
	@PostMapping
	public ResponseEntity<Integer> addBoard(
			@RequestPart(value = "board") RunningData runningData,
			@RequestPart(value = "file", required = false) List<MultipartFile> file) {
		int numberOfChange = runningDataService.addRunningData(runningData);
		ResponseEntity<Integer> responseEntity;
		try {
			if (numberOfChange == 1) {
				responseEntity = new ResponseEntity<>(numberOfChange, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>(numberOfChange, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

		// 저장할 디렉토리 경로 설정
		for (int index = 0; index < file.size(); index++) {
			String subDir = new SimpleDateFormat("/yyyy/MM/dd/HH/").format(new Date());
//			String uploadDir = "/Users/baehanjin/SSAFY/" + subDir;
			String uploadDir = "c:/SSAFY/uploads" + subDir;
			// 폴더생성
			File dir = new File(uploadDir);
			dir.mkdirs();
			// 원본 파일명 가져오기
			String originalFilename = file.get(index).getOriginalFilename();
			String systemName = UUID.randomUUID().toString() + "_" + originalFilename;
			// 저장할 파일 객체 생성
			File destFile = new File(dir, systemName);
			System.out.println(runningData);
			System.out.println(runningData.getRunningDataId());
			RunningDataImage runningDataImage = new RunningDataImage(
					runningData.getRunningDataId(),
					originalFilename,
					systemName,
					uploadDir
					);
			System.out.println(runningDataImage);
			int imageChange = runningDataService.addRunningDataImage(runningDataImage);
			System.out.println(imageChange);
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

//	@PostMapping("/savePosition")
//	@ResponseBody
//	public ResponseEntity<String> savePosition(@RequestBody Map<String, Double> coordinates) {
//		Double lat = coordinates.get("lat");
//		Double lng = coordinates.get("lng");
//		System.out.println(1);
//		// 받은 좌표 값을 활용하여 처리
//		System.out.println("Received Latitude: " + lat + ", Longitude: " + lng);
//
//		// 필요한 처리 후 응답 반환
//		return ResponseEntity.ok("Position received");
//	}