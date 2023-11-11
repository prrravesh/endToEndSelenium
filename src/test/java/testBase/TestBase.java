package testBase;

import java.io.FileInputStream;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;

import java.io.File;
		import java.io.IOException;
		import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
	public WebDriver driver;

	@BeforeMethod(alwaysRun=true)
	public void initilizeDriver() throws IOException {

		Properties property = new Properties();
		FileInputStream file = new FileInputStream(
				System.getProperty("user.dir") + "//src//test//java//Resources//data.property");
		property.load(file);

		String browserName = property.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {

			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			driver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();

		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

	}
	
	public List<HashMap<String, String>> getDataFromJson(String path) throws StreamReadException, DatabindException, IOException {

		        // Create an ObjectMapper object
		        ObjectMapper mapper = new ObjectMapper();

		        // Create a File object for the JSON file
		        File jsonFile = new File(path);

		        // Convert the JSON file to a HashMap
		        List<HashMap<String, String>> map = 
		                mapper.readValue(jsonFile , new TypeReference<List<HashMap<String, String>>>(){});

		        // Print the HashMap
		        return  map;
		    }
	public  String takeScreenShot(String name) throws IOException {
		String path=System.getProperty("user.dir")+ name +"//screenshots//screen.png";
		TakesScreenshot st= (TakesScreenshot)driver;
		File srcFile=st.getScreenshotAs(OutputType.FILE);
		File destFile=new File(path);
		Files.copy(srcFile, destFile);
		return path;
		
	}
	

		
	

	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();

	}
}
