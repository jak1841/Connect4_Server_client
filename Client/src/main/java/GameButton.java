import javafx.scene.control.Button;

public class GameButton extends Button {

    private int row, col;

    public GameButton(int row, int col) {
        this.row = row;
        this.col = col;
        color = "G";
    }

    public String color;





    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public void change_color_yellow () {
        this.setStyle("-fx-background-color: #dbcf21;-fx-border-color: black ; -fx-border-width: 2 ;");
        color = "Y";
    }

    public void change_color_red () {
        this.setStyle("-fx-background-color: #e81717; -fx-border-color: black ; -fx-border-width: 2 ;");
        color = "R";
    }

    public void change_color_gray () {
        this.setStyle("-fx-background-color: #808080; -fx-border-color: black ; -fx-border-width: 2 ;");
        color = "G";
    }

    public void change_color_high_light () {
        String new_style = this.getStyle() + " -fx-border-color: green ; -fx-border-width: 4 ;";
        this.setStyle(new_style);
    }

}
