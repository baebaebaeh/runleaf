package kr.kro.runleaf.board.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.kro.runleaf.board.domain.Board;
import kr.kro.runleaf.board.domain.BoardSearch;
import kr.kro.runleaf.board.domain.dto.DeleteBoardDto;
import kr.kro.runleaf.board.domain.dto.InsertBoardDto;
import kr.kro.runleaf.board.domain.dto.UpdateBoardDto;
import kr.kro.runleaf.board.service.BoardService;
import kr.kro.runleaf.board.util.PageData;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/board")
public class BoardController {
	private final BoardService boardService;

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}

	/**
	 * 게시글 모두 불러오기 게시글이 없을시 204
	 * 게시글이 정상적으로 존재할시 200no content
	 * 불러오면서 서버 내부오류시 500 ok
	 */
	@GetMapping
	public ResponseEntity<List<Board>> getBoardList(BoardSearch boardSearch) {
		List<Board> boardList = boardService.getBoardList(boardSearch);
		ResponseEntity<List<Board>> responseEntity;
		try {
			if (boardList.isEmpty()) {
				responseEntity = new ResponseEntity<>(boardList, HttpStatus.NO_CONTENT);
			} else {
				responseEntity = new ResponseEntity<>(boardList, HttpStatus.OK);
			}
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(boardList, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	/**
	 * 게시글 하나만 불러오기 게시글이 없을시 204 (먼저 누군가 지웠을경우) 게시글이 정상적으로 불러와졌을시 200 no content
	 * 불러오면서 서버 내부 오류시 500 ok
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Board> getBoardDetail(@PathVariable int id) {
		Board board = boardService.getBoardDetail(id);
		ResponseEntity<Board> responseEntity;
		try {
			if (board == null) {
				responseEntity = new ResponseEntity<>(board, HttpStatus.NO_CONTENT);
			} else {
				responseEntity = new ResponseEntity<>(board, HttpStatus.OK);
			}
		} catch (Exception e) {
			responseEntity = new ResponseEntity<>(board, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return responseEntity;
	}

	/**
	 * 게시글 생성하기 게시글 정상적으로 생성시 200 ok
	 * 게시글 생성못했을시 400오류 bad request
	 * 중간에 오류 났을시 internal server error
	 * 
	 */
	@PostMapping
	public ResponseEntity<Integer> addBoard(@RequestBody InsertBoardDto insertBoardDto) {
		int numberOfChange = boardService.addBoard(insertBoardDto);
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
		return responseEntity;
	}

	/**
	 * 게시글 수정하기 게시글 정상적으로 수정시 200 ok
	 * 게시글 수정 못했을시 400오류 bad request
	 * 중간에 오류 났을시 internal server error
	 * 
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Integer> modifiyBoard(@RequestBody UpdateBoardDto updateBoardDto) {
		int numberOfChange = boardService.modifiyBoard(updateBoardDto);
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
		return responseEntity;
	}

	/**
	 * 게시글 삭제하기 게시글 정상적으로 삭제시 200ok
	 * 권한이 없이 요청들어왔을시 203 non authoritative information
	 * 게시글 삭제 못했을시 400오류 bad request
	 * 중간에 오류 났을시 500오류 internal server error
	 * 
	 */
	@DeleteMapping("{id}")
	public ResponseEntity<Integer> deleteBoard(@RequestBody DeleteBoardDto deleteBoardDto,
			@RequestHeader(value = "Authorization", required = false) String authorizationHeader) {
		ResponseEntity<Integer> responseEntity;

		// 헤더가 존재하면 authorizationHeader변수에 들어간다.
		// authorizationHeader변수존재에 따른 로직
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String jwtToken = authorizationHeader.substring(7); // "Bearer " 제거
		} else {
			responseEntity = new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
			return responseEntity;
		}
		
		
		int numberOfChange = 0;
		// 토큰과 memberId와 비교해서 확인하는 로직
		if (// deleteBoardDto.getMemberId == jwtToken
		true) {
			numberOfChange = boardService.deleteBoard(deleteBoardDto);
		}
		if (numberOfChange == 1) {
			responseEntity = new ResponseEntity<>(1, HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<>(0, HttpStatus.NOT_MODIFIED);
		}
		return responseEntity;
	}
}





















