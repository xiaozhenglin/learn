package com.xiaozl.learn.action;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.transaction.Synchronization;

import org.junit.Test;

public class TestAlgorithm implements Serializable{
	
	private static List allSorts = new ArrayList<>();
    
    public static void permutation(String[] before, int start, int end) {
        if (start == end) { // 当只要求对数组中一个数字进行全排列时，只要就按该数组输出即可
        	String[] newNums = new String[before.length]; // 为新的排列创建一个数组容器
            for (int i=0; i<=end; i++) {
                newNums[i] = before[i];
            }
            allSorts.add(newNums); // 将新的排列组合存放起来
        } else {
            for (int i=start; i<=end; i++) {
            	String temp = before[start]; // 交换数组第一个元素与后续的元素
                before[start] = before[i];
                before[i] = temp;
                permutation(before, start + 1, end); // 后续元素递归全排列
                before[i] = before[start]; // 将交换后的数组还原
                before[start] = temp;
            }
        }
    }
	 
  
	 public static void main(String[] args) {
		    String[] numArray = {"1", "2","3", "4", "5", "6"};
	        permutation(numArray, 0, numArray.length - 1);
	        //去重操作
	        List newList = new ArrayList(new HashSet(allSorts)); 
	        
	        String[][] a = new String[newList.size()][];
	        newList.toArray(a);
	        
	        // 打印验证
	        for (int i=0; i<a.length; i++) {
	        	String[] nums = a[i];
	            for (int j=0; j<nums.length; j++) {
	                System.out.print(nums[j]);
	            }
	            System.out.println(); 
	        }
	        System.out.println(a.length);
	 }
}



