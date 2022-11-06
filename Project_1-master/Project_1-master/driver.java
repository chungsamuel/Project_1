public class driver {
    static int globalCounter = 1;

    // Declares size of the algorithms to be tested
    public static void main(String[] args) {
        testAlgorithms(1);
        testAlgorithms(2);
        testAlgorithms(4);
        testAlgorithms(8);
        testAlgorithms(16);
        testAlgorithms(32);
        testAlgorithms(64);
        testAlgorithms(128);
        testAlgorithms(256);
        testAlgorithms(512);
        sanityCheck();
    }

    // implements a timer with the algorithms to measure duration of each test
    static void testAlgorithms(int size) {
        int sizeOfMatrix = size;
        matrixUtilities matrix = new matrixUtilities();
        int randomMatrix1[][] = matrix.generateRandomMatrix(sizeOfMatrix);
        int randomMatrix2[][] = matrix.generateRandomMatrix(sizeOfMatrix);

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

        System.out.println("Test #" + globalCounter + " Size: " + sizeOfMatrix);
        System.out.println("The classical algorithm took " + classicalTime + " milliseconds");
        System.out.println("The divide and conquer algorithm took " + divideAndConquerTime + " milliseconds");
        System.out.println("The strassen algorithm took " + strassenTime + " milliseconds\n");

        globalCounter++;
    }

    // Implements and times the given instance using our algorithms
    static void sanityCheck() {
        int actualMatrix1[][] = new int[4][4];
        int actualMatrix2[][] = new int[4][4];
        // Row 1
        actualMatrix1[0][0] = 2;
        actualMatrix1[0][1] = 0;
        actualMatrix1[0][2] = -1;
        actualMatrix1[0][3] = 6;

        // Row 2
        actualMatrix1[1][0] = 3;
        actualMatrix1[1][1] = 7;
        actualMatrix1[1][2] = 8;
        actualMatrix1[1][3] = 0;

        // Row 3
        actualMatrix1[2][0] = -5;
        actualMatrix1[2][1] = 1;
        actualMatrix1[2][2] = 6;
        actualMatrix1[2][3] = -2;

        // Row 4
        actualMatrix1[3][0] = 8;
        actualMatrix1[3][1] = 0;
        actualMatrix1[3][2] = 1;
        actualMatrix1[3][3] = 7;

        // Row 1
        actualMatrix2[0][0] = 0;
        actualMatrix2[0][1] = 1;
        actualMatrix2[0][2] = 6;
        actualMatrix2[0][3] = 3;

        // Row 2
        actualMatrix2[1][0] = -2;
        actualMatrix2[1][1] = 8;
        actualMatrix2[1][2] = 7;
        actualMatrix2[1][3] = 1;

        // Row 3
        actualMatrix2[2][0] = 2;
        actualMatrix2[2][1] = 0;
        actualMatrix2[2][2] = -1;
        actualMatrix2[2][3] = 0;

        // Row 4
        actualMatrix2[3][0] = 9;
        actualMatrix2[3][1] = 1;
        actualMatrix2[3][2] = 6;
        actualMatrix2[3][3] = -2;

        matrixUtilities matrix = new matrixUtilities();
        long startTimeClassical = System.nanoTime();
        matrix.classical(actualMatrix1, actualMatrix2);
        long endTimeClassical = System.nanoTime();
        long startTimeDivideAndConquer = System.nanoTime();
        matrix.divideAndConquer(actualMatrix1, actualMatrix2);
        long endTimeDivideAndConquer = System.nanoTime();
        long startTimeStrassen = System.nanoTime();
        matrix.strassen(actualMatrix1, actualMatrix2);
        long endTimeStrassen = System.nanoTime();

        long classicalTime = (endTimeClassical - startTimeClassical);
        long divideAndConquerTime = (endTimeDivideAndConquer - startTimeDivideAndConquer);
        long strassenTime = (endTimeStrassen - startTimeStrassen);

        System.out.println("Test: Sanity Check Size: 4");
        System.out.println("The classical algorithm took " + classicalTime + " nanoseconds");
        System.out.println("The divide and conquer algorithm took " + divideAndConquerTime + " nanoseconds");
        System.out.println("The strassen algorithm took " + strassenTime + " nanoseconds\n");

    }

}
