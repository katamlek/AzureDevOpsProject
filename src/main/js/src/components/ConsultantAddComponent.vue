<template>
    <div class="container" style="text-align: center;">
        <h3 is="sui-header" style="text-align: center">Add Consultant</h3>
        <div class="container" style="margin-right: auto; margin-left: auto; ">
            <form @submit="validateAndSubmit">
                <sui-form-fields class="form-group">
                    <sui-label-detail>First Name</sui-label-detail>
                    <sui-input type="text" v-model="first_name"></sui-input>

                    <sui-label-detail>Last Name</sui-label-detail>
                    <sui-input type="text" v-model="last_name"></sui-input>

                    <sui-label-detail>Email</sui-label-detail>
                    <sui-input type="text" v-model="email"></sui-input>
                </sui-form-fields>

                <sui-divider></sui-divider>
                <sui-button primary class="ui button positive" type="submit">Add</sui-button>
            </form>
        </div>
    </div>
</template>

<script>
    import consultantDataService from '../service/ConsultantDataService';
    import ConsultantDto from '../models/ConsultantDto';

    export default {
        name: "consultantDetails",
        data() {
            return {
                first_name: "",
                last_name: "",
                email: "",
                errors: []
            };
        },
        methods: {

            validateAndSubmit(e) {
                e.preventDefault();

                let consultant = new ConsultantDto(
                    null, this.first_name, this.last_name, this.email);
      
                consultantDataService.createConsultant(consultant)
                .then(() => {
                    this.$router.push("/consultants");
                });
            }
        }
    };
</script>
<style>
</style>