import java.util.Random;

public class driver {
    public static void main(String[] args) {
        String hi = "hello world";
        System.out.println(hi);
        int actualMatrix1[][] = new int[2][2];
        int actualMatrix2[][] = new int[2][2];
        actualMatrix1[0][0] = 2;
        actualMatrix1[0][1] = 0;
        actualMatrix1[1][0] = 1;
        actualMatrix1[1][1] = 9;
        actualMatrix2[0][0] = 3;
        actualMatrix2[0][1] = 9;
        actualMatrix2[1][0] = 4;
        actualMatrix2[1][1] = 7;
        int randomMatrix1[][] = generateRandomMatrix(256);
        int randomMatrix2[][] = generateRandomMatrix(256);
        matrixUtilities matrix = new matrixUtilities();
        long startTimeClassical = System.nanoTime();
        matrix.classical(randomMatrix1, randomMatrix2);
        long endTimeClassical = System.nanoTime();
        long startTimeDivideAndConquer = System.nanoTime();
        matrix.divideAndConquer(randomMatrix1, randomMatrix2);
        long endTimeDivideAndConquer = System.nanoTime();
        long startTimeStrassen = System.nanoTime();
        matrix.strassen(randomMatrix1, randomMatrix2);
        long endTimeStrassen = System.nanoTime();
        long classicalTime = (endTimeClassical - startTimeClassical) / 1000000;
        long divideAndConquerTime = (endTimeDivideAndConquer - startTimeDivideAndConquer) / 1000000;
        long strassenTime = (endTimeStrassen - startTimeStrassen) / 1000000;

        System.out.println("That took " + classicalTime + " milliseconds");
        System.out.println("That took " + divideAndConquerTime + " milliseconds");
        System.out.println("That took " + strassenTime + " milliseconds");
    }

    static int[][] generateRandomMatrix(int n) {
        int[][] randomMatrix = new int[n][n];
        int max = 100;
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                randomMatrix[i][j] = rand.nextInt(max);
            }
        }

        return randomMatrix;
    }

}
