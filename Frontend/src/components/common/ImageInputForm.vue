<script setup>
import { ref, defineEmits } from "vue";

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
</script>

<template>
    <div class="mb-1">
        <label for="formFileMultiple" class="form-label">첨부파일 업로드</label>
        <input
            ref="inputRef"
            @change="handleFileChange"
            class="form-control rounded-0"
            type="file"
            accept="image/*"
            id="formFileMultiple"
            multiple
        />
    </div>
    <div class="border border-1 border-secondary-subtle mb-3" style="min-height: 100px">
        <div
            class="d-flex justify-content-start px-4"
            v-for="(file, index) in selectedFiles"
            :key="index"
        >
            <div class="me-auto">
                {{ file.name }}
            </div>
            <div class="ms-auto">
                {{ formatFileSize(file.size) }}
            </div>
            <div class="ps-2">
                <a @click="handleFileCancel(index)">
                    <i class="bi bi-file-earmark-x text-danger"></i>
                </a>
            </div>
        </div>
    </div>
</template>

<style scoped>
a {
    text-decoration: none;
}
</style>
