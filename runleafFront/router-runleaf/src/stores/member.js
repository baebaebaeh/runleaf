import { defineStore } from 'pinia';

export const useMemberStore = defineStore('member', {
  state: () => ({
    memberInfo: {
      username: '',
      password: '',
      nickname: '',
      email: '',
      phone: '',
      memberFile: null,
    },
  }),
  actions: {
    updateMemberInfo(payload) {
      this.memberInfo = { ...this.memberInfo, ...payload };
    },
    async submitJoinForm() {
      const formData = new FormData();
      formData.append('member', JSON.stringify(this.memberInfo));
      
      if (this.memberInfo.memberFile) {
        formData.append('file', this.memberInfo.memberFile);
      }

      try {
        await axios.post('/api/member', formData, {
          headers: { 'Content-Type': 'multipart/form-data' },
        });
        alert('회원가입이 완료되었습니다!');
      } catch (error) {
        console.error('회원가입 실패:', error);
        alert('회원가입 중 문제가 발생했습니다.');
      }
    },
  },
});
