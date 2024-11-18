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
  
  // const memberLogin = function(username, password) {
  //   axios.post('/api/login', {}
  //     username: username,
  //     password,
  //   })
  // }


  const member = ref({})

  const getBoard = async function (id) {
    axios.get(`/api/member/${id}`)
    .then((response) => {
      member.value = response.data
    })
  }

  const loginId = computed(() => member.value.id);

  getBoard(loginId);

  return { router, memberInfo, formData, loginId, updateMemberInfo, addFile, submitJoinForm, getBoard };
}
);
