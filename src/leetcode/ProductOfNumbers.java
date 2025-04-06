package leetcode;

// Solution 1352

import java.util.ArrayList;
import java.util.List;

/**
 * Your ProductOfNumbers object will be instantiated and called as such:
 * ProductOfNumbers obj = new ProductOfNumbers();
 * obj.add(num);
 * int param_2 = obj.getProduct(k);
 */
public class ProductOfNumbers {

    List<Integer> stream;
    public ProductOfNumbers() {
        stream = new ArrayList<>();
        stream.add(1);
    }

    public void add(int num) {
        if (num == 0) {
            stream.clear();
            stream.add(1);
        } else {
            stream.add(stream.getLast() * num);
        }
    }

    public int getProduct(int k) {
        int firstIndex = stream.size() - k - 1;
        if (firstIndex < 0) {
            return 0;
        }
        return stream.getLast() / stream.get(firstIndex);
    }

    public static void main(String[] args) {
        ProductOfNumbers productOfNumbers = new ProductOfNumbers();
        productOfNumbers.add(3);        // [3]
        productOfNumbers.add(0);        // [3,0]
        productOfNumbers.add(2);        // [3,0,2]
        productOfNumbers.add(5);        // [3,0,2,5]
        productOfNumbers.add(4);        // [3,0,2,5,4]
        System.out.println(productOfNumbers.getProduct(2)); // return 20. The product of the last 2 numbers is 5 * 4 = 20
        System.out.println(productOfNumbers.getProduct(3)); // return 40. The product of the last 3 numbers is 2 * 5 * 4 = 40
        System.out.println(productOfNumbers.getProduct(4)); // return 0. The product of the last 4 numbers is 0 * 2 * 5 * 4 = 0
        productOfNumbers.add(8);        // [3,0,2,5,4,8]
        System.out.println(productOfNumbers.getProduct(2)); // return 32. The product of the last 2 numbers is 4 * 8 = 32
    }
}
