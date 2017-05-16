package reflect;

/**
 * Created by lpf on 17/4/26.
 */
class Point{

    private int x;
    protected int y;

    public Point() {

    }
    private Point(int x) {
        this.x = x;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
