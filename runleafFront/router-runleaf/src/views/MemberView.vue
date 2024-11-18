<template>
    <div class="div">
        <div class="frame-44">
            <img class="icons-8-arrow-30-1" src="@/assets/images/icons8-arrow-30.png" />
            <div class="div2">내 정보</div>
            <div class="div3">변경</div>
        </div>
        <div class="frame-48">
            <div class="frame-9">
                <div class="user-circle-1">
                    <img class="vector" :src="member.memberFile" />

                </div>
            </div>
        </div>
        <div class="frame-45">
            <div class="frame-46">
                <div class="div4">닉네임</div>
            </div>
            <div class="frame-47">
                <div class="div5">{{ store.member.nickname }}</div>
            </div>
        </div>
        <div class="frame-462">
            <div class="frame-46">
                <div class="div4">이메일</div>
            </div>
            <div class="frame-47">
                <div class="email">{{ store.member.email }}</div>
            </div>
        </div>
        <div class="frame-472">
            <div class="frame-46">
                <div class="div4">연락처</div>
            </div>
            <div class="frame-47">
                <div class="phone">{{ store.member.phone }}</div>
            </div>
        </div>
    </div>

</template>

<script setup>
import { useMemberStore } from '@/stores/member';
import { onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

const store = useMemberStore();
const route = useRoute();
const router = useRouter();

onMounted(() => {
    store.getBoard(route.params.id)
})

</script>

<style scoped>
.div,
.div * {
    box-sizing: border-box;
}

.div {
    background: #ffffff;
    display: flex;
    flex-direction: column;
    gap: 10px;
    align-items: flex-start;
    justify-content: flex-start;
    height: 640px;
    position: relative;
    overflow: hidden;
}

.frame-44 {
    padding: 10px 0px 10px 0px;
    display: flex;
    flex-direction: row;
    gap: 90px;
    align-items: center;
    justify-content: center;
    align-self: stretch;
    flex-shrink: 0;
    height: 80px;
    position: relative;
    overflow: hidden;
}

.icons-8-arrow-30-1 {
    flex-shrink: 0;
    width: 30px;
    height: 30px;
    position: relative;
    object-fit: cover;
}

.div2 {
    color: #000000;
    text-align: left;
    font-family: "Inter-SemiBold", sans-serif;
    font-size: 24px;
    line-height: 140%;
    font-weight: 600;
    position: relative;
    width: 73px;
    height: 34px;
}

.div3 {
    color: #777777;
    text-align: left;
    font-family: "Inter-Regular", sans-serif;
    font-size: 18px;
    line-height: 140%;
    font-weight: 400;
    position: relative;
}

.frame-48 {
    padding: 15px 10px 15px 10px;
    display: flex;
    flex-direction: row;
    gap: 10px;
    align-items: center;
    justify-content: center;
    align-self: stretch;
    flex-shrink: 0;
    position: relative;
    overflow: hidden;
}

.frame-9 {
    display: flex;
    flex-direction: row;
    gap: 10px;
    align-items: center;
    justify-content: flex-start;
    flex-shrink: 0;
    width: 150px;
    position: relative;
}

.user-circle-1 {
    flex: 1;
    height: 150px;
    position: relative;
    overflow: hidden;
}

.vector {
    width: 100%;
    height: 96.88%;
    position: absolute;
    right: 0%;
    left: 0%;
    bottom: 1.56%;
    top: 1.56%;
    overflow: visible;
}

.group-8 {
    position: absolute;
    inset: 0;
}

.ellipse-1 {
    background: #bdbdbd;
    border-radius: 50%;
    width: 30%;
    height: 30%;
    position: absolute;
    right: 70%;
    left: 0%;
    bottom: 0%;
    top: 70%;
}

.image-2 {
    width: 21.33%;
    height: 21.33%;
    position: absolute;
    right: 74.67%;
    left: 4%;
    bottom: 4.67%;
    top: 74%;
    object-fit: cover;
}

.frame-45 {
    border-style: solid;
    border-color: #e3dfdf;
    border-width: 0px 0px 1px 0px;
    padding: 15px 5px 15px 5px;
    display: flex;
    flex-direction: column;
    gap: 0px;
    align-items: flex-start;
    justify-content: flex-start;
    align-self: stretch;
    flex-shrink: 0;
    position: relative;
    overflow: hidden;
}

.frame-46 {
    padding: 12px 13px 12px 13px;
    display: flex;
    flex-direction: row;
    gap: 5px;
    align-items: center;
    justify-content: center;
    flex-shrink: 0;
    height: 30px;
    position: relative;
    overflow: hidden;
}

.div4 {
    color: #000000;
    text-align: left;
    font-family: "Inter-Regular", sans-serif;
    font-size: 18px;
    line-height: 140%;
    font-weight: 400;
    position: relative;
}

.frame-47 {
    padding: 12px 13px 12px 13px;
    display: flex;
    flex-direction: row;
    gap: 5px;
    align-items: center;
    justify-content: center;
    flex-shrink: 0;
    height: 30px;
    position: relative;
    overflow: hidden;
}

.div5 {
    color: #9c9c9c;
    text-align: left;
    font-family: "Inter-Regular", sans-serif;
    font-size: 20px;
    line-height: 140%;
    font-weight: 400;
    position: relative;
}

.frame-462 {
    border-style: solid;
    border-color: #e3dfdf;
    border-width: 0px 0px 1px 0px;
    padding: 15px 5px 15px 5px;
    display: flex;
    flex-direction: column;
    gap: 0px;
    align-items: flex-start;
    justify-content: flex-start;
    align-self: stretch;
    flex-shrink: 0;
    position: relative;
    overflow: hidden;
}

.email {
    color: #9c9c9c;
    text-align: left;
    font-family: "Inter-Regular", sans-serif;
    font-size: 20px;
    line-height: 140%;
    font-weight: 400;
    position: relative;
}

.frame-472 {
    border-style: solid;
    border-color: #e3dfdf;
    border-width: 0px 0px 1px 0px;
    padding: 15px 5px 15px 5px;
    display: flex;
    flex-direction: column;
    gap: 0px;
    align-items: flex-start;
    justify-content: flex-start;
    align-self: stretch;
    flex-shrink: 0;
    position: relative;
    overflow: hidden;
}

.phone {
    color: #9c9c9c;
    text-align: left;
    font-family: "Inter-Regular", sans-serif;
    font-size: 20px;
    line-height: 140%;
    font-weight: 400;
    position: relative;
}
</style>