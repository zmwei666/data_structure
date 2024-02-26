//给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[
//b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）： 
//
// 
// 0 <= a, b, c, d < n 
// a、b、c 和 d 互不相同 
// nums[a] + nums[b] + nums[c] + nums[d] == target 
// 
//
// 你可以按 任意顺序 返回答案 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,0,-1,0,-2,2], target = 0
//输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [2,2,2,2,2], target = 8
//输出：[[2,2,2,2]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 200 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= target <= 10⁹ 
// 
//
// Related Topics 数组 双指针 排序 👍 1867 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 四数之和
 * @author CodeTheory
 * @date 2024-02-26 14:48:06
 */
public class P18_FourSum{
	 public static void main(String[] args) {
	 	 Solution solution = new P18_FourSum().new Solution();
//		 System.out.println(solution.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
		 System.out.println(solution.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0));
		 /*
		 	测试用例:[1,0,-1,0,-2,2]
			0
			测试结果:[[-2,0,0,2]]
			期望结果:[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
		  */
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
		Arrays.sort(nums);
		ArrayList<List<Integer>> res = new ArrayList<>();

		for (int i = 0; i < nums.length; i++) {

			if (nums[i] > 0 && nums[i] > target) {
				return res;
			}

			if (i > 0 && nums[i] == nums[i - 1]) {  // 去重a
				continue;
			}

			for (int j = i + 1; j < nums.length; j++) {
				if (j > i + 1 && nums[j] == nums[j - 1]) {  // 去重b
					continue;
				}

				int left = j + 1, right = nums.length - 1;
				while (left < right) {
					int sum = nums[i] + nums[j] + nums[left] + nums[right];
					if (sum < target) {
						left++;
					} else if (sum > target) {
						right--;
					} else {
						res.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

						while (left < right && nums[left] == nums[left + 1]) {
							left++;
						}

						while (left < right && nums[right] == nums[right - 1]) {
							right--;
						}

						left++;
						right--;
					}
				}
			}
		}
		return res;
	}
}
//leetcode submit region end(Prohibit modification and deletion)

}