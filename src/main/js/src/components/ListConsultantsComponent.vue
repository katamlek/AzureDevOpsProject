<template>
  <div class="container" style="text-align: center; ">
      <h3>All Consultants</h3>
      <div class="container" style="width: 80%; margin-left: auto; margin-right: auto">

          <table class="ui celled table">
              <thead>
                  <tr>
                      <th>Name</th>
                      <th>Email</th>
                      <th>Details</th>
                  </tr>
              </thead>
              <tbody>
                  <tr v-if="loading">
                    <td colspan="4">
                        <div class="ui fluid placeholder">
                            <div class="image header">
                                <div class="line"></div>
                                <div class="line"></div>
                            </div>
                        </div>
                    </td>
                  </tr>
                  <tr v-for="consultant in consultants" v-bind:key="consultant.id">
                      <td>{{consultant.firstName}} {{consultant.lastName}}</td>
                      <td>{{consultant.email}}</td>
                      <td>
                          <button class="ui button" v-on:click="updateConsultantClicked(consultant.id)">Details
                          </button>
                          <button class="ui red icon button" v-on:click="deleteConsultantClicked(consultant.id)">
                          <i class="trash icon"></i>
                          </button>
                      </td>
                  </tr>
              </tbody>
          </table>
          <div class="row">
              <button class="ui button positive" v-on:click="addConsultantClicked()">Add Consultant</button>
          </div>
      </div>
  </div>
</template>

<script>
  import ConsultantDataService from "../service/ConsultantDataService";

  export default {
      name: "ConsultantsList",
      data() {
          return {
              consultants: [],
              loading: true,
              message: null,
          };
      },
      methods: {
          refreshConsultants() {
              this.loading = true;
              ConsultantDataService.retrieveAllConsultants()
                  .then(response => {
                      this.loading = false;
                      this.consultants = response.data.content;
                  });
          },
          deleteConsultantClicked(id) {
              ConsultantDataService.deleteConsultant(id).then(response => { // eslint-disable-line no-unused-vars
                  this.message = `Delete of consultant ${id} Successful`;
                  this.refreshConsultants();
              });
          },
          updateConsultantClicked(id) {
              this.$router.push(`/consultants/${id}`);
          },
          addConsultantClicked() {
              this.$router.push(`/consultants/add`);
          },
      },
      created() {
          this.refreshConsultants();
      }
  };
</script>

<style></style>