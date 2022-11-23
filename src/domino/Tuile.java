package domino;

import java.util.Random;

import static domino.Direction.*;

public class Tuile {
    Cote[] cotes = new Cote[4];

    public Tuile(){
        Random a = new Random();
        int b = a.nextInt(3);
        this.cotes[0] = new Cote(North);
        this.cotes[1] = new Cote(South);
        this.cotes[2] = new Cote(West);
        this.cotes[3] = new Cote(East);
    }

}
