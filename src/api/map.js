import { localAxios } from "@/util/http-commons";

const axios = localAxios();

function listSido(success, fail) {
  axios.get(`/sido`).then(success).catch(fail);
}

function listGugun(param, success, fail) {
  axios.get(`/gugun`, { params: param }).then(success).catch(fail);
}

export { listSido, listGugun };
