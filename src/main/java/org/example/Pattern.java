package org.example;

public class Pattern {

    /* Pattern 1 (n = 3)
    * 1  2  3
    * 1  2  3
    * 1  2  3
    * */

    public static void patternOne(int n){
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    /*
    * Pattern Two (n=3)
    * 3 2 1
    * 3 2 1
    * 3 2 1
    */

    public static void patternTwo(int n){
        for(int i = 1; i<=n; i++){
            for(int j = n; j>=1; j--){
                System.out.print(j);
            }
            System.out.println();
        }
    }

    /*
    * Pattern Three (n=3)
    * 1 2 3
    * 4 5 6
    * 7 8 9
    * */
    public static void patternThree(int n){
        int k = 1;
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                System.out.print(k);
                k++;
            }
            System.out.println();
        }
    }


//    Pattern Four (n=3)
//    *
//    * *
//    * * *
    public static void patternFour(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=i; j++){
                System.out.print('*');
            }
            System.out.println();
        }
    }

    /*
    * Pattern Five - (n=3)
    * 1
    * 2 2
    * 3 3 3
    * */
    public static void patternFive(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=i; j++){
                System.out.print(i);
            }
            System.out.println();
        }
    }

    /*
    * Pattern Six (n=4)
    * 1
    * 2 3
    * 3 4 5
    * 4 5 6 7
    */
    public static void patternSix(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=i; j++){
                System.out.print(i+j-1);
            }
            System.out.println();
        }
    }
    /*
    * Pattern Seven (n=3)
    * 1
    * 2 1
    * 3 2 1
    */
    public static void patternSeven(int n){
        for(int i = 1; i<=n; i++){
            for(int j = i; j>=1; j--){
                System.out.print(j);
            }
            System.out.println();
        }
    }
    /*
    * Pattern Eight (n=3)
    * A A A
    * B B B
    * C C C
    * */
    public static void patternEight(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                System.out.print((char)('A' + i -1));
            }
            System.out.println();
        }
    }

    /*
    * Pattern Nine (n=3)
    * A B C
    * B C D
    * C D E
    * */
    public static void patternNine(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n; j++){
                System.out.print((char)('A' + i + j - 2));
            }
            System.out.println();
        }
    }
    /*
     * Pattern Ten (n=3)
     * A
     * B B
     * C C C
     * */    public static void patternTen(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=i; j++){
                System.out.print((char)('A' + i - 1));
            }
            System.out.println();
        }
    }
    /*
     * Pattern Eleven (n=3)
     * A
     * B C
     * C D E
     * */
    public static void patternEleven(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=i; j++){
                System.out.print((char)('A' + i - 1));
            }
            System.out.println();
        }
    }
    /*
     * Pattern patternTwelve (n=3)
     * C
     * B C
     * A B C
     */
    public static void patternTwelve(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=i; j++){
                System.out.print((char)('A' + n - i + j - 1));
            }
            System.out.println();
        }
    }
    /*
     * Pattern patternThirteen (n=3)
     * C
     * B C
     * A B C
     */
    public static void patternThirteen(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=i; j++){
                System.out.print((char)('A' + n - i + j - 1));
            }
            System.out.println();
        }
    }

    /*
     * Pattern patternFourteen (n=4)
          *
        * *
      * * *
     * * * *
     */
    public static void patternFourteen(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n-i; j++){
                System.out.print(" ");
            }
            for(int j = 1; j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /*
     * Pattern patternFifteen (n=4)
     * * * *
     * * *
     * *
     *
     */
    public static void patternFifteen(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n-i+1; j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /*
     Pattern patternSixteen (n=4)
     * * * *
       * * *
         * *
           *
     */
    public static void patternSixteen(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<i;j++){
                System.out.print(" ");
            }
            for(int j = n-i+1; j>=1; j--){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    /*
     Pattern patternSeventeen (n=4)
     1 1 1 1
       2 2 2
         3 3
           4
     */
    public static void patternSeventeen(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<i;j++){
                System.out.print(" ");
            }
            for(int j = n-i+1; j>=1; j--){
                System.out.print(i);
            }
            System.out.println();
        }
    }

    /*
     Pattern patternEighteen (n=4)
           1
         2 2
       3 3 3
     4 4 4 4
     */

    public static void patternEighteen(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n-i;j++){
                System.out.print(" ");
            }
            for(int j = 1; j<=i; j++){
                System.out.print(i);
            }
            System.out.println();
        }
    }

    /*
     Pattern patternNineteen (n=4)
     1 2 3 4
       2 3 4
         3 4
           4
     */

    public static void patternNineteen(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<i;j++){
                System.out.print(" ");
            }
            for(int j = i; j<=n; j++){
                System.out.print(j);
            }
            System.out.println();
        }
    }

    /*
     Pattern patternTwenty (n=4)
            1
        1   2   1
     1  2   3   2  1
  1  2  3   4   3  2  1
     */

    public static void patternTwenty(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n-i;j++){
                System.out.print(" ");
            }
            for(int j = 1; j<=i; j++){
                System.out.print(j);
            }
            for(int j = i-1; j>=1; j--){
                System.out.print(j);
            }
            System.out.println();
        }
    }

      /*
     Pattern patternTwentyOne (n=5)
     1 2 3 4 5 5 4 3 2 1
     1 2 3 4 * * 4 3 2 1
     1 2 3 * * * * 3 2 1
     1 2 * * * * * * 2 1
     1 * * * * * * * * 1
     */

    //splitting into 4 triangles
    public static void patternTwentyOne(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n-i+1;j++){
                System.out.print(j);
            }
            for(int j = 1; j<=i-1; j++){
                System.out.print('*');
            }
            for(int j = 1; j<=i-1; j++){
                System.out.print('*');
            }
            for(int j = n-i+1; j>=1; j--){
                System.out.print(j);
            }
            System.out.println();
        }
    }
    //splitting into 3 triangles
    public static void patternTwentyOneMethodTwo(int n){
        for(int i = 1; i<=n; i++){
            for(int j = 1; j<=n-i+1;j++){
                System.out.print(j);
            }
            for(int j = 1; j<=(i-1)*2; j++){
                System.out.print('*');
            }
            for(int j = n-i+1; j>=1; j--){
                System.out.print(j);
            }
            System.out.println();
        }
    }

//        *
//       * *
//      * * *
    public static void patternTwentyTwo(int n){
        for(int i = 0; i<n;i++){
            for(int j=1;j<n-i;j++){
                System.out.print(" ");
            }
            for(int j = 0; j<2*i+1; j++){
                System.out.print("*");
            }
            for(int j=1;j<n-i;j++){
                System.out.print(" ");
            }
            System.out.println();
        }
    }

//    * * *
//     * *
//      *

    public static void patternTwentyThree(int n){
        for(int i = 0; i<n;i++){
            for(int j=1;j<n-i;j++){
                System.out.print(" ");
            }
            for(int j = 0; j<2*i+1; j++){
                System.out.print("*");
            }
            for(int j=1;j<n-i;j++){
                System.out.print(" ");
            }
            System.out.println();
        }
    }
//        1
//        0 1
//        1 0 1
//        0 1 0 1
//        1 0 1 0 1
//        0 1 0 1 0 1
    public static void patternTwentyFour(int n){

        // Write your code here.

        for(int i = 0; i<n; i++){
            for(int j = 0; j<=i; j++){
                if((i+j)%2==0){
                    System.out.print("1 ");
                }
                else{
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }
//        1              1
//        12            21
//        123          321
//        1234        4321
//        12345      54321
//        123456    654321
//        1234567  7654321
//        1234567887654321
    public static void patternTwentyFive(int n){
        for(int i = 0;i<n;i++){
            for(int j=0;j<=i;j++){
                System.out.print(j+1);
            }
            for(int j = 0; j<2*n-2*i-2;j++){
                System.out.print(" ");
            }
            for(int j = i; j>=0;j--){
                System.out.print(j+1);
            }
            System.out.println();
        }
    }

//            ********************
//            *********  *********
//            ********    ********
//            *******      *******
//            ******        ******
//            *****          *****
//            ****            ****
//            ***              ***
//            **                **
//            *                  *
//            *                  *
//            **                **
//            ***              ***
//            ****            ****
//            *****          *****
//            ******        ******
//            *******      *******
//            ********    ********
//            *********  *********
//            ********************
    public static void patternTwentySix(int n){
        for(int i = 0; i<n;i++){
            for(int j = n-i; j>0; j--){
                System.out.print("*");
            }
            for(int j=0;j<2*i;j++){
                System.out.print(" ");
            }
            for(int j=n-i;j>0;j--){
                System.out.print("*");
            }
            System.out.println();
        }
        for(int i = 0; i<n;i++){
            for(int j = 0; j<=i; j++){
                System.out.print("*");
            }
            for(int j=0;j<2*n-2*i-2;j++){
                System.out.print(" ");
            }
            for(int j=0;j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
    }

//            ********
//            *      *
//            *      *
//            *      *
//            *      *
//            *      *
//            *      *
//            ********
    public static void patternTwentySeven(int n){
        for(int i = 0; i<n;i++){
            for(int j=0; j<n;j++){
                if(i==0 || i==n-1 || j==0 || j==n-1){
                    System.out.print("*");
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    
//            *                *
//            **              **
//            ***            ***
//            ****          ****
//            *****        *****
//            ******      ******
//            *******    *******
//            ********  ********
//            ******************
//            ********  ********
//            *******    *******
//            ******      ******
//            *****        *****
//            ****          ****
//            ***            ***
//            **              **
//            *                *

    public static void patternTwentyEight(int n){
        for(int i = 0; i<n;i++){
            for(int j = 0; j<=i;j++){
                System.out.print("*");
            }
            for(int j = 0; j<2*n-2*i-2;j++){
                System.out.print(" ");
            }
            for(int j = 0; j<=i;j++){
                System.out.print("*");
            }
            System.out.println();
        }
        for(int i = 1; i<n;i++){
            for(int j = n-i-1; j>=0;j--){
                System.out.print("*");
            }
            for(int j = 0; j<2*i;j++){
                System.out.print(" ");
            }
            for(int j = n-i-1; j>=0;j--){
                System.out.print("*");
            }
            System.out.println();
        }
    }

    }