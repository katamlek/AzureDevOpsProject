<template>
    <div class="container" style="text-align: center;">

        <sui-modal name="delete-disabled">
            hello, world!
        </sui-modal>

        <h3>Skills Database</h3>
        <div v-if="message" class="alert alert-success">
            {{message}}
        </div>
        <div class="container" style="width: 80%; margin-left: auto; margin-right: auto">
            <table class="ui celled table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Details</th>
                </tr>
                </thead>
                <tbody>
                <tr v-for="skill in skills" v-bind:key="skill.id">
                    <td>{{skill.skillName}}</td>
                    <td>
                        <button class="ui button" v-on:click="updateSkillClicked(skill.id)">Details</button>
                        <button class="ui red icon button" v-on:click="deleteSkillClicked(skill.id)"><i
                                class="trash icon"></i></button>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="row">
                <button class="ui button positive" v-on:click="addSkillClicked()">Add Skill</button>
            </div>
        </div>
    </div>
</template>
<script>
    import SkillDataService from "../service/SkillDataService";

    export default {
        name: "SkillsList",
        data() {
            return {
                skills: [],
                message: null,
            };
        },
        methods: {
            refreshSkills() {
                SkillDataService.retrieveAllSkills()
                    .then(response => {
                        this.skills = response.data.content;
                    });
            },
            deleteSkillClicked(id) { //todo modal
                this.$modal.show('delete-disabled');
                let result = SkillDataService.deleteSkill(id);
                if (result.id == null) {
                    this.$modal.show('delete-disabled');
                } else {
                    this.refreshSkills();
                }
            },
            updateSkillClicked(id) {
                this.$router.push(`/skills/${id}`);
            },
            addSkillClicked() {
                this.$router.push(`/skills/add`);
            },
        },
        created() {
            this.refreshSkills();
        }
    };
</script>

<style></style>