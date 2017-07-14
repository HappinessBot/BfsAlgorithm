import java.util.Arrays;

class Node {
    private int[][] data;
    private Point box;

    public Point getRobot() {
        return robot;
    }

    public void setRobot(Point robot) {
        this.robot = robot;
    }

    public Possition getRobotDirection() {
        return robotDirection;
    }

    public void setRobotDirection(Possition robotDirection) {
        this.robotDirection = robotDirection;
    }

    private Point robot;
    private Point startingPoint;
    private Possition robotDirection;
    
    private String path = "";

    public Node(int[][] data, Point box) {
        path = "";
        setData(data);
        setBox(box);
    }

    public void modifyPath(String newPath) {
        path = newPath;
    }

    public String getPath() {
        return path;
    }

    public Point getBox() {
        return box;
    }

    public void setBox(Point box) {
        this.box = box;
    }

    private void setData(int[][] data) {
        this.data = data;
    }

    public int[][] getData() {
        return data;
    }

    public Point getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(Point startingPoint) {
        this.startingPoint = startingPoint;
    }

    @Override
    public String toString() {
        return "{" +
                "data=" + Arrays.deepToString(data) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return Arrays.deepEquals(getData(), node.getData());
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(getData());
    }
}
