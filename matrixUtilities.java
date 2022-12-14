import java.util.Random;

public class matrixUtilities {

    // Implementation of Classical Matrix Multiplication
    public int[][] classical(int A[][], int B[][]) {
        int arrayLength = A.length;
        int classicalMatrix[][] = null;
        classicalMatrix = new int[arrayLength][arrayLength];
        int i, j, k;
        for (i = 0; i < arrayLength; i++) {
            for (j = 0; j < arrayLength; j++) {
                classicalMatrix[i][j] = 0;
                for (k = 0; k < arrayLength; k++) {
                    classicalMatrix[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return classicalMatrix;
    }

    // Implementation of Naive divide-and-conquer matrix multiplication
    public int[][] divideAndConquer(int A[][], int B[][]) {
        int arrayLength = A.length;
        int divideAndConquerMatrix[][] = null;
        if (arrayLength <= 1) {
            divideAndConquerMatrix = classical(A, B);
        } else {
            divideAndConquerMatrix = recursiveMultiply(A, B, 0, 0, 0, 0, arrayLength);
        }

        return divideAndConquerMatrix;
    }

    // Divide the matrix in 2 recursively
    private int[][] recursiveMultiply(int[][] A, int[][] B, int rowA, int colA, int rowB, int colB, int size) {
        int recursiveMultiplyMatrix[][] = new int[size][size];
        if (size == 1) {
            recursiveMultiplyMatrix[0][0] = A[rowA][colA] * B[rowB][colB];
        } else {
            int newSize = size / 2;
            add(recursiveMultiplyMatrix, recursiveMultiply(A, B, rowA, colA, rowB, colB, newSize),
                    recursiveMultiply(A, B, rowA, colA + newSize, rowB + newSize, colB, newSize),
                    0, 0);

            add(recursiveMultiplyMatrix, recursiveMultiply(A, B, rowA, colA, rowB, colB + newSize, newSize),
                    recursiveMultiply(A, B, rowA, colA + newSize, rowB + newSize, colB + newSize, newSize),
                    0, newSize);

            add(recursiveMultiplyMatrix, recursiveMultiply(A, B, rowA + newSize, colA, rowB, colB, newSize),
                    recursiveMultiply(A, B, rowA + newSize, colA + newSize, rowB + newSize, colB, newSize),
                    newSize, 0);

            add(recursiveMultiplyMatrix, recursiveMultiply(A, B, rowA + newSize, colA, rowB, colB + newSize, newSize),
                    recursiveMultiply(A, B, rowA + newSize, colA + newSize, rowB + newSize, colB + newSize, newSize),
                    newSize, newSize);
        }

        return recursiveMultiplyMatrix;
    }

    // Adds the matrix
    private void add(int[][] addedMatrix, int[][] A, int[][] B, int rowC, int colC) {
        int arrayLength = A.length;
        int i, j;
        for (i = 0; i < arrayLength; i++) {
            for (j = 0; j < arrayLength; j++) {
                addedMatrix[i + rowC][j + colC] = A[i][j] + B[i][j];
            }
        }
    }

    // Implementation of Strassen???s matrix multiplication
    public int[][] strassen(int[][] A, int[][] B) {
        int arrayLength = A.length;
        int[][] strassenMatrix = new int[arrayLength][arrayLength];
        if (arrayLength < 1) {
            strassenMatrix = classical(A, B);
        } else {
            if (arrayLength == 1) {
                strassenMatrix[0][0] = A[0][0] * B[0][0];
            } else {
                int[][] A11 = new int[arrayLength / 2][arrayLength / 2];
                int[][] A12 = new int[arrayLength / 2][arrayLength / 2];
                int[][] A21 = new int[arrayLength / 2][arrayLength / 2];
                int[][] A22 = new int[arrayLength / 2][arrayLength / 2];
                int[][] B11 = new int[arrayLength / 2][arrayLength / 2];
                int[][] B12 = new int[arrayLength / 2][arrayLength / 2];
                int[][] B21 = new int[arrayLength / 2][arrayLength / 2];
                int[][] B22 = new int[arrayLength / 2][arrayLength / 2];

                split(A, A11, 0, 0);
                split(A, A12, 0, arrayLength / 2);
                split(A, A21, arrayLength / 2, 0);
                split(A, A22, arrayLength / 2, arrayLength / 2);
                split(B, B11, 0, 0);
                split(B, B12, 0, arrayLength / 2);
                split(B, B21, arrayLength / 2, 0);
                split(B, B22, arrayLength / 2, arrayLength / 2);

                int[][] M1 = strassen(strassenAdd(A11, A22), strassenAdd(B11, B22));
                int[][] M2 = strassen(strassenAdd(A21, A22), B11);
                int[][] M3 = strassen(A11, sub(B12, B22));
                int[][] M4 = strassen(A22, sub(B21, B11));
                int[][] M5 = strassen(strassenAdd(A11, A12), B22);
                int[][] M6 = strassen(sub(A21, A11), strassenAdd(B11, B12));
                int[][] M7 = strassen(sub(A12, A22), strassenAdd(B21, B22));

                int[][] newMatrix11 = strassenAdd(sub(strassenAdd(M1, M4), M5), M7);
                int[][] newMatrix12 = strassenAdd(M3, M5);
                int[][] newMatrix21 = strassenAdd(M2, M4);
                int[][] newMatrix22 = strassenAdd(sub(strassenAdd(M1, M3), M2), M6);

                join(newMatrix11, strassenMatrix, 0, 0);
                join(newMatrix12, strassenMatrix, 0, arrayLength / 2);
                join(newMatrix21, strassenMatrix, arrayLength / 2, 0);
                join(newMatrix22, strassenMatrix, arrayLength / 2, arrayLength / 2);
            }
        }

        return strassenMatrix;
    }

    // Subtracts Matrices
    private int[][] sub(int[][] A, int[][] B) {
        int arrayLength = A.length;
        int[][] subtractedMatrix = new int[arrayLength][arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            for (int j = 0; j < arrayLength; j++) {
                subtractedMatrix[i][j] = A[i][j] - B[i][j];
            }
        }

        return subtractedMatrix;
    }

    // Adds Matrices
    private int[][] strassenAdd(int[][] A, int[][] B) {
        int arrayLength = A.length;
        int[][] addedMatrix = new int[arrayLength][arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            for (int j = 0; j < arrayLength; j++) {
                addedMatrix[i][j] = A[i][j] + B[i][j];
            }
        }

        return addedMatrix;
    }

    // Splits the values of arrays
    private void split(int[][] parent, int[][] child, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < child.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < child.length; j1++, j2++) {
                child[i1][j1] = parent[i2][j2];
            }
        }
    }

    // Joins the values of arrays with matrices
    private void join(int[][] child, int[][] parent, int iB, int jB) {
        for (int i1 = 0, i2 = iB; i1 < child.length; i1++, i2++) {
            for (int j1 = 0, j2 = jB; j1 < child.length; j1++, j2++) {
                parent[i2][j2] = child[i1][j1];
            }
        }
    }

    // Prints matrix after reading the array
    public void printMatrix(int A[][]) {
        int arrayLength = A.length;
        int i, j;
        for (i = 0; i < arrayLength; i++) {
            for (j = 0; j < arrayLength; j++) {
                System.out.println(A[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Generate random matrix by filling matrix with random numbers
    public int[][] generateRandomMatrix(int n) {
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
