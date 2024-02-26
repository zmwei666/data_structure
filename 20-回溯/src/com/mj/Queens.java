package com.mj;

public class Queens {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Queens().placeQueens(4);
	}
	
	/**
	 * 数组索引是行号，数组元素是列号  cols[4] = 2;  第5行的第3列摆放一个皇后
	 */
	int[] cols;
	/**
	 * 一共有多少种摆法
	 */
	int ways;
	
	void placeQueens(int n) {
		if (n < 1) return;
		cols = new int[n];
		place(0);
		System.out.println(n + "皇后一共有" + ways + "种摆法");
	}
	
	/**
	 * 从第row行开始摆放皇后
	 * @param row
	 */
	void place(int row) {
		// 如果已经达到最大行
		if (row == cols.length) {
			// 记录摆法
			ways++;
			show();
			return;
		}
		
		for (int col = 0; col < cols.length; col++) {
			if (isValid(row, col)) {
				// 在第row行第col列摆放皇后
				cols[row] = col;
				place(row + 1);
			}
		}
	}
	
	/**
	 * <img src="https://gitee.com/codetheory/img-on1/raw/master/img01/1708944023544-2024-2-2618:40:23.png"  />
	 * <br>
	 * 判断第row行第col列是否可以摆放皇后
	 */
	boolean isValid(int row, int col) {
		// 拿到已经摆放的皇后位置 -> cols[i]
		for (int i = 0; i < row; i++) {
			// 第col列已经有皇后  竖线
			if (cols[i] == col) {
				System.out.println("[" + row + "][" + col + "]=false");
				return false;
			}
			// 第i行的皇后跟第row行第col列格子处在同一斜线上
			// 斜率公式 (x1 - x2) / (y1 - y2) = 1/-1
			if (row - i == Math.abs(col - cols[i])) {
				System.out.println("[" + row + "][" + col + "]=false");
				return false;
			}
		}
		System.out.println("[" + row + "][" + col + "]=true");
		return true;
	}
	
	void show() {
		for (int row = 0; row < cols.length; row++) {
			for (int col = 0; col < cols.length; col++) {
				if (cols[row] == col) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
		System.out.println("------------------------------");
	}
}