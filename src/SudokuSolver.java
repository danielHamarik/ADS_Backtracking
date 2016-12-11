
public class SudokuSolver {

    public static void main(String[] args) {
        int[][] sudoku =
                {
                    {1,0,6,0,0,0,0,3,0},
                    {9,0,0,1,0,0,0,0,0},
                    {0,0,4,0,0,0,5,0,0},
                    {0,0,7,0,0,0,6,5,0},
                    {2,1,0,0,0,6,0,8,0},
                    {0,6,0,4,0,0,0,0,0},
                    {0,7,2,0,0,0,0,0,0},
                    {0,8,0,0,3,1,0,7,9},
                    {4,0,0,7,8,0,0,2,0},
                };

        SudokuSolver sudokuSolver = new SudokuSolver(sudoku);
        System.out.println(sudokuSolver.solve());
    }


    private final int SIZE = 9;
    private final int ROOT = 3;
    private int[][] sudoku;

    public SudokuSolver(int[][] sudoku) {
        this.sudoku = sudoku;
    }

    public boolean solve() {
        return solve(0, 0);
    }

    private boolean solve(int row, int column) {
        if (column >= SIZE) {
            column = 0;
            row++;
        }
        if (row >= SIZE) {
            //Solution found - done
            printSudoku(sudoku);
            return true;
        }
        if (sudoku[row][column] != 0) {
            return solve(row, column + 1);
        } else {
            for (int i = 0; i < SIZE; i++) {
                if (validPosition(row, column, (i + 1))) {
                    sudoku[row][column] = (i + 1);
                    if(solve(row, column + 1))return true;
                    else {
                        sudoku[row][column] = 0;
                    }
                }
            }
            return false;
        }
    }

    private boolean validPosition(int row, int column, int value){
        return validRow(row, value) && validColumn(column, value) && validDiagonal(row, column, value);
    }
    private boolean validRow(int row, int value){
        for(int i = 0; i < SIZE; i++){
            if(sudoku[row][i] == value) return false;
        }
        return true;
    }
    private boolean validColumn(int column, int value){
        for(int i = 0; i < SIZE; i++){
            if(sudoku[i][column] == value) return false;
        }
        return true;
    }
    private boolean validDiagonal(int row, int column, int value){
        int startRow = row - (row % ROOT);
        int startColumn = column - (column % ROOT);
        for (int i = 0; i < ROOT; i++) {
            for (int j = 0; j < ROOT; j++) {
                if (sudoku[startRow + i][startColumn + j] == value) return false;
            }
        }
        return true;
    }


    private void printSudoku(int[][] sudoku) {
        for (int i = 0; i < sudoku.length; i++) {
            if ((i % ROOT) == 0) insertLine(sudoku.length);
            for (int j = 0; j < sudoku[i].length; j++) {
                if ((j % ROOT) == 0) System.out.print("|");
                System.out.print(" " + sudoku[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void insertLine(int n) {
        for (int i = 0; i < (n * 3); i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}