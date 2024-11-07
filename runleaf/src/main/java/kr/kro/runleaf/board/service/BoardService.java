package kr.kro.runleaf.board.service;

import java.util.List;

import kr.kro.runleaf.board.domain.Board;
import kr.kro.runleaf.board.domain.BoardSearch;
import kr.kro.runleaf.board.domain.dto.DeleteBoardDto;
import kr.kro.runleaf.board.domain.dto.InsertBoardDto;
import kr.kro.runleaf.board.domain.dto.UpdateBoardDto;
import kr.kro.runleaf.board.util.PageData;

public interface BoardService {

	List<Board> getBoardList(BoardSearch boardSearch);

	Board getBoardDetail(int id);

	int addBoard(InsertBoardDto createBoardDto);

	int modifiyBoard(UpdateBoardDto updateBoardDto);

	int deleteBoard(DeleteBoardDto deleteBoardDto);
	

}
