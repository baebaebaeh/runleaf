package kr.kro.runleaf.board.domain.dto;

/**
 * 게시글 생성시 사용하는 dto
 * createdDate, modifidedDate는 생략
 */
public class UpdateBoardDto {
	private int id;
	private int memberId;
	private int runningDataId;
	private String content;
	private String mainImagePath;
	
}
