public class matrixUtilities {

    public int[][] classical(int A[][], int B[][]) {
        int arrayLength = A.length;
        int newMatrix[][]= new int[arrayLength][arrayLength];
        int i, j, k;
        for (i = 0; i < arrayLength; i++) {
            for (j = 0; j < arrayLength; j++) {
                newMatrix[i][j] = 0;
                for (k = 0; k < arrayLength; k++) {
                    newMatrix[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return newMatrix;
    }

    public int[][] divideAndConquer(int A[][], int B[][]) {
        int arrayLength = A.length;
        int newMatrix[][]= new int[arrayLength][arrayLength];
        if (arrayLength <= 1) {
            newMatrix = classical(A, B);
        } else {
            newMatrix = recursiveMultiply(A, B, 0, 0, 0, 0, 16);
        }

        return newMatrix;
    }

    private int[][] recursiveMultiply(int[][] A, int[][] B, int rowA, int colA, int rowB, int colB, int size) {
        int newMatrix[][]= new int[size][size];
        if (size == 1) {
            newMatrix[0][0] = A[rowA][colA] * B[rowB][colB];
        } else {
            int newSize = size / 2;
            // C11 = A11 * B11 + A12 * B21
            add(newMatrix, recursiveMultiply(A, B, rowA, colA, rowB, colB, newSize),//A11*B11
            recursiveMultiply(A, B, rowA, colA + newSize, rowB + newSize, colB, newSize), //A12*B21
            0, 0);//C11

            // C12 = A11 * B12 + A12 * B22
            add(newMatrix, recursiveMultiply(A, B, rowA, colA, rowB, colB + newSize, newSize),//A11*B12
            recursiveMultiply(A, B, rowA, colA + newSize, rowB + newSize, colB + newSize, newSize),//A12*B22
            0, newSize);//C12

            // C21 = A21 * B11 + A22 * B21
            add(newMatrix, recursiveMultiply(A, B, rowA + newSize, colA, rowB, colB, newSize),//A21*B11
            recursiveMultiply(A, B, rowA + newSize, colA + newSize, rowB + newSize, colB, newSize),//A22*B21
            newSize, 0);//C21

            // C22 = A21 * B12 + A22 * B22
            add(newMatrix, recursiveMultiply(A, B, rowA + newSize, colA, rowB, colB + newSize, newSize),//A21*B12
            recursiveMultiply(A, B, rowA + newSize, colA + newSize, rowB + newSize, colB + newSize, newSize), //A22*B22
            newSize, newSize);//C22
        }

        return newMatrix;
    }

    private void add(int[][] newMatrix, int[][] A, int[][] B, int rowC, int colC) {
        int arrayLength = A.length;
        int i, j;
        for (i = 0; i < arrayLength; i++) {
            for (j = 0; j < arrayLength; j++) {
                newMatrix[i + rowC][j + colC] = A[i][j] + B[i][j];
            }
        }
    }

}
