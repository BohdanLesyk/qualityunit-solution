# QualityUnit solution

For the program to work correctly, you must meet at least the following requirements:
1. Pass the file path as first argument. For example, `Solution path/to/your/source/file`
2. The file must not be empty
3. The first line in the file must contain an integer value greater than `0` and less than `100000`
4. All other rulers contained in the file and their sequence number is greater than this value will be ignored

### All information about the result of the program, as well as errors, is displayed in the console

<hr>
#### Clarification questions/answers through Email

1. `Q:` Can the solution be a console application? `A:` Yes (the input data should be loaded from the file)
2. `Q:` Does the serial number for the service_id/variation_id/question_type_id and other same start with 0 or 1? `A:`
   It starts with 1
3. `Q:` Does one question belong to one category or to several? `A:` Several
4. `Q:` Does one subcategory belong to one category or to several? `A:` One category could include several subcategories
5. `Q:` How to round up the average waiting time for a request if several valid results match it? For example:
   
   C 1.1 8.15.1 P 15.10.2012 83 <br>
   C 1.1 8.9.2 P 10.10.2012 66 <br>
   C 1.1 8.3 P 5.10.2012 33 <br>
   D 1.1 8 P 01.01.2012-01.12.2012 <br>
   <br>
   The average time is 60,666667. How to calculate the accuracy of minutes? From the first second, from the 30th second, or
   from the 59th, count the whole minute?
   `A:` The avg waiting time is in seconds. You shouldn't round it, just ignore the fractional part.

6. `Q:` Can the C (waiting timeline) accept only valid values? Is it possible to enter erroneous data for it? I mean input data contains non-existent values (service_id == 11 OR variation_id == 7 etc.)? `A:` Add the validation for the input data.
7. `Q:` The same question applies to the D line. `A:` Add the validation for the input data.
8. `Q:` If the answer is yes to either of the two questions above, what should be the behavior of the program when an exception occurs? Does the program stop or ask you to enter the data again or does it show an error and crash? `A:` The program should notify that the data is invalid
9. `Q:` From the example given in the terms of reference, it says "Only matching lines defined before the query line is counted". Will the line with the command D (query) find the value after the last previous line D (query) or will it search for all lines from the beginning? `A:` It should search for all lines from the beginning.
10. `Q:` Do I need to use a database for this application? Or is it possible to do without it? `A:` DB is not required.
11. `Q:` Are there any restrictions for date and time values? Minimum, maximum values? `A:` No restrictions.
12. `Q:` Is the file with input data for the program specified through a program argument or can it be hardcoded? `A:` It can be hardcoded. We are interested in the work with the data from the file. You can take the data from the example and/or add your data for better testing.
13. `Q:` How large can input data files be? A few kilobytes, megabytes? Or hundreds of megabytes, up to a gigabyte? `A:` We can skip this part. It's not important in this case. The test task doesn't require you to work with bigger files.
14. `Q:` Where do you want the output to go: to the console, to a file, or somewhere else? `A:` It's up to you.
15. `Q:` If you want to save the output to a file, what extension (*.txt, *.word, *.log, etc.), name, and location should it have? `A:` It's up to you.
16. `Q:` Should my GitHub repository with the solution be public or private? `A:` It's up to you. If you would make it private - let me know, so you can add me. 
