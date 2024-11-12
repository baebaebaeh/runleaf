package kr.kro.runleaf.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.kro.runleaf.domain.Board;
import kr.kro.runleaf.domain.BoardSearch;
import kr.kro.runleaf.domain.RunningData;
import kr.kro.runleaf.domain.RunningDataImage;

@Repository
public interface RunningDataRepository {
	
	int insertRunningData(RunningData runningData);

	int insertRunningDataImage(RunningDataImage runningDataImage);




}
