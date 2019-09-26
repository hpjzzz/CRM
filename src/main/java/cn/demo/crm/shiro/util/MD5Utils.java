package cn.demo.crm.shiro.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5Utils {
		public static final String algorithmName = "MD5";
		public static final int hashIterations = 10;

		public static String createMD5Str(String password){

			ByteSource salt = ByteSource.Util.bytes("itsource");
			SimpleHash simpleHash = new SimpleHash(algorithmName, password,salt, hashIterations);
			System.out.println(simpleHash.toString());
			return simpleHash.toString();
		}

//	public static void main(String[] args) {
//		createMD5Str("admin");
//	}
}
