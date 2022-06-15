package com.springboot.boot.utils;




import java.text.DecimalFormat;


/**
 * String字符串操作工具类
 * @author
 * @version 1.2.0
 */
public class DataUtil {

	/**
	 * 过滤文本HTML标签
	 * @param
	 * @return
	 */

	public static String myPercent(long y, long z) {

		if(z==0){
			return "0%";
		}
		String baifenbi = "";// 接受百分比的值
		double baiy = y * 1.0;
		double baiz = z * 1.0;
		double fen = baiy / baiz;
// NumberFormat nf = NumberFormat.getPercentInstance();注释掉的也是一种方法
// nf.setMinimumFractionDigits( 2 ); 保留到小数点后几位
		DecimalFormat df1 = new DecimalFormat("##%");
// ##.00%
// 百分比格式，后面不足2位的用0补齐
// baifenbi=nf.format(fen);
		baifenbi = df1.format(fen);
		System.out.println(baifenbi);
		return baifenbi;
	}


	public static void main(String[] args) {


	}
}
