//给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。 
//
// 
//
// 示例： 
//
// s = "leetcode"
//返回 0
//
//s = "loveleetcode"
//返回 2
// 
//
// 
//
// 提示：你可以假定该字符串只包含小写字母。 
// Related Topics 队列 哈希表 字符串 计数 
// 👍 430 👎 0

package leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class FirstUniqueCharacterInAString {
    public static void main(String[] args) {
        Solution solution = new FirstUniqueCharacterInAString().new Solution();
//        System.out.println(solution.firstUniqChar("leetcode"));
//        System.out.println(solution.firstUniqChar("loveleetcode"));
        System.out.println(solution.firstUniqChar("aabb"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int firstUniqChar(String s) {
            char[] chars = s.toCharArray();
            Queue<Character> charStack = new LinkedList<Character>();
            HashMap<Character, Integer> charMap = new HashMap<>();
            for (int i = 0; i < chars.length; i++) {
                if (charMap.containsKey(chars[i])) {
                    charMap.put(chars[i], -1);
                } else {
                    charMap.put(chars[i], i);
                    charStack.add(chars[i]);
                }
            }
            Character c;
            while (!charStack.isEmpty() && charMap.get(c = charStack.peek()) == -1) {
                charStack.poll();
            }
            return charStack.isEmpty() ? -1 : charMap.get(charStack.poll());
        }
    }

    class Solution1 {
        /**
         * 使用Map对象存储位置 ，如果有重复元素 位置置为-1
         */
        public int firstUniqChar(String s) {
            Map<Character, Integer> position = new HashMap<Character, Integer>();
            int n = s.length();
            for (int i = 0; i < n; ++i) {
                char ch = s.charAt(i);
                if (position.containsKey(ch)) {
                    position.put(ch, -1);
                } else {
                    position.put(ch, i);
                }
            }
            int first = n;
            for (Map.Entry<Character, Integer> entry : position.entrySet()) {
                int pos = entry.getValue();
                if (pos != -1 && pos < first) {
                    first = pos;
                }
            }
            if (first == n) {
                first = -1;
            }
            return first;
        }
    }

    class Solution2 {
        public int firstUniqChar(String s) {
            Map<Character, Integer> position = new HashMap<Character, Integer>();
            Queue<Pair> queue = new LinkedList<Pair>();
            int n = s.length();
            for (int i = 0; i < n; ++i) {
                char ch = s.charAt(i);
                if (!position.containsKey(ch)) {
                    position.put(ch, i);
                    queue.offer(new Pair(ch, i));
                } else {
                    position.put(ch, -1);
                    while (!queue.isEmpty() && position.get(queue.peek().ch) == -1) {
                        queue.poll();
                    }
                }
            }
            return queue.isEmpty() ? -1 : queue.poll().pos;
        }

        class Pair {
            char ch;
            int pos;

            Pair(char ch, int pos) {
                this.ch = ch;
                this.pos = pos;
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}