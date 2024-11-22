 package kr.kro.runleaf.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Comment {
	private int commentId;
	private int memberId;
	private int parentId;
	private int runningBoardId;
	private String writer;
	private String content;
	private LocalDateTime createdTs;
	private LocalDateTime modifidedTs;
	private List<Comment> replies;
	

	
	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", memberId=" + memberId + ", parentId=" + parentId
				+ ", runningBoardId=" + runningBoardId + ", writer=" + writer + ", content=" + content + ", createdTs="
				+ createdTs + ", modifidedTs=" + modifidedTs + ", replies=" + replies + "]";
	}
	public Comment(int commentId, int memberId, int parentId, int runningBoardId, String writer, String content,
			LocalDateTime createdTs, LocalDateTime modifidedTs, List<Comment> replies) {
		super();
		this.commentId = commentId;
		this.memberId = memberId;
		this.parentId = parentId;
		this.runningBoardId = runningBoardId;
		this.writer = writer;
		this.content = content;
		this.createdTs = createdTs;
		this.modifidedTs = modifidedTs;
		this.replies = replies;
	}
	public LocalDateTime getCreatedTs() {
		return createdTs;
	}
	public void setCreatedTs(LocalDateTime createdTs) {
		this.createdTs = createdTs;
	}
	public LocalDateTime getModifidedTs() {
		return modifidedTs;
	}
	public void setModifidedTs(LocalDateTime modifidedTs) {
		this.modifidedTs = modifidedTs;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getRunningBoardId() {
		return runningBoardId;
	}
	public void setRunningBoardId(int runningBoardId) {
		this.runningBoardId = runningBoardId;
	}
	public List<Comment> getReplies() {
		return replies;
	}
	public void setReplies(List<Comment> replies) {
		this.replies = replies;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getUserId() {
		return memberId;
	}
	public void setUserId(int userId) {
		this.memberId = userId;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<Comment> getComment() {
		return replies;
	}
	public void setComment(List<Comment> comment) {
		this.replies = comment;
	}
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
