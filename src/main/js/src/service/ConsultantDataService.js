import httpServiceInstance from "./HttpService";

const CONSULTANTS_API_URL = `${httpServiceInstance.API_URL}/personProfiles`;

class ConsultantDataService {

    retrieveAllConsultants() {
        //TODO - fix pagination :)
        return httpServiceInstance.get(`${CONSULTANTS_API_URL}?page=0&size=1000`);
    }

    deleteConsultant(id) {
        return httpServiceInstance.delete(`${CONSULTANTS_API_URL}/${id}`);
    }

    retrieveConsultant(id) {
        return httpServiceInstance.get(`${CONSULTANTS_API_URL}/${id}`);
    }

    updateConsultant(id, consultant) {
        return httpServiceInstance.put(`${CONSULTANTS_API_URL}/${id}`, consultant);
    }

    createConsultant(consultant) {
        return httpServiceInstance.post(`${CONSULTANTS_API_URL}`, consultant);
    }

    retrieveConsultantSkills(id) {
        //TODO - fix pagination :)
        return httpServiceInstance.get(`${CONSULTANTS_API_URL}/${id}/skills?page=0&size=1000`);
    }

    deleteConsultantSkill(consultantId, consultantSkillId) {
        return httpServiceInstance.delete(`${CONSULTANTS_API_URL}/${consultantId}/skills/${consultantSkillId}`);
    }

    addConsultantSkill(consultantId, consultantSkillDto) {
        return httpServiceInstance.post(`${CONSULTANTS_API_URL}/${consultantId}/skills`, consultantSkillDto);
    }


}

export default new ConsultantDataService();