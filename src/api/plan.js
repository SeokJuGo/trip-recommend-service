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
};

// 게시글 수정
export const updateBoard = async (id, data) => {
    const response = await api.put(`/plan/${id}`, data);
    return response.data;
};

// 게시글 삭제
export const deleteBoard = async (id) => {
    const response = await api.delete(`/plan/${id}`);
    return response.data;
};


export const fetchBoard = async (id) => {
    const response = await api.get(`/plan/${id}`, );
    return response.data;
};
export default {
    insertBoard,updateBoard,deleteBoard,fetchBoard
};

