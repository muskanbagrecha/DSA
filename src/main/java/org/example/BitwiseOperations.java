package org.example;

public class BitwiseOperations {

    public static void swap(int a, int b){
        //ideally input would be two single element arrays as test cases will validate whether actual swapping has occured or not.
        a = a ^ b;
        b = a ^ b;
        a = a ^ b;
        System.out.println(a);
        System.out.println(b);
    }

    //https://www.naukri.com/code360/problems/check-whether-k-th-bit-is-set-or-not_5026446
    static boolean isKthBitSet(int n, int k) {
        // Write your code here.
        int mask = 1 << k-1;
        if((n & mask) != 0){
            return true;
        }
        return false;
    }

    //Time & space: O(1) - in other words time to compute does not depend on N or k. Also, & and << are O(1) ops.

    static boolean isKthBitSetRightShift(int n, int k) {
        return ((n>>(k-1))&1)!=0;
    }

    //https://www.naukri.com/code360/problems/odd-even_7993579
    /*
    1. If last bit is odd => num is odd and vice versa
    2. & with 1 will return the last bit as it is
     example: 101001 & 000001 = 1, 100 & 001 = 0
     */
    public static String findOddOrEven(int num) {
        return (num & 1) == 1 ? "Odd" : "Even";
    }

    /*
    Input: [1,1,5,7,5,10,9,9,10]
    Output: 7
    Note: here all elements but one are occurring twice. Solution is same
    for any even number of occurrence.
    XOR any number by itself we will get 0.
    So we are going through all elements in the array and ultimately they get
    cancelled out except the one unique element.
    Note order does not matter due to associative property
    (a^b)^c=a^(b^c)
    */
    public static int findUniqueElement(int[] arr) {
        int num = 0;
        for (int i = 0; i < arr.length; i++) {
            num ^= arr[i];
        }
        return num;
    }

    /*
    100 & 010 = 0 (to find 1st bit)
    100 & 100 = 1 (to find 2nd bit)
    to get the right hand operand => 1 << i => 1<<2 = 100
    to get the bit finally => 100 >> i = 1
    */
    public static int findIthBitOfANumber(int num, int i) {
        return (num & (1 << i)) >> i;
    }

    /*
    Given: 100, set 2nd bit
    Output: 100

    Given: 100, set 1st bit
    Output: 110
    */
    public static int setTheIthBit(int num, int i) {
        return (num | (1 << i));
    }

    /*
    Given: 100, reset 2nd bit
    Mask: 011 (complement of 100)
    Output: 000

    Given: 100, reset 1st bit
    Mask: 101 (complement of 010)
    Output: 100
    */
    public static int resetIthBit(int num, int i) {
        int mask = ~(1 << i);
        return (num & mask);
    }

    //Check if it is set and then perform operation
    public static int toggleIthBit(int num, int i){
        int mask = 1 << i;
        boolean set = (num & mask) != 0;
        if(set){
            return num & ~mask;
        }
        return num | mask;
    }

    //Approach 2: perform XOR
    public  static int toggleIthBitXor(int num, int i){
        int mask = 1<<i;
        return num^mask;
    }

    //Set The Rightmost Unset Bit
    public static int setBits(int N){
        // Write your code here.
        int next = N+1;
        if((next & N) == 0) return N;
        return N | next;
    }

    //TODO
    /*Find the position of the right most set bit*/
    /*
    Input: 10110110
    Output: 1
    */

    //TODO
    /*
    Find the unique element when all other elements occur 3 (or any odd
    number of times)
    Input: [1,4,1,2,1,2,2]
    Output: 4
    */
    public static int findUnique(int[] arr){
        int sum = 0;
        for(int i = 0; i<arr.length;i++){
            sum += arr[i];
        }
        int n = (int)(Math.log(sum)/Math.log(2)) + 1;
        int mask = ~(1>>n+1);
        System.out.println(sum%mask);
        return sum%mask;
    }

    /*
    Find nth magic number
    1: 0 0 1 => 1 * 5**1
    2: 0 1 0 => 1 * 5**2
    3: 0 1 1 => 1 * 5**2 + 1 * 5**1
    4: 1 0 0 => 1 * 5**3
    5: 1 0 1 => 1 * 5**3 + 1 * 5**1
    */
    public static double magicNumber(int n){
        double sum = 0;
        int i = 1;
        while(n>0){
            int lastBit = n&1;
            sum+=(lastBit * Math.pow(5, i));
            i++;
            n=n>>1;
         }
        return sum;
    }

    public static int numberOfDigits(int num, int base){
        return (int)Math.floor(Math.log(num)/Math.log(base)) + 1; //no need to add math.floor()
    }

    /*
    Number is a power of two if in binary rep it is 1 followed by all 0s
    Ex: 100, 10, 10000
    */
//    public static boolean powerOfTwo(int num){
//        int mask = ~(1 << (int)Math.floor(Math.log(num)/Math.log(2)));
//        return (num & mask) == 0 ? true: false;
//    }
        public static boolean powerOfTwo(int num){
            if(num==0){
                return false;
            }
            return (num & num-1) == 0 ? true : false;
            /*
            100 = 11 + 1
            n = (n-1) + 1
              1 0 0 --> n
            & 0 1 1 --> n-1
            = 0 0 0
             */
    }

}
