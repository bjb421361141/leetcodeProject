//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 
//输入: s = "rat", t = "car"
//输出: false 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, t.length <= 5 * 104 
// s 和 t 仅包含小写字母 
// 
//
// 
//
// 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 哈希表 字符串 排序 
// 👍 418 👎 0

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;

public class ValidAnagram {
    public static void main(String[] args) {
        Solution solution = new ValidAnagram().new Solution();
        System.out.println(solution.isAnagram("21", ""));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        /**
         * 只要存在一个字符是异位的那么就返回true
         */
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length() || s.length() <= 0) {
                return false;
            } else if (s.equals(t)) {
                return false;
            }
            char[] sChars = s.toCharArray();
            char[] tChars = t.toCharArray();
            HashMap<Character, Integer> sCharsMap = new HashMap<>();
            for (char sChar : sChars) {
                sCharsMap.put(sChar, getOrDefault(sCharsMap, sChar, 0) + 1);
            }
            for (int i = 0; i < tChars.length; i++) {
                if (sCharsMap.containsKey(tChars[i]) && sCharsMap.get(tChars[i]) > 0) {
                    sCharsMap.put(tChars[i], sCharsMap.get(tChars[i]) - 1);
                } else {
                    return false;
                }

            }
            return true;
        }

        private Integer getOrDefault(HashMap<Character, Integer> integerIntegerHashMap, Character key, int defaultValue) {
            return integerIntegerHashMap.containsKey(key) ? integerIntegerHashMap.get(key) : defaultValue;
        }
    }

    class Solution1 {
        public boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            char[] str1 = s.toCharArray();
            char[] str2 = t.toCharArray();
            Arrays.sort(str1);
            Arrays.sort(str2);
            return Arrays.equals(str1, str2);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}