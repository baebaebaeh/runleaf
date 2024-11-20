import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

export const useMemberStore = defineStore('member', () => {

  const router = useRouter();

  const formData = new FormData();

  const memberJoinForm = ref({
    username: '',
    password: '',
    email: '',
    phone: '',
  });

  // const memberImage = ref(null);

  const updateJoinForm = (member) => {
    memberJoinForm.value.username = member.username;
    memberJoinForm.value.password = member.password;
    memberJoinForm.value.email = member.email;
    memberJoinForm.value.phone = member.phone;
  };

  
  const addFile = (file) => {
    formData.append("file", file);
  };
  
  const submitJoinForm = async () => {
    formData.append('member', new Blob([JSON.stringify(memberJoinForm.value)], { type: 'application/json' }));
    try {
      const response = await axios.post('/api/join', formData);
      
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
  
  const memberInfoForm = ref({
    username: '',
    email: '',
    phone: '',
    memberFileUrl: 'uploads/default/profile-default.png'
  });
  
  const getMember = async () => {
    try {
      const token = sessionStorage.getItem('token');
      const response = await axios.get('/api/member', {
        headers: {
          authorization: `Bearer ${token}`,
        }
      });
      memberInfoForm.value = response.data;

      updateEditForm(memberInfoForm.value);
    } catch (error) {
      console.error('회원 정보 조회 실패:', error);
    }
  };
  
  const memberEditForm = ref({
    username: '',
    email: '',
    phone: '',
  });

  const updateEditForm = (member) => {
    memberEditForm.value.username = member.username;
    memberEditForm.value.email = member.email;
    memberEditForm.value.phone = member.phone;
  };

  const editMember = async () => {
    try {
      console.log(memberEditForm.value)
      const token = sessionStorage.getItem('token');
      const response = await axios.put('/api/member', memberEditForm.value, {
        headers: {
          authorization: `Bearer ${token}`, // 토큰 추가
        }
      });
      if (response.status === 200) {
        alert('회원 정보가 수정되었습니다!');
        router.push('/profile'); // 프로필 페이지로 이동
      }
    } catch (error) {
      console.error('회원 정보 수정 실패:', error);
      alert('회원 정보 수정 중 문제가 발생했습니다.');
    }
  };

  return { router, formData, memberInfoForm, memberJoinForm, memberEditForm, updateJoinForm, updateEditForm, addFile, submitJoinForm, getMember, editMember };
}
);
