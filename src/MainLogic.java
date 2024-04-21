package src;
import java.util.*;
import java.util.stream.IntStream;

/**
 * @author Override
 * @since 04/06/2024 @12:25
 * @see <a href="https://Github.com/TuringProblem">GitHub Profile</a>
 */

public class MainLogic {
    Scanner KEYBOARD = new Scanner(System.in);
    private int valuePassed; //First value that's passed by the user
    private int secondValuePassed; //Second value that's passed by the user
    public String userInput; //Used for the User

    /**
     * @see MainLogic -> These are the main String values that are for the prompt, and error messages
     */

    private static final String ERROR = "That value is not an Int\nPlease make sure your input is a valid integer\n";
    public final String SECOND_ERROR = "Please enter a value that greater than: ";
    private  final String FIRST = "Please enter the first number: ";
    private final String SECOND = "Enter a second value: ";
    private static final String SECOND_ATTEMPT = "This is your last attempt\nPlease make sure to input the correct value:\n[ie] Number greater than 0 and less than 10000\n";

    /**
     * @param nOne -> First number passed by the user
     * @param nTwo -> Second number passed by the user.
     * @return -> If nOne > nTwo then it just flips decrements nOne value using the recursion method and then adding the values :
     *              -> else if the values are equal, then the value remains the same -> else it increments using the recursion method, adding teh nOne integers.
     */

    public int sumOfIntegers(int nOne, int nTwo) { return nOne > nTwo ? nOne + sumOfIntegers(nOne - 1, nTwo) : nOne == nTwo ? nOne : nOne + sumOfIntegers(nOne + 1, nTwo); }

    /**
     * @param array -> Takes in an int[] array based on the value passed by the user
     * @param length -> Checks the length of the array, and makes sure that the length is != 0
     * @return -> if length == 0 then it returns 0 else it returns the recursion method which deducts the value by one and adds the value passed by the user.
     */

    public int sumArray(int[] array, int length) { return length == 0 ? 0 : sumArray(array, length - 1) + array[length - 1]; }

    /**
     * @throws InvalidNumberException -> Exception to check and make sure that the user is inputting the correct values
     * for "int" or "integer" {@link #sum(boolean)} -> Handles the sum of the integers passed from the user.
     * for "Array" or "array" {@link #sumArrayHandler()} -> Handles the sum of the Array passed from the user.
     */

    public void logic() throws InvalidNumberException {
        try {
            System.out.println("Please enter whether you want the sum of [Integers] or [Array]: ");
            userInput = KEYBOARD.nextLine();
            if (userInput.equalsIgnoreCase("integers") || userInput.equalsIgnoreCase("int") || userInput.equalsIgnoreCase("integer")) {
                sum(true);
            } else if (userInput.equalsIgnoreCase("array") || userInput.equalsIgnoreCase("arrays")) {
                sumArrayHandler();
            } else {
                throw new InvalidNumberException("Incorrect value passed\n");
            }
        } catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
            logic();
        }
        KEYBOARD.nextLine();
        System.out.println("Would you like to go again?\n[Yes or No]: ");
        userInput = KEYBOARD.nextLine();
        if (userInput.equalsIgnoreCase("yes") || userInput.equalsIgnoreCase("ye") || userInput.equalsIgnoreCase("y")) {
            logic();
        } else {
            System.out.println("Thank you for playing!\n");
            System.exit(0);
        }
    }

    /**
     * Just writes the output for the values passed.
     */

    public void writeOutPut() {
        System.out.printf("The Sum of all integers between %d and %d is: %d \n", valuePassed, secondValuePassed, sumOfIntegers(valuePassed, secondValuePassed));
    }

    /**
     * Using -> This handles the logic behind {@link #sumOfIntegers(int, int)}
     * @param firstOrSecondNumber -> Boolean value that checks which section the user is at.
     */

    public void sum(boolean firstOrSecondNumber) {
        if (firstOrSecondNumber) {
            try {
                System.out.println(FIRST);
                valuePassed = KEYBOARD.nextInt();
                if (valuePassed < 0 || valuePassed > 10000) {
                    throw new InputMismatchException(SECOND_ERROR + valuePassed);
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                KEYBOARD.nextLine();
                sumSecondChance(true);
            }
            sum(false);
        } else {
            try {
                System.out.println(SECOND);
                secondValuePassed = KEYBOARD.nextInt();
                if (secondValuePassed < 0 || secondValuePassed >  10000) {
                    throw new InputMismatchException(SECOND_ERROR + valuePassed);
                }
            } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                    KEYBOARD.nextLine();
                    sumSecondChance(false);
                }
            writeOutPut();
            }
        }

    /**
     *
     * @param isFirst -> If isFirst = true -> the incorrect value was the first integer:
     *                else the incorrect value is the second integer.
     */

    public void sumSecondChance(boolean isFirst) {
            System.out.println();
            String goodBye = "The value you passed was still incorrect\nThe program will now close... goodbye!\n";
            System.out.println(SECOND_ATTEMPT);
            if (isFirst) {
                try {
                    System.out.println(FIRST);
                    valuePassed = KEYBOARD.nextInt();
                    if (valuePassed < 0 || valuePassed > 100000) {
                        throw new InputMismatchException(goodBye);
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
            } else {
                try {
                    System.out.println(SECOND);
                    secondValuePassed = KEYBOARD.nextInt();
                    if (secondValuePassed < 0 || secondValuePassed > 100000) {
                        throw new InputMismatchException(goodBye);
                    }
                } catch (InputMismatchException e) {
                    System.out.println(e.getMessage());
                    System.exit(0);
                }
            }
        }

    /**
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html">Java Streams api</a> ->
     * Using Stram to pipeline the data collected from the user, this allows me to take a collection (such as an array) : pass it a function :
     * and store that data into a new collection or into the same array (in this case using same array).
     */

    public void sumArrayHandler() {
        try {
            System.out.println("Enter the amount of elements in the array: ");
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
            System.out.printf("The sum of the %d elements is: %d\n", n, sum);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
            KEYBOARD.nextLine();
            sumArrayHandler();
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