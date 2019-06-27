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
	
	//统计全部没有匹配到的值
	public static int count(int sum) {
		int got = 0;
		int notGot = 0;
		
		for(int j= 0 ;j<list.size()-1;j++) {
			int[] is = list.get(j); 
			int[] up = list.get(j+1); 
			
			for(int i = 0;i<is.length;i++) {
				int he = is[i]+sum+i;
				int jian = is[i]-(sum+i);
				for(int k = 0;k<up.length;k++) {
					if(he == up[k] || jian == up[k] ) {
						got++;
					}else {
						notGot++;
					}
				}
			}
		}
		int bijiaocishu = (list.size()-1)*36;
		if(notGot==bijiaocishu) {
			return sum;
		}
		return 0;
	}
	
	//算出来的 不会要的值
	public static List<Integer>  getNum(int sum) {
		List<Integer> result = new ArrayList();
		int[] is = list.get(list.size()-1); 
		for(int i = 0;i<is.length;i++) {
			int he = is[i]+sum+i;
			int jian = is[i]-(sum+i);
			for(int k = 1;k<=33;k++) {
				Integer object = k; 
				if(he == object || jian == object) {
					result.add(object);
				}
			}
		}
		return result;
	}
	
	//算出来需要的值
	public static List<Integer>  getNeedNum() {
		List<Integer> result = new ArrayList();
		for(int k = 1;k<=33;k++) {
			result.add(k);
		}
		//统计得到需要的sum值
		for(int k = 1;k<=33;k++) {
			int sum = count(k);	 
			if(sum >0 ) {
				System.out.print("sum"+sum+",");
				//根据sum值筛选出相应的结果
				List<Integer> num = new ArrayList<>();
				num=getNum(sum); 
				for(Integer object: num ) {
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
		List<Integer> result = getNeedNum();
		for(Integer object: result ) {
			System.out.print(object+",");
		}
	}
}
