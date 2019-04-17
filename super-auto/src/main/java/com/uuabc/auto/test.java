package com.uuabc.auto;

public class test {

	public static void main(String[] args) {

		//一批数组从大到小排序
		int[] arr = {5,9,7,22,16};
		//要比较的数的数量
		for(int i=0;i<arr.length;i++) {
			//每个数要比较的次数
			for(int m=0;m<arr.length-i-1;m++) {
				if(arr[m]<arr[m+1]) {
					//定义比较出的最大值,每次比较后进行初始化
					int maxNum=0;
					maxNum = arr[m+1];
					arr[m+1] = arr[m];
					arr[m] = maxNum;
				}
			}
		}
		//按顺序将数打印出来
		for(int t=0;t<arr.length;t++) {
			System.out.println(arr[t]+" ");
		}	
	}

}
