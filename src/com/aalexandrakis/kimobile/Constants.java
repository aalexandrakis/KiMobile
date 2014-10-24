package com.aalexandrakis.kimobile;

public class Constants {

	static final String SHARED_PREFERENCES = "KiMobilePrefs";
//	final static String URL = "http://192.168.1.5:8080"; //ubuntu
//	final static String URL = "http://192.168.1.5:8081"; //laptop at home
	final static String URL = "http://10.0.1.6:8081"; //laptop at work
	final static String SOAP_URL = URL + "/KimoBackEnd/services/KimoDb?wsdl";
	final static String REST_URL = URL + "/KimoBackEnd/rest/KimoRest/";
	static final String NAMESPACE = "http://kimo.aalexandrakis.com";
	static final String SOAP_ACTION_PREFIX = "/";
	
	static final String SENDER_ID = "634777656523";
	final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    
}
