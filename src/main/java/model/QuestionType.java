package model;

public enum QuestionType {
    QUESTION_TYPE_1(1), QUESTION_TYPE_2(2), QUESTION_TYPE_3(3), QUESTION_TYPE_4(4), QUESTION_TYPE_5(5),
    QUESTION_TYPE_6(6), QUESTION_TYPE_7(7), QUESTION_TYPE_8(8), QUESTION_TYPE_9(9), QUESTION_TYPE_10(10);

    private final int ID;

    QuestionType(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}
