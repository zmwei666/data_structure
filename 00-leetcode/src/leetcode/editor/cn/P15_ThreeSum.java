//给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != 
//k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请 
//
// 你返回所有和为 0 且不重复的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,0,1,2,-1,-4]
//输出：[[-1,-1,2],[-1,0,1]]
//解释：
//nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
//nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
//nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
//不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
//注意，输出的顺序和三元组的顺序并不重要。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1,1]
//输出：[]
//解释：唯一可能的三元组和不为 0 。
// 
//
// 示例 3： 
//
// 
//输入：nums = [0,0,0]
//输出：[[0,0,0]]
//解释：唯一可能的三元组和为 0 。
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 3000 
// -10⁵ <= nums[i] <= 10⁵ 
// 
//
// Related Topics 数组 双指针 排序 👍 6708 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * 三数之和
 * @author CodeTheory
 * @date 2024-02-26 13:47:26
 */
public class P15_ThreeSum{
	 public static void main(String[] args) {
	 	 Solution solution = new P15_ThreeSum().new Solution();
		 System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
//		 System.out.println(solution.threeSum(new int[]{0,0,0}));
	 }
	 
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
	/**
	 * <img src="https://gitee.com/codetheory/img-on1/raw/master/img01/1708928486365-2024-2-2614:21:27.png"  />
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> result = new ArrayList<>();
		Arrays.sort(nums);

		for (int i = 0; i < nums.length; i++) {
			// 如果第一个元素大于零，不可能凑成三元组
			if (nums[i] > 0) {
				return result;
			}
			// 三元组元素a去重
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}

			HashSet<Integer> set = new HashSet<>();
			for (int j = i + 1; j < nums.length; j++) {
				// 三元组元素b去重 			1, 1, 1的情况   当j为第三个1的时候, 就continue
				if (j > i + 2 && nums[j] == nums[j - 1] && nums[j - 1] == nums[j - 2]) {
					continue;
				}

				int c = -nums[i] - nums[j];
				if (set.contains(c)) {
					result.add(Arrays.asList(nums[i], nums[j], c));
					set.remove(c); // 三元组元素c去重
				} else {
					set.add(nums[j]);
				}
			}
		}
		return result;
	}

}
//leetcode submit region end(Prohibit modification and deletion)

}