package kr.kro.runleaf.service;

import java.util.List;

import kr.kro.runleaf.domain.Board;
import kr.kro.runleaf.domain.BoardSearch;
import kr.kro.runleaf.domain.Comment;
import kr.kro.runleaf.domain.Location;
import kr.kro.runleaf.domain.RunningBoard;
import kr.kro.runleaf.domain.RunningBoardImage;
import kr.kro.runleaf.util.PageData;

public interface CommentService {
	
	Comment getCommentByCommentId(int commentId);

	List<Comment> getCommentByRunningBoardId(int runningBoardId);

	int addComment(Comment comment);

	int modifyComment(Comment comment);

	int deleteCommentByCommentId(int commentId);
	
}
