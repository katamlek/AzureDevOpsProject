import Vue from "vue";
import Router from "vue-router";

Vue.use(Router);

const router = new Router({
    mode: "history", // Use browser history
    routes: [
        {
            path: "/",
            name: "Home"
        },
        {
            path: "/consultants",
            name: "Consultants",
            component: () => import("./components/ListConsultantsComponent")
        },
        {
            path: "/consultants/add",
            name: "Add Consultant",
            component: () => import("./components/ConsultantAddComponent")
        },
        {
            path: "/consultants/:id",
            name: "Consultant Details",
            component: () => import("./components/ConsultantComponent")
        },
        {
            path: "/consultants/:consultant_id/skills/add",
            name: "Add Consultant Skills",
            component: () => import("./components/ConsultantSkillsAddComponent")
        },
        {
            path: "/available_skills",
            name: "Available Skills",
            component: () => import("./components/ConsultantSkillsAddComponent")
        },
        {
            path: "/skills",
            name: "Skills Database",
            component: () => import("./components/ListSkillsComponent")
        },
        {
            path: "/skills/add",
            name: "Add Skill",
            component: () => import("./components/SkillAddComponent")
        },
        {
            path: "/skills/:id",
            name: "Skill Details",
            component: () => import("./components/SkillComponent")
        },
    ]

});

export default router;