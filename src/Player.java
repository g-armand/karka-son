public class Player {
    private boolean isHuman;
    private String name;
    int points;

    public Player(String name){
        this(name, 0);
    }

    public Player(String name, int points){
        this.setName(name);
        this.points = points;
        setIsHuman(true);
    }

    public void setIsHuman(boolean value){
        this.isHuman = value;
    }

    public boolean getIsHuman(){
        return isHuman;
    }

    public void setName(String newName){
        this.name = newName;
    }
    public String getName(){
        return this.name;
    }

    public void setPoints(int points){
        this.points = points;
    }
    public int getPoints(){
        return this.points;
    }
    public void addPoints(int addedPoints){
        this.points+=addedPoints;
    }
}
