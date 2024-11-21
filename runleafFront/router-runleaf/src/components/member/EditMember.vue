<template>
    <div class="main-container">
        <div class="form-container">
            <div class="header-container">
                <div class="title">회원 정보 변경</div>
            </div>

            <!-- 프로필 이미지 섹션 -->
            <div class="profile-container">
                <div class="profile-preview" @click="triggerFileInput">
                    <img :src="previewImage || editForm.memberFileUrl" alt="프로필 미리보기" />
                </div>
                <input type="file" id="profileImage" ref="fileInput" @change="onFileChange" class="file-input" />
            </div>

            <!-- 회원 정보 입력 -->
            <div class="info-container">
                <label for="username" class="label">아이디</label>
                <input type="text" id="username" v-model="editForm.username" class="input-field-username" disabled />

                <label for="email" class="label">이메일</label>
                <input type="email" id="email" v-model="editForm.email" class="input-field" />

                <label for="phone" class="label">연락처</label>
                <input type="tel" id="phone" v-model="editForm.phone" class="input-field"
                    placeholder="( - )을 제외하고 입력해주세요." />
                <span v-if="errors.phone" class="error-text">{{ errors.phone }}</span>
            </div>

            <button @click="saveChanges" class="save-button">저장</button>
        </div>
    </div>
</template>

<script setup>
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';
import { useMemberStore } from '@/stores/member';

// Pinia 스토어와 라우터 초기화
const memberStore = useMemberStore();
const router = useRouter();

// 상태 변수 정의
const editForm = ref({});
const previewImage = ref('');
const selectedFile = ref(null);
const errors = ref({});

// 초기화 함수
const initialize = () => {
    // 서버에서 회원 정보를 가져오고 폼 초기화
    memberStore.getMember().then(() => {
        editForm.value = { ...memberStore.memberEditForm };
        previewImage.value = `/api/uploads/${memberStore.memberInfoForm.memberFileUrl}`;
    });
};

// 입력값 유효성 검사
const validateInfo = () => {
    errors.value = {};

    if (
        editForm.value.phone &&
        !/^[0-9]{11}$/.test(editForm.value.phone)
    ) {
        errors.value.phone = '연락처는 ( - )을 제외한 숫자 11자리여야 합니다.';
    }

    return Object.keys(errors.value).length === 0;
};

// 파일 선택 핸들러
const onFileChange = (event) => {
    const file = event.target.files[0];
    if (file) {
        selectedFile.value = file;
        const reader = new FileReader();
        reader.onload = (e) => {
            previewImage.value = e.target.result;
        };
        reader.readAsDataURL(file);
    }
};

// 숨겨진 파일 입력창 트리거
const triggerFileInput = () => {
    document.getElementById('profileImage').click();
};

// 프로필 이미지 업로드
const uploadImage = async () => {
    if (selectedFile.value) {
        const formData = new FormData();
        formData.append('profileImage', selectedFile.value);

        try {
            await memberStore.uploadMemberImage(formData);
            alert('프로필 이미지가 변경되었습니다.');
        } catch (error) {
            console.error('이미지 업로드 실패:', error);
            alert('이미지 변경에 실패했습니다.');
        }
    }
};

// 변경 사항 저장
const saveChanges = async () => {
    if (validateInfo()) {
        // 회원 정보 저장
        await memberStore.editMember(editForm.value);

        // 프로필 이미지가 선택된 경우 업로드
        if (selectedFile.value) {
            await uploadImage();
        }
        router.push('/myinfo');
    }
};

// 컴포넌트가 마운트될 때 초기화
onMounted(() => {
    initialize();
});
</script>

<style scoped>
.main-container {
    display: flex;
    justify-content: center;
    align-items: flex-start;
    /* 프로필을 상단에 위치 */
    min-height: 100vh;
    box-sizing: border-box;
    padding-top: 100px;
}

.form-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    /* 폼 자체를 중앙에 정렬 */
    background: #ffffff;
    padding: 40px 20px;
    width: 440px;
    max-width: 640px;
    border-radius: 10px;
}

.header-container {
    width: 100%;
    margin-bottom: 60px;
    text-align: center;
}

.title {
    color: #000000;
    font-size: 24px;
    font-weight: 600;
}

.profile-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    /* 프로필 사진을 중앙 정렬 */
    gap: 10px;
    margin: 20px 0;
}

.profile-preview {
    width: 120px;
    height: 120px;
    border-radius: 50%;
    overflow: hidden;
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    transition: border-color 0.3s ease;
}

.profile-preview img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}

.file-input {
    display: none;
}

.label {
    margin-top: 15px;
    font-size: 16px;
    font-weight: 400;
}

.input-field,
.input-field-username {
    width: 100%;
    height: 45px;
    margin-top: 5px;
    padding: 10px;
    font-size: 14px;
    border: 1px solid #aeaeae;
    border-radius: 5px;
    box-sizing: border-box;
}

.input-field-username {
    color: #9c9c9c;
    background: #f5f5f5;
}

.save-button {
    width: 100%;
    height: 50px;
    margin-top: 25px;
    font-size: 18px;
    font-weight: 600;
    color: #ffffff;
    background: #d1d1d1;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

.save-button:hover {
    background-color: #9c9c9c;
}
</style>
