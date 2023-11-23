import axios from "axios";

const api = axios.create({
    baseURL: import.meta.env.VITE_BACKEND_BASE_URL,
    headers: {
        "Content-Type": "multipart/form-data",
    },
});

// 게시글 첨부파일 목록 조회
export const fetchFiles = async (userId) => {
    const response = await api.get(`/profile/${userId}`);
    return response.data;
};

// 첨부파일 업로드
export const uploadFiles = async (userId, files) => {
    const formData = new FormData();
    files.forEach((file) => formData.append("files", file));
    const response = await api.post(`/profile/upload/${userId}`, formData);
};

// 첨부파일 다운로드
export const downloadFile = async (id) => {
    const response = await axios.get(`/profile/download/${id}`);
    return response;
};

// 이미지 다운로드
export const downloadImage = async (id) => {
    const response = await axios.get(`/profile/image/${id}`);
    return response;
};

// 첨부파일 수정

// 첨부파일 삭제
export const deleteFile = async (id) => {
    const response = await api.delete(`/profile/delete/${id}`);
    return response.data;
};

export default {
    fetchFiles,
    uploadFiles,
    downloadFile,
    downloadImage,
    deleteFile,
};
