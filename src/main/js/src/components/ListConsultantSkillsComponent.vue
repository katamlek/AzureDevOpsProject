<template>
    <div class="container" style="text-align: center; margin-top: 10px;">
        <h3>Consultant Skills</h3>
        <div class="container" style="width: 80%; margin-left: auto; margin-right: auto">

            <div class="ui equal width grid">

                <div class="six column">
                    <table class="ui celled table">
                        <thead>
                        <tr>
                            <th>Skill</th>
                            <th>Level</th>
                            <th>Delete</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="consultant_skill in consultant_skills" v-bind:key="consultant_skill.id">
                            <td>{{consultant_skill.skillName}}</td>
                            <td>{{consultant_skill.skillScore}}</td>
                            <td style="text-align: center;">
                                <button class="ui red icon button" v-on:click="deleteConsultantSkillClicked(consultant_id, consultant_skill.id)">
                                    <i class="trash icon"></i>
                                </button>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    <div class="row">
                        <button class="ui button positive" v-on:click="addConsultantSkillClicked()">Add Consultant Skill
                        </button>
                    </div>
                </div>

                <div class="ten wide column">
                        <table class="ui celled table skill-table">
                        <thead>
                        <tr>
                            <th style="width: 30%">Skill</th>
                            <th colspan="5">Level</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr v-for="consultant_skill in consultant_skills" v-bind:key="consultant_skill.id">
                            <td>{{consultant_skill.skillName}}</td>
                            <td :class="{'skill' : consultant_skill.skillScore >= 1}"></td>
                            <td :class="{'skill' : consultant_skill.skillScore >= 2}"></td>
                            <td :class="{'skill' : consultant_skill.skillScore >= 3}"></td>
                            <td :class="{'skill' : consultant_skill.skillScore >= 4}"></td>
                            <td :class="{'skill' : consultant_skill.skillScore >= 5}"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>                  
            </div>
        </div>
    </div>
</template>

<style>
    .ui.celled.table.skill-table tr td.skill { 
        background-color: #3d7ed4; 
        border-left: 1px solid #fff;
        border-top: 1px solid #fff;
    }
    
    .ui.celled.table.skill-table tr td{
        padding: 5px 10px 5px 10px;   
    }

</style>

<script>
    import consultantDataService from "../service/ConsultantDataService";

    export default {
        name: "ConsultantSkillsList",
        data() {
            return {
                consultant_skills: [],
                message: null,
                consultant_id: null
            };
        },
        methods: {
            refreshConsultantSkills() {
                consultantDataService.retrieveConsultantSkills(this.consultant_id)
                    .then(response => {
                        this.consultant_skills = response.data;
                    });
            },
            deleteConsultantSkillClicked(consultant_id, consultant_skill_id) {
                consultantDataService.deleteConsultantSkill(consultant_id, consultant_skill_id).then(response => { // eslint-disable-line no-unused-vars
                    this.message = `Delete of consultant skill Successful`;
                    this.refreshConsultantSkills();
                });
            },
            addConsultantSkillClicked() {
                this.$router.push(`/consultants/${this.consultant_id}/skills/add`);
            },
        },
        created() {
            this.consultant_id = this.$route.params.id;
            this.refreshConsultantSkills();
        }
    };
</script>

<style></style>