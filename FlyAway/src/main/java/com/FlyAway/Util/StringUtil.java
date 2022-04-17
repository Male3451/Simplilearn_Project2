package com.FlyAway.Util;

public class StringUtil {
	 public static String encodeHTMLtag(String string) {
		StringBuffer stringBuffer = new StringBuffer();
		if (string == null) {
			return null;
		}
		for (int i = 0; i < string.length(); i++) {
			char c=string.charAt(i);
			if (c=='<')
				stringBuffer.append("&lt;");
			else if (c=='>')
				stringBuffer.append("&gt;");
			else if (c=='&')
				stringBuffer.append("&amp;");
			else if (c=='"')
				stringBuffer.append("&quot;");
			else if (c==' ')
				stringBuffer.append("&nbsp;");
			else 
				stringBuffer.append(c);
		}
		return stringBuffer.toString();
	}

	 public static String fixsqlfields(String str) {
		 //System.out.println(str);
			if (str == null) {
				return null;
			}
			StringBuffer fixstr = new StringBuffer();
			for (int i = 0; i < str.length(); i++) {
				char c=str.charAt(i);
				if (c =='\'') {
					fixstr.append("'");
				}else {
					fixstr.append(c);
				}
			}
			//System.out.println(fixstr.toString());
			return fixstr.toString();
		}

}
