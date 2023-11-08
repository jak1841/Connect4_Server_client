import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CFourInfo implements Serializable {
    String message;
    int row;
    int col;

    boolean have2players;
    boolean isTurn;
    String [][] gameBoard;

    ArrayList<ArrayList<Integer>> winning_connect_4_positions = null;

    boolean go_to_play_again_screen = false;

    public boolean isGo_to_play_again_screen() {
        return go_to_play_again_screen;
    }

    public void setGo_to_play_again_screen(boolean go_to_play_again_screen) {
        this.go_to_play_again_screen = go_to_play_again_screen;
    }

    public CFourInfo (String message, int row, int col, boolean have2players, boolean isTurn, String[][] gameBoard) {
        this.message = message;
        this.row = row;
        this.col = col;
        this.have2players = have2players;
        this.isTurn = isTurn;
        deep_copy_gameBoard(gameBoard);
    }

    public String getMessage () {
        return message;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isHave2players() {
        return have2players;
    }

    public boolean isTurn() {
        return isTurn;
    }

    private void deep_copy_gameBoard(String [][] gameBoard) {
        if (gameBoard == null) {
            this.gameBoard = null;
            return;
        }
        this.gameBoard = new String[6][7];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                this.gameBoard[i][j] = gameBoard[i][j];
            }
        }
    }

    public ArrayList<ArrayList<Integer>> getWinning_connect_4_positions() {
        return winning_connect_4_positions;
    }

    public void setWinning_connect_4_positions(ArrayList<ArrayList<Integer>> winning_connect_4_positions) {
        this.winning_connect_4_positions = new ArrayList<>();
        for (int i = 0; i < winning_connect_4_positions.size(); i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(winning_connect_4_positions.get(i).get(0));
            temp.add(winning_connect_4_positions.get(i).get(1));

            this.winning_connect_4_positions.add(temp);
        }
    }

}