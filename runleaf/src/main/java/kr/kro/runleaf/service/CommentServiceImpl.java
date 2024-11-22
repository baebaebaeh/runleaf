package kr.kro.runleaf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kro.runleaf.domain.Comment;
import kr.kro.runleaf.repository.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {
	private final CommentRepository commentRepository;

	public CommentServiceImpl(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}
	
	@Override
	public List<Comment> getCommentByRunningBoardId(int runningBoardId) {
		List<Comment> list = commentRepository.selectCommentByRunningBoardId(runningBoardId);
//		for (int i = 0; i < list.size(); i++) {
//			List<Comment> childList = commentRepository.selectCommentByParentId(list.get(i).getParentId());
//			list.get(i).setComment(childList);
//		}
		return list;
	}

	@Override
	public Comment getCommentByCommentId(int commentId) {
		Comment comment = commentRepository.selectCommentByCommentId(commentId);
		return comment;
	}

	@Override
	public int addComment(Comment comment) {
		int count = commentRepository.insertComment(comment);
		return count;
	}

	@Override
	public int modifyComment(Comment comment) {
		int count = commentRepository.updateComment(comment);
		return count;
	}

	@Override
	public int deleteCommentByCommentId(int commentId) {
		int count = commentRepository.deleteCommentByCommentId(commentId);
		return count;
	}

	
	
}
