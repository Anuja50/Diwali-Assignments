public class ReverseDigits {
    // Method to reverse digits of the given integer
    public static int reverseDigits(int n) {
        int negative = n < 0 ? -1 : 1;
        n = Math.abs(n);
        int reversed = 0;
        while (n != 0) {
            reversed = reversed * 10 + n % 10;
            n /= 10;
        }
        return reversed * negative;
    }

    public static void main(String[] args) {
        int number = 1000; // Example input
        int reversedNumber = reverseDigits(number);
        System.out.println("Original number: " + number);
        System.out.println("Reversed number: " + reversedNumber);
        
        number = -5420; // Another example
        reversedNumber = reverseDigits(number);
        System.out.println("Original number: " + number);
        System.out.println("Reversed number: " + reversedNumber);
    }
}



