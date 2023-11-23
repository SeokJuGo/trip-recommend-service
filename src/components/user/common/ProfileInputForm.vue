<script setup>
import { ref, defineEmits } from "vue";
import ProfileAPI from "@/api/profile.js";
const inputRef = ref(null);
const selectedFiles = ref([]);

// inputRef를 업데이트하는 함수
const updateInputRef = () => {
    const dataTranster = new DataTransfer();
    selectedFiles.value.forEach((file) => dataTranster.items.add(file));
    inputRef.value.files = dataTranster.files;
    emitFiles();
};

const handleFileChange = (event) => {
    for (const file of event.target.files) {
        selectedFiles.value.push(file);
    }
    updateInputRef();
};

const handleFileCancel = (index) => {
    selectedFiles.value.splice(index, 1);
    updateInputRef();
};

const formatFileSize = (sizeInBytes) => {
    // 변환 공식: 1MB = 1024KB, 1KB = 1024Bytes
    let i = 0;
    let fileSize = sizeInBytes;
    const sizes = ["Bytes", "KB", "MB", "GB"];
    while (fileSize > 1024 && i < sizes.length - 1) {
        fileSize /= 1024;
        i++;
    }

    return fileSize.toFixed(2) + " " + sizes[i];
};

const emit = defineEmits(["on-change-files"]);
const emitFiles = () => {
    emit("on-change-files", selectedFiles.value);
};



const files = ref([]);
</script>

<template>
        <input
            ref="inputRef"
            @change="handleFileChange"
            class="form-control rounded-0"
            type="file"
            id="formFileMultiple"
            multiple
        />
</template>

<style scoped>
</style>
