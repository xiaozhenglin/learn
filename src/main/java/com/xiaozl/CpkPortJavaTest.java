package src.com;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class CpkPortJavaTest {
	
	static List<int[]> list = new ArrayList();
	
	public static void init() {
		list.add(a1);list.add(a2);list.add(a3);
	}
	
	public static int[] a1 = new int[] {1,8,19,24,29,30};
	public static int[] a2 = new int[] {2,9,13,15,22,30};
	public static int[] a3=  new int[] {1,2,6,12,16,18};
	
	
	public static void count() {
		int got = 0;
		int notGot = 0;
		
		int sum = 1;
		for(int j= 0 ;j<list.size()-1;j++) {
			int[] is = list.get(j); 
			int[] up = list.get(j+1); 
			
			for(int i = 0;i<is.length;i++) {
				int he = is[i]+sum+i;
				int jian = is[i]-(sum+i);
				int cheng = is[i]*(sum+i);
				int chu = is[i]/(sum+i);
				
				for(int k = 0;k<up.length;k++) {
					if(he == up[k] || jian == up[k] || cheng ==up[k]|| chu==up[k]) {
						System.out.println(up[k]); 
						got++;
					}else {
						notGot++;
					}
				}
			}
		}
		System.out.println("got"+got);
		System.out.println("notGot"+notGot);
	}
	
	public static List<Integer>  getNum() {
		List<Integer> result = new ArrayList();
		for(int k = 1;k<=33;k++) {
			result.add(k);
		}
		
		int[] is = list.get(list.size()-1); 
		int sum = 1;
		for(int i = 0;i<is.length;i++) {
			int he = is[i]+sum+i;
			int jian = is[i]-(sum+i);
			int cheng = is[i]*(sum+i);
			int chu = is[i]/(sum+i);
			
			System.out.print(he+",");
			System.out.print(jian+",");
			System.out.print(cheng+","); 
			System.out.println(chu+","); 
			
			for(int k = 1;k<=33;k++) {
				Integer object = k; 
				if(he == object || jian == object || cheng ==object|| chu==object) {
					if(result.indexOf(object)>-1) {
						result.remove(result.indexOf(object));
					}
				}
				
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		CpkPortJavaTest.init();
		List<Integer> num = getNum(); 
		for(Integer result: num ) {
			System.out.print(result+",");
		}
	}

	
}
