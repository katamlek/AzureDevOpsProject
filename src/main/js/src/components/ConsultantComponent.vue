<template>
    <div class="container" style="text-align: center;">
        <h3>Consultant Details</h3>
        <div class="container" style="width: 80%; margin-left: auto; margin-right: auto">

            <sui-grid :columns="3">
                <sui-grid-column>
                    <sui-card>
                        <sui-card-content>
                            <sui-icon size="massive" name="user" fitted style="color: #EEE;"/>
                            <br /><br />
                            <sui-card-header>{{first_name}} {{last_name}}</sui-card-header>
                            <sui-card-content extra>
                                <sui-icon name="mail" />
                               {{email}}
                            </sui-card-content>
                        </sui-card-content>
                    </sui-card>
                </sui-grid-column>

                <sui-grid-column>
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
                        <sui-button primary class="ui button positive" type="submit">Save</sui-button>
                    </form>
                </sui-grid-column>

                <sui-grid-column>
                </sui-grid-column>
            </sui-grid>
        </div>
        <ListConsultantSkillsComponent />
    </div>
</template>

<script>
    import consultantDataService from '../service/ConsultantDataService';
    import ConsultantDto from '../models/ConsultantDto';
    import ListConsultantSkillsComponent from "./ListConsultantSkillsComponent";

    export default {
        name: "consultantDetails",
        components: {
            ListConsultantSkillsComponent // register component
        },
        data() {
            return {
                first_name: "",
                last_name: "",
                email: "",
                errors: [],
            };
        },
        computed: {
            id() {
                return this.$route.params.id;
            }
        },

        methods: {
            refreshConsultantDetails() {
                consultantDataService.retrieveConsultant(this.id).then(res => {
                    this.first_name = res.data.firstName;
                    this.last_name = res.data.lastName;
                    this.email = res.data.email
                });
            },

            validateAndSubmit(e) {
                e.preventDefault();

                let consultant = new ConsultantDto(
                    this.id, this.first_name, this.last_name, this.email);

                consultantDataService.updateConsultant(this.id, consultant).then(() => {
                    this.$router.push("/consultants");
                });
            }
        },

        created() {
            this.refreshConsultantDetails();
        }

    };
</script>
<style>
</style>