<script setup>
import dayjs from "dayjs";
import { LikeFilled, LikeOutlined, DislikeFilled, DislikeOutlined } from "@ant-design/icons-vue";
import { defineComponent, ref } from "vue";
import relativeTime from "dayjs/plugin/relativeTime";
dayjs.extend(relativeTime);

const likes = ref(0);
const dislikes = ref(0);
const action = ref();
const like = () => {
    likes.value = 1;
    dislikes.value = 0;
    action.value = "liked";
};

const dislike = () => {
    likes.value = 0;
    dislikes.value = 1;
    action.value = "disliked";
};

defineProps({
    comment: Object,
});
</script>

<template>
    <!-- <div class="border border-1 border-black-50 shadow px-3 py-2">
        <div class="d-flex justify-content-between text-black-50">
            <div class="fst-normal">
                <span>작성자&nbsp;</span>
                <small>2023-11-16 10:24:25</small>
            </div>
            <div class="fst-italic">
                <small>수정</small>
                <small>&nbsp;/&nbsp;</small>
                <small>삭제</small>
            </div>
        </div>
        <div>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Sit reiciendis
            nulla, dicta sunt doloribus nobis culpa accusantium provident laboriosam.
            Dolorum aperiam libero quas aut, qui facere commodi ipsa. Fuga,
            consequuntur. Debitis corporis consequatur neque reprehenderit delectus
            culpa ducimus nemo, ad quaerat quisquam omnis eius, suscipit rem repellendus
            quo ab provident ratione non, commodi dolores ut incidunt aliquid quam
            aperiam! Unde? Atque deleniti cupiditate consectetur, fugiat neque suscipit
            in placeat omnis molestias vel animi quas non autem commodi quidem dolor
            eligendi error. Quam nulla dolorum possimus impedit. Velit corporis eveniet
            autem. Omnis et aut culpa. Ducimus perspiciatis soluta vel molestias, minus
            eum quos aut voluptatum architecto aliquid inventore neque eligendi
            voluptatem assumenda ullam reprehenderit facere est totam temporibus dolorum
            iusto odio! Omnis vel porro molestiae praesentium laboriosam consequatur
            nemo unde eum voluptas animi, veritatis impedit, tempore culpa minus. Facere
            vero, ipsa fugiat, natus beatae, iste eaque explicabo exercitationem sint
            itaque consequuntur!
        </div>
    </div> -->
    <a-comment class="ps-3 mb-2">
        <template #actions>
            <span key="comment-basic-like">
                <a-tooltip title="Like">
                    <template v-if="action === 'liked'">
                        <LikeFilled @click="like" />
                    </template>
                    <template v-else>
                        <LikeOutlined @click="like" />
                    </template>
                </a-tooltip>
                <span style="padding-left: 8px; cursor: auto">
                    {{ likes }}
                </span>
            </span>
            <span key="comment-basic-dislike">
                <a-tooltip title="Dislike">
                    <template v-if="action === 'disliked'">
                        <DislikeFilled @click="dislike" />
                    </template>
                    <template v-else>
                        <DislikeOutlined @click="dislike" />
                    </template>
                </a-tooltip>
                <span style="padding-left: 8px; cursor: auto">
                    {{ dislikes }}
                </span>
            </span>
            <span key="comment-basic-reply-to">Reply to</span>
        </template>
        <template #author
            ><a>{{ comment.author.username }}</a></template
        >
        <template #avatar>
            <a-avatar src="/attach/image/0" alt="Han Solo" />
        </template>
        <template #content>
            <p>
                {{ comment.content }}
            </p>
        </template>
        <template #datetime>
            <a-tooltip :title="dayjs().format('YYYY-MM-DD HH:mm:ss')">
                <span>{{ dayjs().fromNow(comment.createdDate) }}</span>
            </a-tooltip>
        </template>
    </a-comment>
</template>

<style scoped></style>
