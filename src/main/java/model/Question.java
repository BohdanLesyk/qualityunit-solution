package model;

public class Question {
    private QuestionType questionType;
    private QuestionCategory questionCategory;
    private QuestionSubCategory questionSubCategory;

    public Question(QuestionType questionType) {
        this.questionType = questionType;
    }

    public QuestionType getQuestionType() {
        return questionType;
    }

    public QuestionCategory getQuestionCategory() {
        return questionCategory;
    }

    public void setQuestionCategory(QuestionCategory questionCategory) {
        this.questionCategory = questionCategory;
    }

    public QuestionSubCategory getQuestionSubCategory() {
        return questionSubCategory;
    }

    public void setQuestionSubCategory(QuestionSubCategory questionSubCategory) {
        this.questionSubCategory = questionSubCategory;
    }

    @Override
    public String toString() {
        return "Question{" +
                "questionType=" + questionType +
                ", questionCategory=" + questionCategory +
                ", questionSubCategory=" + questionSubCategory +
                '}';
    }
}
