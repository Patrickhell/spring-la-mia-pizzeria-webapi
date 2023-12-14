<template>
  <div>
    <pizze-index
      v-if="pizzaActive == null"
        :pizze="pizze"
        @open-pizza="showSinglePizza"
    />
    <pizza-show
     v-else
     :pizza= "pizzaActive"
    />

  </div>
  
</template>


<script setup>
import { onMounted, ref } from 'vue';
import axios from 'axios';

import PizzeIndex from './components/PizzeIndex.vue';
import PizzaShow from './components/PizzaShow.vue';

const pizze = ref(null);
const pizzaActive = ref(null);

const getPizze = async () => {
  const data = await axios.get("http://localhost:8080/api/pizze");
  pizze.value = data.data;
};

const showSinglePizza = (id) => {
  pizze.value.forEach((pizza) => {

    if(pizza.id === id) {
      pizzaActive.value = pizza;
    }
    
  });
};
onMounted(getPizze);
</script>



<style>


</style>
