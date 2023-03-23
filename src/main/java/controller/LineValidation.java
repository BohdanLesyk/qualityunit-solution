package controller;

import model.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Arrays;

public class LineValidation {
    public static boolean isLineDataTypeValid(String[] inputParams) {
        if (inputParams.length < 1) {
            System.err.println("Invalid input data: " + Arrays.toString(inputParams) + ". Empty line is not accepted");

            return false;
        }

        if (inputParams[0].equals(LineDataType.C.name()) ||
                inputParams[0].equals(LineDataType.D.name())) {
            return true;
        }

        System.err.println("Invalid input data: " + Arrays.toString(inputParams) + ". Line Data Type parameter should be only '"
                + LineDataType.C.name() + "' or '" + LineDataType.D.name() + "'");

        return false;
    }

    public static boolean isServiceValid(String[] inputParams) {
        if (inputParams.length < 2) {
            System.err.println("Invalid input data. You have not specified the Service ID parameter");

            return false;
        }

        if (inputParams[0].equals(LineDataType.D.name()) && inputParams[1].equals("*")) {
            return true;
        }

        String[] serviceParams = inputParams[1].split("[.]");
        int service;

        try {
            service = Integer.parseInt(serviceParams[0]);

            if (service < ServiceID.SERVICE_ID_1.getID() || service > ServiceID.SERVICE_ID_10.getID()) {
                throw new IllegalArgumentException("Invalid input data: " + Arrays.toString(inputParams) + ". Wrong Service ID param." +
                        "It should be grater than " + (ServiceID.SERVICE_ID_1.getID() - 1) + " and less then " +
                        ServiceID.SERVICE_ID_10.getID());
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input data: " + Arrays.toString(inputParams) + ". Wrong Service ID param, it must be an integer type");

            return false;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());

            return false;
        }

        return true;
    }

    public static boolean isServiceVariationValid(String[] inputParams) {
        if (inputParams.length < 2) {
            System.err.println("Invalid input data. You have not specified the Service ID parameter");

            return false;
        }

        if (inputParams[0].equals(LineDataType.D.name()) && inputParams[1].equals("*")) {
            return true;
        }

        String[] serviceParams = inputParams[1].split("[.]");

        if (serviceParams.length == 1) { // Service Variation ID value hasn't passed
            return true;
        }

        try {
            int serviceVariation = Integer.parseInt(serviceParams[1]);

            if (serviceVariation < ServiceVariation.SERVICE_VARIATION_1.getID() ||
                    serviceVariation > ServiceVariation.SERVICE_VARIATION_3.getID()) {
                throw new IllegalArgumentException("Invalid input data: " + Arrays.toString(inputParams) +
                        ". Wrong Service Variation ID param. It should be grater than " + (ServiceVariation.SERVICE_VARIATION_1.getID() - 1)
                        + " and less then " + ServiceVariation.SERVICE_VARIATION_3.getID());
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input data: " + Arrays.toString(inputParams) + ". Wrong Service Variation ID param, it must be an integer type");

            return false;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());

            return false;
        }

        return true;
    }

    public static boolean isQuestionTypeValid(String[] inputParams) {
        if (inputParams.length < 3) {
            System.err.println("Invalid input data. You have not specified the Question Type ID parameter");

            return false;
        }

        if (inputParams[0].equals(LineDataType.D.name()) && inputParams[2].equals("*")) {
            return true;
        }

        String[] questionParams = inputParams[2].split("[.]");

        try {
            int questionType = Integer.parseInt(questionParams[0]);

            if (questionType < QuestionType.QUESTION_TYPE_1.getID() || questionType > QuestionType.QUESTION_TYPE_10.getID()) {
                throw new IllegalArgumentException("Invalid input data: " + Arrays.toString(inputParams) + ". Wrong Question Type ID param." +
                        " It should be grater than " + (QuestionType.QUESTION_TYPE_1.getID() - 1) + " and less then " +
                        QuestionType.QUESTION_TYPE_10.getID());
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input data: " + Arrays.toString(inputParams) + ". Wrong Question Type ID param, it must be an integer type");

            return false;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());

            return false;
        }

        return true;
    }

    public static boolean isQuestionCategoryValid(String[] inputParams) {
        if (!isQuestionTypeValid(inputParams)) {
            return false;
        }

        if (inputParams[0].equals(LineDataType.D.name()) && inputParams[2].equals("*")) {
            return true;
        }

        String[] questionParams = inputParams[2].split("[.]");

        if (questionParams.length < 2) { // Question Category ID value hasn't passed
            return true;
        }

        try {
            int questionCategoryType = Integer.parseInt(questionParams[1]);

            if (questionCategoryType < QuestionCategory.QUESTION_CATEGORY_1.getID() ||
                    questionCategoryType > QuestionCategory.QUESTION_CATEGORY_20.getID()) {
                throw new IllegalArgumentException("Invalid input data: " + Arrays.toString(inputParams) +
                        ". Wrong Question Category ID param. It should be grater than " + (QuestionCategory.QUESTION_CATEGORY_1.getID() - 1)
                        + " and less then " + QuestionCategory.QUESTION_CATEGORY_20.getID());
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input data: " + Arrays.toString(inputParams) + ". Wrong Question Category ID param, it must be an integer type");

            return false;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());

            return false;
        }

        return true;
    }

    public static boolean isQuestionSubCategoryValid(String[] inputParams) {
        if (!isQuestionCategoryValid(inputParams)) {
            return false;
        }

        if (inputParams[0].equals(LineDataType.D.name()) && inputParams[2].equals("*")) {
            return true;
        }

        String[] questionParams = inputParams[2].split("[.]");

        if (questionParams.length < 3) { // Question Sub Category ID value hasn't passed
            return true;
        }

        try {
            int questionSubCategory = Integer.parseInt(questionParams[2]);

            if (questionSubCategory < QuestionSubCategory.QUESTION_SUB_CATEGORY_1.getID() ||
                    questionSubCategory > QuestionSubCategory.QUESTION_SUB_CATEGORY_5.getID()) {
                throw new IllegalArgumentException("Invalid input data: " + Arrays.toString(inputParams) +
                        ". Wrong Question Sub Category ID param. It should be grater than " + (QuestionSubCategory.QUESTION_SUB_CATEGORY_1.getID() - 1)
                        + " and less then " + QuestionSubCategory.QUESTION_SUB_CATEGORY_5.getID());
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input data: " + Arrays.toString(inputParams) + ". Wrong Question Sub Category ID param, it must be an integer type");

            return false;
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());

            return false;
        }

        return true;
    }

    public static boolean isResponseTypeValid(String[] inputParams) {
        if (inputParams.length < 4) {
            System.err.println("Invalid input data: " + Arrays.toString(inputParams) + ". Empty Response Type parameter");

            return false;
        }

        if (inputParams[3].equals(ResponseType.P.name()) ||
                inputParams[3].equals(ResponseType.N.name())) {
            return true;
        }

        System.err.println("Invalid input data: " + Arrays.toString(inputParams) + ". Response Type parameter should be only '"
                + ResponseType.P.name() + "' or '" + ResponseType.N.name() + "'");

        return false;
    }

    public static boolean isDateValid(String[] inputParams) {
        if (inputParams.length < 5) {
            System.err.println("Invalid input data: " + Arrays.toString(inputParams) + ". Empty Response Date parameter");

            return false;
        }

        String[] dateParams = inputParams[4].split("[.-]");

        try {
            LocalDate.of(Integer.parseInt(dateParams[2]), Integer.parseInt(dateParams[1]), Integer.parseInt(dateParams[0]));

            if (inputParams[0].equals(LineDataType.D.name()) && dateParams.length > 3) {
                LocalDate.of(Integer.parseInt(dateParams[5]), Integer.parseInt(dateParams[4]), Integer.parseInt(dateParams[3]));
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid data for date: " + Arrays.toString(inputParams) + ". Response Date should contain only an integer types");

            return false;
        } catch (DateTimeException e) {
            System.err.println("Invalid data for date: " + Arrays.toString(inputParams) + ". The value of any field is out of range, or the day-of-month is invalid for the month-year.");

            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Invalid data for date: " + Arrays.toString(inputParams) + ". Not enough parameters for Date. Only format DD.MM.RRRR is needed");

            return false;
        }

        return true;
    }

    public static boolean isTimeValid(String[] inputParams) {
        if (!inputParams[0].equals(LineDataType.C.name())) {
            return true;
        }

        if (inputParams.length < 6) {
            System.err.println("Invalid input data: " + Arrays.toString(inputParams) + ". Empty Time parameter");

            return false;
        }


        try {
            Integer.parseInt(inputParams[5]);
        } catch (NumberFormatException e) {
            System.err.println("Invalid data for date: " + Arrays.toString(inputParams) + ". Response Time should contain only an integer types");

            return false;
        }

        return true;
    }
}
