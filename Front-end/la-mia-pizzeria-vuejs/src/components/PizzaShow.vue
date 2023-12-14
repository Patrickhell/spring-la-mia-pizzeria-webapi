<template>
  <div>
    <div>
        <div>
            <p>
              Single pizza : {{ singlePizza.name }}
            </p>
            <span class="link"
                @click="$emit('closeSinglePizza')">
             Close
            </span>
        </div>
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

const emits = defineEmits( ["closeSinglePizza"])


</script>

<style scoped>
.link {
    cursor: pointer;
    text-decoration: underline;
}
</style>
