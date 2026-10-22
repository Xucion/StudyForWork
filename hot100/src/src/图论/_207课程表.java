import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<List<Integer>>();// 邻接表
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] p : prerequisites) {
            // p[0]是要修的课，p[1]是先修的课
            indegree[p[0]]++;
            adj.get(p[1]).add(p[0]);
        }
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int finished = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int pre = queue.poll();
                finished++;
                for (int cur : adj.get(pre)) {
                    indegree[cur]--;
                    if (indegree[cur] == 0) {
                        queue.offer(cur);
                    }
                }
            }
        }
        return finished == numCourses;
    }
}