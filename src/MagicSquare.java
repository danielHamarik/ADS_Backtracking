public class MagicSquare {

    public static void main(String[] args) {
        MagicSquare square = new MagicSquare(3);
        square.solveMagicSquare();
    }

    private int size;
    private int[][] matrix;
    private boolean[] unused;
    private int magicSum;
    private int solutions;

    public MagicSquare(int size) {
        this.size = size;
        matrix = createSquare(size);
        unused = new boolean[size * size];
        for (int i = 0; i < unused.length; i++) {
            unused[i] = true;
        }
        magicSum = (int) ((size * (Math.pow(size, 2) + 1)) / 2);
        solutions = 0;
    }

    public void solveMagicSquare() {
        solveMagicSquare(0, 0);
        System.out.println("Number of solutions : "+solutions);
    }

    private void solveMagicSquare(int row, int column) {
        if (column >= size) {
            column = 0;
            row++;
        }
        if (row >= size) {
            printMatrix();
            solutions++;
        } else {
            for (int i = 0; i < unused.length; i++) {
                if (unused[i]) {
                    matrix[row][column] = i + 1;
                    unused[i] = false;
                    if (!wrongPos(row, column)) {
                        solveMagicSquare(row, column + 1);
                    }
                    matrix[row][column] = 0;
                    unused[i] = true;
                }
            }
        }
    }

    private boolean wrongPos(int row, int col){
        return wrongRow(row) || wrongColumn(col) || wrongDiag(row, col);
    }

    private boolean wrongRow(int row) {
        int sum = 0;
        for(int i = 0; i < size; i++){
            if(matrix[row][i]==0)return false;
            sum+= matrix[row][i];
        }
        return sum!=magicSum;
    }
    private boolean wrongColumn(int column) {
        int sum = 0;
        for(int i = 0; i < size; i++){
            if(matrix[i][column]==0)return false;
            sum+= matrix[i][column];
        }
        return sum!=magicSum;
    }
    private boolean wrongDiag(int row, int col){
        return wrongDiag1(row, col) || wrongDiag2(row, col);
    }
    private boolean wrongDiag1(int row, int col){
        if(row!=col)return false;
        int sum = 0;
        for(int i = 0; i < size; i++){
            if(matrix[i][i] == 0) return false;
            sum+=matrix[i][i];
        }
        return sum!=magicSum;
    }
    private boolean wrongDiag2(int row, int col){
        if((row+col)!=(size+1))return false;
        int sum = 0;
        for(int i = 0; i <size; i++){
            if(matrix[i][(size-1)-i]==0)return false;
            sum+=matrix[i][(size-1)-i];
        }
        return sum!=magicSum;
    }

    private void printMatrix() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(" " + matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    private int[][] createSquare(int size) {
        int[][] square = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                square[i][j] = 0;
            }
        }
        return square;
    }

}
