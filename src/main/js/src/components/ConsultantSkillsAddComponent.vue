<template>
    <div class="container" style="text-align: center;">
        <h3 is="sui-header" style="text-align: center">Add Skill for Consultant</h3>
        <div class="container" style="margin-right: auto; margin-left: auto; ">
            <form @submit="validateAndSubmit">
                <div class="ui grid">
                    <div class="eight wide right aligned column">
                        <sui-dropdown selection 
                                    v-model="selected_skill" 
                                    :options="skills" 
                                    search
                                    placeholder="Select a skill"
                                    />
                    </div>
                    <div class="eight wide left aligned column">
                        <sui-dropdown selection v-model="selected_score" :options="scores" />
                    </div>
                </div>
                    
                <sui-divider></sui-divider>
                <sui-button primary class="ui button positive" type="submit">Add</sui-button>
            </form>
        </div>

    </div>
</template>

<script>
    import skillDataService from '../service/SkillDataService';
    import consultantDataService from '../service/ConsultantDataService';
    import ConsultantSkillDto from '../models/ConsultantSkillDto';

    export default {
        name: "consultantSkillAdd",
        data() {
            return {
                skills: [],
                scores: [
                    {value: 1, text: "1"},
                    {value: 2, text: "2"},
                    {value: 3, text: "3"},
                    {value: 4, text: "4"},
                    {value: 5, text: "5"}
                    ],
                errors: [],
                selected_skill: "",
                selected_score: 1,
                consultant_id: null
            };
        },
        methods: {
            validateAndSubmit(e) {
                e.preventDefault();

                let consultantSkill = new ConsultantSkillDto(null, this.consultant_id, this.selected_skill, this.selected_score, "");

                consultantDataService.addConsultantSkill(this.consultant_id, consultantSkill).then(response => {
                    if(response.status != 200){
                        //TODO - alert
                    }
                    this.$router.push(`/consultants/${this.consultant_id}`);
                });
                
            },
            populateSkillsDropdown() {
                skillDataService.retrieveAllSkills()
                    .then(response => {

                        this.skills = new Array(); 

                        for (let idx in response.data.content){
                            let entry = response.data.content[idx];
                            let optionsEntry = {
                                value: entry.id,
                                text: entry.skillName
                            };
                            this.skills.push(optionsEntry);
                        }
                        
                    });
            }
        },

        created() {
            this.consultant_id = this.$route.params.consultant_id;
            this.populateSkillsDropdown();
        }
    };
</script>
<style>
</style>