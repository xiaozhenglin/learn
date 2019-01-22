package com.xiaozl.learn.common;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by DK on 2017/11/15.
 */
public class StringUtil extends StringUtils {
   static String[]
           sheng = new String[]{"京", "沪", "浙", "苏", "粤", "鲁", "晋", "冀", "豫", "川", "渝", "辽", "吉", "黑", "皖", "鄂", "湘", "赣", "闽", "陕", "甘", "宁", "蒙", "津", "贵", "云", "桂", "琼", "青", "新", "藏", "使", "领", "警", "学", "港", "澳"};
    static String[]  zimu = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    static String[] shuzi = new String[]{"0","1","2","3","4","5","6","7","8","9"};

	public static String listToString(Collection<?> coll) {
		return listToString(coll,",");
	}
	
	public static String listToString(Collection<?> coll, String delim) {
		if (CollectionUtils.isEmpty(coll)) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		Iterator<?> it = coll.iterator();
		while (it.hasNext()) {
			sb.append(it.next());
            sb.append(delim);
		}
		return sb.deleteCharAt(sb.length()-1).toString();
	}


	public static String getLastLetters(String str, int n) {
		return str.substring(str.length()-n);
	}
	
    public static String getSetterName(String fieldName){
        return "set"+toUpperCaseFirstOne(fieldName);
    }
    
    //首字母转大写
    public static String toUpperCaseFirstOne(String s)
    {
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 判断两个对象是否相等(都为null 不相等)
     *
     * @param o1
     * @param o2
     * @return
     */
    public static boolean equals(Object o1,Object o2) {
    	return equals(o1,o2,false);
    }


    /**
     * 判断两个对象是否相等
     *
     * @param o1
     * @param o2
     * @param isAllNull 都为null返回该对象
     * @return
     */
    public static boolean equals(Object o1,Object o2,boolean isAllNull) {
    	if(o1 == null && o2==null) {
    		return isAllNull;
    	}
    	if(o1 == null || o2==null) {
    		return false;
    	}
    	return o1.toString().equals(o2.toString());
    }

    //base64
    public static  String base64Encode(String data) {

        return Base64.encodeBase64String(data.getBytes());
    }

    public static List<String>  stringToList(String str){
        return stringToList(str,",");
    }

    public static  List<String>  stringToList(String str,String regex){
        if(StringUtil.isBlank(str)){
            return new ArrayList<String>();
        }
        return Arrays.asList(str.split(regex));
    }


    public static boolean checkLicensePlate(String licensePlate){
        if(licensePlate.length() != 7 &&  licensePlate.length()!=8 ){
            return  false;
        }
        return ArrayUtils.contains(sheng,licensePlate.charAt(0)+"") && ArrayUtils.contains(zimu,licensePlate.charAt(1)+"");
    }


    public static  String getRandomCode (){
        String randomCode;
        int rnd = new Random().nextInt(999998)+1;
        if(rnd<10) {
            randomCode = "00000" + rnd;
        }else if(rnd < 100) {
            randomCode = "0000" + rnd;
        }else if(rnd < 1000) {
            randomCode = "000" + rnd;
        }else if(rnd < 10000) {
            randomCode = "00" + rnd;
        }else if(rnd < 100000) {
            randomCode = "0" + rnd;
        }else {
            randomCode = "" + rnd;
        }
        return randomCode;
    }

    /**
     * 任意进制转换
     * @param oldData 需要转换的数据
     * @param oldDecimal  需要转换数据的进制 (注意 此进制只能在 [2,36] 之间)
     * @param nowDecimal  转换后的进制 (注意 此进制只能在 [2,36] 之间)
     * @param nowDataLength 转换后数据的长度 (不足则在前面补0,此字段为null则忽略)
     * @return
     */
    public static String decimalConvert(String oldData,int oldDecimal,int nowDecimal,Integer nowDataLength){
        Long l = Long.parseLong(oldData, oldDecimal);
        String s = Long.toString(l, nowDecimal).toUpperCase();
        if(nowDataLength != null){
            while (s.length()<nowDataLength){
                s="0"+s;
            }
        }
        return  s;
    }


    public static void main(String[] args) {
        String s = decimalConvert("0A", 36, 10, null);
        System.out.println(s);
        System.out.println(decimalConvert(s, 10, 36, 2));
    }
}
