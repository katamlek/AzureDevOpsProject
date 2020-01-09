export default class ConsultantSkillDto {

    constructor(id, consultantId, skillId, skillScore, skillName) {
        this.id = id;
        this.personProfileId = consultantId;
        this.skillId = skillId;
        this.skillScore = skillScore;
        this.skillName = skillName;
    }

}