<script setup>
defineProps({
    board: Object,
});
</script>

<template>
    <tbody>
        <tr>
            <td class="text-center">{{ board.id }}</td>
            <td class="text-start">
                <router-link :to="{ name: 'share-view', params: { id: board.id } }">
                    {{ board.title }}
                </router-link>
            </td>
            <td class="text-center">{{ board.author.username }}</td>
            <td class="text-center">{{ board.hit }}</td>
            <td class="text-center">{{ board.createdDate }}</td>
        </tr>
    </tbody>
</template>

<style scoped>
a {
    color: black;
    text-decoration: none;
}

td {
    vertical-align: middle;
}
</style>
