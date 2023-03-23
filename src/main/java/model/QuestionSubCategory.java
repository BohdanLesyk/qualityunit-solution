package model;

public enum QuestionSubCategory {
    QUESTION_SUB_CATEGORY_1(1), QUESTION_SUB_CATEGORY_2(2), QUESTION_SUB_CATEGORY_3(3), QUESTION_SUB_CATEGORY_4(4), QUESTION_SUB_CATEGORY_5(5);

    private final int ID;

    QuestionSubCategory(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }
}
