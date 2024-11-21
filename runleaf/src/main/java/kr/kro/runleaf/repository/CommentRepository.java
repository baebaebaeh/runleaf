package kr.kro.runleaf.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.kro.runleaf.domain.Board;
import kr.kro.runleaf.domain.BoardSearch;
import kr.kro.runleaf.domain.Comment;
import kr.kro.runleaf.domain.Location;
import kr.kro.runleaf.domain.RunningBoard;
import kr.kro.runleaf.domain.RunningBoardImage;

@Repository
public interface CommentRepository {

	int selectCommentList(int runningBoardId);

	int insertComment(Comment comment);
	
}
