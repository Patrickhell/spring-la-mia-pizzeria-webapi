<template>

  <button v-if="!creatingPizza && pizzaActive == null"
    @click="creatingPizza = true"
  >
    New pizza
  </button>
  <pizza-form
   v-if="creatingPizza"
   @back = "creatingPizza = false"
   @created="createdPizza"
  />
  <div v-else>
    <pizze-index
      v-if="pizzaActive == null"
        :pizze="pizze"
        @open-pizza="showSinglePizza"
    />
    <pizza-show
     v-else
     :pizza= "pizzaActive"
     @close-single-pizza="closeSinglePizza"
     @delete-pizza="deleteSinglePizza"
    />

  </div>
  
</template>


<script setup>
import { onMounted, ref } from 'vue';
import axios from 'axios';

import PizzeIndex from './components/PizzeIndex.vue';
import PizzaShow from './components/PizzaShow.vue';
import PizzaForm from './components/PizzaForm.vue';

const pizze = ref(null);
const pizzaActive = ref(null);
const creatingPizza = ref(false);

const showSinglePizza = (id) => {
  pizze.value.forEach((pizza) => {

    if(pizza.id === id) {
      pizzaActive.value = pizza;
    }
    
  });
};

const closeSinglePizza = () => {
    pizzaActive.value = null;
  }

const getPizze = async () => {
  const data = await axios.get("http://localhost:8080/api/pizze");
  pizze.value = data.data;
};

const createdPizza = () => {
  create.value = true;
}

const deleteSinglePizza = () => {
  pizzaActive.value = null;
}


onMounted(getPizze);
</script>



<style>


</style>
