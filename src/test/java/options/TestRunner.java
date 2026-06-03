package options;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import resources.Retry;

@CucumberOptions(features = "src/test/java/features", glue = "stepDefinitions", plugin = {
		"json:target/jsonReports/cucumber-report.json" }, tags = "@AddPlace or @DeletePlace")
public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = true)
	public Object[][] scenarios() {
		return super.scenarios();
	}

	@Test(groups = "cucumber", description = "Runs API Scenarios", dataProvider = "scenarios", retryAnalyzer = Retry.class)
	@Override
	public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {

		System.out.println("Retrying...");
		super.runScenario(pickleWrapper, featureWrapper);
	}

}
