import axios from "axios";

const api = axios.create({
    baseURL: import.meta.env.VITE_BACKEND_BASE_URL,
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    },
});
// 게시글 삽입
export const insertBoard = async (data) => {
    const response = await api.post(`/plan`, data);
    return response.data;
};