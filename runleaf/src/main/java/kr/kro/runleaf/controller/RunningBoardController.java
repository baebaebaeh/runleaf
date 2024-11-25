package kr.kro.runleaf.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.kro.runleaf.domain.BoardSearch;
import kr.kro.runleaf.domain.Location;
import kr.kro.runleaf.domain.RunningBoard;
import kr.kro.runleaf.domain.RunningBoardImage;
import kr.kro.runleaf.dto.CustomUserDetails;
import kr.kro.runleaf.dto.RunningDataRequest;
import kr.kro.runleaf.jwt.JWTUtil;
import kr.kro.runleaf.service.RunningDataService;

@CrossOrigin
@RestController
@RequestMapping("/running")
public class RunningBoardController {
	private final RunningDataService runningBoardService;
	private final JWTUtil jwtUtil;
	private final double EARTH_RADIUS = 6371000.0;

	public RunningBoardController(RunningDataService runningDataService, JWTUtil jwtUtil) {
		this.runningBoardService = runningDataService;
		this.jwtUtil = jwtUtil;
	}

	@GetMapping("/list")
	public ResponseEntity<List<RunningBoard>> getRunningBoardList(
			@ModelAttribute BoardSearch boardSearch) {
//		String username = userDetails.getUsername();
		ResponseEntity<List<RunningBoard>> responseEntity;
//		boardSearch.setUsername(username);
		List<RunningBoard> list = runningBoardService.getRunningBoardList(boardSearch);
		responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
		return responseEntity;
	}
	@GetMapping("/myrun")
	public ResponseEntity<List<RunningBoard>> getRunningMyBoardList(
			@ModelAttribute BoardSearch boardSearch,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		boardSearch.setUsername(userDetails.getUsername());
		ResponseEntity<List<RunningBoard>> responseEntity;
		List<RunningBoard> list = runningBoardService.getRunningBoardList(boardSearch);
		responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/board/{id}")
	public ResponseEntity<RunningBoard> getRunningBoardDetail(@PathVariable("id") int runningBoardId) {
		ResponseEntity<RunningBoard> responseEntity;
		RunningBoard BoardDetail = runningBoardService.getRunningBoardById(runningBoardId);
		System.out.println(BoardDetail.getTitle());
		responseEntity = new ResponseEntity<>(BoardDetail, HttpStatus.OK);
		return responseEntity;
	}

	@GetMapping("/image/{id}")
	public ResponseEntity<List<RunningBoardImage>> getRunningBoardDetailImage(@PathVariable("id") int runningBoardId) {
		ResponseEntity<List<RunningBoardImage>> responseEntity;
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
	public ResponseEntity<Integer> addBoard(@RequestPart(value = "board") RunningBoard runningBoard,
			@RequestPart(value = "location") List<Location> locations,
			@RequestPart(value = "file", required = false) List<MultipartFile> file,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		ResponseEntity<Integer> responseEntity;

		String username = userDetails.getUsername();
		
		double totalDist = 0;
		for (int i = 1; i < locations.size(); i++) {
			double lat1Rad = Math.toRadians(locations.get(i - 1).getLatitude());
			double lon1Rad = Math.toRadians(locations.get(i - 1).getLongitude());
			double lat2Rad = Math.toRadians(locations.get(i).getLatitude());
			double lon2Rad = Math.toRadians(locations.get(i).getLongitude());
			
			// 위도와 경도의 차이 계산
			double deltaLat = lat2Rad - lat1Rad;
			double deltaLon = lon2Rad - lon1Rad;
			
			// 하버사인 공식 적용
			double a = Math.pow(Math.sin(deltaLat / 2), 2)
					+ Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(deltaLon / 2), 2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			
			// 거리 계산
			totalDist += EARTH_RADIUS * c;
		}
		
		Duration totalRunningTs = Duration.between(runningBoard.getStartRunningTs(), runningBoard.getEndRunningTs());
        double totalRunningSecond = totalRunningTs.toSeconds();
        System.out.println(totalRunningSecond);
        
        
		runningBoard.setTotalRunningSecond(totalRunningSecond);
		runningBoard.setTotalDist(totalDist);
		RunningDataRequest runningDataRequest = new RunningDataRequest(username, totalDist, totalRunningSecond);
		runningBoardService.updateMember(runningDataRequest);
		
		
		/**
		 * RunningBoard 데이터베이스에 등록하는 부분
		 */
		runningBoard.setWriter(username);
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
			e.printStackTrace();
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			System.out.println("runningBoard저장할때 문제가 생겼습니다.");
		}

		

		
		
		
		for (int index = 0; locations != null && index < locations.size(); index++) {
			Location location = locations.get(index);
			location.setRunningBoardId(runningBoard.getRunningBoardId());
			int count = runningBoardService.addLocation(location);
		}

		
		
		for (int index = 0; file != null && index < file.size(); index++) {
			String subDir = "uploads" + new SimpleDateFormat("/yyyy/MM/dd/HH/").format(new Date());
//			String uploadDir = "/Users/baehanjin/SSAFY/" + subDir;
			String uploadDir = "C:/SSAFY/" + subDir;
			// 폴더생성
			File dir = new File(uploadDir);
			dir.mkdirs();
			// 원본 파일명 가져오기
			String originalFilename = file.get(index).getOriginalFilename();
			String systemName = UUID.randomUUID().toString() + "_" + originalFilename;
			// 저장할 파일 객체 생성
			File destFile = new File(dir, systemName);
			RunningBoardImage runningBoardImage = new RunningBoardImage(runningBoard.getRunningBoardId(),
					originalFilename, systemName, subDir);
			int imageChange = runningBoardService.addRunningBoardImage(runningBoardImage);
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
			@RequestPart(value = "file", required = false) List<MultipartFile> file,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		runningBoard.setWriter(userDetails.getUsername());
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



//		이미지 관련 로직
//		(file이 잘 들어왔거나 file 개수가 0이 아닐경우)
		System.out.println(file);
		if (file != null && file.size() != 0) {
			
			//진짜 이미지 지우는 로직
			List<RunningBoardImage> imageList = runningBoardService
					.getRunningBoardImageList(runningBoard.getRunningBoardId());

			int numberOfImages = imageList.size();

			for (int index = 0; index < numberOfImages; index++) {
				String path = "c:/SSAFY/" + imageList.get(index).getPath() + imageList.get(index).getSystemName();
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
			
			// 데이터베이스 이미지 지우는 로직
			try {
				int count = runningBoardService.deleteRunningBoardImage(runningBoard.getRunningBoardId());
			} catch (Exception e) {
				System.out.println("이미지 삭제가 완료되지 않았습니다");
			}

			// 데이터베이스 이미지 넣는 로직
			for (int index = 0; index < file.size(); index++) {
				String subDir = "uploads" + new SimpleDateFormat("/yyyy/MM/dd/HH/").format(new Date());
//			String uploadDir = "/Users/baehanjin/SSAFY/" + subDir;
				String uploadDir = "c:/SSAFY/" + subDir;
				// 폴더생성
				File dir = new File(uploadDir);
				dir.mkdirs();
				// 원본 파일명 가져오기
				String originalFilename = file.get(index).getOriginalFilename();
				String systemName = UUID.randomUUID().toString() + "_" + originalFilename;
				// 저장할 파일 객체 생성
				File destFile = new File(dir, systemName);
				RunningBoardImage runningBoardImage = new RunningBoardImage(runningBoard.getRunningBoardId(),
						originalFilename, systemName, subDir);
				int imageChange = runningBoardService.addRunningBoardImage(runningBoardImage);
				if (index == 0) {
					runningBoard.setMainImagePath(subDir + systemName);
				}
				try {
					// 파일 저장
					file.get(index).transferTo(destFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			try {
				int change = runningBoardService.updateRunningBoardMainPath(runningBoard);
				if (change == 1) {
					responseEntity = new ResponseEntity<>(change, HttpStatus.OK);
				} else {
					responseEntity = new ResponseEntity<>(change, HttpStatus.BAD_REQUEST);
				}
			} catch (Exception e) {
				responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return responseEntity;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Integer> deleteRunningBoard(
			@PathVariable("id") int runningBoardId,
			@RequestBody List<RunningBoardImage> runningBoardImage,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		System.out.println(runningBoardId);
		System.out.println(runningBoardImage);
		// 진짜 이미지 제거
		for (int i = 0; i < runningBoardImage.size(); i++) {
			String realPath = "C:/SSAFY/" + runningBoardImage.get(i).getPath() + runningBoardImage.get(i).getSystemName();
			System.out.println("진짜패스" + realPath);
			File file = new File(realPath);
			try {
				if (file.exists()) {
					file.delete();				
				}				
			} catch (Exception e) {
				System.out.println("파일을 지우지 못했습니다");
			}
		}
		
		// 아직 글쓴이와 삭제하려는 유저가 같은지 확인하는 작업을 하지 않음
		ResponseEntity<Integer> responseEntity;
		int count = runningBoardService.deleteRunningBoard(runningBoardId);
		responseEntity = new ResponseEntity<>(count, HttpStatus.OK);
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