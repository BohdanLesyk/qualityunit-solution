package controller;

import model.*;

import java.time.LocalDate;

public class LineBuilder {
    public static Line buildLine(String[] inputParams) {
        Line line = new Line();

        line.setService(buildService(inputParams));
        line.setQuestion(buildQuestion(inputParams));
        line.setResponseType(buildResponseType(inputParams));
        line.setDate(buildResponseDate(inputParams));
        line.setTime(Integer.parseInt(inputParams[5]));

        return line;
    }

    private static Service buildService(String[] inputParams) {
        String[] services = inputParams[1].split("[.]");

        Service service = new Service(ServiceID.valueOf("SERVICE_ID_" + services[0]));

        if (services.length == 2) {
            service.setServiceVariation(ServiceVariation.valueOf("SERVICE_VARIATION_" + services[1]));
        }

        return service;
    }

    private static Question buildQuestion(String[] inputParams) {
        String[] questionParams = inputParams[2].split("[.]");
        Question question = new Question(QuestionType.valueOf("QUESTION_TYPE_" + questionParams[0]));

        if (questionParams.length == 2) {
            question.setQuestionCategory(QuestionCategory.valueOf("QUESTION_CATEGORY_" + questionParams[1]));
        } else if (questionParams.length == 3) {
            question.setQuestionCategory(QuestionCategory.valueOf("QUESTION_CATEGORY_" + questionParams[1]));
            question.setQuestionSubCategory(QuestionSubCategory.valueOf("QUESTION_SUB_CATEGORY_" + questionParams[2]));
        }

        return question;
    }

    private static ResponseType buildResponseType(String[] inputParams) {
        return ResponseType.valueOf(inputParams[3]);
    }

    private static LocalDate buildResponseDate(String[] inputParams) {
        String[] dateParams = inputParams[4].split("[.]");

        return LocalDate.of(Integer.parseInt(dateParams[2]), Integer.parseInt(dateParams[1]), Integer.parseInt(dateParams[0]));
    }
}
