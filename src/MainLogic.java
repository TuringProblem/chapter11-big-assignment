package src;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author Override
 * @since 04/06/2024 @12:25
 * @see <a href="https://Github.com/TuringProblem">GitHub Profile</a>
 */

public class MainLogic {
    static Scanner KEYBOARD = new Scanner(System.in);
    private int valuePassed;
    private int secondValuePassed;
    private static final String ERROR = "That value is not an Int\n please make sure that you use an Int and not: \n";
    private static final String FIRST = "Please enter the first number: ";
    private static  final String SECOND = "Enter a second value: ";
    /**
     * Write a static recursive method that computes the sum of the integers between two numbers k1 and k2 (including both k1 and k2).
     * For example, the sum of the numbers between 1 and 5 is 1+2+3+4+5 = 15.
     * Test your method in a program that prompts the user to input both integers and then print out the sum of all the integers between the two numbers.
     */

    /**
     * Use of Ternary operator for the cases.
     * if k1 > k2 then ret: 0
     * else if k1 equals k2 return k1
     * else return the recursion of k1 + value of k1 + 1 + k2
     */
    public int sumOfIntegers(int k1, int k2) { return k1 > k2 ? 0 : k1 == k2 ? k1 : k1 + sumOfIntegers(k1 + 1, k2); }

    /**
     * Use of ternary operator for each case.
     * Write a static recursive method that returns the sum of the integers
     * in the array of int values passed to it as a single argument.
     * Test your method in a program that prompts the user to input the length of the array,
     * and then has them enter the int at every index of the array
     * Then, it should print the sum of the array using the recursive method you wrote.
     */

    public int sumArray(int[] array, int length) { return length == 0 ? 0 : sumArray(array, length - 1) + array[length - 1]; }

    public void logic() throws InvalidNumberException {
        try {
            System.out.println("Please enter whether you want the sum of [Integers] or [Array]: ");
            String userInput = KEYBOARD.nextLine();
            if (userInput.equalsIgnoreCase("integers") || userInput.equalsIgnoreCase("int")) {
                sum(true);
            } else if (userInput.equalsIgnoreCase("array")) {
                sumArray();
            } else {
                throw new InvalidNumberException("Incorrect value passed\n");
            }
        } catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
            logic();
        }
    }
    public void sum(boolean firstOrSecondNumber) {
        if (firstOrSecondNumber) {
            try {
                System.out.println(FIRST);
                valuePassed = KEYBOARD.nextInt();
                if (valuePassed < 0) {
                    throw new InputMismatchException(ERROR);
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                KEYBOARD.nextLine();
                sum(true);
            }
            sum(false);
        } else {
            try {
                System.out.println(SECOND);
                secondValuePassed = KEYBOARD.nextInt();
                if (secondValuePassed < 0) {
                    throw new InputMismatchException(ERROR);
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                KEYBOARD.nextLine();
                sum(false);
            }
            System.out.printf("The Sum of %d to %d = %d\n", valuePassed, secondValuePassed, sumOfIntegers(valuePassed, secondValuePassed));

        }
    }

    /**
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html">Java Streams api</a> ->
     * Using List to pipeline the data collected from the user, instead of Array for efficiency.
     * @see <a href="
     */

    public void sumArray() {
        try {
            System.out.println("Enter the elements: ");
            int n = KEYBOARD.nextInt();
            if (n < 0 || n > 50) { throw new InputMismatchException(ERROR); }
            int[] num = IntStream.range(0, n).map(i -> {
                System.out.printf("Enter element: [%d]%s\n", i, indexSuffix(i));
                int userInt = KEYBOARD.nextInt();
                while (userInt < 0) {
                    System.out.printf("That's not a valid number\nPlease Enter a value greater than %d: %n", userInt);
                    userInt = KEYBOARD.nextInt();
                }
                return userInt;
            }).toArray();
            int sum = sumArray(num, n);
            System.out.printf("The sum of the Array from %d  is: %d\n", n, sum);

        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            KEYBOARD.nextLine();
            sumArray();
        }
    }

    /**
     * @param value -> The value of the index that the users
     * @return -> The corresponding suffix for the value that we are on.
     */

    public String indexSuffix(int value) {
        return switch (value) {
            case 1, 21, 31, 41 -> "st";
            case 2, 22, 32, 42 -> "nd";
            case 3, 23, 33, 43 -> "rd";
            default -> "th";
        };
    }
}