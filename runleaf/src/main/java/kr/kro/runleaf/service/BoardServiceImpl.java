package kr.kro.runleaf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kro.runleaf.board.domain.dto.DeleteBoardDto;
import kr.kro.runleaf.board.domain.dto.InsertBoardDto;
import kr.kro.runleaf.board.domain.dto.UpdateBoardDto;
import kr.kro.runleaf.domain.Board;
import kr.kro.runleaf.domain.BoardSearch;
import kr.kro.runleaf.repository.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {
	private final BoardDao boardDao;
	public BoardServiceImpl(BoardDao boardDao) {
		this.boardDao = boardDao;
	}
	
	
	@Override
	public List<Board> getBoardList(BoardSearch boardSearch) {
		List<Board> boardList = null;
		boardList = boardDao.selectBoardByOrder(boardSearch);
		System.out.println(boardList);
		return boardList;
	}


	@Override
	public Board getBoardDetail(int id) {
		Board board = boardDao.selectBoardById(id);
		return board;
	}


	@Override
	public int addBoard(Board board) {
		int numberOfChange = boardDao.insertBoard(board);
		return numberOfChange;
	}


	@Override
	public int modifiyBoard(Board board) {
		int numberOfChange = boardDao.updateBoard(board);
		return numberOfChange;
	}


	@Override
	public int deleteBoard(Board board) {
		int numberOfChange = boardDao.deleteBoard(board);
		return numberOfChange;
	}
}
