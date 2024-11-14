package kr.kro.runleaf.service;

import java.util.List;import org.springframework.boot.SpringApplication.Running;
import org.springframework.stereotype.Service;

import kr.kro.runleaf.domain.Board;
import kr.kro.runleaf.domain.BoardSearch;
import kr.kro.runleaf.domain.RunningBoard;
import kr.kro.runleaf.domain.RunningBoardImage;
import kr.kro.runleaf.repository.RunningBoardRepository;
import kr.kro.runleaf.util.PageData;

@Service
public class RunningDataServiceImpl implements RunningDataService {
	private final RunningBoardRepository runningBoardRepository;
	public RunningDataServiceImpl(RunningBoardRepository runningDataRepository) {
		this.runningBoardRepository = runningDataRepository;
	}
	@Override
	public List<RunningBoard> getRunningBoardList(BoardSearch boardSearch) {
		List<RunningBoard> list = runningBoardRepository.selectRunningBoardList(boardSearch);
		return list;
	}
	@Override
	public int addRunningBoard(RunningBoard runningBoard) {
		int numberOfChange = runningBoardRepository.insertRunningBoard(runningBoard);
		return numberOfChange;
	}
	@Override
	public int addRunningBoardImage(RunningBoardImage runningBoardImage) {
		int numberOfChange = runningBoardRepository.insertRunningBoardImage(runningBoardImage);
		return numberOfChange;
	}
	@Override
	public RunningBoard getRunningBoardById(int runningBoardId) {
		RunningBoard runningBoard = runningBoardRepository.selectRunningBoardById(runningBoardId);
		return runningBoard;
	}
	@Override
	public int modifyRunningBoard(RunningBoard runningBoard) {
		int numberOfChange = runningBoardRepository.updateRunningBoard(runningBoard);
		return numberOfChange;
	}
	@Override
	public int updateRunningBoardImage(RunningBoardImage runningBoardImage) {
		int numberOfChange = runningBoardRepository.updateRunningBoardImage(runningBoardImage);
		return numberOfChange;
	}
	@Override
	public List<RunningBoardImage> getRunningBoardImageList(int runningBoardId) {
		List<RunningBoardImage> list = runningBoardRepository.selectRunningBoardImageList(runningBoardId);
		return list;
	}
	@Override
	public int updateRunningBoardMainPath(RunningBoard runningBoard) {
		int numberOfChange = runningBoardRepository.updateRunningBoardMainPath(runningBoard);
		return numberOfChange;
	}
	

}
