public class Radix {
    public static int length(int n) {
        int count = 0;
        while (n != 0) {
            n /= 10;
            count++;
        }
        return count;
    }

    public static int nth(int n, int col) throws IndexOutOfBoundsException {
        if (col < 0 || col >= length(n)) {
            throw new IndexOutOfBoundsException(col + " index is out of bounds");
        }
        int temp = n / (Math.pow(10, col));
        return (temp % 10);
    }
}