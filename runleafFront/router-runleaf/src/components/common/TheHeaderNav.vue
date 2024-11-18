<template>
    <div>
        <nav>
            <RouterLink :to="{ name: 'gps' }">gps</RouterLink>
            <RouterLink :to="{ name: 'board' }">board</RouterLink>
            <RouterLink :to="{ name: 'joinInfo' }">회원가입</RouterLink>
            <RouterLink :to="{ name: 'runningDataView' }">runningData</RouterLink>
            <RouterLink :to="{ name: 'menu' }">menu</RouterLink>
        </nav>
    </div>
    <div class="nav">
        <RouterLink class="menu-bar" :to="{ name: 'menu' }">
            <img class="menu-img" :src="`/api/icons/menu-img.svg`" />
        </RouterLink>
        <RouterLink class="logo-bar" :to="{ name: 'home' }">
            <img class="feather-img" :src="`/api/icons/feather-img.svg`" />
        </RouterLink>
        <RouterLink class="mypage-bar" :to="{ name: 'home' }">
            <img class="smile-img" :src="`/api/icons/smile-img.svg`" />
        </RouterLink>
    </div>
</template>

<script setup>

</script>

<style scoped>
nav {
    text-align: center;
}

nav a.router-link-exact-active {
    color: #42b983
}
</style>