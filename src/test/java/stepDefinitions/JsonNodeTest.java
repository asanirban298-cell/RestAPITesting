package stepDefinitions;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import resources.Utilities;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.Assert;

import resources.APIResources;
import resources.TestDataBuild;
import resources.Utilities;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class JsonNodeTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		File file = new File(System.getProperty("user.dir") + "//src//test//resources//getPlace.json");
		InputStream is = new FileInputStream(file);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode rootNode = mapper.readTree(is);
		JsonNode latitude = rootNode.path("location").path("latitude");
		String data = latitude.asText();
		System.out.println(data);

		

		String resStr = given().contentType("application/json").header("Authorization",
				"eyJhbGciOiJIUzI1NiIsInR5cCI" + "6IkpXVCJ9.eyJfaWQiOiI2OTVlMGQwOGM5NDE2NDZiN2E4NGFlMzciLCJ1c2VyR"
						+ "W1haWwiOiJhc2FuaXJiYW4yOThAZ21haWwuY29tIiwidXNlck1vYmlsZSI6OTA1MTEzMDU4N"
						+ "iwidXNlclJvbGUiOiJjdXN0b21lciIsImlhdCI6MTc3NTU3NTg4NSwiZXhwIjoxODA3MTMzN"
						+ "Dg1fQ.Q2TKuZCBxBsM-h7J8P6bgKmeAB06vDQgSAQT67mBmqw")
				.when().post("https://rahulshettyacademy.com/api/ecom/product/get-all-products").then().extract().response().asString();
		System.out.println(resStr);
		JsonPath jsonPath = new JsonPath(resStr);
		String productName = jsonPath.get("data[0].productName").toString();
		System.out.println(productName);
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		String url = "https://rahulshettyacademy.com/client";
		driver.manage().window().maximize();
		driver.get(url);
		String prod = driver.findElement(By.xpath("//b[test()='" + productName + "']")).getText();
		System.out.println(prod);
		Assert.assertTrue(prod == productName);

	}

}
