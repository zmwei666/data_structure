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
// 1 <= s.length, t.length <= 5 * 10⁴ 
// s 和 t 仅包含小写字母 
// 
//
// 
//
// 进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
//
// Related Topics 哈希表 字符串 排序 👍 893 👎 0


package leetcode.editor.cn;

import java.util.HashMap;

/**
 * 有效的字母异位词
 * @author CodeTheory
 * @date 2024-02-23 11:14:36
 */
public class P242_ValidAnagram{
	 public static void main(String[] args) {
	 	 Solution solution = new P242_ValidAnagram().new Solution();
		 System.out.println(solution.isAnagram("anagram", "nagaram"));
		 System.out.println(solution.isAnagram("rat", "car"));
		 System.out.println(solution.isAnagram("aacc", "ccac"));
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isAnagram(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		HashMap<Character, Integer> mapS = new HashMap<>();
		for (int i = 0; i < s.length(); i++) {
			if (mapS.containsKey(s.charAt(i))) {
				mapS.put(s.charAt(i), mapS.get(s.charAt(i)) + 1);
			} else {
				mapS.put(s.charAt(i), 1);
			}
		}

		for (int i = 0; i < t.length(); i++) {
			if (mapS.containsKey(t.charAt(i)) && mapS.get(t.charAt(i)) != 0) {
				mapS.put(t.charAt(i), mapS.get(t.charAt(i)) - 1);
			} else {
				return false;
			}
		}

		return true;


	}
}
//leetcode submit region end(Prohibit modification and deletion)

}