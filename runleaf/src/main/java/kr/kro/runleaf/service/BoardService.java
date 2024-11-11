package kr.kro.runleaf.service;

import java.util.List;

import kr.kro.runleaf.domain.Board;
import kr.kro.runleaf.domain.BoardSearch;
import kr.kro.runleaf.util.PageData;

public interface BoardService {

	List<Board> getBoardList(BoardSearch boardSearch);

	Board getBoardDetail(int id);

	int addBoard(Board board);

	int modifiyBoard(Board board);

	int deleteBoard(Board board);
	

}
