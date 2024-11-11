package kr.kro.runleaf.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import kr.kro.runleaf.domain.Location;

@RestController
@RequestMapping("/gps")
public class MainController {

	@PostMapping
	public String receiveLocation(@RequestBody Location location) {
		System.out.println("Received Latitude: " + location.getLatitude());
		System.out.println("Received Longitude: " + location.getLongitude());

		// 받은 위치 정보를 바탕으로 처리 로직을 추가
		// 예: 위치 기반 서비스, 데이터베이스 저장 등

		return "{\"status\": \"Location received successfully!\"}";
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