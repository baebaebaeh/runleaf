package kr.kro.runleaf.service;

import java.util.List;

import org.springframework.stereotype.Service;

import kr.kro.runleaf.domain.Board;
import kr.kro.runleaf.domain.BoardSearch;
import kr.kro.runleaf.domain.RunningData;
import kr.kro.runleaf.domain.RunningDataImage;
import kr.kro.runleaf.repository.RunningDataRepository;
import kr.kro.runleaf.util.PageData;

@Service
public class RunningDataServiceImpl implements RunningDataService {
	private final RunningDataRepository runningDataRepository;
	public RunningDataServiceImpl(RunningDataRepository runningDataRepository) {
		this.runningDataRepository = runningDataRepository;
	}
	
	@Override
	public int addRunningData(RunningData runningData) {
		int count = runningDataRepository.insertRunningData(runningData);
		return count;
	}

	@Override
	public int addRunningDataImage(RunningDataImage runningDataImage) {
		int count = runningDataRepository.insertRunningDataImage(runningDataImage);
		return count;
	}

	@Override
	public int getRunningDataList() {
		int count = runningDataRepository.selectRunningDataList();
		return count;
	}



}
