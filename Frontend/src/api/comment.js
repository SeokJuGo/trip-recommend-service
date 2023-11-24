import axios from "axios";

const api = axios.create({
    baseURL: import.meta.env.VITE_BACKEND_BASE_URL,
    headers: {
        "Content-Type": "application/json;charset=utf-8",
    },
});

// 게시글 댓글 목록 조회
export const fetchComments = async (boardId) => {
    const response = await api.get(`/comment?boardId=${boardId}`);
    return response.data;
};

// 댓글 삽입
export const insertComment = async (data) => {
    const response = await api.post(`/comment`, data);
    return response.data;
};

// 댓글 수정
export const updateComment = async (id, data) => {
    const response = await api.put(`/comment/${id}`, data);
    return response.data;
};

// 댓글 삭제
export const deleteComment = async (id) => {
    const response = await api.delete(`/comment/${id}`);
    return response.data;
};

export default {
    fetchComments,
    insertComment,
    updateComment,
    deleteComment,
};
