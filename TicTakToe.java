package zadania.TicTakToe;
import java.util.Scanner;

public class TicTakToe {
    protected final String x = " X ", o = " O ", empty = "   ";
    protected String[][] board;
    protected String currentGamer;
    protected Scanner scanner = new Scanner(System.in);
    StatusOfGame status;

    public void showGrid(int size) {

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j]);
                if (j != size - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i != size - 1) {
                for (int k = 0; k < size; k++) {
                    System.out.print("----");
                }
                System.out.println();
            }
        }
    }

    public void start(int size) {
        board = new String[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = "   ";
            }
        }
        currentGamer = x;
        showGrid(size);
    }

    public void setCoordinates(int size) {

        boolean isCorrect = false;
        do {
            System.out.println("Gamer " + currentGamer + ", enter coordinates: row from 1 to " + size + " and column from 1 to " + size);
            int row = scanner.nextInt() - 1;
            int column = scanner.nextInt() - 1;
            if (row >= 0 && row < size && column >= 0 && column < size && board[row][column].equals(empty) && board[row][column].equals(empty)) {
                board[row][column] = currentGamer;
                isCorrect = true;
            } else {
                System.out.println("The entered coordinates are not available. Try again");
            }
        }
        while (!isCorrect);
    }

    public String findWinner(int size) {

        for (int row = 0; row < size; row++) {
            int iterator_1 = 0;
            for (int j = 0; j < size; j++) {
                if (!board[row][0].equals(empty) && board[row][0].equals(board[row][j])) {
                    iterator_1++;
                }
                if (iterator_1 == size) {
                    return board[row][0];
                }
            }
        }

        for (int col = 0; col < size; col++) {
            int iterator_2 = 0;
            for (int j = 0; j < size; j++) {
                if (!board[0][col].equals(empty) && board[0][col].equals(board[j][col])) {
                    iterator_2++;
                }
                if (iterator_2 == size) {
                    return board[0][col];
                }
            }
        }

        int iterator_3 = 0;
        for (int i = 0; i < size; i++) {
            if (!board[0][0].equals(empty) && board[0][0].equals(board[i][i])) {
                iterator_3++;
            }
            if (iterator_3 == size) {
                return board[0][0];
            }
        }

        int iterator_4 = 0;
        for (int i = size; i > 0; i--) {
            if (!board[0][size - 1].equals(empty) && board[0][size - 1].equals(board[i-1][size-i])) {
                iterator_4++;
            }
            if (iterator_4 == size) {
                return board[0][size - 1];
            }
        }
        return "game continues";
    }

    public boolean boardIsFull(int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].equals(empty)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void analizeOfBoard(int size) {
        String winner = findWinner(size);
        if (winner.equals(x)) {
            status = StatusOfGame.X_WON;
        } else if (winner.equals(o)) {
            status = StatusOfGame.O_WON;
        } else if (boardIsFull(size)) {
            status = StatusOfGame.TIE;
        } else {
            status = StatusOfGame.GAME_CONTINUES;
        }
    }

    protected void initialization() {
        System.out.println("Set the size of the board");
        int size = scanner.nextInt();
        if (size < 3) {
            System.out.println("Dimension of board must be above 3. Try again");
            initialization();
        }

        start(size);
        do {
            setCoordinates(size);
            analizeOfBoard(size);
            showGrid(size);
            if (status == StatusOfGame.X_WON) {
                System.out.println("X won! Congratulations!");
            } else if (status == StatusOfGame.O_WON) {
                System.out.println("O won! Congratulations!");
            } else if (status == StatusOfGame.TIE) {
                System.out.println("Tie...");
            }
            currentGamer = currentGamer.equals(x) ? o : x;
        }
        while (status == StatusOfGame.GAME_CONTINUES);
    }
}