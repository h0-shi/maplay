<template>
  <div class="map__wrapper">
      <p>OSM MAP PAGE</p>
      // 1. 지도 렌더 타겟 div 선언
      <div class="map" ref="map"></div>
  </div>
</template>

<script>
import OlLayerTile from 'ol/layer/Tile.js'
import OlView from 'ol/View.js'
import OlMap from 'ol/Map.js'
import OSM from 'ol/source/OSM'
import { fromLonLat } from 'ol/proj.js'

import { ref, onMounted } from 'vue'

export default {
name: 'TestPage',

setup() {
      // 2. target element 변수에 담기
      const map = ref(null);
      // 3. Map 객체 담을 변수 선언
      let olMap;
      // 4. 지도 중심좌표
      const center = fromLonLat([126.9251405697578, 37.53033241217628])
      // 5. OlMap 객체 생성 및 타겟 엘리먼트에 부착
      onMounted(() => {
          olMap = new OlMap({
              target: map.value,
              layers: [new OlLayerTile({ source: new OSM() })],
              view: new OlView({
                  center: center, // 여의도 좌표
                  zoom: 17,
              }),
          })          
      })
      // 6. onMounted에서 접근하기 위해 return
      return { olMap }
},
}
</script>

<style scoped>
.map__wrapper {
width: 100%;
height: 100%;
display: flex;
flex-direction: column;
align-items: center;
}
.map {
width: 100%;
height: 100%;
}
</style>