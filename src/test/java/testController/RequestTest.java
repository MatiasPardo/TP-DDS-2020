package testController;

import java.util.HashMap;
import java.util.Map;

import spark.Request;

public class RequestTest extends Request{
	
	private Map<String, String> params;
	private SessionTest session;

	@Override
	public String queryParams(String queryParam) {
		
		return params.get(queryParam);
	}
	
	
	public RequestTest() {
		super();
		params = new HashMap<String,String>();
		session = new SessionTest();
	}
	
}