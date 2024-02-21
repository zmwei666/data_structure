//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。 
//
// 你可以按任意顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,4], target = 6
//输出：[1,2]
// 
//
// 示例 3： 
//
// 
//输入：nums = [3,3], target = 6
//输出：[0,1]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= target <= 10⁹ 
// 只会存在一个有效答案 
// 
//
// 
//
// 进阶：你可以想出一个时间复杂度小于 O(n²) 的算法吗？ 
//
// Related Topics 数组 哈希表 👍 18297 👎 0


package leetcode.editor.cn;

import java.util.HashMap;

/**
 * 两数之和
 * @author CodeTheory
 * @date 2024-02-21 11:03:33
 */
public class P1_TwoSum{
	 public static void main(String[] args) {
	 	 Solution solution = new P1_TwoSum().new Solution();
		 for (int i : solution.twoSum(new int[]{2, 7, 11, 15}, 9)) {
			 System.out.println(i);
		 }
		 for (int i : solution.twoSum(new int[]{3, 2, 4}, 6)) {
			 System.out.println(i);
		 }
		 for (int i : solution.twoSum(new int[]{3, 3}, 6)) {
			 System.out.println(i);
		 }

	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int[] result = new int[2];
		for (int i = 0; i < nums.length; i++) {
			int temp = target - nums[i];
			if (map.containsKey(temp)) {
				result[1] = i;
				result[0] = map.get(target - nums[i]);
				break;
			}
			map.put(nums[i], i);
		}

		return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}