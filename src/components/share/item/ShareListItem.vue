<script setup>
defineProps({ board: Object });
</script>

<template>
				<tbody>
						<tr class="text-center" >
							<th scope="row">{{ board.userName }}</th> <!--id-->
							
							<td class="text-start">
								<router-link :to="{name:'share-view',params:{id:board.id}}"
								class="boards-title link-dark" data-no=""
								style="text-decoration: none">{{ board.title }}  </router-link></td>
							<td>{{ board.userId }}</td> <!--id-->
							<td>{{ board.userEmail }}</td> <!--hit-->
							<td>{{ board.createdDate }}</td> <!--createddate-->
						</tr>
					</tbody>
</template>

<style scoped>
a {
  text-decoration: none;
}
</style>
