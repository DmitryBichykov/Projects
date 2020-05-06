package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameActionListener implements ActionListener {

    private int row;
    private int cell;
    private GameButton button;

    public GameActionListener(int row, int cell, GameButton button) {
        this.row = row;
        this.cell = cell;
        this.button = button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameBoard board=button.getBoard();

        if (board.isTurnable(row,cell)) {
            if (!updateByPlayerDate(board)) {

                if (board.isFull()) {
                    board.getGame().showMessage("Ничья!");
                    board.emptyField();
                    board.getGame().passTurn();
                } else {
                    updateByAiDate(board);
                }
            }
        }else {
            board.getGame().showMessage("Некорректный ход!");
        }
    }

    //ход человека
    public boolean updateByPlayerDate(GameBoard board){
        board.updateGameField(row,cell);
        button.setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));

        if (board.checkWin()){
            button.getBoard().getGame().showMessage("Вы выиграли!");
            board.emptyField();
            return true;
        }else{
            board.getGame().passTurn();
        }
        return false;
    }

    //ход компьютера
    public void updateByAiDate(GameBoard board){
        boolean result=false;

        //поиск возможного выигрыша
            if (!searchPossibleWinnings(board, board.getGame().getCurrentPlayer().getPlayerSign())){
                //поиск возможного проигрыша
                if (!searchPossibleLoss(board, 'X')) {
                    //если ничего то свободный ход
                    computerFreeRun(board, board.getGame().getCurrentPlayer().getPlayerSign());
                }
            }

        //проверка ваигрыша
        if (board.checkWin()){
            button.getBoard().getGame().showMessage("Компьютер выиграл!");
            board.emptyField();
            board.getGame().passTurn();
        }else{
            board.getGame().passTurn();
        }
    }

    //поиск возможного выигрыша в один ход
    private boolean searchPossibleWinnings(GameBoard board, char searchSymbol) {
        int x = 0;
        int y = 0;
        boolean result = false;
        System.out.println();
        for (int i = 0; i < board.getDimension(); i++) {
            System.out.println();
            for (int j = 0; j < board.getDimension(); j++) {
                if (board.isTurnable(i, j)) {
                    int quantityO = 0;
                    //проверка горизонтали и вертикали на возможный выигрыш
                    for (int q = 0; q < board.getDimension(); q++) {
                        if (board.getGameField(i, q) == searchSymbol) {
                            quantityO++;
                        }
                    }
                    if (board.getDimension() - quantityO == 1) {
                        y = j;
                        x = i;
                        result = true;
                        break;
                    }

                     quantityO = 0;
                     for (int q = 0; q < board.getDimension(); q++) {
                         if (board.getGameField(q, j) == searchSymbol) {
                             quantityO++;
                         }
                     }
                     if (board.getDimension() - quantityO == 1) {
                         y = j;
                         x = i;
                         result = true;
                         break;
                     }

                    quantityO = 0;
                    //проверяем главную диагональ
                    if (i==j){
                        for (int q=0; q<board.getDimension();q++){
                            if (board.getGameField(q,q)==searchSymbol){
                                quantityO++;
                            }
                        }
                        if (board.getDimension() - quantityO == 1) {
                            y = j;
                            x = i;
                            result = true;
                            break;
                        }
                    }

                    quantityO = 0;
                    //проверяем побочную диагональ
                    if (i==board.getDimension()-1-j){
                        for (int q=0; q<board.getDimension();q++){
                            if (board.getGameField(q,board.getDimension()-1-q)==searchSymbol){
                                quantityO++;
                            }
                        }
                        if (board.getDimension() - quantityO == 1) {
                            y = j;
                            x = i;
                            result = true;
                            break;
                        }
                    }
                }
            }

            if (result){
                break;
            }
        }

            if (result) {
                board.updateGameField(x, y);
                int cellIndex = board.getDimension() * x + y;
                board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
                return true;
            }
        return false;
    }

    //поиск возможного проигрыша в один ход
    private boolean searchPossibleLoss(GameBoard board, char searchSymbol) {
        int x = 0;
        int y = 0;
        boolean result = false;
        for (int i = 0; i < board.getDimension(); i++) {
            for (int j = 0; j < board.getDimension(); j++) {
                if (board.isTurnable(i, j)) {
                    int quantityX= 0;
                    //проверка горизонтали и вертикали на возможный проигрыш
                    for (int q = 0; q < board.getDimension(); q++) {
                        if (board.getGameField(i, q) == searchSymbol) {
                            quantityX++;
                        }
                    }
                    if (board.getDimension() - quantityX == 1) {
                        y = j;
                        x = i;
                        result = true;
                        break;
                    }
                    quantityX= 0;
                    for (int q = 0; q < board.getDimension(); q++) {
                        if (board.getGameField(q, j) == searchSymbol) {
                            quantityX++;
                        }
                    }
                    if (board.getDimension() - quantityX == 1) {
                        y = j;
                        x = i;
                        result = true;
                        break;
                    }
                    quantityX= 0;

                    //проверяем главную диагональ
                    if (i==j){
                        for (int q=0; q<board.getDimension();q++){
                            if (board.getGameField(q,q)==searchSymbol){
                                quantityX++;
                            }
                        }
                        if (board.getDimension() - quantityX == 1) {
                            y = j;
                            x = i;
                            result = true;
                            break;
                        }
                    }
                    quantityX= 0;

                    //проверяем побочную диагональ
                    if (i==board.getDimension()-1-j){
                        for (int q=0; q<board.getDimension();q++){
                            if (board.getGameField(q,board.getDimension()-1-q)==searchSymbol){
                                quantityX++;
                            }
                        }
                        if (board.getDimension() - quantityX == 1) {
                            y = j;
                            x = i;
                            result = true;
                            break;
                        }
                    }
                }
            }

            if (result){
                break;
            }
        }

        if (result) {
            board.updateGameField(x, y);
            int cellIndex = board.getDimension() * x + y;
            board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
            return true;
        }
        return false;
    }

    //свободный ход компьютера
    private void computerFreeRun(GameBoard board, char searchSymbol) {
        int x = -1;
        int y = -1;
        boolean result = false;
        for (int i = 0; i < board.getDimension(); i++) {
            for (int j = 0; j < board.getDimension(); j++) {
                if (board.isTurnable(i, j)) {
                    if (!result){
                        result=true;
                        y = j;
                        x = i;
                    }
                    int quantityO = 0;
                    int maxNull=0;
                    //проверка горизонтали и вертикали на лучший ход
                    for (int q = 0; q < board.getDimension(); q++) {
                        if (board.getGameField(i, q) == searchSymbol) {
                            quantityO++;
                        }
                    }
                    if (maxNull<quantityO){
                        maxNull=quantityO;
                        y = j;
                        x = i;
                    }

                    quantityO = 0;
                    for (int q = 0; q < board.getDimension(); q++) {
                        if (board.getGameField(q, j) == searchSymbol) {
                            quantityO++;
                        }
                    }
                    if (maxNull<quantityO){
                        maxNull=quantityO;
                        y = j;
                        x = i;
                    }

                    quantityO = 0;
                    //проверяем главную диагональ
                    if (i==j){
                        for (int q=0; q<board.getDimension();q++){
                            if (board.getGameField(q,q)==searchSymbol){
                                quantityO++;
                            }
                        }
                        if (maxNull<quantityO){
                            maxNull=quantityO;
                            y = j;
                            x = i;
                        }
                    }

                    quantityO = 0;
                    //проверяем побочную диагональ
                    if (i==board.getDimension()-1-j){
                        for (int q=0; q<board.getDimension();q++){
                            if (board.getGameField(q,board.getDimension()-1-q)==searchSymbol){
                                quantityO++;
                            }
                        }
                        if (maxNull<quantityO){
                            maxNull=quantityO;
                            y=i;
                            x=j;
                        }
                    }
                }
            }
        }

        board.updateGameField(x, y);
        int cellIndex = board.getDimension() * x + y;
        board.getButton(cellIndex).setText(Character.toString(board.getGame().getCurrentPlayer().getPlayerSign()));
    }
}
