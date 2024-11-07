package kr.kro.runleaf.board.repository;

import java.util.List;

import kr.kro.runleaf.board.domain.Board;
import kr.kro.runleaf.board.domain.BoardSearch;
import kr.kro.runleaf.board.domain.dto.DeleteBoardDto;
import kr.kro.runleaf.board.domain.dto.InsertBoardDto;
import kr.kro.runleaf.board.domain.dto.UpdateBoardDto;

public interface BoardDao {

	List<Board> selectBoardByOrder(BoardSearch boardSearch);

	Board selectBoardById(int id);

	int insertBoard(InsertBoardDto insertBoardDto);

	int updateBoard(UpdateBoardDto updateBoardDto);

	int deleteBoard(DeleteBoardDto deleteBoardDto);


}
