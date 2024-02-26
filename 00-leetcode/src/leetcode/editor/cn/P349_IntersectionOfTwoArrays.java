//给定两个数组 nums1 和 nums2 ，返回 它们的交集 。输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,2,2,1], nums2 = [2,2]
//输出：[2]
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
//输出：[9,4]
//解释：[4,9] 也是可通过的
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums1.length, nums2.length <= 1000 
// 0 <= nums1[i], nums2[i] <= 1000 
// 
//
// Related Topics 数组 哈希表 双指针 二分查找 排序 👍 882 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * 两个数组的交集
 * @author CodeTheory
 * @date 2024-02-23 14:28:45
 */
public class P349_IntersectionOfTwoArrays{
	 public static void main(String[] args) {
	 	 Solution solution = new P349_IntersectionOfTwoArrays().new Solution();
		 for (int i : solution.intersection(new int[]{1, 2, 2, 1}, new int[]{2, 2})) {
			 System.out.println(i);
		 }
		 for (int i : solution.intersection(new int[]{4,9,5}, new int[]{9,4,9,8,4})) {
			 System.out.println(i);
		 }

		 System.out.println("===============");
		 for (int i : solution.intersection(new int[]{4,7,9,7,6,7}, new int[]{5,0,0,6,1,6,2,2,4})) {
			 System.out.println(i);
		 }
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
		int[] hash1 = new int[1002];
		int[] hash2 = new int[1002];
		for(int i : nums1) {
			hash1[i]++;
		}
		for(int i : nums2) {
			hash2[i]++;
		}
		List<Integer> resList = new ArrayList<>();
		for(int i = 0; i < 1002; i++) {
			if(hash1[i] > 0 && hash2[i] > 0) {
				resList.add(i);
			}
		}
		int index = 0;
		int res[] = new int[resList.size()];
		for(int i : resList) {
			res[index++] = i;
		}
		return res;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}