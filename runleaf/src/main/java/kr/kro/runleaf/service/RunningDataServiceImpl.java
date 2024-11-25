package kr.kro.runleaf.service;

import java.util.List;import org.springframework.boot.SpringApplication.Running;
import org.springframework.stereotype.Service;

import kr.kro.runleaf.domain.Board;
import kr.kro.runleaf.domain.BoardSearch;
import kr.kro.runleaf.domain.Location;
import kr.kro.runleaf.domain.RunningBoard;
import kr.kro.runleaf.domain.RunningBoardImage;
import kr.kro.runleaf.dto.RunningDataRequest;
import kr.kro.runleaf.repository.MemberRepository;
import kr.kro.runleaf.repository.RunningBoardRepository;
import kr.kro.runleaf.util.PageData;

@Service
public class RunningDataServiceImpl implements RunningDataService {
	private final RunningBoardRepository runningBoardRepository;
	private final MemberRepository memberRepository;
	public RunningDataServiceImpl(RunningBoardRepository runningDataRepository, MemberRepository memberRepository) {
		this.runningBoardRepository = runningDataRepository;
		this.memberRepository = memberRepository;
	}
	@Override
	public List<RunningBoard> getRunningBoardList(BoardSearch boardSearch) {
		List<RunningBoard> list = runningBoardRepository.selectRunningBoardList(boardSearch);
		for (int i = 0; i < list.size(); i++) {
			int runningBoardId = list.get(i).getRunningBoardId();
			list.get(i).setRunningBoardImage(runningBoardRepository.selectRunningBoardImageList(runningBoardId));
			list.get(i).setLocation(runningBoardRepository.selectRunningBoardCoodinate(runningBoardId));
		}
		list.size();
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
	@Override
	public int addLocation(Location location) {
		int count = runningBoardRepository.insertLocation(location);
		return count;
	}
	@Override
	public List<Location> getRunningBoardLocationList(int runningBoardId) {
		List<Location> location = runningBoardRepository.selectRunningBoardCoodinate(runningBoardId);
		return location;
	}
	@Override
	public int deleteRunningBoardImage(int runningBoardId) {
		int count = runningBoardRepository.deleteRunningBoardImage(runningBoardId);
		return count;
	}
	@Override
	public int deleteRunningBoard(int runningBoardId) {
		
		
		runningBoardRepository.deleteRunningCoodinate(runningBoardId);
		int tmp = runningBoardRepository.deleteRunningBoardImage(runningBoardId);
		System.out.println(tmp);
		int count = runningBoardRepository.deleteRunningBoard(runningBoardId);
		return count;
	}
	@Override
	public void updateMember(RunningDataRequest runningDataRequest) {
		memberRepository.updateDistAndRunningSecond(runningDataRequest);
	}

}
