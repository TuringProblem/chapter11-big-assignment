package src;

public class MainLogic {
    /**
     * Write a static recursive method that computes the sum of the integers between two numbers k1 and k2 (including both k1 and k2).
     * For example, the sum of the numbers between 1 and 5 is 1+2+3+4+5 = 15.
     * Test your method in a program that prompts the user to input both integers and then print out the sum of all the integers between the two numbers.
     */

    /**
     * if k1 > k2 then ret: 0
     * else if k1 equals k2 return k1
     * else return the recurssion of k1 + value of k1 + 1 + k2
     */
    public int sumOfIntegers(int k1, int k2) {
        return k1 > k2 ? 0 : k1 == k2 ? k1 : k1 + sumOfIntegers(k1 + 1, k2);
    }

    /**
     * Write a static recursive method that returns the sum of the integers
     * in the array of int values passed to it as a single argument.
     * Test your method in a program that prompts the user to input the length of the array,
     * and then has them enter the int at every index of the array
     * Then, it should print the sum of the array using the recursive method you wrote.
     */

    public int sumArray(int[] array, int length) {
        return length == 0 ? 0 : sumArray(array, length - 1) + array[length - 1];
    }
}
