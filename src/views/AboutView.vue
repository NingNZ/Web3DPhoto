<template>
  <div id="container">
  </div>
  <h1></h1>
</template>

<script setup>
        import * as THREE from 'three';
        import { OrbitControls } from 'three/examples/jsm/controls/OrbitControls.js';
        import { Water } from 'three/examples/jsm/objects/Water.js';
        import { Sky } from 'three/examples/jsm/objects/Sky.js';
        import { onMounted} from 'vue';
  onMounted(()=>{
    init();
    animate();
  })
let container;
let camera, scene, renderer;
let controls, water, sun;
let texLoader;
function init() {
container = document.querySelector( '#container' );
//
texLoader= new THREE.TextureLoader();
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
  // let mesh1 = new THREE.Mesh(geometry,material);
  // let b=new THREE.Vector3(-1,0,0);
  // mesh1.position.set;
  // mesh1.rotateY(-1*(b.angleTo(mesh1.position.negate())));
  // console.log(b.angleTo(mesh1.position.negate()));
  // scene.add(mesh2);
    // const uv = new Float32Array([
    // 0, 0, 1, 0, 1, 1, 0, 1])
    // geometry.setAttribute("uv",new THREE.BufferAttribute(uv,2))
  const geometry = new THREE.BoxGeometry(70, 70, 5);
  const uv = new Float32Array([
  0, 0, 1, 0, 1, 1, 0, 1,
  0, 0, 1, 0, 1, 1, 0, 1,
  0, 0, 1, 0, 1, 1, 0, 1,
  0, 0, 1, 0, 1, 1, 0, 1,
  0, 0, 1, 0, 1, 1, 0, 1,
  0, 0, 1, 0, 1, 1, 0, 1,]);
    // geometry.setAttribute("uv",new THREE.BufferAttribute(uv,2))
  const texture=texLoader.load('../picture/R-C.jpg');
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
  let board = new THREE.Mesh(geometry, cubeMaterials);
  board.position.setY(35);
  console.log(board);
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
</script>
<script>
</script> 
<style>
#container{
  width: max-content;
  height: max-content;

}
</style>
