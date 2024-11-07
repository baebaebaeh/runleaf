package kr.kro.runleaf.board.domain.dto;

/**
 * 게시글 생성시 사용하는 dto
 * createdDate, modifidedDate는 생략
 */
public class InsertBoardDto {
	private int id;
	private int runningDataId;
	private String content;
	private String mainImagePath;
	
}
