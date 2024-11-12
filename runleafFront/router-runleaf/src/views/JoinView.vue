<template>
    <form action="회원가입_처리_페이지_URL" method="POST" enctype="multipart/form-data">
        <div class="div">
            <div class="div2">회원가입</div>

            <label for="username" class="div3">아이디*</label>
            <input type="text" id="username" name="username" class="rectangle-1" placeholder="영문, 숫자 조합 6~12자" required>

            <label for="password" class="div4">비밀번호*</label>
            <input type="password" id="password" name="password" class="rectangle-12" placeholder="영문, 숫자 조합 8~16자"
                required>

            <label for="confirm_password" class="div5">비밀번호 확인*</label>
            <input type="password" id="confirm_password" name="confirm_password" class="rectangle-13"
                placeholder="비밀번호를 한 번 더 입력해주세요." required>

            <label for="nickname" class="div7">닉네임*</label>
            <input type="text" id="nickname" name="nickname" class="rectangle-14" placeholder="영문, 숫자 조합 8~16자"
                required>

            <label for="email" class="div8">이메일*</label>
            <input type="email" id="email" name="email" class="rectangle-15" placeholder="예) abc@ssafy.com" required>

            <label for="phone" class="div9">연락처*</label>
            <input type="tel" id="phone" name="phone" class="rectangle-16" placeholder="‘-’을 제외하고 입력해주세요." required>

            <button type="submit" class="rectangle-2">다음</button>
            <img class="image-1" src="../assets/images/icons8-arrow-30.png" alt="다음 아이콘">
        </div>
    </form>
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
    height: 779px;
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

.group-1 {
    position: absolute;
    inset: 0;
}

.div3 {
    color: #000000;
    text-align: left;
    font-family: "Inter-Regular", sans-serif;
    font-size: 14px;
    line-height: 150%;
    font-weight: 400;
    position: absolute;
    left: 20px;
    top: 102px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.rectangle-1 {
    background: #ffffff;
    border-radius: 5px;
    border-style: solid;
    border-color: #aeaeae;
    border-width: 1px;
    width: 320px;
    height: 50px;
    position: absolute;
    left: 20px;
    top: 129px;
}

.group-2 {
    position: absolute;
    inset: 0;
}

.div4 {
    color: #000000;
    text-align: left;
    font-family: "Inter-Regular", sans-serif;
    font-size: 14px;
    line-height: 150%;
    font-weight: 400;
    position: absolute;
    left: 20px;
    top: 196px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.rectangle-12 {
    background: #ffffff;
    border-radius: 5px;
    border-style: solid;
    border-color: #aeaeae;
    border-width: 1px;
    width: 320px;
    height: 50px;
    position: absolute;
    left: 20px;
    top: 223px;
}

.group-3 {
    position: absolute;
    inset: 0;
}

.div5 {
    color: #000000;
    text-align: left;
    font-family: "Inter-Regular", sans-serif;
    font-size: 14px;
    line-height: 150%;
    font-weight: 400;
    position: absolute;
    left: 20px;
    top: 290px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.rectangle-13 {
    background: #ffffff;
    border-radius: 5px;
    border-style: solid;
    border-color: #aeaeae;
    border-width: 1px;
    width: 320px;
    height: 50px;
    position: absolute;
    left: 20px;
    top: 317px;
}

.group-4 {
    position: absolute;
    inset: 0;
}

.div7 {
    color: #000000;
    text-align: left;
    font-family: "Inter-Regular", sans-serif;
    font-size: 14px;
    line-height: 150%;
    font-weight: 400;
    position: absolute;
    left: 20px;
    top: 384px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.rectangle-14 {
    background: #ffffff;
    border-radius: 5px;
    border-style: solid;
    border-color: #aeaeae;
    border-width: 1px;
    width: 320px;
    height: 50px;
    position: absolute;
    left: 20px;
    top: 411px;
}

.group-5 {
    position: absolute;
    inset: 0;
}

.div8 {
    color: #000000;
    text-align: left;
    font-family: "Inter-Regular", sans-serif;
    font-size: 14px;
    line-height: 150%;
    font-weight: 400;
    position: absolute;
    left: 20px;
    top: 478px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.rectangle-15 {
    background: #ffffff;
    border-radius: 5px;
    border-style: solid;
    border-color: #aeaeae;
    border-width: 1px;
    width: 320px;
    height: 50px;
    position: absolute;
    left: 20px;
    top: 505px;
}

.group-6 {
    position: absolute;
    inset: 0;
}

.div9 {
    color: #000000;
    text-align: left;
    font-family: "Inter-Regular", sans-serif;
    font-size: 14px;
    line-height: 150%;
    font-weight: 400;
    position: absolute;
    left: 20px;
    top: 572px;
    display: flex;
    align-items: center;
    justify-content: flex-start;
}

.rectangle-16 {
    background: #ffffff;
    border-radius: 5px;
    border-style: solid;
    border-color: #aeaeae;
    border-width: 1px;
    width: 320px;
    height: 50px;
    position: absolute;
    left: 20px;
    top: 599px;
}

.group-7 {
    position: absolute;
    inset: 0;
}

.rectangle-2 {
    background: #c5c5c5;
    width: 360px;
    height: 55px;
    position: absolute;
    left: calc(50% - 180px);
    bottom: 0px;
}

.div11 {
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

.image-1 {
    width: 25px;
    height: 25px;
    position: absolute;
    left: 20px;
    top: 15px;
    object-fit: cover;
}
</style>