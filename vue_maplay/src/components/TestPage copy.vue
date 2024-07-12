<template>
  <div class="wrapper">
    <div class="side-nav">
      <v-btn
        dark
        depressed
        @click="getCurrentPosition()"
      > 위치 정보 확인</v-btn>
      <div>
        {{ isPostionReady ? 'yes' : 'no'}}
        <hr>
        {{  this.positionObj.longitude }} / {{ this.positionObj.latitude }}
        <hr>
        <v-btn
          @click="transferLonlat(this.positionObj.longitude, this.positionObj.latitude)"
        >
          lonlet 치환
        </v-btn>
        {{  this.lonlet }}
      </div>
    </div>
    <div class="main-map" ref="map">
    </div>
  </div>
</template>

<script>
import OlLayerTile from 'ol/layer/Tile.js';
import OlView from 'ol/View.js';
import OlMap from 'ol/Map.js';
import OSM from 'ol/source/OSM';

import {fromLonLat} from 'ol/proj.js'
/*
import Feature from 'ol';
import { Point } from 'ol/geom';

import VectorSource from 'ol/source/Vector';
import {Vector as VectorLayer} from 'ol/layer/Vector';
import {Circle as CircleStyle, Fill, Stroke, Style} from 'ol/style.js';
*/
export default {
  name: 'MainMap',
  data() {
    return {
      olMap: undefined,
      positionObj : {},
      isPostionReady : false,
      lonlet : [127.07000046194874, 37.06499508020727]
    }
  },
  mounted() {
    this.olMap = new OlMap({
      target: this.$refs.map,
      layers: [
          new OlLayerTile({
            source: new OSM()
          })
      ],
      view: new OlView({
        center: fromLonLat([127.07000046194874, 37.06499508020727]), // 경기도 성남
        zoom: 18
      })
    })
  },
  methods: {
    getCurrentPosition(){
      if(!navigator.geolocation) {
        this.setAlert('위치 정보 찾을 수 없음 1')
      } else {
        navigator.geolocation.getCurrentPosition(this.getPositionValue, this.geolocationError)
      }
    },
    getPositionValue (val) {
      this.positionObj.latitude = val.coords.latitude
      this.positionObj.longitude = val.coords.longitude
      this.isPostionReady = true
      this.setAlert('주소 정보 확인')
    },
    geolocationError() {
      this.setAlert('위치정보 찾을 수 없음 2')
    },
    setAlert(text) {
      alert(text)
    },

    transferLonlat(long,lat) {
      alert('치환 작동')
      this.lonlet = fromLonLat([long, lat])
      console.log('-----');
      console.log(this.olMap);
/*
      const pointFeature = new Feature({ 
        geometry : new Point(fromLonLat(this.lonlet)),
        type : "Ponit"
      })

      const styles = {
        Point : new Style ({
          image: new CircleStyle({
            radius: 10,
            fill : new Fill({ color: [160, 51, 255, 1] }),            
            stroke : new Stroke({
              color : [160, 51, 255, 1],
              width : 0.1,
            })
          })
        })
      }

      const vectorSource = new VectorSource({
        features : pointFeature
      })

      const vectorLayer = new VectorLayer({
        source : vectorSource,
        style: (feature) => styles[feature.getProperties().type]
      })

      this.map.add(vectorLayer)
*/
    }
  }
}
</script>

<style scoped>
.main-map {
  width: 100%;
  height: 100%;
}
.wrapper{
  width: 100%;
  height: 100%;
}
.side-nav{
  width: 200px;
  height: 100%;
  position: absolute;
  background-color: white;
  z-index: 999;
}
</style>