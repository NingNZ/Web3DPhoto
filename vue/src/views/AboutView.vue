<template>
<div>
  <el-upload style="display: inline-block; padding-right:10px;" action="http://localhost:5213/last/photo/upload" accept=".jpg" :on-success="()=>{countall();console.log(photo)}">
    <el-button>上传照片(.jpg)</el-button>
  </el-upload>
  <el-button style="padding-right: 10px;" @click="lastpic">上一张</el-button>
  <el-button style="padding-right: 10px;" @click="nextpic">下一张</el-button>
  <el-button @click="handledelete">删除照片</el-button>
  <el-button @click="GetComment">评论</el-button>  
</div>
  <div id="container">
  </div>
  <el-drawer v-model="drawer" title="评论区" :with-header="true">
    <div>
      <ul  class="far" style="overflow: auto">
     <li v-for="i in count" :key="i" class="son">{{ comment[i-1].username+" : "+comment[i-1].content }}</li>
      </ul>
    </div>
    <div><el-input  v-model="textsub" style="padding-top: 10px;" type="textarea" :row="6"></el-input>
    <el-button @click="Submit">提交</el-button></div>
  </el-drawer>
</template>

<script setup>
        import qs from 'querystring'
        import * as THREE from 'three';
        import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js';
        import { Water } from 'three/examples/jsm/objects/Water.js';
        import { Sky } from 'three/examples/jsm/objects/Sky.js';
        import { onMounted,ref} from 'vue';
        import axios from 'axios';
        import { ElMessage } from 'element-plus';
  onMounted(()=>{
    countall();
    init();
    animate();
  })
let count = ref(0);
let photoaddress='';
let photo={
  first:0,
  last:0,
  sum:0
};
const drawer = ref(false);
let nowid=1;
let container;
let camera, scene, renderer;
let controls, water, sun;
let texLoader=new THREE.TextureLoader();
let board=new THREE.Mesh();
let comment=ref([]);
let textsub=ref('')
// let left,right;
function init() {
container = document.querySelector( '#container' );
//
renderer = new THREE.WebGLRenderer();
renderer.setPixelRatio( window.devicePixelRatio );
renderer.setSize( window.innerWidth, window.innerHeight );
renderer.toneMapping = THREE.ACESFilmicToneMapping;
renderer.toneMappingExposure = 0.5;
container.appendChild( renderer.domElement );

//

scene = new THREE.Scene();


camera = new THREE.PerspectiveCamera( 75, window.innerWidth / window.innerHeight, 1, 10000 );
camera.position.set( 30, 30, 100 );

//

sun = new THREE.Vector3();

// Water

const waterGeometry = new THREE.PlaneGeometry( 10000, 10000 );

water = new Water(
  waterGeometry,
  {
    textureWidth: 512,
    textureHeight: 512,
    waterNormals: texLoader.load( '../picture/self/waternormals.jpg', function ( texture ) {

      texture.wrapS = texture.wrapT = THREE.RepeatWrapping;
    } ),
    sunDirection: new THREE.Vector3(0,0,0),
    sunColor: 0xd6ecf0,
    waterColor: 0x001e0f,
    distortionScale: 3.7,
    fog: scene.fog!==undefined
  }
);

water.rotation.x = - Math.PI / 2;

scene.add( water );

// Skybox

const sky = new Sky();
sky.scale.setScalar( 10000 );
scene.add( sky );

// const skyUniforms = sky.material.uniforms;

// skyUniforms[ 'turbidity' ].value = 10;
// skyUniforms[ 'rayleigh' ].value = 2;
// skyUniforms[ 'mieCoefficient' ].value = 0.005;
// skyUniforms[ 'mieDirectionalG' ].value = 0.8;

const parameters = {
  elevation: 2,
  azimuth: 180
};

const pmremGenerator = new THREE.PMREMGenerator( renderer );
const sceneEnv = new THREE.Scene();

let renderTarget;

function updateSun() {

  const phi = THREE.MathUtils.degToRad( 90 - parameters.elevation );
  const theta = THREE.MathUtils.degToRad( parameters.azimuth );

  sun.setFromSphericalCoords( 1, phi, theta );

  sky.material.uniforms[ 'sunPosition' ].value.copy( sun );
  water.material.uniforms[ 'sunDirection' ].value.copy( sun ).normalize();

  if ( renderTarget !== undefined ) renderTarget.dispose();

  sceneEnv.add( sky );
  renderTarget = pmremGenerator.fromScene( sceneEnv );
  scene.add( sky );

  scene.environment = renderTarget.texture;
}
updateSun();
controls = new OrbitControls( camera, renderer.domElement );
controls.maxPolarAngle = Math.PI * 0.495;
controls.target.set( 0, 10, 0 );
controls.minDistance = 30.0;
controls.maxDistance = 200.0;
controls.update();


  const geometry = new THREE.BoxGeometry(70, 70, 5);
  photoaddress="http://localhost:5213/last/"+nowid+".jpg";
  const texture =texLoader.load(photoaddress);
  let coo=texture.clone();
  const wood =texLoader.load('../picture/self/wood.jpg');
  let cubeMaterials = [
    new THREE.MeshBasicMaterial({ map:wood, side: THREE.DoubleSide }),
    new THREE.MeshBasicMaterial({ map:wood, side: THREE.DoubleSide }),
    // new THREE.MeshBasicMaterial({map:new THREE.TextureLoader().load('./statics/imgs/floor.jpg'),side:THREE.DoubleSide}),
    new THREE.MeshBasicMaterial({ map:wood, side: THREE.DoubleSide }),
    new THREE.MeshBasicMaterial({ color: 0x808080, side: THREE.DoubleSide }),
    new THREE.MeshBasicMaterial({ map:texture, side: THREE.DoubleSide }),
    new THREE.MeshBasicMaterial({ map :wood , side: THREE.DoubleSide }),
  ];
  board = new THREE.Mesh(geometry, cubeMaterials);
  board.position.setY(35);
  // console.log(board);
  scene.add(board);


}

function animate() {
requestAnimationFrame( animate );
render();
}
function render() {

const time = performance.now() * 0.001;
water.material.uniforms[ 'time' ].value += 1.0 / 60.0;
renderer.render( scene, camera );

}
function nextpic(){
  nowid++;
  console.log(photo);
  if(nowid<=photo.last){
    photoaddress="http://localhost:5213/last/"+nowid+".jpg";
    texLoader.load(photoaddress,(texture)=>{
      texture.needsUpdate=true;
      board.material[4].map.dispose();
      board.material[4].map=texture;
      });
  }
  else{
    nowid--;
    ElMessage.warning("已经是最后一张照片");
  }
}
function lastpic(){
  nowid--;
  if(nowid>=photo.first){
    photoaddress="http://localhost:5213/last/"+nowid+".jpg";
    texLoader.load(photoaddress,(texture)=>{
      texture.needsUpdate=true;
      board.material[4].map.dispose();
      board.material[4].map=texture;
      });
  }
  else{
    nowid++;
    ElMessage.warning("不能再往前翻了");
  }
}
function countall(){
  axios.get("http://localhost:5213/last/photo/countall")
  .then((res)=>{
    photo=res.data;
    console.log(photo);
  })
}
function handledelete()
{
  axios.get("http://localhost:5213/last/photo/delete",{
    params:{
      id:nowid
    }
  })
  .then((res)=>{
    if(res.data.code==200){
      // board.material[4].map.dispose();
      // ElMessage.success(res.data.msg);
      // nowid=(nowid==allid)?nowid-1:nowid;
      // photoaddress="http://localhost:5213/last/"+nowid+".jpg";
      // console.log(photoaddress)
      // console.log(nowid)
      // texLoader.load(photoaddress,(texture)=>{
      // texture.needsUpdate=true;
      // board.material[4].map=texture;
      // allid--;
      // });
      board.material[4].map.dispose();
      nowid=(nowid==photo.last)?nowid-1:nowid;
      photoaddress="http://localhost:5213/last/"+nowid+".jpg";
      console.log(photoaddress)
      console.log(nowid)
      texLoader.load(photoaddress,(texture)=>{
      texture.needsUpdate=true;
      board.material[4].map=texture;
      ElMessage.success(res.data.msg);
      })
    }else{
      ElMessage.error(res.data.msg);
    }
  })
  countall();
  console.log(photo)
}
function GetComment(){
  axios.get("http://localhost:5213/last/comment/list")
  .then((res)=>{
    comment.value=res.data;
    count.value=comment.value.length;

    // console.log(comment.value.length)
    drawer.value=true;
  })
}
function Submit(){
  if(textsub.value.length>0){
    const cc=(count.value+1);
    console.log(cc)
    let data={
      cid:cc,
      username:sessionStorage.getItem("username"),
      content:textsub.value
    }
    console.log(data)
    axios.post("http://localhost:5213/last/comment/add",qs.stringify(data))
    .then((res)=>{
      if(res.data.code==200){
        ElMessage.success(res.data.msg);
        GetComment();
        textsub.value='';
      }
    })
  }
}
</script>
<style>
#container{
  width: max-content;
  height: max-content;
}
.far{
  height: 500px;
  padding: 0;
  margin: 0;
  list-style: none;
}
.son{
  background: var(--el-color-primary-light-9);
  color: black;
  font-size: large;
  font-family:"Microsoft YaHei" !important;
  margin-top: 10px;
  padding-bottom: 5px;
  background: var(--el-color-primary-light-9);
  line-break: anywhere;

}
</style>
