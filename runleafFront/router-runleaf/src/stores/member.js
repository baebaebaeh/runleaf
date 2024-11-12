import { defineStore } from 'pinia';

export const useMemberStore = defineStore('member', {
  state: () => ({
    memberInfo: {
      name: '',
      email: '',
      password: '',
      profileImage: null,
    },
  }),
  actions: {
    updateMemberInfo(payload) {
      this.memberInfo = { ...this.memberInfo, ...payload };
    },
    async submitSignupForm() {
      const formData = new FormData();
      formData.append('member', JSON.stringify(this.memberInfo));
      
      if (this.memberInfo.profileImage) {
        formData.append('file', this.memberInfo.profileImage);
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
