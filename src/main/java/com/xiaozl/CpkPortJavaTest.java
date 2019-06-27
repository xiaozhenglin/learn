package src.com;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class CpkPortJavaTest {
	
	static List<int[]> list = new ArrayList();
	
	public static void init() {
		list.add(a);
		list.add(b);
		list.add(c);
	}
	
	public static int[] a = new int[] {1,8,19,24,29,30};
	public static int[] b = new int[] {2,9,13,15,22,30};
	public static int[] c=  new int[] {1,2,6,12,16,18};
	
	public static void main(String[] args) {
		CpkPortJavaTest.init();
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
				
//				System.out.print(he+",");
//				System.out.print(jian+",");
//				System.out.println(cheng+","); // 2
				
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

	
}
