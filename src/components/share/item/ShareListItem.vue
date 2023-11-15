<script setup>
defineProps({
    board: Object,
});
</script>

<template>
    <tbody>
        <tr class="text-center">
            <!-- id -->
            <td class="text-center">{{ board.id }}</td>
            <!-- title -->
            <td class="text-start">
                <router-link
                    :to="{ name: 'share-view', params: { id: board.id } }"
                    class="boards-title link-dark"
                    data-no=""
                    style="text-decoration: none"
                    >{{ board.title }}
                </router-link>
            </td>
            <!-- author -->
            <td>{{ board.author.username }}</td>
            <!--hit-->
            <td>DB 수정작업 필요</td>
            <!-- createdDate -->
            <td>{{ board.createdDate }}</td>
        </tr>
    </tbody>
</template>

<style scoped>
a {
    text-decoration: none;
}
</style>
