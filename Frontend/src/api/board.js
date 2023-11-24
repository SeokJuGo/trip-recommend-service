import axios from "axios";

const api = axios.create({
    baseURL: import.meta.env.VITE_BACKEND_BASE_URL,
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    },
});

// 게시판 목록 조회
export const fetchBoards = async (data) => {
    const { sortBy, orderBy, pageNum, pageSize, searchType, searchQuery, boardTypeId } = data;
    const params = { sortBy, orderBy, pageNum, pageSize, searchType, searchQuery, boardTypeId };
    const response = await api.get(`/board`, { params });
    return response.data;
};

// 게시글 조회
export const fetchBoard = async (id) => {
    const response = await api.get(`/board/${id}`);
    return response.data;
};

// 게시글 삽입
export const insertBoard = async (data) => {
    const response = await api.post(`/board`, data);
    return response.data;
};

// 게시글 수정
export const updateBoard = async (id, data) => {
    const response = await api.put(`/board/${id}`, data);
    return response.data;
};

// 게시글 삭제
export const deleteBoard = async (id) => {
    const response = await api.delete(`/board/${id}`);
    return response.data;
};

export default {
    fetchBoards,
    fetchBoard,
    insertBoard,
    updateBoard,
    deleteBoard,
};
