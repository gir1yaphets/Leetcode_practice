package backtrack;

import java.util.*;

interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current
    // cell.
    public boolean move();

    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();

    public void turnRight();

    // Clean the current cell.
    public void clean();
}

class LC489 {
    public void cleanRoom(Robot robot) {
        Set<String> visited = new HashSet<>();

        //没给网格，所以需要使用相对位置
        dfs(robot, 0, 0, 0, visited);
    }

    private void dfs(Robot robot, int x, int y, int dir, Set<String> visited) {
        String point = x + "-" + y;
        if (visited.contains(point))
            return;

        robot.clean();
        visited.add(point);

        for (int i = 0; i < 4; i++) {
            int nx = x, ny = y;
            if (robot.move()) {
                switch (dir) {
                    case 0:
                        nx = x - 1;
                        break;
                    case 90:
                        ny = y + 1;
                        break;
                    case 180:
                        nx = x + 1;
                        break;
                    case 270:
                        ny = y - 1;
                        break;
                    default:
                        break;
                }

                dfs(robot, nx, ny, dir, visited);

                //backtrack回溯，想往回走首先先转头180度，然后再把头重新转向上
                robot.turnLeft();
                robot.turnLeft();
                robot.move();
                robot.turnLeft();
                robot.turnLeft();
            }

            // 当前方向不可以走，换方向，总是向右转
            dir += 90;
            dir %= 360;
            robot.turnRight();
        }
    }
}