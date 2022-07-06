package com.springboot.boot.utils;



import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * String字符串操作工具类
 * @author sea
 * @version 1.2.0
 */
public class StringUtil {

	/**
	 * 过滤文本HTML标签
	 * @param html
	 * @return
	 */
	public static String removeHTML(String html){
        String regEx_script="<script[^>]*?>[\\s\\S]*?<\\/script>"; //定义script的正则表达式
        String regEx_style="<style[^>]*?>[\\s\\S]*?<\\/style>"; //定义style的正则表达式
        String regEx_html="<[^>]+>"; //定义HTML标签的正则表达式

        Pattern p_script=Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
        Matcher m_script=p_script.matcher(html);
        html=m_script.replaceAll(""); //过滤script标签

        Pattern p_style=Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
        Matcher m_style=p_style.matcher(html);
        html=m_style.replaceAll(""); //过滤style标签

        Pattern p_html=Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
        Matcher m_html=p_html.matcher(html);
        html=m_html.replaceAll(""); //过滤html标签

        return html.trim(); //返回文本字符串 
    }




	public static boolean isNumeric(final CharSequence cs) {
		// 判断是否为空，如果为空则返回false
		if (!isEmptyString(cs)) {
			return false;
		}
		// 通过 length() 方法计算cs传入进来的字符串的长度，并将字符串长度存放到sz中
		final int sz = cs.length();
		// 通过字符串长度循环
		for (int i = 0; i < sz; i++) {
			// 判断每一个字符是否为数字，如果其中有一个字符不满足，则返回false
			if (!Character.isDigit(cs.charAt(i))) {
				return false;
			}
		}
		// 验证全部通过则返回true
		return true;

	}
		/**
         * 判断字符是否为null，为null就返回空字符串
         * @param object
         * @return
         */
	public static String getEmptyString(Object object){
		try {
			if(object==null){
				return "";
			}
			String content=String.valueOf(object);
			return content;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 判断字符是否为null，为null就返回false
	 * @param object
	 * @return
	 */
	public static boolean isEmptyString(Object object){
		return object != null && !"".equals(object.toString().trim());
	}

    /**
     * 校验日期格式
     * @param pattern
     * @param dateString
     * @return
     */
    public static boolean isValidDateFormat(String pattern, String dateString) {
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            format.setLenient(false);
            format.parse(dateString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	/**
	 * 获取随机字符串
	 *
	 * @return
	 */
	public static Integer getRandomOrderNumberInteger() {
		String chars = "1234567891";
//		int index1 = (int) (Math.random() * 26);
//		int index2 = (int) (Math.random() * 26);
//		int index3 = (int) (Math.random() * 26);
//		int index4 = (int) (Math.random() * 26);
//		int index5 = (int) (Math.random() * 26);

		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		int index1=1;
		int index2=1;
		int index3=1;
		int index4=1;
		int index5=1;
		int index6=1;
		if(random!=null){
			index1=random.nextInt(10);
			index2=random.nextInt(10);
			index3=random.nextInt(10);
			index4=random.nextInt(10);
			index5=random.nextInt(10);
			index6=random.nextInt(10);
		}
//		int index1=random.nextInt(26);
//		int index2=random.nextInt(26);
//		int index3=random.nextInt(26);
//		int index4=random.nextInt(26);
//		int index5=random.nextInt(26);
		char[] charArray = new char[]{
				chars.charAt(index1),
				chars.charAt(index2),
				chars.charAt(index3),
				chars.charAt(index4),
				chars.charAt(index5),
				chars.charAt(index6)
		};
		return    Integer.valueOf(String.valueOf(charArray));
	}


	/**
	 * 判断对象的值是否包含在该目标数组中（数组为空时候，只判断对象自己）
	 * @param object
	 * @param filters
	 * @return
	 */
	public static boolean isContain(Integer object,int[] filters){
		if(object==null){
			return false;
		}
		if(filters!=null && filters.length>0){
			for (int i = 0; i <filters.length ; i++) {
				if(object.intValue()==filters[i]){
					return true;
				}
			}
			return false;
		}

		return true;
	}

	/**
	 * 获取相应的显示格式时候
	 * @param dateTime
	 * @return
	 */
	public static String getShowDate(String dateTime){
		String createDate="";
		try {
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);
			format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			SimpleDateFormat formatShow=new SimpleDateFormat("MM-dd HH:mm");
			Date oldDate=format.parse(dateTime);
			Date nowDate=new Date();
			long longTime=nowDate.getTime()-oldDate.getTime();
			long day=longTime/(24*60*60*1000);
			long hour=(longTime/(60*60*1000)-day*24);
		    long min=((longTime/(60*1000))-day*24*60-hour*60);
		    long s=(longTime/1000-day*24*60*60-hour*60*60-min*60);

		    if(day>0){
		    	createDate=formatShow.format(oldDate);
		    }else{
		    	if(hour>0){
		    		createDate=hour+"小时前";
		    	}else{
		    		if(min>0){
		    			createDate=min+"分钟前";
		    		}else{
		    			createDate=s+"秒前";
		    		}
		    	}
		    }
		}catch (Exception e) {
			e.printStackTrace();
		}
		return 	createDate;
	}

	/**
	 * 获取时间天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Long getDay(String startDate,String endDate) {
		try {
			if(!isEmptyString(startDate) || !isEmptyString(endDate)){
				return -10000L;
			}
			if(startDate.length()>20){
				startDate=startDate.substring(0, 20);
			}
			if(endDate.length()>20){
				endDate=endDate.substring(0, 20);
			}

			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd",Locale.US);
			format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
			Date oldDate=format.parse(startDate);
			Date nowDate=format.parse(endDate);
			return getDay(oldDate,nowDate);
		} catch (Exception e) {

			return -10000L;
		}
	}

	/**
	 * 获取时间天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static Long getDay(Date startDate,Date endDate) {
		try {
			long longTime=endDate.getTime()-startDate.getTime();
			long day=longTime/(24*60*60*1000);
			return day;
		} catch (Exception e) {

			return -10000L;
		}
	}

	/**
	 * unicode转UTF-8
	 * @param theString
	 * @return
	 */
	public static String unicodeToUtf8(String theString) {
		char aChar;
		int len = theString.length();
		StringBuffer outBuffer = new StringBuffer(len);
		for (int x = 0; x < len;) {
			aChar = theString.charAt(x++);
			if (aChar == '\\') {
				aChar = theString.charAt(x++);
				if (aChar == 'u') {
					// Read the xxxx
					int value = 0;
					for (int i = 0; i < 4; i++) {
						aChar = theString.charAt(x++);
						switch (aChar) {
						case '0':
						case '1':
						case '2':
						case '3':
						case '4':
						case '5':
						case '6':
						case '7':
						case '8':
						case '9':
							value = (value << 4) + aChar - '0';
							break;
						case 'a':
						case 'b':
						case 'c':
						case 'd':
						case 'e':
						case 'f':
							value = (value << 4) + 10 + aChar - 'a';
							break;
						case 'A':
						case 'B':
						case 'C':
						case 'D':
						case 'E':
						case 'F':
							value = (value << 4) + 10 + aChar - 'A';
							break;
						default:
							throw new IllegalArgumentException("Malformed   \\uxxxx   encoding.");
						}
					}
					outBuffer.append((char) value);
				} else {
					if (aChar == 't')
						aChar = '\t';
					else if (aChar == 'r')
						aChar = '\r';
					else if (aChar == 'n')
						aChar = '\n';
					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);
				}
			} else
				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	/**
	 * ISO-8859-1 转 UTF-8
	 * @param content
	 * @return
	 */
	public static String isoToUtf8(String content) {
		if(!isEmptyString(content)){
			return content;
		}
		try {
			content=new String(content.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}

	/**
	 * 保留小数点后两位数字
	 * @param number
	 * @return
	 */
	public static String doubleFormatByTwo(Double number) {
		if(number==null){
			return "";
		}
		if(number.doubleValue()==0){
			return "0";
		}
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(number);
	}

	/**
	 * 保留小数点后两位数字(不保留四舍五入)
	 * @param number
	 * @return
	 */
	public static String doubleFormatByTwoA(Double number) {
		if(number==null){
			return "";
		}
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setGroupingSize(0);
		df.setRoundingMode(RoundingMode.FLOOR);
		return df.format(number);
	}
	/**
	 * 保留小数点后一位数字
	 * @param number
	 * @return
	 */
	public static String doubleFormatByOne(Double number) {
		if(number==null){
			return "";
		}
		if(number.doubleValue()==0){
			return "0";
		}
		DecimalFormat df = new DecimalFormat("0.0");
		return df.format(number);
	}

	/**
	 * 过滤JSON格式内容
	 * @param content
	 * @return
	 */
	public static String replaceAllByJson(String content) {
		if(!isEmptyString(content)){
			return content;
		}
		content=content.replaceAll("'", "");
		content=content.replaceAll("\"", "");
		content=content.replaceAll(":", "");
		content=content.replaceAll(";", "");
		content=content.replaceAll("[\\[\\]]","");
		content=content.replaceAll("[\\{\\}]","");
		return content;
	}


	/**
	 * 将数字补全后返回字符串（9->009）
	 * @param pattern 补全格式（000）
	 * @param number 数字
	 * @return
	 */
	public static String numberFormat(String pattern,long number){
		DecimalFormat df = new DecimalFormat(pattern);
		return df.format(number);
	}

	/**
	 * 获取随机数（按时间）
	 * @return
	 */
	public static String getRandomOrderNumber(){
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMddHHmmssSSS",Locale.US);
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		int randomNumber=10000;
		if(random!=null){
			randomNumber=random.nextInt(10000);
		}
//		int randomNumber=(int)(Math.random()*10000);
		String number= StringUtil.numberFormat("00000", randomNumber);

		return format.format(new Date())+ number;
	}

	/**
	 * 获取日期数字
	 *
	 * @param randomNumber
	 * @return
	 */
	public static String getRandomOrderNumber(int randomNumber) {
		return getRandomOrderNumber(randomNumber, 5);
	}

	/**
	 * 获取日期数字
	 *
	 * @param randomNumber
	 * @param index
	 * @return
	 */
	public static String getRandomOrderNumber(int randomNumber, int index) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd", Locale.US);
		format.setTimeZone(TimeZone.getTimeZone("GMT+8"));

		String pattern = "";
		for (int i = 0; i < index; i++) {
			pattern += "0";
		}
		String number = StringUtil.numberFormat(pattern, randomNumber);

		return format.format(new Date()) + number;
	}

	/**
	 * 获取随机字符串
	 *
	 * @return
	 */
	public static String getRandomOrderNumberString() {
		String chars = "abcdefghijklmnopqrstuvwxyz";
//		int index1 = (int) (Math.random() * 26);
//		int index2 = (int) (Math.random() * 26);
//		int index3 = (int) (Math.random() * 26);
//		int index4 = (int) (Math.random() * 26);
//		int index5 = (int) (Math.random() * 26);

		SecureRandom random = null;
		try {
			random = SecureRandom.getInstance("SHA1PRNG");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		int index1=1;
		int index2=1;
		int index3=1;
		int index4=1;
		int index5=1;
		if(random!=null){
			 index1=random.nextInt(26);
			 index2=random.nextInt(26);
			 index3=random.nextInt(26);
			 index4=random.nextInt(26);
			 index5=random.nextInt(26);
		}
//		int index1=random.nextInt(26);
//		int index2=random.nextInt(26);
//		int index3=random.nextInt(26);
//		int index4=random.nextInt(26);
//		int index5=random.nextInt(26);
		char[] charArray = new char[]{
				chars.charAt(index1),
				chars.charAt(index2),
				chars.charAt(index3),
				chars.charAt(index4),
				chars.charAt(index5)
		};
		return String.valueOf(charArray);
	}

	/**
	 * 过滤emoji 或者 其他非文字类型的字符
	 * @param source
	 * @return
	 */
	public static String removeFourChar(String source) {
		if (!containsEmoji(source)) {
			return source;// 如果不包含，直接返回
		}
		// 到这里铁定包含
		StringBuilder buf = null;
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (isEmojiCharacter(codePoint)) {
				if (buf == null) {
					buf = new StringBuilder(source.length());
				}
				buf.append(codePoint);
			}
		}

		if (buf == null) {
			return source;// 如果没有找到 emoji表情，则返回源字符串
		} else {
			if (buf.length() == len) {
				buf = null;
				return source;
			} else {
				return buf.toString();
			}
		}
	}

	/**
	 * 检测是否有emoji字符
	 * @param source
	 * @return
	 */
	private static boolean containsEmoji(String source) {
		if (source == null || "".equals(source.trim())) {
			return false;
		}
		int len = source.length();
		for (int i = 0; i < len; i++) {
			char codePoint = source.charAt(i);
			if (isEmojiCharacter(codePoint)) {
				// do nothing，判断到了这里表明，确认有表情字符
				return true;
			}
		}
		return false;
	}

	/**
	 * 检测是否有emoji字符
	 * @param codePoint
	 * @return
	 */
	private static boolean isEmojiCharacter(char codePoint) {
		return (codePoint == 0x0) || (codePoint == 0x9)
				|| (codePoint == 0xA) || (codePoint == 0xD)
				|| ((codePoint >= 0x20) && (codePoint <= 0xD7FF))
				|| ((codePoint >= 0xE000) && (codePoint <= 0xFFFD));
	}




	/**
	 * 把输入字符串分割成指定长度的字符串列表
	 * @param inputString
	 * @param length
	 * @return
	 */
	public static List<String> splitStringByLength(String inputString, int length) {
		int size = inputString.length() / length;
		if (inputString.length() % length != 0) {
			size += 1;
		}

		List<String> list = new ArrayList<String>();

		if (length > inputString.length()){
			list.add(inputString);
			return list;
		}
		String childStr = null;
		for (int index = 0; index < size; index++) {
			int f = index * length;
			int t = (index + 1) * length;
			if (f > inputString.length())
				break;
			if (t > inputString.length() )
				childStr = inputString.substring(f);
			else
				childStr = inputString.substring(f,t);

			list.add(childStr);
		}
		return list;

	}

	public static void main(String[] args) {


	}
}
