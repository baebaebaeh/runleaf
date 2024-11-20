<template>
  <div id="map"></div>
</template>

<script setup>
const props = defineProps({
  coodinate: Object,
});
import { ref, onMounted } from "vue";
  onMounted(() => {
    console.log("네이버지도 마운트됨")
    // 네이버 지도 API 로드
    const script = document.createElement("script");
    script.src =
      "https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=89rquykz7t";
    script.async = true;
    script.defer = true;
    document.head.appendChild(script);
    script.onload = () => {
      // 네이버 지도 생성
      const map = new window.naver.maps.Map("map", {
        center: new window.naver.maps.LatLng(props.coodinate[0].latitude, props.coodinate[0].longitude),
        zoom: 16,
      });
      const tempPath = ref([])
      props.coodinate.forEach((element) => {
        tempPath.value.push(new window.naver.maps.LatLng(element.latitude, element.longitude))
      });
      // 폴리라인 생성
      const polyline = new window.naver.maps.Polyline({
        map: map,
        path: tempPath.value,
        clickable: true,
        strokeColor: "#5347AA",
        strokeStyle: "shortdash",
        strokeOpacity: 0.5,
        strokeWeight: 1,
      });

      // 이벤트 리스너 추가
      window.naver.maps.Event.addListener(polyline, "click", () => {
        polyline.setOptions({
          strokeColor: "#E51D1A",
          strokeStyle: "solid",
          strokeOpacity: 1,
        });
      });

      window.naver.maps.Event.addListener(polyline, "mouseout", () => {
        polyline.setOptions({
          strokeColor: "#5347AA",
          strokeStyle: "longdash",
          strokeOpacity: 0.5,
        });
      });
    };
  });
</script>

<style scoped></style>