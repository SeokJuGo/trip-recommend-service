import { localAxios } from "@/util/http-commons";

const attraction = localAxios();

function listAttraction (param, success, fail) {
  attraction.get("/attraction", { params: param }).then(success).catch(fail);
}

export {listAttraction};
