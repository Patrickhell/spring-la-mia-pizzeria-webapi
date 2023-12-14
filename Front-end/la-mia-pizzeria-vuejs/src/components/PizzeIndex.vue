<template>
    <ul>
        <li  v-for="pizza in pizze" :key="pizza.id" @click="$emit('openPizza', pizza.id)" >
            <p class="list-elem">
                {{ pizza.name }}
            </p>
        </li>
    </ul>
    
</template>

<script setup>
import { defineProps } from 'vue';

const props = defineProps({
    pizze: {
        type: Array,
        required: true
    }
});

</script>

<style scoped>
ul{
    list-style-type: none;
}
.list-elem {
    cursor: pointer;
    text-decoration: underline;
}
</style>
