//给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（ 包括重复字符），并以数组形式返回。你可以按 任意顺序 返回答
//案。
//
// 
//
// 示例 1： 
//
// 
//输入：words = ["bella","label","roller"]
//输出：["e","l","l"]
// 
//
// 示例 2： 
//
// 
//输入：words = ["cool","lock","cook"]
//输出：["c","o"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= words.length <= 100 
// 1 <= words[i].length <= 100 
// words[i] 由小写英文字母组成 
// 
//
// Related Topics 数组 哈希表 字符串 👍 365 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 查找共用字符
 * @author CodeTheory
 * @date 2024-02-23 11:38:20
 */
public class P1002_FindCommonCharacters{
	 public static void main(String[] args) {
	 	 Solution solution = new P1002_FindCommonCharacters().new Solution();
		 System.out.println(solution.commonChars(new String[]{
				 "bella", "label", "roller"
		 }));
		 System.out.println(solution.commonChars(new String[]{
				 "cool", "lock", "cook"
		 }));
		 System.out.println(solution.commonChars(new String[]{
				 "acabcddd","bcbdbcbd","baddbadb","cbdddcac","aacbcccd","ccccddda","cababaab","addcaccd"
		 }));
		 /*
	测试用例:["acabcddd","bcbdbcbd","baddbadb","cbdddcac","aacbcccd","ccccddda","cababaab","addcaccd"]
	测试结果:["a","b","c","c","d","d"]
	期望结果:[]
		  */

	 }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<String> commonChars(String[] words) {
		ArrayList<String> list = new ArrayList<>();
		if (words[0].isEmpty()) {
			return list;
		}
		int[] record = new int[26];
		for (int i = 0; i < words[0].length(); i++) {
			record[words[0].charAt(i) - 'a']++;
		}


		for (int i = 1; i < words.length; i++) {
			int[] temp = new int[26];
			for (int j = 0; j < words[i].length(); j++) {
				temp[words[i].charAt(j) - 'a']++;
			}
			for (int k = 0; k < record.length; k++) {
				if (record[k] > temp[k]) {
					record[k] = temp[k];
				}
			}
		}

		for (int i = 0; i < record.length; i++) {
			while (record[i] != 0) {
				list.add(String.valueOf((char) (i + 'a')));
				record[i]--;
			}
		}

		return list;

	}
}
//leetcode submit region end(Prohibit modification and deletion)

}