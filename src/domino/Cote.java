package domino;
import java.util.Random;


public class Cote {
    int[] cases = new int[3];
    Direction oriented;


    public Cote(Direction oriented) {
        Random a = new Random();
        this.oriented = oriented;
        this.cases[0] = a.nextInt(3);
        this.cases[1] = a.nextInt(3);
        this.cases[2] = a.nextInt(3);
    }

    public boolean estJoignable(Cote b){
        return this.cases[0] == b.cases[0] && this.cases[1] == b.cases[1] && this.cases[3] == b.cases[3];
    }

}
