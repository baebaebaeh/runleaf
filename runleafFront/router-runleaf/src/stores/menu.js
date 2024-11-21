import { defineStore } from 'pinia';

export const useMenuStore = defineStore('menu', {
  state: () => ({
    isMenuVisible: false,  // 메뉴 보임/숨김 상태
  }),
  actions: {
    toggleMenu() {
      this.isMenuVisible = !this.isMenuVisible;
    },
    hideMenu() {
      this.isMenuVisible = false;
    },
  },
});
