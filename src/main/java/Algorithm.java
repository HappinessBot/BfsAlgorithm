import com.sun.org.apache.xpath.internal.SourceTree;

import java.util.*;

public class Algorithm {
    private List<Node> visited;
    private Node node;
    private Point robot;

    public Algorithm() {
        robot = new Point(1,0);
        visited = new ArrayList<>();
        node = new Node(new int[][] {
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,1,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0}}, new Point(6,3 ));
        bfs(new Node(new int[][]{
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,1,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,0}}, new Point(1, 1)));
    }

    private Node bfs(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();

            if (currentNode.equals(node)) {
                System.out.println(currentNode.getPath());
                return currentNode;
            }

            if (!visited.contains(currentNode)) {
                visited.add(currentNode);
                List<Node> children = generateMatrix(currentNode);
                for (int i = 0; i < children.size(); i++ ) {
                    if (!visited.contains(children.get(i))) {
                        queue.add(children.get(i));
                    }
                }
            }
        }
        return null;
    }

    public List<Node> generateMatrix(Node node) {
        List<Node> array = new ArrayList<>();
        Node newNode;
        int[][] data = new int[node.getData().length][node.getData().length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                data[i][j] = node.getData()[i][j];
            }
        }
        if (node.getBox().getX() + 1 < node.getData().length - 1
                && node.getBox().getX() > 0
                && node.getData()[node.getBox().getY()][node.getBox().getX() + 1] == 0
                && node.getData()[node.getBox().getY()][node.getBox().getX() - 1] == 0) {

            data[node.getBox().getY()][node.getBox().getX() + 1]
                    = node.getData()[node.getBox().getY()][node.getBox().getX()];

            data[node.getBox().getY()][node.getBox().getX()] = 0;
            newNode = new Node(data, new Point(node.getBox().getX() + 1, node.getBox().getY()));
            if(node.getRobot() == null){
                newNode.modifyPath(node.getPath() + "ff");
                newNode.setRobotDirection(Possition.RIGHT);
            } else {
                if (node.getRobot().getX() == node.getBox().getX() && node.getRobot().getY() < node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "rfflfflff");
                }else if (node.getRobot().getX() < node.getBox().getX() && node.getRobot().getY() == node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "f");
                }else if (node.getRobot().getX() == node.getBox().getX() && node.getRobot().getY() > node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "lffrffrff");
                }else if (node.getRobot().getX() > node.getBox().getX() && node.getRobot().getY() == node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "rfflffflfflff");
                }

            }

            newNode.setRobot(new Point(node.getBox().getX(), node.getBox().getY()));
            array.add(newNode);
            newNode = null;

            data = new int[node.getData().length][node.getData().length];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data.length; j++) {
                    data[i][j] = node.getData()[i][j];
                }
            }

            data[node.getBox().getY()][node.getBox().getX() - 1]
                    = node.getData()[node.getBox().getY()][node.getBox().getX()];

            data[node.getBox().getY()][node.getBox().getX()] = 0;
            newNode = new Node(data, new Point(node.getBox().getX() - 1, node.getBox().getY()));

            if(node.getRobot() == null){
                newNode.modifyPath(node.getPath()+"ff");
                newNode.setRobotDirection(Possition.LEFT);
            } else {
                if (node.getRobot().getX() == node.getBox().getX() && node.getRobot().getY() < node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "lffrffrff");
                }else if (node.getRobot().getX() < node.getBox().getX() && node.getRobot().getY() == node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "lffrfffrffrff");
                }else if (node.getRobot().getX() == node.getBox().getX() && node.getRobot().getY() > node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "rfflfflff");
                }else if (node.getRobot().getX() > node.getBox().getX() && node.getRobot().getY() == node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "f");
                }

            }

            newNode.setRobot(new Point(node.getBox().getX(), node.getBox().getY()));
            array.add(newNode);
            newNode = null;

        }

        if (node.getBox().getY() + 1 < node.getData().length - 1
                && node.getBox().getY() > 0
                && node.getData()[node.getBox().getY() + 1][node.getBox().getX()] == 0
                && node.getData()[node.getBox().getY() - 1][node.getBox().getX()] == 0) {

            data = new int[node.getData().length][node.getData().length];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data.length; j++) {
                    data[i][j] = node.getData()[i][j];
                }
            }

            data[node.getBox().getY() + 1][node.getBox().getX()]
                    = node.getData()[node.getBox().getY()][node.getBox().getX()];

            data[node.getBox().getY()][node.getBox().getX()] = 0;
            newNode = new Node(data, new Point(node.getBox().getX(), node.getBox().getY() + 1));
            if(node.getRobot() == null){
                newNode.modifyPath(node.getPath() + "ff");
                newNode.setRobotDirection(Possition.DOWN);
            } else {
                if (node.getRobot().getX() == node.getBox().getX() && node.getRobot().getY() < node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "f");
                }else if (node.getRobot().getX() < node.getBox().getX() && node.getRobot().getY() == node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "lffrfffrff");
                }else if (node.getRobot().getX() == node.getBox().getX() && node.getRobot().getY() > node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "rfflffflfflff");
                }else if (node.getRobot().getX() > node.getBox().getX() && node.getRobot().getY() == node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "rfflfflff");
                }
            }

            newNode.setRobot(new Point(node.getBox().getX(), node.getBox().getY()));
            array.add(newNode);
            newNode = null;

            data = new int[node.getData().length][node.getData().length];
            for (int i = 0; i < data.length; i++) {
                for (int j = 0; j < data.length; j++) {
                    data[i][j] = node.getData()[i][j];
                }
            }

            data[node.getBox().getY() - 1][node.getBox().getX()]
                    = node.getData()[node.getBox().getY()][node.getBox().getX()];
            data[node.getBox().getY()][node.getBox().getX()] = 0;
            newNode = new Node(data, new Point(node.getBox().getX(), node.getBox().getY() - 1));

            if(node.getRobot() == null){
                newNode.modifyPath(node.getPath() + "ff");
                newNode.setRobotDirection(Possition.UP);
            } else {
                if (node.getRobot().getX() == node.getBox().getX() && node.getRobot().getY() < node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "lffrfffrffrff");
                }else if (node.getRobot().getX() < node.getBox().getX() && node.getRobot().getY() == node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "rfflfflff");
                }else if (node.getRobot().getX() == node.getBox().getX() && node.getRobot().getY() > node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "f");
                }else if (node.getRobot().getX() > node.getBox().getX() && node.getRobot().getY() == node.getBox().getY()) {
                    newNode.modifyPath(node.getPath() + "lffrffrff");
                }
            }

            newNode.setRobot(new Point(node.getBox().getX(), node.getBox().getY()));
            array.add(newNode);
        }

        return array;
    }
}
