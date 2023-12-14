<template>
  <div>
    <div>
        <p>
            Single pizza : {{ singlePizza.name }}
        </p>
        <p>Price: {{ singlePizza.price }}</p>
        <span>{{ singlePizza.description }}</span>
        <br>
        {{ pizza.image }}
        <img :src="singlePizza.foto" alt="pizza image" >
    </div>

  </div>
    
</template>

<script setup>

import { onMounted, ref } from 'vue';
import axios from 'axios';

const props = defineProps ({
    pizza: {
        type: Object,
        required:true
    }
});
const singlePizza = ref(props.pizza);

const showPizza = async (id) => {
    const data = await axios.get(
        `http://localhost:8080/api/pizze/${id}`)
        singlePizza.value = data;
};

onMounted(showPizza);
</script>

<style>
</style>
