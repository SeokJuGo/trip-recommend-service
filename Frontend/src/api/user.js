import { localAxios } from "@/util/http-commons";

const axios = localAxios();

async function login(username, success, fail) {
  await axios.post(`/auth/login`, JSON.stringify(username)).then(success).catch(fail);
}

async function join(user, success, fail)  {
  await axios.post('/user', user).then(success).catch(fail);
};

async function findById(username, success, fail) {
  axios.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await axios.get(`/user/${username}`).then(success).catch(fail);
}

async function idCheck(username, success, fail) {
  await axios.get(`/user/idcheck/${username}`).then(success).catch(fail);
}
async function tokenRegeneration(username, success, fail) {
  axios.defaults.headers["refresh-token"] =
    sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await axios.post(`/auth/refresh`, username).then(success).catch(fail);
}

async function logout(username, success, fail) {
  await axios.get(`/auth/logout/${username}`).then(success).catch(fail);
}

async function modify(user, success, fail)  {
  await axios.put('/user', user).then(success).catch(fail);
};

async function deleteUser(username, success, fail) {
  await axios.delete(`/user/${username}`).then(success).catch(fail);
}

async function findByEmail(email, success, fail) {
  await axios.get(`/user/email/{email}?email=${email}`).then(success).catch(fail);
}
export { login, findById, tokenRegeneration, logout, modify, deleteUser ,idCheck,join,findByEmail};
