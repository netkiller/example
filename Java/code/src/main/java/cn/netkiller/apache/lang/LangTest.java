package cn.netkiller.apache.lang;

import org.apache.commons.lang3.StringEscapeUtils;

@SuppressWarnings("deprecation")
public class LangTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String html = "<span>Neo's book</span>";
		String encode = StringEscapeUtils.escapeHtml4(html);
		String decode = StringEscapeUtils.unescapeHtml4(encode);
		System.out.println(encode);
		System.out.println(decode);

	}

}
