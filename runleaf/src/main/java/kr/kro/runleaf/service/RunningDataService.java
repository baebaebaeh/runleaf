package kr.kro.runleaf.service;

import java.util.List;

import kr.kro.runleaf.domain.Board;
import kr.kro.runleaf.domain.BoardSearch;
import kr.kro.runleaf.domain.Location;
import kr.kro.runleaf.domain.RunningBoard;
import kr.kro.runleaf.domain.RunningBoardImage;
import kr.kro.runleaf.util.PageData;

public interface RunningDataService {

	List<RunningBoard> getRunningBoardList(BoardSearch boardSearch);

	RunningBoard getRunningBoardById(int runningBoardId);

	int addRunningBoard(RunningBoard runningBoard);

	int addRunningBoardImage(RunningBoardImage runningBoardImage);

	int modifyRunningBoard(RunningBoard runningBoard);

	int updateRunningBoardImage(RunningBoardImage runningBoardImage);

	List<RunningBoardImage> getRunningBoardImageList(int runningBoardId);

	int updateRunningBoardMainPath(RunningBoard runningBoard);

	int addLocation(Location location);

	List<Location> getRunningBoardLocationList(int runningBoardId);



}
