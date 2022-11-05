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

    

}
