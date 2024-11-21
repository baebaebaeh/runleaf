package kr.kro.runleaf.service;

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
	public int getCommentList(int runningBoardId) {
		int count = commentRepository.selectCommentList(runningBoardId);
		return count;
	}

	@Override
	public int addComment(Comment comment) {
		int count = commentRepository.insertComment(comment);
		return count;
	}
	
	
}
