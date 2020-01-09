import Vue from "vue";
import App from "./App.vue";
import router from "./routes";
import SemanticUIVue from 'semantic-ui-vue'
import 'semantic-ui-css/semantic.min.css';
import VModal from 'vue-js-modal';

Vue.use(SemanticUIVue, VModal);

Vue.config.productionTip = false;

new Vue({
    router,
    render: h => h(App)
}).$mount("#app");