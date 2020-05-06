package game;

import javax.swing.*;

public class Game {

    private GameBoard board;
    private GamePlayer[] gamePlayers=new GamePlayer[2];
    private int playerTurn=0;

    public Game() {
        this.board = new GameBoard(this);
    }

    //инициализация игры
    public void initGame(){
        gamePlayers[0]=new GamePlayer('X',true);
        gamePlayers[1]=new GamePlayer('O',false);
    }

    //передача хода игрока
    public void passTurn(){
        if (playerTurn==0){
            playerTurn=1;
        }else playerTurn=0;
    }

    //получение техущего игрока
    public GamePlayer getCurrentPlayer(){
        return gamePlayers[playerTurn];
    }

    //вывод информационного сообщения
    public void showMessage(String messageText){
        JOptionPane.showMessageDialog(board,messageText);
    }
}
