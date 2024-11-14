import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

export const useMemberStore = defineStore('member', () => {
  const router = useRouter();

  const memberInfo = ref({
    username: '',
    password: '',
    nickname: '',
    email: '',
    phone: '',
  });

  const formData = new FormData();

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
      router.push({ name: 'join' })
    }
  };

  return { memberInfo, formData, router, updateMemberInfo, addFile, submitJoinForm };
}
);
