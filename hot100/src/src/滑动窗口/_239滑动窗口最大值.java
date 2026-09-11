package 滑动窗口;

import java.util.ArrayDeque;
import java.util.Deque;

public class _239滑动窗口最大值 {class Solution {

    // 自定义单调队列（从大到小）
    class MyQueue {
        Deque<Integer> que = new ArrayDeque<>();  // 双端队列

        // 弹出元素：只有当滑出的值等于队头时才真正弹出
        void pop(int value) {
            if (!que.isEmpty() && value == que.peekFirst()) {
                que.pollFirst();
            }
        }

        // 加入元素：踢掉所有比 value 小的队尾元素，保持单调递减
        void push(int value) {
            while (!que.isEmpty() && value > que.peekLast()) {
                que.pollLast();
            }
            que.offerLast(value);
        }

        // 返回队头（当前窗口最大值）
        int front() {
            return que.peekFirst();
        }
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        MyQueue que = new MyQueue();
        int len = nums.length;
        int[] result = new int[len - k + 1];
        int idx = 0;

        // 1. 先将前 k 个元素放入队列
        for (int i = 0; i < k; i++) {
            que.push(nums[i]);
        }
        result[idx++] = que.front();  // 记录前 k 个元素的最大值

        // 2. 滑动窗口
        for (int i = k; i < len; i++) {
            que.pop(nums[i - k]);   // 移除滑出窗口的元素
            que.push(nums[i]);      // 加入新元素
            result[idx++] = que.front();  // 记录当前窗口最大值
        }

        return result;
    }
}

}
