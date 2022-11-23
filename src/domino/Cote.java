package domino;
import java.util.Random;


public class Cote {
    int[] Case = new int[3];
    Direction oriented;

    public Cote(Direction oriented) {
        Random a = new Random();
        this.oriented = oriented;
        this.Case[0] = a.nextInt(3);
        this.Case[1] = a.nextInt(3);
        this.Case[2] = a.nextInt(3);
    }

}
