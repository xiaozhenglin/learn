package com.xiaozl.learn.action;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Synchronization;

import org.junit.Test;

public class TestAlgorithm implements Serializable{
	
	public static void test() {
		int n = 4;
		int count = 1;
		Serializable[][] num = new Serializable[n][n];
		for(int i = 0 ; i< n ;i++) {
			for(int j = 0 ; j< n ;j++) {
				num[i][j]=count++;
			}
		}
		outPut(num,0,n-1);
	}

	private static void outPut(Serializable[][] num, int start, int end) {
		if(start > end ) {
			return ;
		}
		for(int i = start ;i <= end ;i++) {
			System.out.print(num[start][i]+",");
		}
		for(int i = start +1 ; i<=end ; i++) {
			System.out.print(num[i][end]+","); 
		}
		for(int i=end-1;i>=start;i--){
			System.out.print(num[end][i]+",");
		}
		for(int i = 2 ;i>=1 ;i--) {
			System.out.print(num[i][0]+","); 
		}
		for(int i = 1 ;i<=2 ;i++) {
			System.out.print(num[1][i]+","); 
		}
		for(int j = 2 ;j>=1 ;j--) {
			if(j!=1) {
				System.out.print(num[2][j]+","); 
			}else {
				System.out.print(num[2][j]); 
			}
		}
	}
	
	
	public static void test2() {
		int[] num = {1,2,2,3,4,5,6,7,8,9};
		int sum = 7;
		findSum(num,sum);
	}
	
	
	private static void findSum(int[] num, int sum) {
		
	}
	
	

	public static void main(String[] args) {
		 test();
	} 
	
}


