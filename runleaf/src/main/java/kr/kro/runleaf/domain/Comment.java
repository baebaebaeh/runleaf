 package kr.kro.runleaf.domain;

import java.util.List;

public class Comment {
	private int comment_id;
	private int user_id;
	private String parent_id;
	private String running_board_id;
	private String content;
	private String created_date;
	private String modifided_date;
	private List<Comment> comment;
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comment(int comment_id, int user_id, String parent_id, String running_board_id, String content,
			String created_date, String modifided_date, List<Comment> comment) {
		super();
		this.comment_id = comment_id;
		this.user_id = user_id;
		this.parent_id = parent_id;
		this.running_board_id = running_board_id;
		this.content = content;
		this.created_date = created_date;
		this.modifided_date = modifided_date;
		this.comment = comment;
	}
	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getParent_id() {
		return parent_id;
	}
	public void setParent_id(String parent_id) {
		this.parent_id = parent_id;
	}
	public String getRunning_board_id() {
		return running_board_id;
	}
	public void setRunning_board_id(String running_board_id) {
		this.running_board_id = running_board_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	public String getModifided_date() {
		return modifided_date;
	}
	public void setModifided_date(String modifided_date) {
		this.modifided_date = modifided_date;
	}
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Comment [comment_id=" + comment_id + ", user_id=" + user_id + ", parent_id=" + parent_id
				+ ", running_board_id=" + running_board_id + ", content=" + content + ", created_date=" + created_date
				+ ", modifided_date=" + modifided_date + ", comment=" + comment + "]";
	}
	
	
}
