import { defineStore } from 'pinia';
import { ref, computed } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

export const useAuthStore = defineStore('auth', () => {
    const router = useRouter();
    const isLoggedIn = ref(false);
    const token = ref(null);
    // const username = ref('');

    // 로그인 처리
    const login = async (username, password) => {
        try {
            const response = await axios.post('/api/login', { username, password });

            // 응답에서 Authorization 헤더를 통해 JWT 토큰을 추출
            const tokenValue = response.headers['authorization']; // authorization 헤더에서 'Bearer {token}' 값 추출
            if (tokenValue) {
                const accessToken = tokenValue.split(' ')[1]; // 'Bearer {token}' 형태에서 {token} 부분만 추출
                sessionStorage.setItem('token', accessToken);  // 토큰을 sessionStorage에 저장
                console.log('로그인 성공! 토큰 저장됨:', token);
                const decodedToken = JSON.parse(atob(accessToken.split('.')[1]));  // 토큰을 디코딩하여 사용자 정보 추출
                // console.log(decodedToken.username)
                // username.value = decodedToken.username;

                isLoggedIn.value = true;  // 로그인 상태 업데이트
                token.value = accessToken; // 토큰 저장

                // 로그인 성공 후 메인 페이지로 리디렉션
                router.push('/');
            } else {
                console.error('토큰이 응답 헤더에 없습니다.');
                router.push({ name: 'login' });
            }
        } catch (err) {
            console.error('로그인 실패:', err);
            router.push({ name: 'login' });
        }
    };

    // 로그아웃 처리
    const logout = () => {
        sessionStorage.removeItem('token');  // 토큰 삭제
        isLoggedIn.value = false;  // 로그인 상태 업데이트
        // username.value = '';  // 사용자 이름 초기화
        router.push('/');  // 로그인 페이지로 리디렉션
    };

    // 로그인 상태를 초기화 (새로고침 시에도 상태 유지)
    const initializeAuthState = () => {
        const storedToken = sessionStorage.getItem('token');
        if (storedToken) {
            token.value = storedToken;
            isLoggedIn.value = true;
            // 여기서 서버에 요청을 보내어 사용자 정보를 가져올 수 있습니다.
        }
    };

    // 로그인 상태를 나타내는 computed 값 (UI에서 사용)
    const getUserName = computed(() => username.value);

    // 페이지 로드 시 로그인 상태 초기화
    initializeAuthState();

    return { router, token, isLoggedIn, login, logout, getUserName };
});
