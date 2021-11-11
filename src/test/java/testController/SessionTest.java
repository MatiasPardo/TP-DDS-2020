package testController;

import java.util.HashMap;
import java.util.Map;

import spark.Session;

public class SessionTest {
	private Map<String,String> session;
	
	public String attribute(String sessionParam) {
		return session.get(sessionParam);
	}
	
	/*public SessionTest() {
		super(null);
		session = new HashMap<String,String>();
	}*/
}
