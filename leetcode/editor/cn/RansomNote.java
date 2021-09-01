//给定一个赎金信 (ransom) 字符串和一个杂志(magazine)字符串，判断第一个字符串 ransom 能不能由第二个字符串 magazines 里面
//的字符构成。如果可以构成，返回 true ；否则返回 false。 
//
// (题目说明：为了不暴露赎金信字迹，要从杂志上搜索各个需要的字母，组成单词来表达意思。杂志字符串中的每个字符只能在赎金信字符串中使用一次。) 
//
// 
//
// 示例 1： 
//
// 
//输入：ransomNote = "a", magazine = "b"
//输出：false
// 
//
// 示例 2： 
//
// 
//输入：ransomNote = "aa", magazine = "ab"
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：ransomNote = "aa", magazine = "aab"
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 你可以假设两个字符串均只含有小写字母。 
// 
// Related Topics 哈希表 字符串 计数 
// 👍 175 👎 0

package leetcode.editor.cn;

import java.util.HashMap;

public class RansomNote {
    public static void main(String[] args) {
        Solution solution = new RansomNote().new Solution();
        System.out.println(solution.canConstruct("dehifb", "hhjdgjbhahaagihhhhhajjibjffhijehda"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean canConstruct(String ransomNote, String magazine) {
            char[] magazineChars = magazine.toCharArray();
            char[] ransomNoteChars = ransomNote.toCharArray();
            //记录magazine 中的 字符数
            HashMap<Character, Integer> magazineCharMap = new HashMap<>();
            for (int i = 0; i < magazineChars.length; i++) {
                magazineCharMap.put(magazineChars[i], this.getOrDefault(magazineCharMap, magazineChars[i], 0) + 1);
            }
            //记录magazine 中的 字符数
            for (int i = 0; i < ransomNoteChars.length; i++) {
                if (magazineCharMap.containsKey(ransomNoteChars[i]) && magazineCharMap.get(ransomNoteChars[i]) > 0) {
                    magazineCharMap.put(ransomNoteChars[i], magazineCharMap.get(ransomNoteChars[i]) - 1);
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
//leetcode submit region end(Prohibit modification and deletion)

}