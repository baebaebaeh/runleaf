package kr.kro.runleaf.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.kro.runleaf.domain.Board;
import kr.kro.runleaf.domain.BoardSearch;

@Repository
public interface BoardDao {

	List<Board> selectBoardByOrder(BoardSearch boardSearch);

	Board selectBoardById(int id);

	int insertBoard(Board board);

	int updateBoard(Board board);

	int deleteBoard(Board board);


}
