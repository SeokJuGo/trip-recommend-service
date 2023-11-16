import { localAxios } from "@/util/http-commons";

const axios = localAxios();

async function getUsers({ search }, success, fail) {
  await axios
    .get(`/admin/users`, {
      params: { pgno: search.pgno, key: search.key, word: search.word },
    })
    .then(success)
    .catch(fail);
}

export { getUsers };
