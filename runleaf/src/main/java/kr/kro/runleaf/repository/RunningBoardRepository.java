package kr.kro.runleaf.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.kro.runleaf.domain.Board;
import kr.kro.runleaf.domain.BoardSearch;
import kr.kro.runleaf.domain.RunningBoard;
import kr.kro.runleaf.domain.RunningBoardImage;

@Repository
public interface RunningBoardRepository {
	
	List<RunningBoardImage> selectRunningBoardImageList(int runningBoardId);
	
	List<RunningBoard> selectRunningBoardList(BoardSearch boardSearch);

	RunningBoard selectRunningBoardById(int runningBoardId);
	
	int insertRunningBoard(RunningBoard runningBoard);
	
	int insertRunningBoardImage(RunningBoardImage runningBoardImage);
	
	int updateRunningBoard(RunningBoard runningBoard);
	
	int updateRunningBoardImage(RunningBoardImage runningBoardImage);
	
	int updateRunningBoardMainPath(RunningBoard runningBoard);
	
	int deleteRunningBoard(int runningBoardId);

	
	
}
