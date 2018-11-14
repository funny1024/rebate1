package com.cmsy.rebate.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;

public class StringUtil {

	public static String uuid(){
		return UUID.randomUUID().toString();
	}

	public final static String md5(String src, int length, boolean upCase, String charset) {

		System.out.println("签名字符串" + src);
		if (StringUtils.isEmpty(src)) {
			throw new NullPointerException("The src must not be null.");
		}

		if (!(length == 16 || length == 32)) {
			throw new IllegalArgumentException("The length value only is 16 or 32.");
		}

		String result = null;
		try {
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			byte[] md = mdInst.digest(src.getBytes(charset));
			StringBuilder sb = new StringBuilder();
			for (byte b : md) {
				int val = b & 0xff;
				if (val < 16) {
					sb.append("0");
				}
				sb.append(Integer.toHexString(val));
			}

			result = length == 16 ? sb.substring(8, 23) : sb.toString();

			return upCase ? result.toUpperCase() : result;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}
	}

	/**
	 * 字符串替换函数
	 */
	public static String replace(String strSrc, String strFind,	String strReplace) {
		if (strSrc == null)
			return (null);
		if (strFind == null || strReplace == null)
			return (strSrc);
		StringBuffer dst = new StringBuffer();
		int lngLength = strFind.length();
		int lngBeginPos = 0, lngCurrentPos;

		while ((lngCurrentPos = strSrc.indexOf(strFind, lngBeginPos)) >= lngBeginPos) {
			dst.append(strSrc.substring(lngBeginPos, lngCurrentPos));
			dst.append(strReplace);
			lngBeginPos = lngCurrentPos + lngLength;
		}
		if (lngBeginPos < strSrc.length())
			dst.append(strSrc.substring(lngBeginPos));
		return (dst.toString());
	}

	/**
	 * 取字符串src的左length位
	 */
	public static String left(String src, int length) {
		if (src == null)
			return (null);
		if (length <= 0)
			return ("");
		if (length > src.length())
			length = src.length();
		return (src.substring(0, length));
	}

	/**
	 * 取字符串src的右length位
	 */
	public static String right(String src, int length) {
		if (src == null)
			return (null);
		if (length <= 0)
			return ("");
		if (length > src.length())
			length = src.length();
		return (src.substring(src.length() - length, src.length()));
	}

	/**
	 * 取字符串src的从index开始的length位
	 */
	public static String mid(String src, int index, int length) {
		if (src == null)
			return (null);
		if (index <= 0) {
			length += index;
			index = 0;
		}
		if (index >= src.length())
			return ("");
		if (length <= 0)
			return ("");
		if (length > src.length() - index)
			length = src.length() - index;
		return (src.substring(index, index + length));
	}

	/**
	 * 用重复相同字符串src的方法构造另一个指定长度length的字符串
	 * e.g. makeString("123",10)返回"1231231231"
	 */
	public static String makeString(String src, int length) {
		if (src == null)
			return (null);
		if (length <= 0)
			return ("");
		StringBuffer r = new StringBuffer(length);
		int count = length / src.length();
		int rest = length % src.length();

		for (int i = 0; i < count; i++)
			r = r.append(src);
		r.append(src.substring(0, rest));
		return (r.toString());
	}

	/**
	 * 用指定字符串s左填充字符串src到指定长度length<br>
	 * 如果原字符串src的长度大于指定长度length,就不进行填充<br>
	 * e.g. lpad('abc',10,'01')返回"0101010abc"
	 */ 
	public static String lpad(String src, int length, String s) {
		return (makeString(s, length - src.length()) + src);
	}

	/**
	 * 用指定字符串s右填充字符串src到指定长度length<br>
	 * 如果原字符串src的长度大于指定长度length,就不进行填充<br>
	 * e.g. rpad('abc',10,'01')返回"abc0101010"
	 */
	public static String rpad(String src, int length, String s) {
		return (src + makeString(s, length - src.length()));
	}

	/**
	 * 判断s是不是null或者""
	 */
	public static final boolean isEmpty(String s) {
		return s == null || s.length() == 0;
	}
	
	/**
	 * 空值转换，把null转换为s
	 * e.g. nullTo(null,"ok")返回"ok" nullTo("1","ok")返回"1"
	 */
	public static String nullTo(String src, String s) {
		return (src == null ? s : src);
	}

	/**
	 * 默认的空值转换，把null转换为""
	 */
	public static String nullTo(String src) {
		return (src == null ? "" : src);
	}

	/**
	 * 空字符串转换，把null或""转换为s
	 * e.g. emptyTo(null,"ok")返回"ok" emptyTo("","ok")返回"ok"
	 */
	public static String emptyTo(String src, String s) {
		return ((src == null || src.equals("")) ? s : src);
	}

	/**
	 * 空字符串转换，把null或""转换为""
	 */
	public static String emptyTo(String src) {
		return (src == null ? "" : src);
	}

	/**
	 * 把字符串进行编码转换
	 */
	public static String convert(String src, String fromCode, String toCode) {
		try {
			return (new String(src.getBytes(fromCode), toCode));
		} catch (Exception e) {
			return (null);
		}
	}
	
	/**
	 * url字符串编码
	 */
	public static String urlEncode(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (Exception e) {
		}
		return "";
	}
	
	/**
	 * url字符串解码
	 */
	public static String urlDecode(String s) {
		try {
			return URLDecoder.decode(s, "UTF-8");
		} catch (Exception e) {
		}
		return "";
	}

	/**
	 * 将一个字符串数组values中的所有数据项合并为一个字符串，并用separator连接相邻的2个数据项
	 */
	public static String join(String[] values, String separator) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < values.length; i++) {
			result.append(values[i]);
			if (i != values.length - 1 && separator != null) {
				result.append(separator);
			}
		}
		return result.toString();
	}

	/**
	 * 将一个字符串数组values中的所有数据项合并为一个字符串
	 */
	public static String join(String[] values) {
		return join(values, null);
	}

	/**
	 * 将一个字符串分割成一个字符串数组.
	 *<BR><BR>
	 *@param    str 待分割的字符串
	 *@param    separator 用以分割的字符串
	 *<BR><BR>
	 *@return  分割后的字符串数组，如果str或者separator为空，那么返回null
	 *</DD><BR><BR><DT>
	 *<B>举例：</B>
	 *<BR><BR><DD>
	 *String sample = "a,b,c";
	 *<BR>
	 *String temp[] = split(sample,",");
	 *<BR>
	 *for (int i=0;i&lt;temp.length;i++) System.out.println("The No."+ i + " string of temp is: " + temp[i]);
	 *<BR><BR><BR>
	 *运行结果是<BR>
	 *The No.0 string is: a
	 *<BR>
	 *The No.1 string is: b
	 *<BR>
	 *The No.2 string is: c
	 *<BR>
	 */
	public static String[] split(String str, String separator){
		Vector<String> v = new Vector<String>();
		if (str==null || str.equals("") || separator==null || separator.equals("")) return null;
		String temp = new String(str);
		int len= separator.length();
		int pos = temp.indexOf(separator);
		while (pos!=-1)
		{
			if (pos>0)
			{
				v.add(temp.substring(0,pos));
			}
			temp = temp.substring(pos+len,temp.length());
			pos = temp.indexOf(separator);
		}
		if (temp.length()>0) v.add(temp);
		String[] result = new String[v.size()];
		int i=0;
		for (Enumeration<String> e = v.elements(); e.hasMoreElements();i++)
		{
        	result[i] = (String)e.nextElement();
        }
		return result;
	}
	
	/**
	 * 将一个字符串分割成一个字符串数组，比split快<br>
	 * <b>返回数组中会忽略空字符串，即："1,2,,,3"会返回1 2 3这3个元素</b>
	 *@param str 待分割的字符串
	 *@param separator 用以分割的字符串
	 *@return 分割后的字符串数组，如果str或者separator为空，那么返回null
	 */
	public static ArrayList<String> splitFast(String str, String separator){
		if (str == null || str.length() == 0 || separator == null || separator.length() == 0) return null;
		ArrayList<String> list = new ArrayList<String>();
		String temp = new String(str);
		int len = separator.length();
		int pos = temp.indexOf(separator);
		while (pos != -1)
		{
			if (pos > 0)
			{
				list.add(temp.substring(0, pos));
			}
			temp = temp.substring(pos + len, temp.length());
			pos = temp.indexOf(separator);
		}
		if (temp.length() > 0) list.add(temp);
		return list;
	}
	
	/**
	 * 取得一个随机的字符串.
	 * 
	 * @param stringLength
	 *            字符串的长度
	 * 
	 * @return 长为stringLength的随机的字符串
	 * 
	 */
	public static String getRandomString(int stringLength) {
		return getRandomString(
				"abcdefghijklmnopqrstuvwxyz1234567890",
				stringLength);
	}

	/**
	 * 在指定符chars中生成随机字符串
	 */
	public static String getRandomString(int stringLength, String Chars) {
		return getRandomString(Chars, stringLength);
	}

	public static String getRandomString(String chars, int stringLength) {
		if (stringLength <= 0)
			return null;
		Date d = new Date();
		char[] b = new char[stringLength];
		String result = null;
		long l = (long) (Math.random() * d.getTime());
		Random rd = new Random(d.getTime() + l);
		for (int i = 0; i < stringLength; i++) {
			int index = (int) (rd.nextDouble() * chars.length());
			b[i] = chars.charAt(index);
		}
		result = new String(b);
		return result;
	}

	/**
	 * 判断字符串str是否包含指定的字符chars
	 * e.g. containsChar("65535", "ABC")返回false;
	 *      containsChar("3.14", "<>.*&")返回true,因为它含有".";
	 */
	public static boolean containsChar(String str, String chars) {
		if (chars == null || "".equals(chars)) {
			return false;
		}
		for (int i = 0; i < chars.length(); i++) {
			if (str.indexOf(chars.charAt(i)) > -1) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 验证账号是否有效，用于游戏中的注册账号验证<br>
	 * name为空将返回false<br>
	 */
	public static boolean isValidAccount(String name, int maxLength) {
		//得到名字长度
		int length = getNameLen(name);
		if (length > maxLength) return false;
		//是否包含中文
		if (containsChinese(name)) return false;
		return true;
	}
	
	/**
	 * 验证名称是否有效，用于游戏中的各种名称验证<br>
	 * name为空将返回false<br>
	 */
	public static boolean isValidName(String name, int maxLength) {
		return isValidName(name, maxLength, null);
	}
	
	/**
	 * 验证名称是否有效，用于游戏中的各种名称验证<br>
	 * 当canChar为不为空时，所有canChar中包括的字符都认为是合法的<br>
	 * name为空将返回false<br>
	 * TODO 加入屏蔽词库判断
	 */
	public static boolean isValidName(String name, int maxLength, String canChar) {
		if (name == null || name.length() == 0) return false;
		int nameLen = 0;
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (!isValidChar(c, canChar) && !isChinese(c)) {
				return false;
			}
			//根据汉字2个字符,计算长度
			if (isChinese(c)) {
				nameLen += 2;
			} else {
				nameLen += 1;
			}
		}
		if (nameLen > maxLength) return false;
		return true;
	}
	
	/** 得到名字长度 */
	public static int getNameLen(String name) {
		int nameLen = 0;
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			//根据汉字2个字符,计算长度
			if (isChinese(c)) {
				nameLen += 2;
			} else {
				nameLen += 1;
			}
		}
		return nameLen;
	}
	
	/** 判定字符是否为汉字 */
	public static boolean isChinese(char a) { 
		int v = (int)a; 
		return (v >=19968 && v <= 171941); 
	}
	
	/** 判定字符串是否包含汉字 */
	public static boolean containsChinese(String str) { 
		if (str == null || str.length() == 0) return false;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (isChinese(c)) return true;
		}
		return false;
	}
	
	/**
	 * 验证单个字符是否有效，包括a-z,A-Z,0-9和非ascii字符
	 * 如果cc不为空，那是为cc内的字符都是合法的
	 */
	public static boolean isValidChar(char c, String cc) {
		int charCode = (int)c;
		if (!isEmpty(cc)) {
			for (int i = 0; i < cc.length(); i++) {
				if(c == cc.charAt(i)) return true;
			}
		}
		//不能有引号,以及$和.
		if (charCode == 34 || charCode == 39 || charCode == 36 || charCode == 46) return false;
		return (charCode >= 33 && charCode <= 126);
//		if (charCode < 0 || charCode > 255) return true;
//		if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9')) {
//			return true;
//		}
//		else {
//			return false;
//		}
	}
	
	
	/**
	 * 判断s是不是有效的身份证号码
	 */
	public static boolean isValidIDCardNo(String s) {
		if (StringUtil.isEmpty(s)) return false;
		if (s.length() != 15 && s.length() != 18) return false;
		return true;
	}
	
	/**
	 * 返回身份证s对应人的年龄，按周岁计算，例如：大于9岁不到10岁就返回9岁<br>
	 * 身份证无效返回0
	 */
	public static int getIDCardAge(String s) {
		if (!isValidIDCardNo(s)) return 0;
		//取得身份证中的日期字符串，如：19980512
		String birthday = null;
		if (s.length() == 15) {
			birthday = "19" + s.substring(6, 12);
		}
		else if (s.length() == 18) {
			birthday = s.substring(6, 14);
		}
		else {
			return 0;
		}
		//计算年龄
		int age = 0;
		try {
			String thisday = new SimpleDateFormat("yyyyMMdd").format(new Date());
			age = Integer.parseInt(thisday.substring(0, 4)) - Integer.parseInt(birthday.substring(0, 4));
			if (thisday.substring(4).compareTo(birthday.substring(4)) < 0) age--;
		} catch (Exception e) {
		}
		return age;
	}
	

	/**
	 * 检查参数中是含有空串
	 * @param ss 指定的字符串
	 */
	/*public static boolean containEmpty(String...ss) {
		if(ColUtil.isEmpty(ss)) {
			return true;
		}
		for(String s : ss) {
			if(isEmpty(s)) {
				return true;
			}
		}
		return false;
	}*/
	
	/** 得到错误信息 */
	public static String getStackTrace(Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter out = new PrintWriter(sw);
		e.printStackTrace(out);
		out.flush();
		out.close();
		return sw.toString();
	}

	private static String[] HTTP_PROXY_HEADER_NAME = new String[]{
			"CLIENTIP",
			"X-FORWARDED-FOR"
	};

	private enum LoginPlatform {
		LINUX("PC", 0),
		WINDOW("PC", 1),
		IPHONE("iPhone", 2),
		IPAD("iPad", 3),
		MAC("Mac", 4),
		ANDROID("Android", 5);

		private String name;
		private int value;

		LoginPlatform(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public int getValue() {
			return value;
		}
	}

	public static String getClientIP(HttpServletRequest request) {
		for (String headerName : HTTP_PROXY_HEADER_NAME) {
			String clientIP = request.getHeader(headerName);
			if (StringUtils.isNotBlank(clientIP)) {
				return clientIP;
			}
		}
		return request.getRemoteAddr();
	}

	public static String getLoginPlatform(HttpServletRequest request) {
		String userAgent = request.getHeader("User-Agent").toUpperCase();
		if (userAgent.contains(LoginPlatform.WINDOW.name())) {
			return LoginPlatform.WINDOW.getName();
		} else if (userAgent.contains(LoginPlatform.IPHONE.name())) {
			return LoginPlatform.IPHONE.getName();
		} else if (userAgent.contains(LoginPlatform.IPAD.name())) {
			return LoginPlatform.IPAD.getName();
		} else if (userAgent.contains(LoginPlatform.MAC.name())) {
			return LoginPlatform.MAC.getName();
		} else if (userAgent.contains(LoginPlatform.ANDROID.name())) {
			return LoginPlatform.ANDROID.getName();
		} else if (userAgent.contains(LoginPlatform.LINUX.name())) {
			return LoginPlatform.LINUX.getName();
		}
		return null;
	}

	/*public static String urlConnection(String url, String pa) {
		return urlConnection(url, pa, CharsetConstant.UTF8_STRING);
	}
*/
	public static String urlConnection(String url, String pa, String charset) {

		String response = null;

		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setDoOutput(true);
			conn.setConnectTimeout(3000);
			conn.setRequestMethod("POST");


			// Send data
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(conn.getOutputStream(), charset));
			// pa为请求的参数
			if (null != pa) {
				pw.print(pa);
			}
			pw.flush();
			pw.close();

			// Get the response!
			int httpResponseCode = conn.getResponseCode();
			if (httpResponseCode != HttpURLConnection.HTTP_OK) {
				throw new Exception("HTTP response code: " + httpResponseCode +
						"\nurl:" + url);
			}

			InputStream inputStream = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset));
			StringBuilder builder = new StringBuilder();
			String readLine;
			while (null != (readLine = br.readLine())) {
				builder.append(readLine);
			}
			inputStream.close();
			response = builder.toString();

			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	public static String get(String url, String charset) {

		String response = null;

		try {
			HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
			conn.setConnectTimeout(3000);
			conn.setRequestMethod("GET");
			// Get the response!
			int httpResponseCode = conn.getResponseCode();
			if (httpResponseCode != HttpURLConnection.HTTP_OK) {
				throw new Exception("HTTP response code: " + httpResponseCode +
						"\nurl:" + url);
			}

			InputStream inputStream = conn.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset));
			StringBuilder builder = new StringBuilder();
			String readLine;
			while (null != (readLine = br.readLine())) {
				builder.append(readLine);
			}
			inputStream.close();
			response = builder.toString();

			conn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;
	}

	/*public static String urlConnectionByRsa(String url, String pa) {

		try {
			Key publicKey = RSAUtils.getPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDmlCWDcOa9hOWq+ZTmuaKAr7yQqRRGBNb1LtYAlXMtuuXCWMSGdRiIrRrEsTDDBNRcjjm+slFt0BOCZoR4xtcO9d4+SLkg8mIJnDaLPnNsSM1GVuxMGTjdqT9jl/N7LBkHuW3JeIlZ5qk/7iX3JCUzXxGbs6aHnP2KW9RvXrdvPQIDAQAB");
			if (CoreStringUtils.isEmpty(pa)) {
				return new String(RSAUtils.decrypt(publicKey, urlConnection(url, null, "utf-8")), "utf-8");
			}
			byte[] bytes = RSAUtils.encrypt(publicKey, pa);
			return new String(RSAUtils.decrypt(publicKey, urlConnection(url, URLEncoder.encode(new String(bytes, "utf-8"), "utf-8"), "utf-8")), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/

	/**
	 * 微信统一下单
	 *
	 * @param url
	 * @param param
	 * @return
	 */
	public static String wechatUnified(String url, String param) {
		String result = null;
		try {
			URL orderUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) orderUrl.openConnection();
			conn.setConnectTimeout(30000); // 设置连接主机超时（单位：毫秒)
			conn.setReadTimeout(30000); // 设置从主机读取数据超时（单位：毫秒)
			conn.setDoOutput(true); // post请求参数要放在http正文内，顾设置成true，默认是false
			conn.setDoInput(true); // 设置是否从httpUrlConnection读入，默认情况下是true
			conn.setUseCaches(false); // Post 请求不能使用缓存
			// 设定传送的内容类型是可序列化的java对象(如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestMethod("POST");// 设定请求的方法为"POST"，默认是GET
			conn.setRequestProperty("Content-Length", param.length() + "");
			String encode = "utf-8";
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), encode);
			out.write(param.toString());
			out.flush();
			out.close();
			result = getOut(conn);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String getOut(HttpURLConnection conn) throws IOException {
		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			return null;
		}
		// 获取响应内容体
		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream(), "UTF-8"));
		String line = "";
		StringBuffer strBuf = new StringBuffer();
		while ((line = in.readLine()) != null) {
			strBuf.append(line).append("\n");
		}
		in.close();
		return strBuf.toString().trim();
	}

	/**
	 * @param url 是加上？参数后的url
	 * @return
	 */
	public static String SendGET(String url) {
		String result = "";//访问返回结果
		BufferedReader read = null;//读取访问结果

		try {
			//创建url
			URL realurl = new URL(url);
			//打开连接
			URLConnection connection = realurl.openConnection();
			// 设置通用的请求属性
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			//建立连接
			connection.connect();
			// 获取所有响应头字段
//             Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段，获取到cookies等
//             for (String key : map.keySet()) {
//                 System.out.println(key + "--->" + map.get(key));
//             }C:\ProgramData\Oracle\Java\javapath;
			// 定义 BufferedReader输入流来读取URL的响应
			read = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));
			String line;//循环读取
			while ((line = read.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (read != null) {//关闭流
				try {
					read.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	
	
}
