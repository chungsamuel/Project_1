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
        int randomMatrix1[][] = generateRandomMatrix(4);
        int randomMatrix2[][] = generateRandomMatrix(4);
        matrixUtilities matrix = new matrixUtilities();
        int matrices1[][] = matrix.classical(randomMatrix1, randomMatrix2);
        int matrices2[][] = matrix.divideAndConquer(randomMatrix1, randomMatrix2);
        int matrices3[][] = matrix.strassen(randomMatrix1, randomMatrix2);
        matrix.printMatrix(matrices1);
        matrix.printMatrix(matrices2);
        matrix.printMatrix(matrices3);
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
