import javax.swing.*;

public class KarkasonFrame extends GameFrame {

    public KarkasonFrame(Player[] playerList) {
        super(playerList);
    }

    public void fillBag(int bagSize){
        this.globalBag = new KarkasonTile[bagSize]; //max 72
        for(int i = 0; i<bagSize; i++){
            this.globalBag[i] = new KarkasonTile();
        }
    }

    public void updateScores(int x, int y){
        //in case of default call: just call super method
        if(x==0 && y==0){
            super.updateScores(0,0);
        } else {
            x += Board.findBoundary(gameBoard.tiles, "north")*5-5;
            y += Board.findBoundary(gameBoard.tiles, "west")*5-5;
            this.gameBoard.tiles[x/5][y/5].content[x%5][y%5].setOwner(playerTurnIndex+1);
            ((KarkasonBoard) this.gameBoard).updateTerritoriesForEach();
            int pointsScored = this.gameBoard.countPoints(x, y);
            playerList[playerTurnIndex].addPoints(pointsScored);
            super.updateScores(0,0);
            tilesGrid.setButtonsToValue(false);
        }
    }

    public Player[] countPointsAtEndOfGame(Player[] playerList){
        for(Player p: playerList){
            p.setPoints(0);
        }
        ((KarkasonBoard) this.gameBoard).updateTerritoriesForEach();
        Cell[][] flattenedTiles = Board.flattenBoard(this.gameBoard.tiles);
        for(int x = 0; x<this.gameBoard.tiles.length*5; x++){
            for(int y = 0; y<this.gameBoard.tiles.length*5; y++){
                if(flattenedTiles[x][y].getOwner()!=0 && flattenedTiles[x][y].getOwner()!=666 && flattenedTiles[x][y].getChar()!='C'){
                    int pointsScored = this.gameBoard.countPoints(x, y);
                    if(pointsScored!=0){
                        playerList[flattenedTiles[x][y].getOwner()-1].addPoints(pointsScored);
                        ((KarkasonBoard) this.gameBoard).eraseTerritoriesRecursive(x, y,flattenedTiles[x][y], flattenedTiles, flattenedTiles[x][y].getOwner());
                    }
                }
            }
        }
        return playerList;
    }

    @Override
    public Board createBoard(){
        return new KarkasonBoard();
    }

    public void restart(Player[] playerList){
        new KarkasonFrame(playerList);
    }

    @Override
    public KarkasonLeftPanelGUI createLeftPanelGUI(){
        return new KarkasonLeftPanelGUI();
    }

    public class KarkasonLeftPanelGUI extends GameFrame.LeftPanelGUI{
        private JButton placeButton;
        private JButton skipButton;
        public KarkasonLeftPanelGUI(){
            super();
            this.fullCapacity = 4;
            this.placeButton = new JButton("place minion ?");
            this.skipButton = new JButton("skip");
            placeButton.addActionListener(e -> {
                this.spinButton.setEnabled(true);
                this.add_button.setEnabled(true);
                placeButton.setVisible(false);
                skipButton.setVisible(false);
                tilesGrid.setButtonsToValue(true);
            });
            skipButton.addActionListener(e -> {
                this.spinButton.setEnabled(true);
                this.add_button.setEnabled(true);
                placeButton.setVisible(false);
                skipButton.setVisible(false);
                tilesGrid.setButtonsToValue(false);
                updateScores(0,0);
                while(!playerList[playerTurnIndex].getIsHuman()){
                    int[] coords = playTurn();
                    updateScores(0,0);
                }
            });
            placeButton.setVisible(false);
            skipButton.setVisible(false);
            this.add(placeButton);
            this.add(skipButton);
        }
        public void displayButtons(){
            this.spinButton.setEnabled(false);
            this.add_button.setEnabled(false);
            placeButton.setVisible(true);
            skipButton.setVisible(true);
        }
    }
}
