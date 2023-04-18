import controller.LineBuilder;
import controller.LineFilter;
import controller.LineValidation;
import model.Line;
import model.LineDataType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    private static List<Line> lines = new ArrayList<>();

    public static void main(String[] args) {
        int countAllLines = 0;
        String stringLine;
        String[] inputParams;
        double averageWaitingTime;

        try (Scanner scanner = new Scanner(new File(args[0]))) {
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

                if (isValidInputParams(inputParams)) {
                    if (inputParams[0].equals(LineDataType.C.name())) {
                        lines.add(LineBuilder.buildLine(inputParams));
                    } else {
                        averageWaitingTime = LineFilter.filterLineByParamsAndReturnAverage(inputParams, lines);
                        showAverageWaitingTime(averageWaitingTime);
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

    private static void showAverageWaitingTime(double averageWaitingTime) {
        if (averageWaitingTime != Double.NEGATIVE_INFINITY) {
            System.out.format("%.0f\n", averageWaitingTime);
        } else {
            System.out.println("-");
        }
    }
}
