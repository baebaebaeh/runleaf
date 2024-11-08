package kr.kro.runleaf.board.domain.dto;

/**
 * 게시글 생성시 사용하는 dto
 * createdDate, modifidedDate는 생략
 */
public class UpdateBoardDto {
	private int id;
	private String content;
	private String mainImagePath;
	public UpdateBoardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdateBoardDto(int id, String content, String mainImagePath) {
		super();
		this.id = id;
		this.content = content;
		this.mainImagePath = mainImagePath;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMainImagePath() {
		return mainImagePath;
	}
	public void setMainImagePath(String mainImagePath) {
		this.mainImagePath = mainImagePath;
	}
	
	
}
