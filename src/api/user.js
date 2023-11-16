import { localAxios } from "@/util/http-commons";

const axios = localAxios();

async function login(user, success, fail) {
  await axios.post(`/auth/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(username, success, fail) {
  axios.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await axios.get(`/user/${username}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  axios.defaults.headers["refresh-token"] =
    sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await axios.post(`/auth/refresh`, user).then(success).catch(fail);
}

async function logout(username, success, fail) {
  await axios.get(`/auth/logout/${username}`).then(success).catch(fail);
}

async function modify(user, success, fail) {
  await axios.put(`/user/`, user).then(success).catch(fail);
}

async function deleteUser(username, success, fail) {
  await axios.delete(`/user/${username}`).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout, modify, deleteUser };
