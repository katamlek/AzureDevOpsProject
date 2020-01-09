import httpServiceInstance from "./HttpService";

const SKILLS_API_URL = `${httpServiceInstance.API_URL}/skills`;

class SkillDataService {
    retrieveAllSkills() {
        //TODO - fix pagination :)
        return httpServiceInstance.get(`${SKILLS_API_URL}?page=0&size=1000`);
    }
    deleteSkill(id) {
        return httpServiceInstance.delete(`${SKILLS_API_URL}/${id}`);
    }
    retrieveSkill(id) {
        return httpServiceInstance.get(`${SKILLS_API_URL}/${id}`);
    }
    updateSkill(id, skill) {
        return httpServiceInstance.put(`${SKILLS_API_URL}/${id}`, skill);
    }
    createSkill(skill) {
        return httpServiceInstance.post(`${SKILLS_API_URL}`, skill);
    }
}

export default new SkillDataService();