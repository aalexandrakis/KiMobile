package com.aalexandrakis.kimobile;

public class Constants {

	static final String SHARED_PREFERENCES = "KiMobilePrefs";
	final static String PROTOCOL = "https://"; //ubuntu & node server
	final static String HOST = "192.168.1.5:3000"; //ubuntu & node server
//	final static String URL = "http://192.168.1.2:9090"; //ubuntu
//	final static String URL = "http://192.168.2.5:8080"; //laptop at home
//	final static String URL = "http://10.0.1.6:8080"; //laptop at work
//	final static String REST_URL = URL + "/KimoWebServices/rest/KimoRest/";
	final static String REST_URL = PROTOCOL + HOST + "/";
	static final String NAMESPACE = "http://kimo.aalexandrakis.com";
	static final String SOAP_ACTION_PREFIX = "/";
	
	static final String SENDER_ID = "634777656523";
	final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

}
