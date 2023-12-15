<template>
  <div v-if="!pizzaUpdate">
    <div class="d-flex" >
        <div>
          <p class="fs-4">
           {{ singlePizza.name }}
          </p>
          <p>Price: {{ singlePizza.price }}</p>
          <p>{{ singlePizza.description }}</p>
          <span class="fs5">Ingredienti:</span>
          <div class="mb-2">
            <span v-for="ingredient in singlePizza.ingredients" >
                {{ ingredient.name }} - 
            </span>
          </div>
          <div v-for="discount in singlePizza.specialDiscount" >
            <span>
              Offerta: {{ discount.title}} -
            </span>
            <span>
              Data inizio: {{ discount.startDate }} -
            </span>
            <span >
              Data fine: {{ discount.endDate}}
            </span>
          </div>
          <img :src="singlePizza.foto" alt="pizza image" style="width: 200px" class="mt-4 mb-4">

        </div>
        <div class="link"
            @click="$emit('closeSinglePizza')">
            Close
        </div>
    </div>

      <button type="button" class="btn btn-primary mx-3" @click="deletePizza(singlePizza.id)">
        Cancella
      </button>
      <button type="button"  @click="pizzaUpdate = true" class="btn btn-primary mx-3">
        UPDATE
      </button>
    </div>
  <div v-else>
    <PizzaForm
      :oldPizza="pizza"
      @back="pizzaUpdate = false"
      @created="updatedPizza"
    />

  </div>
  
</template>

<script setup>

import { ref } from 'vue';
import axios from 'axios';
import PizzaForm from './PizzaForm.vue';

const props = defineProps ({
    pizza: {
        type: Object,
        required:true
    }
});


const pizzaUpdate = ref(false);
const singlePizza = ref(props.pizza);


const emits = defineEmits( ["closeSinglePizza", "deletePizza"]);

const deletePizza = async (id) => {
    const data = await axios.delete(`http://localhost:8080/api/pizze/${id}`);
    console.log("data", data);
    emits("deletePizza ");
}

const updatedPizza = (newPizza) => {
    pizzaUpdate.value = false;
    singlePizza.value = newPizza;
}

</script>

<style scoped>

</style>
