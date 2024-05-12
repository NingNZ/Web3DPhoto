<script setup>
import { ref } from 'vue';
import axios from 'axios';
import qs from "querystring"
import TheWelcome from '../components/TheWelcome.vue'
        // async btn(){
        //   const res = await Axios({url:"http://localhost:8886/getpic",method:'post',responseType:'blob'})
        //   let blob = new window.Blob([res.data]);
        //   let url = window.URL.createObjectURL(blob);
        //   this.src = url;
        // }
let src=ref("http://localhost:5213/last/1.jpg");
// function handle(){
//   let data={};
//   axios.post("http://localhost:5213/last/photo/getpic",qs.stringify(data),{responseType:'blob'})
//         .then((res)=>{
//           let blob=new window.Blob([res.data]);
//           console.log(res)
//           let url=window.URL.createObjectURL(blob);
//           src=url;
//           console.log(src)
//         })
// }
</script>

<template>
  <el-upload action="http://localhost:5213/last/photo/upload">
    <el-button>On</el-button>
  </el-upload>
      <el-button @click="handle">显示</el-button>
    <el-image :src=src></el-image>


</template>

