import controller.LineBuilder;
import controller.LineValidation;
import model.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class Solution {
    private static List<Line> lines = new ArrayList<>();

    public static void main(String[] args) {
        parseFile(new File(args[0]));
    }

    private static void parseFile(File file) {
        int countAllLines = 0;
        String stringLine;
        String[] inputParams;
        boolean isValidInputParams;

        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                countAllLines = Integer.parseInt(scanner.nextLine());

                if (countAllLines <= 0 || countAllLines > 100_000) {
                    throw new IllegalArgumentException("Incorrect input value for count all lines. The range should be integer in range of 1 to 100000. PLease, change first value and resubmit file again");
                }
            }

            while (countAllLines > 0 && scanner.hasNextLine()) {
                stringLine = scanner.nextLine();
                countAllLines--;

                if (stringLine.isBlank()) {
                    System.err.println("Line is empty and not valid!");
                    continue;
                }

                inputParams = stringLine.split(" ");
                isValidInputParams = isValidInputParams(inputParams);

                if (isValidInputParams) {
                    if (inputParams[0].equals(LineDataType.C.name())) {
                        lines.add(LineBuilder.buildLine(inputParams));
                    } else {
                        showAverageWaitingTimeByCriteria(inputParams);
                    }
                } else {
                    System.err.println("Line: `" + stringLine + "` is not valid");
                }
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Incorrect input value for count all lines. The range should be integer in range of 1 to 100000. PLease, change first value and resubmit file again");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static boolean isValidInputParams(String[] inputParams) {
        return LineValidation.isLineDataTypeValid(inputParams) &&
                LineValidation.isServiceValid(inputParams) &&
                LineValidation.isServiceVariationValid(inputParams) &&
                LineValidation.isQuestionTypeValid(inputParams) &&
                LineValidation.isQuestionCategoryValid(inputParams) &&
                LineValidation.isQuestionSubCategoryValid(inputParams) &&
                LineValidation.isResponseTypeValid(inputParams) &&
                LineValidation.isDateValid(inputParams) &&
                LineValidation.isTimeValid(inputParams);
    }

    private static void showAverageWaitingTimeByCriteria(String[] inputParams) {
        String[] serviceParams = inputParams[1].split("[.]");
        String[] questionParams = inputParams[2].split("[.]");

        OptionalDouble averageWaitingTime = lines.stream()
                .filter(line -> {
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
                })
                .filter(line -> {
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
                })
                .filter(line -> {
                    if (inputParams[3].equals(ResponseType.P.name())) {
                        return line.getResponseType().name().equals(ResponseType.P.name());
                    }

                    return line.getResponseType().name().equals(ResponseType.N.name());
                })
                .filter(line -> {
                    String[] dateParams = inputParams[4].split("[.-]");

                    if (dateParams.length > 3) {
                        return line.getDate().isAfter(LocalDate.of(Integer.parseInt(dateParams[2]), Integer.parseInt(dateParams[1]), Integer.parseInt(dateParams[0])))
                                && line.getDate().isBefore(LocalDate.of(Integer.parseInt(dateParams[5]), Integer.parseInt(dateParams[4]), Integer.parseInt(dateParams[3])));
                    }

                    return line.getDate().isAfter(LocalDate.of(Integer.parseInt(dateParams[2]), Integer.parseInt(dateParams[1]), Integer.parseInt(dateParams[0])));
                })
                .mapToInt(Line::getTime)
                .average();

        if (averageWaitingTime.isPresent()) {
            System.out.format("%.0f\n", averageWaitingTime.getAsDouble());
        } else {
            System.out.println("-");
        }
    }
}
