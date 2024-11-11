package kr.kro.runleaf.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.kro.runleaf.board.domain.dto.DeleteBoardDto;
import kr.kro.runleaf.board.domain.dto.InsertBoardDto;
import kr.kro.runleaf.board.domain.dto.UpdateBoardDto;
import kr.kro.runleaf.domain.Board;
import kr.kro.runleaf.domain.BoardSearch;

@Repository
public interface BoardDao {

	List<Board> selectBoardByOrder(BoardSearch boardSearch);

	Board selectBoardById(int id);

	int insertBoard(InsertBoardDto insertBoardDto);

	int updateBoard(UpdateBoardDto updateBoardDto);

	int deleteBoard(DeleteBoardDto deleteBoardDto);


}
