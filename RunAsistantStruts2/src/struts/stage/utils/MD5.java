package struts.stage.utils;

import java.security.MessageDigest;

public class MD5 {
	
	private String md5;
	
	public MD5(String md5){
		this.md5 = md5;
	}
	
	public String getMd5() {
		return md5;
	}
	public void setMd5(String md5) {
		this.md5 = md5;
	}
	
	public String hashing() throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(this.md5.getBytes());

		byte byteData[] = md.digest();

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		return sb.toString();
	}
}
