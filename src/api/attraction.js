import { localAxios } from "@/util/http-commons";

const axios = localAxios();

function listAttraction (param, success, fail) {
  axios.get("/attraction", { params: param }).then(success).catch(fail);
}

export {listAttraction};
