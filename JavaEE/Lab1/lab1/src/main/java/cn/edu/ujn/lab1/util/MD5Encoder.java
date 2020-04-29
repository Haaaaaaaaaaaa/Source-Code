package cn.edu.ujn.lab1.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Encoder {

	public String geMd5(String salt, String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		str = str + "{" + salt + "}";// 密码和盐值组合方式
		return this.geMd5(str);
	}

	public static String geMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		byte[] strBytes = str.getBytes("UTF-8");
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] digest = md.digest(strBytes);
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		int j = digest.length;
		char strs[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte b = digest[i];
			strs[k++] = hexDigits[b >> 4 & 0xf];
			strs[k++] = hexDigits[b & 0xf];
		}
		return new String(strs);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 72c31e9c40be70d402f85b006943117a---sysout
		// 72c31e9c40be70d402f85b006943117a-----DB
		// 8eb1732c4b2bcb990e2d3864eb703879
		// 8eb1732c4b2bcb990e2d3864eb703879
		// f4bb616b4dbec1d6fe90492a0889b2c8
		// f4bb616b4dbec1d6fe90492a0889b2c8
		String name = "2011030336";
		String pass = "2011030336";
		String result = null;
		MD5Encoder pe = new MD5Encoder();
		try {
			result = pe.geMd5(name, pass);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(result);
	}

}
