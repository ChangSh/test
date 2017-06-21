package com.chang.thread.test;

public class EnergySystem {

	// 能量盒子，能量存储的地方
	private final double[] energyBoxes;

	private final Object lockObj = new Object();

	/**
	 * @param n
	 *            能量盒子的数量
	 * @param initialEnergy
	 *            每个能量盒子初始含有的能量值
	 */
	public EnergySystem(int n, double initialEnergy) {
		energyBoxes = new double[n];
		for (int i = 0; i < energyBoxes.length; i++) {
			energyBoxes[i] = initialEnergy;
		}
	}

	/**
	 * 能量转移，从一个盒子到另一个盒子
	 * 
	 * @param from
	 *            能量源
	 * @param to
	 *            能量终点
	 * @param amount
	 *            能量值
	 */
	public void transfer(int from, int to, double amount) {
		synchronized (lockObj) {
			while (energyBoxes[from] < amount) {
				try {
					lockObj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			/*
			 * if (energyBoxes[from] < amount) { return; }
			 */
			System.out.println(Thread.currentThread().getName());
			energyBoxes[from] -= amount;
			System.out.printf("从%d转移%10.2f单位的能量到%d", from, amount, to);
			energyBoxes[to] += amount;
			System.out.printf("能量综合：%10.2f%n", getTotalEnergys());
			lockObj.notifyAll();
		}

	}

	public double getTotalEnergys() {
		double sum = 0;
		for (double amount : energyBoxes)
			sum += amount;
		return sum;
	}

	public int getBoxAmount() {
		return energyBoxes.length;
	}
}
