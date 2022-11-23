package domino;

public enum Direction {
    North, South, West, East;

    public Direction opposed(){
        if(this.equals(North)){
            return South;
        } else if (this.equals(South)) {
            return North;
        } else if (this.equals(East)) {
            return West;
        } else {
            return East;
        }
    }

    public Direction toRight(){
        if(this.equals(North)){
            return East;
        } else if (this.equals(South)) {
            return West;
        } else if (this.equals(East)) {
            return South;
        } else {
            return North;
        }
    }

    public Direction toLeft(){
        return this.toRight().toRight().toRight();
    }
}
