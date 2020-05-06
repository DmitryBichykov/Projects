package game;

import javax.swing.*;

public class GameButton extends JButton {

    private int buttonIndex;
    private GameBoard board;

    public GameButton(int buttonIndex,GameBoard board){
        this.buttonIndex=buttonIndex;
        this.board=board;

        int rowNumber=buttonIndex / board.getDimension();
        int cellNumber=buttonIndex % board.getDimension();

        setSize(board.getCellSize()-5,board.getCellSize()-5);
        addActionListener(new GameActionListener(rowNumber,cellNumber,this));

    }

    public GameBoard getBoard() {
        return board;
    }
}
