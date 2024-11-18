package kr.kro.runleaf.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.kro.runleaf.domain.Member;
import kr.kro.runleaf.dto.CustomUserDetails;
import kr.kro.runleaf.repository.MemberRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
	
	private final MemberRepository memberRepository;
	
	public CustomUserDetailService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Member member = memberRepository.selectMemberByUsername(username);
		
		if (member != null) {
			
			return new CustomUserDetails(member);
		}
		throw new UsernameNotFoundException("회원을 찾을 수 없습니다: " + username);
	}
	
}
