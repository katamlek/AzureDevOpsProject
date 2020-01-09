<template>
    <div class="container">
        <MenuComponent />
        <router-view />
    </div>
</template>

<script>

import MenuComponent from './components/MenuComponent.vue';

export default{
    name: 'SkillOMatic',
    components:{
        MenuComponent // register component
    },
}

</script>