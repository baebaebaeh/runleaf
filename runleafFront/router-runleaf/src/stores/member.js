import { defineStore } from 'pinia';
import { computed, ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

export const useMemberStore = defineStore('member', () => {
  const router = useRouter();

  const formData = new FormData();

  const memberInfo = ref({
    username: '',
    password: '',
    nickname: '',
    email: '',
    phone: '',
  });

  const updateMemberInfo = (member) => {
      memberInfo.value.username = member.username;
      memberInfo.value.password = member.password;
      memberInfo.value.nickname = member.nickname;
      memberInfo.value.email = member.email;
      memberInfo.value.phone = member.phone;
  };

  const addFile = (file) => {
    formData.append("file", file);
  };

  const submitJoinForm = async () => {
    formData.append('member', new Blob([JSON.stringify(memberInfo.value)], { type: 'application/json' }));
    try {
      const response = await axios.post('/api/member', formData);

      // 회원가입 성공 시
      if (response.status === 200) {
        alert('회원가입이 완료되었습니다!');
        router.push('/');
      }
    } catch (error) {
      console.error('회원가입 실패:', error);
      alert('회원가입 중 문제가 발생했습니다.');
      router.push('/join')
    }
  }

  const loginMember = ref(null);
  
  const memberLogin = function(username, password){
    axios.post('/api/login', {
      username: username,
      password: password,
    })
    .then((res)=>{
      // 응답에서 Authorization 헤더를 통해 JWT 토큰을 추출
    const token = res.headers['Authorization'];  // Authorization 헤더에서 'Bearer {token}' 값 추출

    if (token) {
      // 'Bearer ' 앞부분을 제거하고 실제 토큰만 가져오기
      const accessToken = token.split(' ')[1];  // 'Bearer {token}' 형태에서 {token} 부분만 추출

      // 토큰을 sessionStorage에 저장
      sessionStorage.setItem('token', accessToken);

      // JWT 페이로드에서 사용자 정보 추출
      const decodedToken = JSON.parse(atob(accessToken.split('.')[1]));  // 토큰을 디코딩하여 사용자 정보 추출
      const name = decodedToken.name;  // 예시: 토큰 안에 name 필드가 있다면

      loginMember.value = name;  // 로그인한 사용자 이름 저장

      // 로그인 성공 후, 메인 페이지로 리디렉션
      router.push('/');
    } else {
      console.error('토큰이 응답 헤더에 없습니다.');
      router.push({ name: 'login' });
    }
  })
  .catch((err) => {
    console.error(err);
    router.push({ name: 'login' });
  });
  }

  const member = ref({})

  const getMember = async function () {
    axios.get('/api/member')
    .then((response) => {
      member.value = response.data
    })
  }

  const loginId = computed(() => member.value.id);

  getMember(loginId);

  return { router, memberInfo, formData, loginId, loginMember, updateMemberInfo, addFile, submitJoinForm, getMember, memberLogin };
}
);
