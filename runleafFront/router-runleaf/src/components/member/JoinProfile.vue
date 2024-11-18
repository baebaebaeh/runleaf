<template>
    <div class="container">
        <RouterLink to="/join">
            <img class="image-arrow" src="@/assets/images/join/icons8-arrow-30.png" alt="다음 아이콘">
        </RouterLink>
        <div class="div2">프로필 사진 등록</div>
        <div class="profile-container" @click="triggerFileInput">
            <img :src="previewImage || defaultProfileImage" alt="프로필 사진" class="image-profile" />
        </div>
        <!-- 파일 선택 input 요소 (화면에서는 숨김 처리) -->
        <input type="file" ref="fileInput" class="file-input" @change="onFileChange" style="display: none" />
        <button class="complete-button" @click="saveAndSubmit">완료</button>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useMemberStore } from '@/stores/member';
import defaultProfileImage from '@/assets/images/join/profile-default.png'; // 기본 프로필 이미지를 import


const memberStore = useMemberStore();

const previewImage = ref(null);

const fileInput = ref(null);

const triggerFileInput = () => {
    fileInput.value.click();
}

const onFileChange = (event) => {
    const file = event.target.files[0];
    if (file) {
        memberStore.addFile(file);
        previewImage.value = URL.createObjectURL(file);
    } else {
        // 파일이 선택되지 않으면 기본 이미지(default.jpg)로 설정
        previewImage.value = defaultProfileImage;
    }
}

const saveAndSubmit = async () => {
    memberStore.submitJoinForm();
}

</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    /* 내부 콘텐츠는 왼쪽 정렬 */
    background: #ffffff;
    padding: 100px;
    width: 600px;
    max-width: 800px;
    box-sizing: border-box;
}

.div2 {
    color: #000000;
    font-family: "Inter-SemiBold", sans-serif;
    font-size: 20px;
    font-weight: 600;
}

.rectangle-2 {
    background: #bdbdbd;
    width: 360px;
    height: 55px;
    position: absolute;
    left: calc(50% - 180px);
    bottom: 0px;
}

.profile-container {
    position: relative;
}

.image-profile {
    width: 100px;
    height: 100px;
    cursor: pointer;
}

.complete-button {
    background: #c5c5c5;
    width: 100%;
    height: 55px;
    margin-top: 20px;
    color: #ffffff;
    font-family: "Inter-SemiBold", sans-serif;
    font-size: 16px;
    font-weight: 600;
    text-align: center;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}
</style>