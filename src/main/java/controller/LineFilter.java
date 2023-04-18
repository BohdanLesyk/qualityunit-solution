package controller;

import model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Predicate;

public class LineFilter {
    private static String[] inputParams;
    private static String[] serviceParams;
    private static String[] questionParams;

    public static void setParams(String[] params) {
        inputParams = params;

        serviceParams = inputParams[1].split("[.]");
        questionParams = inputParams[2].split("[.]");
    }

    public static Predicate<Line> filterByService = line -> {
        if (serviceParams[0].equals("*")) {
            return true;
        }

        if (serviceParams.length == 1) {
            return line.getService().getServiceID().equals(ServiceID.valueOf("SERVICE_ID_" + serviceParams[0]));
        } else if (serviceParams.length == 2 && line.getService().getServiceVariation() != null) {
            return line.getService().getServiceID().equals(ServiceID.valueOf("SERVICE_ID_" + serviceParams[0])) &&
                    line.getService().getServiceVariation().equals(ServiceVariation.valueOf("SERVICE_VARIATION_" + serviceParams[1]));
        }

        return false;
    };

    public static Predicate<Line> filterByQuestion = line -> {
        if (questionParams[0].equals("*")) {
            return true;
        }

        if (questionParams.length == 1) {
            return line.getQuestion().getQuestionType().equals(QuestionType.valueOf("QUESTION_TYPE_" + questionParams[0]));
        } else if (questionParams.length == 2 && line.getQuestion().getQuestionCategory() != null) {
            return line.getQuestion().getQuestionType().equals(QuestionType.valueOf("QUESTION_TYPE_" + questionParams[0])) &&
                    line.getQuestion().getQuestionCategory().equals(QuestionCategory.valueOf("QUESTION_CATEGORY_" + questionParams[1]));
        } else if (questionParams.length == 3
                && line.getQuestion().getQuestionCategory() != null
                && line.getQuestion().getQuestionSubCategory() != null) {
            return line.getQuestion().getQuestionType().equals(QuestionType.valueOf("QUESTION_TYPE_" + questionParams[0])) &&
                    line.getQuestion().getQuestionCategory().equals(QuestionCategory.valueOf("QUESTION_CATEGORY_" + questionParams[1])) &&
                    line.getQuestion().getQuestionSubCategory().equals(QuestionSubCategory.valueOf("QUESTION_SUB_CATEGORY_" + questionParams[2]));
        }

        return false;
    };

    public static Predicate<Line> filterByRequestType = line -> {
        if (inputParams[3].equals(ResponseType.P.name())) {
            return line.getResponseType().name().equals(ResponseType.P.name());
        }

        return line.getResponseType().name().equals(ResponseType.N.name());
    };

    public static Predicate<Line> filterByDate = line -> {
        String[] dateParams = inputParams[4].split("[.-]");

        if (dateParams.length > 3) {
            return line.getDate().isAfter(LocalDate.of(Integer.parseInt(dateParams[2]), Integer.parseInt(dateParams[1]), Integer.parseInt(dateParams[0])))
                    && line.getDate().isBefore(LocalDate.of(Integer.parseInt(dateParams[5]), Integer.parseInt(dateParams[4]), Integer.parseInt(dateParams[3])));
        }

        return line.getDate().isAfter(LocalDate.of(Integer.parseInt(dateParams[2]), Integer.parseInt(dateParams[1]), Integer.parseInt(dateParams[0])));
    };

    public static double filterLineByParamsAndReturnAverage(String[] inputParams, List<Line> lines) {
        setParams(inputParams);

        OptionalDouble averageWaitingTime = lines.stream()
                .filter(LineFilter.filterByService)
                .filter(LineFilter.filterByQuestion)
                .filter(LineFilter.filterByRequestType)
                .filter(LineFilter.filterByDate)
                .mapToInt(Line::getTime)
                .average();

        return averageWaitingTime.isPresent() ? averageWaitingTime.getAsDouble() : Double.NEGATIVE_INFINITY;
    }
}
