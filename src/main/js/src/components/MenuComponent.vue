<template>
    <div class="ui attached stackable menu" style="margin-bottom: 30px;">

        <div class="ui container">
            
            <a class="item">
                <img class="ui image" src="/logo.png" height="30" />
            </a>
    
            <a class="item" v-on:click="consultantsListClicked()">
                    <i class="grid users icon"></i> Consultants
                </a>
            <a class="item" v-on:click="skillsListClicked()">
                    <i class="grid layout icon"></i> Skills
                </a>
    
        </div>
    
        <div class="right menu">
            <a class="ui item" href="/logout">Logout</a>
        </div>
    </div>
</template>

<script>
export default {
    name: 'MenuComponent',
    props: {
        msg: String
    },
    methods: {
        consultantsListClicked() {
            this.$router.push('/consultants')
        },

        skillsListClicked() {
            this.$router.push('/skills')
        },
    }
}
</script>