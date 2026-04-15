package cn.netkiller.i18n;

import java.util.Locale;

public class Lang {

	public Lang() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// create a new locale
		Locale locale = new Locale("ENGLISH", "US");

		// print locale
		System.out.println("Locale:" + locale);

		// print display name for locale - based on inLocale
		System.out.println("Name:" + locale.getDisplayName(new Locale("GERMAN", "GERMANY")));

	}

}
