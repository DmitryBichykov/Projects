package game;

public class GamePlayer {

    private char playerSign;
    private boolean RealPlayer=true;

    public GamePlayer(char playerSign, boolean RealPlayer) {
        this.playerSign = playerSign;
        this.RealPlayer = RealPlayer;
    }

    public char getPlayerSign() {
        return playerSign;
    }
}
