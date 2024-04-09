package src;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Override
 * @since 04/06/2024 @12:25
 * @see <a href="https://Github.com/TuringProblem">GitHub Profile</a>
 */

public class MainLogic {
    static Scanner KEYBOARD = new Scanner(System.in);
    private int valuePassed;
    private int secondValuePassed;
    private final String outputError = "That value is not an Int\n please make sure that you use an Int and not: \n";
    /**
     * Write a static recursive method that computes the sum of the integers between two numbers k1 and k2 (including both k1 and k2).
     * For example, the sum of the numbers between 1 and 5 is 1+2+3+4+5 = 15.
     * Test your method in a program that prompts the user to input both integers and then print out the sum of all the integers between the two numbers.
     */

    /**
     * Use of Ternary operator for the cases.
     * if k1 > k2 then ret: 0
     * else if k1 equals k2 return k1
     * else return the recurssion of k1 + value of k1 + 1 + k2
     */
    public int sumOfIntegers(int k1, int k2) { return k1 > k2 ? 0 : k1 == k2 ? k1 : k1 + sumOfIntegers(k1 + 1, k2); }

    /**
     * Use of ternary operator for  each cases.
     * Write a static recursive method that returns the sum of the integers
     * in the array of int values passed to it as a single argument.
     * Test your method in a program that prompts the user to input the length of the array,
     * and then has them enter the int at every index of the array
     * Then, it should print the sum of the array using the recursive method you wrote.
     */

    public int sumArray(ArrayList<Integer> array, int length) {
        return length == 0 ? 0 : sumArray(array, length - 1) + array.get(length - 1);
    }

    public void logic() throws InvalidNumberException {
        ArrayList<Integer> array = new ArrayList<>();

        try {
            System.out.println("Please enter whether you want the [Sum] or [SumArray]: ");
            String userInput = KEYBOARD.nextLine();
            if (userInput.equalsIgnoreCase("sum")) {
                sum();
            } else if (userInput.equalsIgnoreCase("sumarray")) {
                sumArray();
            } else {
                throw new InvalidNumberException("Incorrect value passed\n");
            }
        } catch (InvalidNumberException e) {
            System.out.println(e.getMessage());
        }
    }
    public void sum() {
        try {
            System.out.println("Please enter the first number: ");
            valuePassed = KEYBOARD.nextInt();

            System.out.println("Enter a second value: ");
            secondValuePassed = KEYBOARD.nextInt();

        } catch(InputMismatchException e) {
            System.out.println(outputError);
            System.out.println(e.getMessage());
            KEYBOARD.nextLine();
            sum();
        }
        System.out.println(sumOfIntegers(valuePassed, secondValuePassed));
    }
    public void sumArray() {
        try {
            System.out.println("Please enter the first number: ");
            valuePassed = KEYBOARD.nextInt();

            System.out.println("Please enter the second value: ");
            secondValuePassed = KEYBOARD.nextInt();
        } catch(InputMismatchException e) {
            System.out.println(outputError);
            System.out.println(e.getMessage());
        }

    }
}
