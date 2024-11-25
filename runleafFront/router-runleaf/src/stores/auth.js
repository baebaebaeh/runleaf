import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import { useMemberStore } from './member';
import Swal from 'sweetalert2';

export const useAuthStore = defineStore('auth', () => {
    const router = useRouter();
    const memberStore = useMemberStore();

    const isLoggedIn = ref(false);
    const token = ref(null);
    const loginUsername = ref('');
    const loginProfileImage = ref('');

    // 로그인 처리
    const login = async (username, password) => {
        try {
            const response = await axios.post('/api/login', { username, password });

            // 응답에서 Authorization 헤더를 통해 JWT 토큰을 추출
            const tokenValue = response.headers['authorization']; // authorization 헤더에서 'Bearer {token}' 값 추출
            if (tokenValue) {
                const accessToken = tokenValue.split(' ')[1]; // 'Bearer {token}' 형태에서 {token} 부분만 추출
                sessionStorage.setItem('token', accessToken);  // 토큰을 sessionStorage에 저장

                isLoggedIn.value = true;  // 로그인 상태 업데이트
                token.value = accessToken; // 토큰 저장

                await memberStore.getMember();

                const memberData = memberStore.memberInfoForm;
                loginUsername.value = memberData.username;
                loginProfileImage.value = memberData.memberFileUrl;

                Swal.fire({
                    title: '로그인 성공',
                    text: '환영합니다!',
                    icon: 'success',
                    width: 300,
                    timer: 1500,
                    showConfirmButton: false, // 확인 버튼 제거
                });
                router.push('/');
            } else {
                router.push({ name: 'login' });
            }
        } catch (err) {
            console.error('로그인 실패:', err);
            Swal.fire({
                title: '로그인 실패',
                text: err.response?.data?.error || '아이디 또는 비밀번호가 잘못되었습니다.',
                icon: 'error',
                width: 300,
                confirmButtonText: '확인',
            });
            router.push({ name: 'login' });
        }
    };

    // 로그아웃 처리
    const logout = () => {
        sessionStorage.removeItem('token');  // 토큰 삭제
        isLoggedIn.value = false;  // 로그인 상태 업데이트
        loginUsername.value = '';  // 사용자 이름 초기화
        loginProfileImage.value = '';
        router.push('/');  // 로그인 페이지로 리디렉션
    };

    // 로그인 상태를 초기화 (새로고침 시에도 상태 유지)
    const initializeAuthState = async () => {
        const storedToken = sessionStorage.getItem('token');
        if (storedToken) {
            token.value = storedToken;
            isLoggedIn.value = true;
            try {
                const response = await axios.get('/api/member', {
                    headers: {
                        Authorization: `Bearer ${storedToken}`,
                    },
                });

                const memberData = response.data; // 서버에서 받아온 사용자 정보
                loginUsername.value = memberData.username;
                loginProfileImage.value = memberData.memberFileUrl;
            } catch (err) {
                console.error('사용자 정보 가져오기 실패:', err);
                Swal.fire({
                    title: '로그인 정보 확인 실패',
                    text: '다시 로그인 해주세요.',
                    icon: 'error',
                    width: 300,
                    confirmButtonText: '확인',
                });
                logout(); // 로그인 상태 초기화
            }
        }
    };

    // 페이지 로드 시 로그인 상태 초기화
    initializeAuthState();

    return { router, token, isLoggedIn, loginUsername, loginProfileImage, login, logout };
});
