package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utilities {

	public static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {

		if (req == null) {
			PrintStream stream = new PrintStream(new FileOutputStream("log.txt"));
			req = new RequestSpecBuilder().setBaseUri(getGlobalData("baseURL")).addQueryParam("key", "qaclick123")
					.setContentType("application/json").addFilter(RequestLoggingFilter.logRequestTo(stream))
					.addFilter(ResponseLoggingFilter.logResponseTo(stream)).build();
		}
		return req;
	}

	public String getGlobalData(String key) throws IOException {

		Properties prop = new Properties();
		FileInputStream inputStr = new FileInputStream(
				"/Users/anirbansarkar/eclipse-workspace/APIFramework/src/test/java/resources/GlobalData.properties");
		prop.load(inputStr);
		return prop.getProperty(key);

	}

	public String getJSONPath(Response response, String key) {

		String res = response.asString();
		JsonPath jsp = new JsonPath(res);
		String value = jsp.get(key).toString();
		return value;

	}

}
