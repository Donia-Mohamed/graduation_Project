package dto;

/**
 *
 * @author atef
 */
public class Relationship {

    //constants 
    public final static int FATHER = 3;
    public final static int MOTHER = 4;
    public final static int SON = 5;
    public final static int DAUGHTER = 6;
    public final static int BROTHER = 7;
    public final static int SISTER = 8;
    public final static int HUSBAND = 9;
    public final static int WIFE = 10;

    public final static int GRANDFATHER = 13;
    public final static int GRANDMOTHER = 14;
    public final static int GRANDSON = 15;
    public final static int GRANDDAGHUTER = 16;
    public final static int GRANDCHILDREN = 17;
    public final static int UNCLE_FATHER_SIDE = 18;
    public final static int UNCLE_MOTHER_SIDE = 19;
    public final static int AUNT_FATHER_SIDE = 20;
    public final static int AUNT_MOTHER_SIDE = 21;
    public final static int COUSIN_MALE_FATHER_SIDE = 22;
    public final static int COUSIN_MALE_MOTHER_SIDE = 23;
    public final static int COUSIN_FEMALE_FATHER_SIDE = 24;
    public final static int COUSIN_FEMALE_MOTHER_SIDE = 25;
    public final static int NEPHEW_BROTHERS_SON = 31;
    public final static int NEPHEW_SISTERS_SON = 32;
    public final static int NIECE_BROTHERS_dAUGHTER = 33;
    public final static int NIECE_SISTERS_dAUGHTER = 34;
    public final static int FATHER_IN_LAW = 35;
    public final static int MOTHER_IN_LAW = 36;
    public final static int SON_IN_LAW = 37;
    public final static int DAUGHTER_IN_LAW = 38;
    public final static int BROTHER_IN_LAW = 39;
    public final static int SISTER_IN_LAW = 40;
    public final static int STEPFATHER = 41;
    public final static int STEPMOTHER = 42;
    public final static int STEPSON = 43;
    public final static int STEPDAUGHTER = 44;
    public final static int STEPSISTER = 45;
    public final static int STEPBROTHER = 46;
    public final static int HALF_BROTHER = 47;
    public final static int HALF_SISTER = 48;

    int relationId;
    int relativeId;
    int patientId;

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public int getRelativeId() {
        return relativeId;
    }

    public void setRelativeId(int relativeId) {
        this.relativeId = relativeId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

}
