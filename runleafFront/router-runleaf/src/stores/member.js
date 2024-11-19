import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

export const useMemberStore = defineStore('member', () => {
  const router = useRouter();

  const formData = new FormData();

  const memberInfo = ref({
    username: '',
    password: '',
    email: '',
    phone: '',
  });

  const updateMemberInfo = (member) => {
      memberInfo.value.username = member.username;
      memberInfo.value.password = member.password;
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

  const member = ref({
    email: '',
    phone: '',
  });

  const getMember = async () => {
    try {
      console.log(sessionStorage.getItem)
      const token = sessionStorage.getItem('token');
      const response = await axios.get('/api/member', {
        headers: {
          authorization: `Bearer ${token}`, // 헤더에 토큰 추가
        }
      });
      console.log(response)
      member.value = response.data;
    } catch (error) {
      console.error('회원 정보 조회 실패:', error);
    }
  };

  // const loginId = computed(() => member.value.username);

  // getMember(loginId);

  return { router, memberInfo, formData, member, updateMemberInfo, addFile, submitJoinForm, getMember };
}
);
