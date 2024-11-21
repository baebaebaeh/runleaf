package kr.kro.runleaf.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kro.runleaf.domain.Comment;
import kr.kro.runleaf.jwt.JWTUtil;
import kr.kro.runleaf.service.CommentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin
@RestController
@RequestMapping("/comment")
public class CommentController {
	private final CommentService commentService;
	private final JWTUtil jwtUtil;

	public CommentController(CommentService commentService, JWTUtil jwtUtil) {
		this.commentService = commentService;
		this.jwtUtil = jwtUtil;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getCommentList(@PathVariable("id") int runningBoardId) {
		ResponseEntity<?> responseEntity;
		try {
			int count = commentService.getCommentList(runningBoardId);
			System.out.println("List를 " + count + " 개 불러왔습니다");			
			responseEntity = new ResponseEntity<>(count, HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("comment를 불러오는 도중 문제가 발생 했습니다.");
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@PostMapping
	public ResponseEntity<?> postComment(@ModelAttribute Comment comment) {
		ResponseEntity<?> responseEntity;
		try {
			int count = commentService.addComment(comment);
			System.out.println("comment를 " + count + "개 등록했습니다.");
			responseEntity = new ResponseEntity<>(count, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("comment를 등록하던 도중 문제가 발생 했습니다.");
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	
	@PutMapping
	public ResponseEntity<?> putComment(@PathVariable String id, @RequestBody String entity) {
		//TODO: process PUT request
		
		return entity;
	}
	
//	@PostMapping
//	public ResponseEntity<?> p(@ModelAttribute Comment comment) {
//		ResponseEntity<?> responseEntity;
//		
//		responseEntity = new ResponseEntity<>( HttpStatus.OK);
//		return responseEntity;
//	}
	
	
}