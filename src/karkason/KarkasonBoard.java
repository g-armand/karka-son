package karkason;

import karkason.Board;
import karkason.Cell;
import karkason.KarkasonTile;

import java.util.HashSet;

public class KarkasonBoard extends Board {

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

    public int getScoreOfTerritory(int x, int y){
        Cell[][] flattenedTiles = Board.flattenBoard(this.tiles);
        if(flattenedTiles[x][y].getChar()=='.'){
            System.out.println(flattenedTiles[x][y].getChar());
        }
        int specialModifierFlagCounter = 0; //for cities only
        HashSet<int[]> visitedCoordinates = new HashSet<>();
        HashSet<int[]> tilesCoordinates = getTilesOfTerritory(x, y, flattenedTiles[x][y], flattenedTiles, visitedCoordinates);
        HashSet<int[]> finalTilesCoordinates = new HashSet<>();
        for(int[] coordinates: tilesCoordinates){
            if(coordinates[0]==-1 && coordinates[1]==-1){
                return 0;
            }
            if(!containsArray(finalTilesCoordinates, coordinates)){
                finalTilesCoordinates.add(coordinates);
            }
        }
        return finalTilesCoordinates.size()*2;
    }

    public HashSet<int[]> getTilesOfTerritory(int x, int y, Cell startCell, Cell[][] cellMatrix, HashSet<int[]> visitedCoordinates){
        HashSet<int[]> tilesCoordinates = new HashSet<>();
        int[] currentTileCoordinates = {x/5, y/5};
        visitedCoordinates.add(new int[]{x, y});
        if (startCell.getChar()=='t'){
            if(cellMatrix[x-1][y].getChar()=='.' ||
                cellMatrix[x+1][y].getChar()=='.' ||
                cellMatrix[x][y-1].getChar()=='.' ||
                cellMatrix[x][y+1].getChar()=='.'){
                int[] impossibleNeighborFlag = {-1,-1};
                tilesCoordinates.add(impossibleNeighborFlag);
                return tilesCoordinates;
            }
        }
        if (cellMatrix[x][y].getChar() == 'c' &&
                ( (cellMatrix[x+1][y].getChar()=='f' || cellMatrix[x+1][y].getChar()=='t' || cellMatrix[x+1][y].getOwner()==-1 || cellMatrix[x+1][y].getChar()== '.')
                ||(cellMatrix[x-1][y].getChar()=='f' || cellMatrix[x-1][y].getChar()=='t' || cellMatrix[x-1][y].getOwner()==-1 || cellMatrix[x-1][y].getChar()== '.')
                ||(cellMatrix[x][y+1].getChar()=='f' || cellMatrix[x][y+1].getChar()=='t' || cellMatrix[x][y+1].getOwner()==-1 || cellMatrix[x][y+1].getChar()== '.')
                ||(cellMatrix[x][y-1].getChar()=='f' || cellMatrix[x][y-1].getChar()=='t' || cellMatrix[x][y-1].getOwner()==-1 || cellMatrix[x][y-1].getChar()== '.') )
        ){
            int[] impossibleNeighborFlag = {-1,-1};
            tilesCoordinates.add(impossibleNeighborFlag);
            return tilesCoordinates;
        }
        if(x>0
            && y>0
            && x<cellMatrix.length-1
            && y<cellMatrix[0].length-1
            && cellMatrix[x][y].getOwner() == startCell.getOwner()
            && Character.toLowerCase(cellMatrix[x][y].getChar()) ==startCell.getChar()){
            tilesCoordinates.add(currentTileCoordinates);
//                tilesCoordinates.addAll(getTilesOfTerritory(x-1,y, startCell, cellMatrix));
//                tilesCoordinates.addAll(getTilesOfTerritory(x+1,y, startCell, cellMatrix));
//                tilesCoordinates.addAll(getTilesOfTerritory(x,y-1, startCell, cellMatrix));
//                tilesCoordinates.addAll(getTilesOfTerritory(x,y+1, startCell, cellMatrix));

                if(cellMatrix[x-1][y].getOwner()==startCell.getOwner()
                   && !containsArray(visitedCoordinates, new int[]{x-1, y})){
                    visitedCoordinates.add(new int[]{x-1, y});
                    tilesCoordinates.addAll(getTilesOfTerritory(x-1,y, startCell, cellMatrix, visitedCoordinates));
                }
                if(cellMatrix[x+1][y].getOwner()==startCell.getOwner()
                        && !containsArray(visitedCoordinates, new int[]{x+1, y})){
                    visitedCoordinates.add(new int[]{x+1, y});
                    tilesCoordinates.addAll(getTilesOfTerritory(x+1,y, startCell, cellMatrix, visitedCoordinates));
                }
                if(cellMatrix[x][y-1].getOwner()==startCell.getOwner()
                        && !containsArray(visitedCoordinates, new int[]{x, y-1})){
                    visitedCoordinates.add(new int[]{x, y-1});
                    tilesCoordinates.addAll(getTilesOfTerritory(x,y-1, startCell, cellMatrix, visitedCoordinates));
                }
                if(cellMatrix[x][y+1].getOwner()==startCell.getOwner()
                        && !containsArray(visitedCoordinates, new int[]{x, y+1})){
                    visitedCoordinates.add(new int[]{x, y+1});
                    tilesCoordinates.addAll(getTilesOfTerritory(x,y+1, startCell, cellMatrix, visitedCoordinates));
                }

        }
        return tilesCoordinates;
    }

    private boolean containsArray(HashSet<int[]> set, int[] array){
        for(int[] tab: set){
            if(array[0]== tab[0] && array[1] == tab[1]){
                return true;
            }
        }
        return false;
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
