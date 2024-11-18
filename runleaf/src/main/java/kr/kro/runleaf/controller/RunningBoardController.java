package kr.kro.runleaf.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.boot.SpringApplication.Running;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import kr.kro.runleaf.domain.RunningBoardImage;
import kr.kro.runleaf.domain.Location;
import kr.kro.runleaf.domain.RunningBoard;
import kr.kro.runleaf.service.RunningDataService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@CrossOrigin
@RestController
@RequestMapping("/running")
public class RunningBoardController {
	private final RunningDataService runningBoardService;
	public RunningBoardController(RunningDataService runningDataService) {
		this.runningBoardService = runningDataService;
	}

	
	@GetMapping
	public ResponseEntity<List<RunningBoard>> getRunningBoardList(@ModelAttribute BoardSearch boardSearch) {
		int page = boardSearch.getPage();
		System.out.println(page);
		ResponseEntity<List<RunningBoard>> responseEntity;
		List<RunningBoard> list = runningBoardService.getRunningBoardList(boardSearch);
		responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/board/{id}")
	public ResponseEntity<RunningBoard> getRunningBoardDetail(@PathVariable("id") int runningBoardId) {
		ResponseEntity<RunningBoard> responseEntity;
		RunningBoard BoardDetail = runningBoardService.getRunningBoardById(runningBoardId);
		responseEntity = new ResponseEntity<>(BoardDetail, HttpStatus.OK);
		return responseEntity;
	}
	
	@GetMapping("/image/{id}")
	public ResponseEntity<List<RunningBoardImage>> getRunningBoardDetailImage(@PathVariable("id") int runningBoardId) {
		ResponseEntity<List<RunningBoardImage>> responseEntity;
		System.out.println("이미지로들어옴");
		List<RunningBoardImage> BoardDetailImage = runningBoardService.getRunningBoardImageList(runningBoardId);
		responseEntity = new ResponseEntity<>(BoardDetailImage, HttpStatus.OK);
		return responseEntity;
	}
	@GetMapping("/coodinate/{id}")
	public ResponseEntity<List<Location>> getRunningBoardDetailCoodinate(@PathVariable("id") int runningBoardId) {
		ResponseEntity<List<Location>> responseEntity;
		List<Location> location = runningBoardService.getRunningBoardLocationList(runningBoardId);
		responseEntity = new ResponseEntity<>(location, HttpStatus.OK);
		return responseEntity;
	}
	
	@PostMapping
	public ResponseEntity<Integer> addBoard(
			@RequestPart(value = "board") RunningBoard runningBoard,
			@RequestPart(value = "location") List<Location> locations,
			@RequestPart(value = "file", required = false) List<MultipartFile> file) {
		ResponseEntity<Integer> responseEntity;
		/**
		 * RunningBoard 데이터베이스에 등록하는 부분
		 */
		try {
			if (file == null) {
				runningBoard.setMainImagePath("uploads/defaultimg/abcd.PNG");
			}
		} catch (Exception e) {
			System.out.println("기본 폴더와 기본 이미지를 미리 만들어 놔야 합니다.");
		}
		try {
			int numberOfChange = runningBoardService.addRunningBoard(runningBoard);
			if (numberOfChange == 1) {
				responseEntity = new ResponseEntity<>(numberOfChange, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>(numberOfChange, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			System.out.println("runningBoard저장할때 문제가 생겼습니다.");
		}

		/**
		 * RunningBoardImage 데이터베이스에 등록하는 부분
		 */
		for (int index = 0; locations != null && index < locations.size(); index++) {
			Location location = locations.get(index);
			location.setRunningBoardId(runningBoard.getRunningBoardId());
			int count = runningBoardService.addLocation(location);
		}
		
		for (int index = 0; file != null && index < file.size(); index++) {
			
			String subDir ="uploads" + new SimpleDateFormat("/yyyy/MM/dd/HH/").format(new Date());
			String uploadDir = "/Users/baehanjin/SSAFY/" + subDir;
//			String uploadDir = "C:/SSAFY/uploads" + subDir;
			// 폴더생성
			File dir = new File(uploadDir);
			dir.mkdirs();
			// 원본 파일명 가져오기
			String originalFilename = file.get(index).getOriginalFilename();
			String systemName = UUID.randomUUID().toString() + "_" + originalFilename;
			// 저장할 파일 객체 생성
			File destFile = new File(dir, systemName);
			System.out.println(runningBoard);
			RunningBoardImage runningBoardImage = new RunningBoardImage(runningBoard.getRunningBoardId(),
					originalFilename, systemName, subDir);
			int imageChange = runningBoardService.addRunningBoardImage(runningBoardImage);
			System.out.println(imageChange);
			if (index == 0) {
				runningBoard.setMainImagePath(subDir + systemName);
			}
			try {
				// 파일 저장
				file.get(index).transferTo(destFile);
			} catch (Exception e) {
				System.out.println("파일저장할때 문제가 생겼습니다");
			}
		}

		/**
		 * RunningBoard의 mainImagePath 데이터베이스에 업데이트 하는 부분
		 */
		try {
			int numberOfChange = runningBoardService.updateRunningBoardMainPath(runningBoard);
			if (numberOfChange == 1) {
				responseEntity = new ResponseEntity<>(numberOfChange, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>(numberOfChange, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@PutMapping("/{id}")
	public ResponseEntity<Integer> putMethodName(@RequestPart(value = "board") RunningBoard runningBoard,
			@RequestPart(value = "file", required = false) List<MultipartFile> file) {
		int numberOfChange = runningBoardService.modifyRunningBoard(runningBoard);
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

		List<RunningBoardImage> imageList = runningBoardService
				.getRunningBoardImageList(runningBoard.getRunningBoardId());
		int numberOfImages = imageList.size();
		for (int index = 0; index < numberOfImages; index++) {
			String path = imageList.get(index).getPath();
			File deleteFile = new File(path);
			try {
				if (deleteFile.exists()) {
					deleteFile.delete();
				} else {
					System.out.println("File does not exist.");
				}
			} catch (SecurityException e) {
				System.out.println("File deletion failed due to security exception: " + e.getMessage());
			}
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
			System.out.println(runningBoard);
			System.out.println(runningBoard.getRunningBoardId());
			RunningBoardImage runningBoardImage = new RunningBoardImage(runningBoard.getRunningBoardId(),
					originalFilename, systemName, uploadDir);
			System.out.println(runningBoardImage);
			int imageChange = runningBoardService.updateRunningBoardImage(runningBoardImage);
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