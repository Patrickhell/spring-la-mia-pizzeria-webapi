<template>
  <div style="width: 650px;" class="mb-4">
    <form v-if="!creatingPizza && pizzaActive == null"  @submit.prevent="getPizzaByName" class="d-flex" role="search">
      <input class="form-control me-2"  type="text" query="name" aria-label="Search"  v-model="nameToSearch" >
      <button class="btn btn-outline-success" type="submit" >Search</button>
    </form>
  </div>


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

const nameToSearch = ref('');

const showSinglePizza = (id) => {
  pizze.value.forEach((pizza) => {

    if(pizza.id === id) {
      pizzaActive.value = pizza;
    }
    
  });
};

const closeSinglePizza = (update) => {
    pizzaActive.value = null;
    if (update) updatePizze();

  }


const createdPizza = () => {
  creatingPizza.value = false;
  updatePizze();
}

const updatePizze = async () => {
  const data = await axios.get("http://localhost:8080/api/pizze");
  pizze.value = data.data;
};

const deleteSinglePizza = () => {
  pizzaActive.value = null;
  updatePizze();
}

const getPizzaByName = async() => {

  let params = "";
  if (nameToSearch.value != null )
    params = "?query=" + nameToSearch.value;

    const data = await axios.get("http://localhost:8080/api/pizze" + params);
    pizze.value = data.data;
}


onMounted(updatePizze);
</script>



<style>


</style>
