import { defineStore } from 'pinia';
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';
import Swal from 'sweetalert2';

export const useMemberStore = defineStore('member', () => {

  const router = useRouter();

  const formData = new FormData();
  
  const memberFile = ref({})
  const memberJoinForm = ref({
    username: '',
    password: '',
    email: '',
    phone: '',
  });


  const updateJoinForm = (member) => {
    memberJoinForm.value.username = member.username;
    memberJoinForm.value.password = member.password;
    memberJoinForm.value.email = member.email;
    memberJoinForm.value.phone = member.phone;
  };

  
  const addFile = (file) => {
    memberFile.value = file;
  };

  // formData 초기화 함수
  const resetFormData = () => {
    formData.delete('file');
    formData.delete('member');
  };
  
  const submitJoinForm = async () => {
    resetFormData();
    formData.append("file", memberFile.value);
    formData.append('member', new Blob([JSON.stringify(memberJoinForm.value)], { type: 'application/json' }));
    
    formData.forEach((value, key) => {
      console.log(`${key}: ${value}`);
    });
    try {

      const response = await axios.post('/api/join', formData);
      
      if (response.status === 200) {
        alert('회원가입이 완료되었습니다!');
        router.push('/');
        
        resetJoinForm();
      }
    } catch (error) {
      console.error('회원가입 실패:', error);
      alert('회원가입 중 문제가 발생했습니다.');
      router.push('/join')
      
      resetJoinForm();
    }
  }

   // 회원가입 폼 초기화 함수
   const resetJoinForm = () => {
    memberJoinForm.value = {
      username: '',
      password: '',
      email: '',
      phone: '',
    };
  };
  
  const memberInfoForm = ref({
    username: '',
    email: '',
    phone: '',
    memberFileUrl: 'uploads/default/profile-default.png',
    totalDist: '',
    totalRunningSecond: '',
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
      const token = sessionStorage.getItem('token');
      const response = await axios.put('/api/member', memberEditForm.value, {
        headers: {
          authorization: `Bearer ${token}`, 
        }
      });
      if (response.status === 200) {
        Swal.fire({
          title: '성공!',
          icon: 'success',
          width: '300px',
          confirmButtonText: '확인',
          confirmButtonColor: '#4caf50',
      });
        router.push('/profile'); 
      }
    } catch (error) {
      console.error('회원 정보 수정 실패:', error);
      Swal.fire({
        title: '실패!',
        icon: 'error',
        width: '300px',
        confirmButtonText: '확인',
        confirmButtonColor: '#f44336',
    });
    }
  };

  const uploadMemberImage = async (formData) => {
    try {
      console.log('업로드할 formData:', formData);
      const token = sessionStorage.getItem('token');
      const response = await axios.put('/api/profile', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
          authorization: `Bearer ${token}`,
      },
      });
      memberInfoForm.memberFileUrl = response.data.filePath;
      return response.data;
    } catch (error) {
      console.error('이미지 업로드 에러:', error);
    }
  }

  return { router, formData, memberInfoForm, memberJoinForm, memberEditForm, updateJoinForm, updateEditForm, addFile, submitJoinForm, getMember, editMember, uploadMemberImage };
}
);