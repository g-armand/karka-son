package domino;

public class Plateau {
    Tuile[][] tuiles = new Tuile[1][1];
    static int tilesTotal = 0;
    public Plateau(){

    }

    public void addTuile(Tuile t, int x, int y){
        tilesTotal ++;
        Tuile[][] tuilesUpdated = new Tuile[tilesTotal*2-1][tilesTotal*2-1];
        //verifier si joinable

        //recopie le this.tuiles dans tuilesUpdated

        //ajouter t dans tuilesUpdated

        this.tuiles = tuilesUpdated;

    }

}
