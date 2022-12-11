package domino;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class KarkasonBoard extends Board implements KarkasonTileTemplate{

    public KarkasonBoard(){
        super();
        this.tiles[1][1] = new KarkasonTile();
        updateTerritoriesForEach();
    }



    public void updateTerritoriesForEach(){
        Cell[][] flattenedTiles = Board.flattenBoard(this.tiles);
        for(int x=0; x<flattenedTiles.length; x++){
            for(int y=0; y<flattenedTiles[0].length; y++){
                if(flattenedTiles[x][y].getOwner()!=0){
                    updateTerritoriesRecursive(x,y, flattenedTiles[x][y], flattenedTiles);
                }
            }
        }
        System.out.println();
    }

    public int getScoreOfTerritory(){
        Cell[][] flattenedTiles = Board.flattenBoard(this.tiles);

        int score = 0;
        return score;
    }

    public HashSet<int[]> getTilesOfTerritory(int x, int y, Cell startCell, Cell[][] cellMatrix){
        HashSet<int[]> tilesCoordinates = new HashSet<>();
        int[] currentTileCoordinates = {x/5, y/5};
        if (cellMatrix[x][y].getChar() == 'C' && startCell.getChar()=='c'){
            tilesCoordinates.add(currentTileCoordinates);
        }
        if (cellMatrix[x][y].getChar() == 'c' &&
                ( (cellMatrix[x+1][y].getChar()=='f' || cellMatrix[x+1][y].getChar()=='t' || cellMatrix[x+1][y].getOwner()==-1)
                ||(cellMatrix[x-1][y].getChar()=='f' || cellMatrix[x-1][y].getChar()=='t' || cellMatrix[x-1][y].getOwner()==-1)
                ||(cellMatrix[x][y+1].getChar()=='f' || cellMatrix[x][y+1].getChar()=='t' || cellMatrix[x][y+1].getOwner()==-1)
                ||(cellMatrix[x][y-1].getChar()=='f' || cellMatrix[x][y-1].getChar()=='t' || cellMatrix[x][y-1].getOwner()==-1) )
        ){
            int[] impossibleNeighborFlag = {-1,-1};
            tilesCoordinates.add(impossibleNeighborFlag);
            return tilesCoordinates;
        }
        if(x>0
            && y>0
            && x<cellMatrix.length-1
            && y<cellMatrix[0].length-1
            && cellMatrix[x][y].getOwner()== startCell.getOwner()
            && cellMatrix[x][y].getChar()==startCell.getChar()){
                tilesCoordinates.add(currentTileCoordinates);
                tilesCoordinates.addAll(getTilesOfTerritory(x-1,y, startCell, cellMatrix));
                tilesCoordinates.addAll(getTilesOfTerritory(x+1,y, startCell, cellMatrix));
                tilesCoordinates.addAll(getTilesOfTerritory(x,y-1, startCell, cellMatrix));
                tilesCoordinates.addAll(getTilesOfTerritory(x,y+1, startCell, cellMatrix));

    //            if(cellMatrix[x-1][y].getOwner()==startCell.getOwner()){
    //                tilesCoordinates.addAll(getTilesOfTerritory(x-1,y, startCell, cellMatrix));
    //            }
    //            if(cellMatrix[x+1][y].getOwner()==startCell.getOwner()){
    //                tilesCoordinates.addAll(getTilesOfTerritory(x+1,y, startCell, cellMatrix));
    //            }
    //            if(cellMatrix[x][y-1].getOwner()==startCell.getOwner()){
    //                tilesCoordinates.addAll(getTilesOfTerritory(x,y-1, startCell, cellMatrix));
    //            }
    //            if(cellMatrix[x][y+1].getOwner()==startCell.getOwner()){
    //                tilesCoordinates.addAll(getTilesOfTerritory(x,y+1, startCell, cellMatrix));
    //            }

        }
        return tilesCoordinates;
    }

    public void updateTerritoriesRecursive(int x, int y, Cell startCell, Cell[][] cellMatrix){
        if(x>0
            && y>0
            && x<cellMatrix.length-1
            && y<cellMatrix[0].length-1){
            if(cellMatrix[x][y].getChar() == startCell.getChar()){
                cellMatrix[x][y].setOwner(startCell.getOwner());
                if(cellMatrix[x-1][y].getOwner()==0){
                    updateTerritoriesRecursive(x-1,y, startCell, cellMatrix);
                }
                if(cellMatrix[x+1][y].getOwner()==0){
                    updateTerritoriesRecursive(x+1,y, startCell, cellMatrix);
                }
                if(cellMatrix[x][y-1].getOwner()==0){
                    updateTerritoriesRecursive(x,y-1, startCell, cellMatrix);
                }
                if(cellMatrix[x][y+1].getOwner()==0){
                    updateTerritoriesRecursive(x,y+1, startCell, cellMatrix);
                }
            }
        }
    }


    public int countPoints(int x, int y){
        return 0;
    }
}
