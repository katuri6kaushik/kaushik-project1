import java.util.*;

public class TicTacToe {

    static void displayBoard(char[] board) {
        System.out.println("-|-|-");
        System.out.println(board[7] + "|" + board[8] + "|" + board[9]);
        System.out.println("-|-|-");
        System.out.println(board[4] + "|" + board[5] + "|" + board[6]);
        System.out.println("-|-|-");
        System.out.println(board[1] + "|" + board[2] + "|" + board[3]);
        System.out.println("-|-|-");
    }

    static char[] playerInput(Scanner sc) {
        char marker;
        while (true) {
            System.out.print("Player 1 choose X or O: ");
            marker = sc.next().toUpperCase().charAt(0);

            if (marker == 'X' || marker == 'O')
                break;
        }

        if (marker == 'X')
            return new char[] { 'X', 'O' };
        else
            return new char[] { 'O', 'X' };
    }

    static void placeMarker(char[] board, char marker, int position) {
        board[position] = marker;
    }

    static boolean winCheck(char[] board, char mark) {

        return (board[7] == mark && board[8] == mark && board[9] == mark) ||
                (board[4] == mark && board[5] == mark && board[6] == mark) ||
                (board[1] == mark && board[2] == mark && board[3] == mark) ||

                (board[7] == mark && board[4] == mark && board[1] == mark) ||
                (board[8] == mark && board[5] == mark && board[2] == mark) ||
                (board[9] == mark && board[6] == mark && board[3] == mark) ||

                (board[7] == mark && board[5] == mark && board[3] == mark) ||
                (board[9] == mark && board[5] == mark && board[1] == mark);
    }

    static String chooseFirst() {
        Random r = new Random();
        if (r.nextInt(2) == 0)
            return "Player1";
        else
            return "Player2";
    }

    static boolean spaceCheck(char[] board, int position) {
        return board[position] == ' ';
    }

    static boolean fullBoardCheck(char[] board) {
        for (int i = 1; i <= 9; i++) {
            if (board[i] == ' ')
                return false;
        }
        return true;
    }

    static int playerChoice(char[] board, Scanner sc) {
        int position;

        while (true) {
            System.out.print("Choose position (1-9): ");
            position = sc.nextInt();

            if (position >= 1 && position <= 9 && spaceCheck(board, position))
                break;
        }
        return position;
    }

    static boolean replay(Scanner sc) {
        System.out.print("Play again? yes or no: ");
        String choice = sc.next();
        return choice.equalsIgnoreCase("yes");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe");

        while (true) {

            char[] board = new char[10];
            Arrays.fill(board, ' ');

            char[] markers = playerInput(sc);
            char player1 = markers[0];
            char player2 = markers[1];

            String turn = chooseFirst();
            System.out.println(turn + " will go first");

            System.out.print("Ready to play? y or n: ");
            String ready = sc.next();

            boolean gameOn = ready.equalsIgnoreCase("y");

            while (gameOn) {

                if (turn.equals("Player1")) {

                    displayBoard(board);
                    int position = playerChoice(board, sc);

                    placeMarker(board, player1, position);

                    if (winCheck(board, player1)) {
                        displayBoard(board);
                        System.out.println("Player1 wins!");
                        gameOn = false;
                    }

                    else if (fullBoardCheck(board)) {
                        displayBoard(board);
                        System.out.println("Tie Game!");
                        gameOn = false;
                    }

                    else
                        turn = "Player2";
                }

                else {

                    displayBoard(board);
                    int position = playerChoice(board, sc);

                    placeMarker(board, player2, position);

                    if (winCheck(board, player2)) {
                        displayBoard(board);
                        System.out.println("Player2 wins!");
                        gameOn = false;
                    }

                    else if (fullBoardCheck(board)) {
                        displayBoard(board);
                        System.out.println("Tie Game!");
                        gameOn = false;
                    }

                    else
                        turn = "Player1";
                }
            }

            if (!replay(sc))
                break;
        }

        sc.close();
    }
}