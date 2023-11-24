<template>
  <div class="padding">
    <div class="col-md-8">
        <!-- Column -->
        <div class="card"> <img class="card-img-top" src="../../../assets/img/default_background.jpg" alt="Card image cap">
            <div class="card-body little-profile text-center">
                
	            
                <div class="pro-img" @click="profileChange"><img src="../../../assets/img/default_user.jpg" alt="user"></div>
                
                <h3 class="m-b-0">{{userDetail.username}}</h3>
                <div></div>
                <p>{{userDetail.nickname}}</p><button @click="modify" class="m-t-10 waves-effect waves-dark btn btn-primary btn-md btn-rounded" data-abc="true">Edit</button> 
                
                <div class="row text-center m-t-20">
                    <div class="col-lg-4 col-md-4 m-t-20">
                        <h3 class="m-b-0 font-light">10</h3><small>게시물</small>
                    </div>
                    <div class="col-lg-4 col-md-4 m-t-20">
                        <h3 class="m-b-0 font-light">7</h3><small>계획</small>
                    </div>
                    <div class="col-lg-4 col-md-4 m-t-20">
                        <h3 class="m-b-0 font-light">5</h3><small>hotplace</small>
                    </div>
                </div>
				<div class="d-flex justify-content-between align-items-center mb-4">
              <p class="lead fw-normal mb-0">Recent photos</p>
              <p class="mb-0"><a href="#!" class="text-muted">Show all</a></p>
            </div>
            <div class="row g-2">
              <div class="col mb-2">
                <img src="https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(112).webp"
                  alt="image 1" class="w-100 rounded-3">
              </div>
              <div class="col mb-2">
                <img src="https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(107).webp"
                  alt="image 1" class="w-100 rounded-3">
              </div>
            </div>
            <div class="row g-2">
              <div class="col">
                <img src="https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(108).webp"
                  alt="image 1" class="w-100 rounded-3">
              </div>
              <div class="col">
                <img src="https://mdbcdn.b-cdn.net/img/Photos/Lightbox/Original/img%20(114).webp"
                  alt="image 1" class="w-100 rounded-3">
              </div>
			  </div>
            </div>
        </div>
        
        
    </div>
</div>


</template>
<script setup>
import {onMounted,ref,reactive} from 'vue';
import {findById} from "@/api/user";
import { userStore} from "@/stores/userPiniaStore"
import { useRouter, useRoute } from "vue-router";
import ProfileAPI from "@/api/profile.js";
const store = userStore();
const username = ref(store.userInfo.username);
const userDetail = ref("");
const isProfileOption = ref(false);
const router = useRouter();

const modify = ()=>router.push({ name: "user-update" }); 

  onMounted(() => {
    findById(
    username.value,
    ({ data }) => {
      userDetail.value=data.userInfo;
    },
    (err) => {
      console.log(err);
    }

    
  );
  
  fetchFiles();
});

const cancleOption = () => {
    isProfileOption.value=false;
}
const profileChange = (event) =>{
    isProfileOption.value=true;
    this.refs.images.click
}



// Fetch Files

const files = ref([]);
const fetchFiles = async () => {
    await ProfileAPI.fetchFiles(store.userInfo.id)
        .then((response) => {
            files.value = response;
            console.log(response);
        })
        .catch((error) => {
            console.log("fetchFiles() Error >> ", error);
        });
};
const formatFileSize = (sizeInBytes) => {
    // 변환 공식: 1MB = 1024KB, 1KB = 1024Bytes
    let i = 0;
    let fileSize = sizeInBytes;
    const sizes = ["Bytes", "KB", "MB", "GB"];
    while (fileSize > 1024 && i < sizes.length - 1) {
        fileSize /= 1024;
        i++;
    }

    return fileSize.toFixed(2) + " " + sizes[i];
};

</script>
<style scoped>

.pro-img {    filter: brightness(1);} 
.pro-img:hover {    filter: brightness(0.5);}

body {
    background-color: #000000
}

.padding {
    padding: 3rem !important;
    margin-left: 200px
}

.card-img-top{
    height:300px;
}

.card-no-border .card {
    border-color: #d7dfe3;
    border-radius: 4px;
    margin-bottom: 30px;
    -webkit-box-shadow: 0px 5px 20px rgba(0, 0, 0, 0.05);
    box-shadow: 0px 5px 20px rgba(0, 0, 0, 0.05)
}

.card-body {
    -ms-flex: 1 1 auto;
    flex: 1 1 auto;
    padding: 1.25rem
}

.pro-img {
    margin-top: -80px;
    margin-bottom: 20px
}

.little-profile .pro-img img {
    width: 128px;
    height: 128px;
    -webkit-box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
    box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
    border-radius: 100%
}

html body .m-b-0 {
    margin-bottom: 0px
}

h3 {
    line-height: 30px;
    font-size: 21px
}

.btn-rounded.btn-md {
    padding: 12px 35px;
    font-size: 16px
}

html body .m-t-10 {
    margin-top: 10px
}

.btn-primary,
.btn-primary.disabled {
    background: #7460ee;
    border: 1px solid #7460ee;
    -webkit-box-shadow: 0 2px 2px 0 rgba(116, 96, 238, 0.14), 0 3px 1px -2px rgba(116, 96, 238, 0.2), 0 1px 5px 0 rgba(116, 96, 238, 0.12);
    box-shadow: 0 2px 2px 0 rgba(116, 96, 238, 0.14), 0 3px 1px -2px rgba(116, 96, 238, 0.2), 0 1px 5px 0 rgba(116, 96, 238, 0.12);
    -webkit-transition: 0.2s ease-in;
    -o-transition: 0.2s ease-in;
    transition: 0.2s ease-in
}

.btn-rounded {
    border-radius: 60px;
    padding: 7px 18px
}

.m-t-20 {
    margin-top: 20px
}

.text-center {
    text-align: center !important
}

h1,
h2,
h3,
h4,
h5,
h6 {
    color: #455a64;
    font-family: "Poppins", sans-serif;
    font-weight: 400
}

p {
    margin-top: 0;
    margin-bottom: 1rem
}
</style>
