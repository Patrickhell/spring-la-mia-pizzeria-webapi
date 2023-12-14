<template>
  <div>
    <div class="d-flex">
        <div>
            <p>
              Single pizza : {{ singlePizza.name }}
            </p>
            <p>Price: {{ singlePizza.price }}</p>
            <p>{{ singlePizza.description }}</p>
            <img :src="singlePizza.foto" alt="pizza image" style="width: 200px">
            
        </div>
        <div class="link"
            @click="$emit('closeSinglePizza')">
            Close
        </div>
    </div>
    <button type="button" class="btn btn-primary mx-3" @click="deletePizza(singlePizza.id)">
        Cancella
    </button>

  </div>
    
</template>

<script setup>

import { ref } from 'vue';
import axios from 'axios';

const props = defineProps ({
    pizza: {
        type: Object,
        required:true
    }
});

const singlePizza = ref(props.pizza);

const emits = defineEmits( ["closeSinglePizza", "deleteSinglePizza"]);

const deletePizza = async (id) => {
    const data = await axios.delete(`http://localhost:8080/api/pizze/${id}`);
    console.log("data", data);
    emits("deleteSinglePizza");
}


</script>

<style scoped>
.link {
    cursor: pointer;
    text-decoration: underline;
}
</style>
