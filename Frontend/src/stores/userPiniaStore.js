import { defineStore } from "pinia";
import jwtDecode from "jwt-decode";
import router from "@/router";

import {
  login,
  findById,
  tokenRegeneration,
  logout,
  modify,
  deleteUser,
} from "@/api/user";

export const userStore = defineStore("userPiniaStore", {
  persist: {
    storage: sessionStorage, 
  },
  state: () => ({
    isLogin: false,
    isLoginError: false,
    userInfo: null,
    isValidToken: false,
  }),
  getters: {
    checkLogin: (state) => {
      return state.isLogin;
    },
    checkUserInfo: (state) => {
      return state.userInfo;
    },
    checkToken: (state) => {
      return state.isValidToken;
    },
  },
  actions: {
    async userConfirm(user) {
      await login(
        user,
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            // console.log("login success token created!!!! >> ", accessToken, refreshToken);
            this.isLogin = true;
            this.isLoginError = false;
            this.isValidToken = true;
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          } else {
            this.isLogin = false;
            this.isLoginError = true;
            this.isValidToken = false;
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async getUserInfo(token) {
      let decodeToken = jwtDecode(token);
      console.log("2. getUserInfo() decodeToken :: ", decodeToken);
      await findById(
        decodeToken.username,
        ({ data }) => {
          if (data.message === "success") {
            this.userInfo = data.userInfo;
            this.isLogin = true;
            this.isLoginError = false;
            this.isValidToken = true;
            // console.log("3. getUserInfo data >> ", data);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.log(
            "getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ",
            error.response.status,
          );
          this.isValidToken = false;
          this.tokenRegeneration();
        },
      );
    },
    async tokenRegeneration() {
      console.log(
        "토큰 재발급 >> 기존 토큰 정보 : {}",
        sessionStorage.getItem("access-token"),
      );
      await tokenRegeneration(
        JSON.stringify(this.userInfo),
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
            sessionStorage.setItem("access-token", accessToken);
            this.isValidToken = true;
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            console.log("갱신 실패");
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              this.userInfo.username,
              ({ data }) => {
                if (data.message === "success") {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("로그인 만료! 다시 로그인해 주세요.");
                this.isLogin = false;
                this.userInfo = null;
                this.isValidToken = false;
                router.push({ name: "login" });
              },
              (error) => {
                console.log(error);
                this.isLogin = false;
                this.userInfo = null;
              },
            );
          }
        },
      );
    },
    async userLogout(username) {
      await logout(
        username,
        ({ data }) => {
          if (data.message === "success") {
            this.isLogin = false;
            this.userInfo = null;
            this.isValidToken = false;
            sessionStorage.removeItem('access-token');
            sessionStorage.removeItem('refresh-token');
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        (error) => {
          console.log(error);
        },
      );
    },
    async modifyUserInfo(user) {
      // let decodeToken = jwtDecode(token);
      // console.log("회원정보수정중2");
      // console.log("5. modifyUserInfo() decodeToken :: ", decodeToken);
      console.log(user);
      await modify(
        user,
        ({ data }) => {
          if (data.message === "success") {
            this.userInfo = data.userInfo;
            // console.log("3. getUserInfo data >> ", data);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.log(
            "modifyUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ",
            error.response.status
          );
          this.isValidToken = false;
          this.tokenRegeneration();
        },
      );
    },
    async deleteUser(username) {
      // let decodeToken = jwtDecode(token);
      // console.log("회원정보수정중2");
      // console.log("5. modifyUserInfo() decodeToken :: ", decodeToken);
      console.log(username);
      await deleteUser(
        username,
        ({ data }) => {
          if (data.message === "success") {
            this.isLogin = false;
            this.userInfo = null;
            this.isValidToken = false;
            // console.log("3. getUserInfo data >> ", data);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.log(
            "modifyUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ",
            error.response.status
          );
          this.isValidToken = false;
          this.tokenRegeneration();
        },
      );
    },
  },
});
