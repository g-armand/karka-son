package karkason;

import java.awt.*;

public class DominoFrame extends GameFrame {

    public DominoFrame(Player[] playerList) {
        super(playerList);
        this.globalBag = new DominoTile[28];
        main_panel.add(left_panel, BorderLayout.WEST);
        for(int i = 0; i<28; i++){
            this.globalBag[i] = new DominoTile();
        }
    }

    @Override
    public Board createBoard(){
        return new DominoBoard();
    }

    public void restart(Player[] playerList){
        new DominoFrame(playerList);
    }
    public void updateScores(int x, int y){
        if(!(x==0 && y==0)){
            int pointsScored = gameBoard.countPoints(x-1,y-1);
            playerList[playerTurnIndex].points = playerList[playerTurnIndex].points + pointsScored;
        }
        super.updateScores(0,0);
    }


}
