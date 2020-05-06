package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameBoard extends JFrame {

    private int dimension=3;
    private int cellSize=150;
    private char[][] gameField;
    private GameButton[] gameButton;
    private static char nullSymbol='\u0000';

    private Game game;

    public GameBoard(Game currentGame){
        this.game=currentGame;
        initField();
    }

    //инициализация поля
    public void initField(){
        setBounds(cellSize*dimension,cellSize*dimension,400,300);
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel controlPanel=new JPanel();
        JButton newGameButton=new JButton("Новая игра");
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                emptyField();
            }
        });

        controlPanel.setLayout(new BoxLayout(controlPanel,BoxLayout.Y_AXIS));
        controlPanel.add(newGameButton);
        controlPanel.setSize(cellSize*dimension,150);

        JPanel gameFieldPanel=new JPanel();
        gameFieldPanel.setLayout(new GridLayout(dimension,dimension));
        gameFieldPanel.setSize(cellSize*dimension,cellSize*dimension);
        gameField=new char[dimension][dimension];
        gameButton=new GameButton[dimension*dimension];

        for (int i=0; i<dimension*dimension;i++){
            GameButton fieldButton=new GameButton(i,this);
            gameFieldPanel.add(fieldButton);
            gameButton[i]=fieldButton;
        }

        getContentPane().add(controlPanel,BorderLayout.NORTH);
        getContentPane().add(gameFieldPanel,BorderLayout.CENTER);
        setVisible(true);
    }

    //очистка поля
    public void emptyField(){
        for (int i=0; i<dimension*dimension; i++){
            gameButton[i].setText("");

            int x=i / dimension;
            int y=i % dimension;
            gameField[x][y]=nullSymbol;
        }
    }

    //в клетку можно пойти
    public boolean isTurnable(int x, int y){
        return gameField[x][y] == nullSymbol;
    }

    //обновление поля ходов
    public void updateGameField(int x, int y){
        gameField[x][y]=game.getCurrentPlayer().getPlayerSign();
    }

    //проверка победы
    public boolean checkWin(){
        char playerSymbol=game.getCurrentPlayer().getPlayerSign();
        return checkWinDiagonals(playerSymbol) || checkWinLines(playerSymbol);
    }

    private boolean checkWinDiagonals(char playerSymbol){
        boolean mainDiagonal, sideDiagonal;
        mainDiagonal=true;
        sideDiagonal=true;

        for (int i=0; i<dimension; i++){
            mainDiagonal &= (gameField[i][i]==playerSymbol);
            sideDiagonal &= (gameField[i][dimension-1-i]==playerSymbol);
        }

        return mainDiagonal || sideDiagonal;
    }

    private boolean checkWinLines(char playerSymbol){
        boolean cols, rows;

        for (int col=0; col<dimension; col++){
            cols=true;
            rows=true;

            for (int row=0; row<dimension; row++){
                cols &= (gameField[col][row]==playerSymbol);
                rows &= (gameField[row][col]==playerSymbol);
            }

            if (cols || rows){
                return true;
            }
        }
        return false;
    }

    //ничья
    public boolean isFull(){
        for (int i=0;i<dimension;i++){
            for (int j=0;j<dimension;j++){
                if (gameField[i][j]==nullSymbol){
                    return false;
                }
            }
        }
        return true;
    }

    public char getGameField(int col,int row) {
        return gameField[col][row];
    }

    public Game getGame() {
        return game;
    }

    public int getDimension() {
        return dimension;
    }

    public int getCellSize() {
        return cellSize;
    }

    public GameButton getButton(int index) {
        return gameButton[index];
    }
}
