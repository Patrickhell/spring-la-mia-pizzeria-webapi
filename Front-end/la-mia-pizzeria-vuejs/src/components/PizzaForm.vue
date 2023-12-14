<template>
    <form @submit.prevent="submit" >
        <div class="mb-3">
           <label for="name" class="form-label">Nome</label>
           <input type="tex" class="form-control" id="name" v-model="newPizza.name"  >
        </div>
        <div class="mb-3">
           <label for="description" class="form-label">Descizione</label>
           <input type="text" class="form-control" id="description" v-model="newPizza.description">
        </div>
        <div class="mb-3">
        <label for="foto" class="form-label">Image</label>
           <input type="url" class="form-control" id="foto" v-model="newPizza.foto">
        </div>
        <div class="mb-3">
           <label for="price" class="form-label">Prezzo</label>
           <input type="number" class="form-control" id="price" v-model="newPizza.price">
        </div>
        <div>
            <button type="submit" class="btn btn-primary">
              Create
            </button>
            <button type="button" class="btn btn-primary mx-3" @click="$emit('back')">
               Indietro
            </button>
        </div>
        

    </form>
    
</template>

<script setup>

import { defineEmits, ref } from 'vue';
import axios from 'axios';



const newPizza = ref({
     name:"",
     description:"",
     foto:"",
     price:""
 });

const emits = defineEmits(["back", "created"]);

const submit = async () => {
    const data = await axios.post("http://localhost:8080/api/pizze", newPizza.value);

    console.log("data" , data);
    emits("created");

    return;

}


</script>

<style>
</style>

