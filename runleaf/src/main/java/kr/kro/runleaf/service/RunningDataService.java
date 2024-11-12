package kr.kro.runleaf.service;

import java.util.List;

import kr.kro.runleaf.domain.Board;
import kr.kro.runleaf.domain.BoardSearch;
import kr.kro.runleaf.domain.RunningData;
import kr.kro.runleaf.domain.RunningDataImage;
import kr.kro.runleaf.util.PageData;

public interface RunningDataService {

	int addRunningData(RunningData runningData);

	int addRunningDataImage(RunningDataImage runningDataImage);

	int getRunningDataList();

}
