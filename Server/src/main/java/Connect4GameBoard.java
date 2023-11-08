import java.lang.reflect.Array;
import java.util.ArrayList;

public class Connect4GameBoard {


    // Connect4 gameboard represented as 2D string array where strings represent the piece at that position
    // R -> Red
    // Y - > Yellow
    // G -> empty place
    private String [][] gameBoard;
    public Connect4GameBoard() {
        init_game_Board();
    }

    private void init_game_Board() {
        gameBoard = new String[6][7];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                gameBoard[i][j] = "G";
            }
        }
    }

    // Input:       int row, int col
    // Ouput:       boolean
    // Function:    Returns if move is legal
    public boolean is_move_legal (int row, int col) {
        // Out of bounds
        if (!is_inbound(row, col)) {
            return false;
        }

        // Position not empty
        if (gameBoard[row][col] != "G") {
            return false;
        }

        // Piece below or at the bottom of the board
        if (row == 5) {
            return true;
        } else if (gameBoard[row + 1][col] == "G") {
            return false;
        }


        return true;
    }

    public void update_GameBoard(int row, int col, String piece_color) {
        if (!is_move_legal(row, col)) {
            return;
        }

        if (is_gameover()) {
            return;
        }

        gameBoard[row][col] = piece_color;

    }

    // Returns if the connect4 game resulted in a tie or win
    public boolean is_gameover () {

        // There is a win
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (is_connect4_at_position_right_downward(i, j)) {
                    return true;
                }
            }
        }

        // Still a open position in gameboard
        if (!is_game_tie()) {
            return false;
        }

        return true;
    }

    // Returns true if there is an connect4 at that position only in Down and right position
    // **IGNORES Left and up checking of connect 4**
    private boolean is_connect4_at_position_right_downward(int row, int col) {
        if (!is_inbound(row, col)) {
            return false;
        }

        // Only need to keep track of right horizontal, right upper diagonal, right lower diagonal, down vertical

        String piece_color = gameBoard[row][col];

        // Empty position cannot have connect4 starting at that posiiton
        if (piece_color.equals("G")) {
            return false;
        }

        // Keeps track of the number of number of same color pieces
        int right_horizontal = 1;
        int right_upper_diagonal = 1;
        int right_lower_diagonal = 1;
        int down_vertical = 1;

        for (int i = 1; i < 4; i++) {
            // Right horizontal
            if (is_inbound(row, col + i) && gameBoard[row][col + i].equals(piece_color)) {
                right_horizontal+=1;
            }
            // Right upper diagonal
            if (is_inbound(row + i, col - i) && gameBoard[row + i][col - i].equals(piece_color)) {
                right_upper_diagonal+= 1;
            }
            // Right lower diagonal
            if (is_inbound(row + i, col + i) && gameBoard[row + i][col + i].equals(piece_color)) {
                right_lower_diagonal+= 1;
            }
            // Down vertical
            if (is_inbound(row + i, col) && gameBoard[row + i][col].equals(piece_color)) {
                down_vertical+=1;
            }
        }

        return (right_horizontal == 4 || right_upper_diagonal == 4 || right_lower_diagonal == 4 || down_vertical == 4);

    }

    // Returns true if the row and col is inbounds
    private boolean is_inbound(int row, int col) {
        if (row < 0 || row >= 6 || col < 0 || col >= 7) {
            return false;
        }
        return true;
    }

    // Given a gameboard 6x7, sets the gameboard
    public void setGameBoard(String [][] gameBoard) {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                this.gameBoard[i][j] = gameBoard[i][j];
            }
        }
    }

    public String [][] getGameBoard() {
        return gameBoard;
    }

    public void print_gameboard() {
        System.out.println("");
        for (int i = 0; i < 6; i++) {
            String row = "";
            for (int j = 0; j < 7; j++) {
                row+= gameBoard[i][j] + " ";
            }
            System.out.println(row);
        }
    }

    // **ASSUMES GAME IS OVER**
    public boolean is_game_tie() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if(gameBoard[i][j].equals("G")) {
                    return false;
                }
            }
        }

        return true;
    }

    public String get_piece_color_who_won () {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (is_connect4_at_position_right_downward(i, j)) {
                    return gameBoard[i][j];
                }
            }
        }
        return null;
    }

    // Assuming that a someone has won the game will return the connect4 tuple position of the winning move
    // [[row1, col1], [row2, col2], [row3, col3], [row4, col4]]
    public ArrayList<ArrayList<Integer>> get_winning_connect4_positions() {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (is_connect4_at_position_right_downward(i, j)) {
                    return helper_get_winning_connect4_positions(i, j);
                }
            }
        }

        return null;
    }

    // Assuming current row, col is the start of a connect 4 will return array whith all the rows and cols
    private ArrayList<ArrayList<Integer>> helper_get_winning_connect4_positions(int row, int col) {
        if (!is_inbound(row, col)) {
            return null;
        }

        // Only need to keep track of right horizontal, right upper diagonal, right lower diagonal, down vertical

        String piece_color = gameBoard[row][col];

        // Empty position cannot have connect4 starting at that posiiton
        if (piece_color.equals("G")) {
            return null;
        }

        // Keeps track of the number of number of same color pieces
        int right_horizontal = 1;
        int right_upper_diagonal = 1;
        int right_lower_diagonal = 1;
        int down_vertical = 1;

        for (int i = 1; i < 4; i++) {
            // Right horizontal
            if (is_inbound(row, col + i) && gameBoard[row][col + i].equals(piece_color)) {
                right_horizontal+=1;
            }
            // Right upper diagonal
            if (is_inbound(row + i, col - i) && gameBoard[row + i][col - i].equals(piece_color)) {
                right_upper_diagonal+= 1;
            }
            // Right lower diagonal
            if (is_inbound(row + i, col + i) && gameBoard[row + i][col + i].equals(piece_color)) {
                right_lower_diagonal+= 1;
            }
            // Down vertical
            if (is_inbound(row + i, col) && gameBoard[row + i][col].equals(piece_color)) {
                down_vertical+=1;
            }
        }

        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            if (right_horizontal == 4) {
                temp.add(row);
                temp.add(col + i);
            } else if (right_lower_diagonal == 4) {
                temp.add(row + i);
                temp.add(col + i);
            } else if (right_upper_diagonal == 4) {
                temp.add(row - i);
                temp.add(col + i);
            } else if (down_vertical == 4) {
                temp.add(row + i);
                temp.add(col);
            }
            ret.add(temp);
        }

        return ret;

    }

    // Method clears the gameBoard
    public void clear_gameboard () {
        init_game_Board();
    }


}
