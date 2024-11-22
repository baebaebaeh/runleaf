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
                <div class="form-group">
                    <label for="username" class="label">아이디</label>
                    <input type="text" id="username" v-model="editForm.username" class="input-field" disabled />
                </div>
                <div class="form-group">
                    <label for="email" class="label">이메일</label>
                    <input type="email" id="email" v-model="editForm.email" class="input-field" />
                </div>
                <div class="form-group">
                    <label for="phone" class="label">연락처</label>
                    <input type="tel" id="phone" v-model="editForm.phone" class="input-field"
                        placeholder="( - )을 제외하고 입력해주세요." />
                </div>
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

const memberStore = useMemberStore();
const router = useRouter();

const editForm = ref({});
const previewImage = ref('');
const selectedFile = ref(null);
const errors = ref({});

const initialize = () => {
    memberStore.getMember().then(() => {
        editForm.value = { ...memberStore.memberEditForm };
        previewImage.value = `/api/uploads/${memberStore.memberInfoForm.memberFileUrl}`;
    });
};

const validateInfo = () => {
    errors.value = {};

    if (editForm.value.phone && !/^[0-9]{11}$/.test(editForm.value.phone)) {
        errors.value.phone = '연락처는 ( - )을 제외한 숫자 11자리여야 합니다.';
    }

    return Object.keys(errors.value).length === 0;
};

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

const triggerFileInput = () => {
    document.getElementById('profileImage').click();
};

const uploadImage = async () => {
    if (selectedFile.value) {
        const formData = new FormData();
        formData.append('profileImage', selectedFile.value);
        try {
            await memberStore.uploadMemberImage(formData);
        } catch (error) {
            console.error('이미지 업로드 실패:', error);
        }
    }
};

const saveChanges = async () => {
    if (validateInfo()) {
        memberStore.updateEditForm(editForm.value)
        await memberStore.editMember();
        if (selectedFile.value) {
            await uploadImage();
        }
        router.push('/myinfo');
    }
};

onMounted(() => {
    initialize();
});
</script>

<style scoped>
.main-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    box-sizing: border-box;
}

.form-container {
    display: flex;
    flex-direction: column;
    align-items: center;
    background: #ffffff;
    padding: 40px 20px;
    width: 400px;
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

.info-container {
    width: 100%;
    display: flex;
    flex-direction: column;
    gap: 20px;
}

.form-group {
    display: flex;
    flex-direction: column;
    gap: 8px;
    width: 100%;
}

.label {
    font-size: 14px;
    font-weight: 600;
}

.input-field {
    width: 100%;
    height: 50px;
    padding: 10px;
    font-size: 16px;
    border: 1px solid #aeaeae;
    border-radius: 5px;
    box-sizing: border-box;
}

.input-field:disabled {
    background: #f5f5f5;
    color: #868686;
}

.save-button {
    width: 100%;
    height: 50px;
    margin-top: 25px;
    font-size: 16px;
    font-weight: 600;
    color: #ffffff;
    background: #d1d1d1;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.save-button:hover {
    background-color: #9c9c9c;
}
</style>
