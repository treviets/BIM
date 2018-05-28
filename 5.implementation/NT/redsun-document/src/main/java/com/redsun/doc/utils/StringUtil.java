package com.redsun.doc.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {

	public final static String capitalizeUnderScore(String value) {
		Pattern p = Pattern.compile( "_([a-z])" );
		Matcher m = p.matcher(value);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
		    m.appendReplacement(sb, m.group(1).toUpperCase());
		}
		m.appendTail(sb);
		
		return sb.toString();
	}
}
