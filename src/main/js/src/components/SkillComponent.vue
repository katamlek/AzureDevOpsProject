<template>
    <div class="container" style="text-align: center;">
        <h3>Skill Details</h3>
        <div class="container" style="width: 80%; margin-left: auto; margin-right: auto">
            <form @submit="validateAndSubmit">

                <sui-form-fields class="form-group">
                    <sui-label-detail>Skill Name</sui-label-detail>
                    <sui-input type="text" v-model="skill_name"></sui-input>
                </sui-form-fields>

                <sui-divider></sui-divider>
                <sui-button primary class="ui button positive" type="submit">Save</sui-button>
            </form>
        </div>
    </div>
</template>

<script>
    import skillDataService from '../service/SkillDataService';
    import SkillDto from '../models/SkillDto';

    export default {
        name: "skillDetails",
        data() {
            return {
                skill_name: "",
                errors: [],
            };
        },
        computed: {
            id() {
                return this.$route.params.id;
            }
        },

        methods: {
            refreshSkillDetails() {
                skillDataService.retrieveSkill(this.id).then(res => {
                    this.skill_name = res.data.skillName;
                });
            },

            validateAndSubmit(e) {
                e.preventDefault();
                let skill = new SkillDto(
                    this.id, this.skill_name);

                skillDataService.updateSkill(this.id, skill).then(() => {
                    this.$router.push("/skills");
                });
            }
        },

        created() {
            this.refreshSkillDetails();
        }

    };
</script>
<style>
</style>