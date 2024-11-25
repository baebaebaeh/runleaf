package kr.kro.runleaf.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kro.runleaf.domain.Comment;
import kr.kro.runleaf.dto.CustomUserDetails;
import kr.kro.runleaf.jwt.JWTUtil;
import kr.kro.runleaf.repository.CommentRepository;
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

	// id는 runningBoardID
	@GetMapping("/{id}")
	public ResponseEntity<?> getCommentList(@PathVariable("id") int runningBoardId,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		ResponseEntity<?> responseEntity;
		try {
			List<Comment> list = commentService.getCommentByRunningBoardId(runningBoardId);
			System.out.println("List를 " + list.size() + " 개 불러왔습니다");
			responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("comment를 불러오는 도중 문제가 발생 했습니다.");
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}
	@GetMapping("/reply/{id}")
	public ResponseEntity<?> getRepltList(@PathVariable("id") int commentId,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		ResponseEntity<?> responseEntity;
		try {
			List<Comment> list = commentService.getCommentByParentId(commentId);
			System.out.println("List를 " + list.size() + " 개 불러왔습니다");
			responseEntity = new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("comment를 불러오는 도중 문제가 발생 했습니다.");
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@PostMapping
	public ResponseEntity<?> postComment(@RequestBody Comment comment,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		ResponseEntity<?> responseEntity;
		String username = userDetails.getUsername();
		comment.setWriter(username);
		try {
			System.out.println(comment);
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

	// id는 commentId
	@PutMapping("{id}")
	public ResponseEntity<?> putComment(@PathVariable int commentId, @RequestBody Comment comment,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		// TODO: process PUT request
		String username = userDetails.getUsername();
		ResponseEntity<?> responseEntity;
		// 수정요청 보낸사람과 댓글작성자와 이름이 다르면 수정 불가
		if (!commentService.getCommentByCommentId(commentId).getWriter().equals(username)) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		try {
			int count = commentService.modifyComment(comment);
			if (count == 1) {
				responseEntity = new ResponseEntity<>(count, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>(count, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			System.out.println("comment가 " + comment + "개 변경되었습니다");
		} catch (Exception e) {
			System.out.println("comment 변경중 오류가 발생 했습니다.");
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteMapping(@PathVariable int commentId,
			@AuthenticationPrincipal CustomUserDetails userDetails) {
		String username = userDetails.getUsername();
		ResponseEntity<?> responseEntity;
		try {
			Comment comment = commentService.getCommentByCommentId(commentId);
			// 이름과 지우려는 댓글이 다를때 삭제 불가능
			if (!comment.getWriter().equals(username)) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			// 위조건을 통과했으면 삭제
			int count = commentService.deleteCommentByCommentId(comment.getCommentId());
			if (count == 1) {
				responseEntity = new ResponseEntity<>(count, HttpStatus.OK);
			} else {
				responseEntity = new ResponseEntity<>(count, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			System.out.println("commet가 " + comment + "개 변경되었습니다");
		} catch (Exception e) {
			System.out.println("comment 삭제중 오류가 발생했습니다.");
			responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

//	@PostMapping
//	public ResponseEntity<?> p(@ModelAttribute Comment comment) {
//		ResponseEntity<?> responseEntity;
//		
//		responseEntity = new ResponseEntity<>( HttpStatus.OK);
//		return responseEntity;
//	}

}