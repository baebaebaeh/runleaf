<template>
    <div>
        <nav>
            <RouterLink :to="{ name: 'gps' }">gps</RouterLink>
            <RouterLink :to="{ name: 'board'}">board</RouterLink>
            <RouterLink :to="{ name: 'joinInfo' }">회원가입</RouterLink>
            <RouterLink :to="{ name: 'runningDataView'}">runningData</RouterLink>
        </nav>
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