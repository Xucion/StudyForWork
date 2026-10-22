package src.图论;

import java.util.LinkedList;
import java.util.Queue;

public class _994腐烂的橘子 {

}

class Solution {
    public static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };// direction
    int minute;

    public int orangesRotting(int[][] grid) {
        int m = grid.length;// 图的行数
        int n = grid[0].length;// 图的列数
        Queue<int[]> queue = new LinkedList<int[]>();
        int fresh = 0;// 新鲜的桔子数
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    queue.offer(new int[] { i, j });// 先遍历，把所有坏橘子入队
                } else if (grid[i][j] == 1) {
                    fresh++;// 统计新鲜橘子数
                }
            }
        }
        if (fresh == 0)
            return 0;
        while (!queue.isEmpty() && fresh > 0) {// 每轮
            int size = queue.size();
            minute++;
            for (int i = 0; i < size; i++) {// 取出该轮中的坏橘子
                int[] bad = queue.poll();// 取出坏橘子
                for (int k = 0; k < 4; k++) {
                    int nextX = bad[0] + dir[k][0];// 蔓延
                    int nextY = bad[1] + dir[k][1];// 蔓延
                    if (nextX < 0 || nextX >= m || nextY < 0 || nextY >= n) {// 越界跳过
                        continue;
                    }
                    if (grid[nextX][nextY] == 1) {// 如果原来是新鲜的
                        queue.offer(new int[] { nextX, nextY });// 现在变成坏的
                        grid[nextX][nextY] = 2;
                        fresh--;// 新鲜的减少
                    }
                }
            }
        }
        if (fresh > 0) {
            return -1;
        } else {
            return minute;
        }
    }

    public void bfs(int[][] grid, int x, int y) {

    }
}