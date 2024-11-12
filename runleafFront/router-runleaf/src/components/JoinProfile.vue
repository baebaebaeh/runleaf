<template>
    <div class="div">
        <div class="div2">프로필 이미지 등록</div>
        <div class="rectangle-2"></div>
        <div class="div3">다음</div>
        <img class="image-user" src="../assets/images/join/user-circle.svg" />
        <div class="user-circle-1">
            <img class="image-camera" src="../assets/images/join/icons8-camera-50.png" />
        </div>
        <div class="div4">나중에 등록하기</div>
    </div>

</template>

<script setup>

</script>

<style scoped>
.div,
.div * {
    box-sizing: border-box;
}

.div {
    background: #ffffff;
    height: 619px;
    position: relative;
    overflow: hidden;
}

.div2 {
    color: #000000;
    text-align: left;
    font-family: "Inter-SemiBold", sans-serif;
    font-size: 20px;
    line-height: 140%;
    font-weight: 600;
    position: absolute;
    left: 20px;
    top: 50px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.group-7 {
    position: absolute;
    inset: 0;
}

.rectangle-2 {
    background: #bdbdbd;
    width: 360px;
    height: 55px;
    position: absolute;
    left: calc(50% - 180px);
    bottom: 0px;
}

.div3 {
    color: #ffffff;
    text-align: center;
    font-family: "Inter-SemiBold", sans-serif;
    font-size: 16px;
    line-height: 100%;
    font-weight: 600;
    position: absolute;
    left: calc(50% - 15px);
    bottom: 20px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.image-user {
    width: 25px;
    height: 25px;
    position: absolute;
    left: 20px;
    top: 15px;
    object-fit: cover;
}

.user-circle-1 {
    width: 150px;
    height: 150px;
    position: absolute;
    left: 105px;
    top: 159px;
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

.image-camera {
    width: 21.33%;
    height: 21.33%;
    position: absolute;
    right: 74.67%;
    left: 4%;
    bottom: 4.67%;
    top: 74%;
    object-fit: cover;
}

.div4 {
    color: #adadad;
    text-align: center;
    font-family: "Inter-SemiBold", sans-serif;
    font-size: 16px;
    line-height: 100%;
    font-weight: 600;
    position: absolute;
    left: 127px;
    top: 349px;
    display: flex;
    align-items: center;
    justify-content: center;
}
</style>