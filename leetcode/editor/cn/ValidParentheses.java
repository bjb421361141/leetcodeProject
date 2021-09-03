//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 示例 4： 
//
// 
//输入：s = "([)]"
//输出：false
// 
//
// 示例 5： 
//
// 
//输入：s = "{[]}"
//输出：true 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 104 
// s 仅由括号 '()[]{}' 组成 
// 
// Related Topics 栈 字符串 
// 👍 2608 👎 0

package leetcode.editor.cn;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        Solution solution = new ValidParentheses().new Solution();
        System.out.println(solution.isValid("({})"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 栈可以只保存{ （ [  ，遇到 ] ) }时 比较栈内的元素
         */
        public boolean isValid(String s) {
            char[] chars = s.toCharArray();
            Stack<Character> strStack = new Stack<>();
            for (int i = 0; i < chars.length; i++) {
                if ('}' == chars[i] && !getAndPopChar(strStack, '{')) {
                    return false;
                } else if (']' == chars[i] && !getAndPopChar(strStack, '[')) {
                    return false;
                } else if (')' == chars[i] && !getAndPopChar(strStack, '(')) {
                    return false;
                } else if ("}])".indexOf(chars[i]) < 0) {
                    strStack.push(chars[i]);
                }
            }
            //判断stack 中的情况
            while (!strStack.isEmpty() && strStack.peek() != null) {
                if ("{[(".indexOf(strStack.pop()) > 0) {
                    return false;
                }
            }
            return true;
        }

        /**
         * 从栈中将目标值弹出
         *
         * @param stack      栈
         * @param targetChar 要弹出的元素
         * @return 有弹出元素返回 true 否则 返回 false
         */
        boolean getAndPopChar(Stack<Character> stack, Character targetChar) {
            while (stack != null && !stack.isEmpty() && stack.peek() != null) {
                Character tmpChar = stack.pop();
                if ("{[(".indexOf(targetChar) >= 0 && targetChar != tmpChar) {
                    return false;
                } else if (targetChar == tmpChar) {
                    return true;
                }
            }
            return false;
        }
    }

    class Solution1 {
        /**
         * 使用Map 来保存相应的元素 顺序问题
         */
        public boolean isValid(String s) {
            HashMap<Character, Character> pairs = new HashMap<>();
            pairs.put('}', '{');
            pairs.put(']', '[');
            pairs.put(')', '(');
            Deque<Character> bracketsStack = new LinkedList<Character>();
            for (int i = 0; i < s.length(); i++) {
                Character character = s.charAt(i);
                if (pairs.containsKey(character)) { //存在则从操作栈中取出元素
                    if (bracketsStack.isEmpty() || bracketsStack.peek() != pairs.get(character)) {
                        return false;
                    }
                    bracketsStack.pop();
                } else if("{[(".indexOf(character) >= 0){
                    bracketsStack.push(character);
                }
            }
            //对括号栈中的数据进行判断
            return bracketsStack.isEmpty();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}