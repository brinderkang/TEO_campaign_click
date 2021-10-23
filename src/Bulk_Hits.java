import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class Bulk_Hits {
	static WebDriver driver;
	String url1="https://campaign.targeteveryone.com/mobiHtml.aspx?pk_campaign=3c159a88-237f-46e3-a01c-b99d91bc0463";
	static String url2="https://campaign.targeteveryone.com/mobiSite.aspx?8I46OKWRDy5vyaXZTa5tsahgs49tozpDuS5GrqJzqitaO5cGD1et2MC6q5tmHz27%2BnYAr7XbzoJqy%2BpQyH84QL2j7NDkBEHoImlkoaZjMrD9P0y13i27gWkljclKiXFwJDUD2jdGPUg%3D";
	static String url;
	
	public static void main(String args[]) throws InterruptedException
	{
//		launchBrowser();
		Xls_Reader xr=new Xls_Reader(System.getProperty("user.dir")+"\\src\\Testdata.xlsx");
		int rowcount=xr.getRowCount("TEO");
		for(int i=1;i<=rowcount;i++)
		{
			String tRun=xr.getCellData("TEO", "RUN", i);
			if(tRun.equalsIgnoreCase("ON"))
			{
				 launchBrowser();
				 url=xr.getCellData("TEO", "URL", i);
				 driver.get(url);
				 
				 String mainwindow = driver.getWindowHandle();
				 System.out.println(mainwindow);
				 
				 if(url.contains("mobiSite.aspx"))
				 {
					 System.out.println("mobiSite.aspx");
					 
						clickvideos();
//						clickimages();
						buttonclick();
//					
				 }
				 else if(url.contains("mobiHtml.aspx"))
				 {
					 System.out.println("mobiHtml.aspx");
					 
						clickimages();
					
//						clickvideos();
					
				 }
				 try {
//					for (int j=(driver.getWindowHandles().size()-1);j>0;j--)
//					 {
//						 String winhandel = driver.getWindowHandles().toArray()[j].toString();
//						 driver.switchTo().window(winhandel);
//						 driver.close();
//					 }
					 driver.switchTo().window(mainwindow);
				} catch (Exception e) {
					
					e.printStackTrace();
				}
				 Thread.sleep(2000);
				 xr.setCellData("TEO", "Status", i, "Done");
//				 driver.close();
//				 driver.quit();
			}
		}
	}
	
	public static void launchBrowser() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver", "D:\\Brinder\\BrowserDrivers\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		Thread.sleep(8000);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	public static void clickvideos() throws InterruptedException
	{
		Thread.sleep(4000);
//		driver.switchTo().frame(1);
		List<WebElement> frames=driver.findElements(By.tagName("iframe"));
		
		for(WebElement frameid : frames)
		{
			String name=frameid.getAttribute("id");
			System.out.println(name);
			if(name.contains("player_"))
			{
				frameid.click();
				Thread.sleep(4000);
			}
		}
		
				
	}
	
	public static void clickimages() throws InterruptedException
	{
		
		List<WebElement>ifm=driver.findElements(By.tagName("iframe"));
		for(WebElement iff:ifm)
		{
			System.out.println(iff.getAttribute("id"));
		}
		Thread.sleep(5000);
		driver.switchTo().frame(0);
		List<WebElement>images=driver.findElements(By.tagName("img"));
		System.out.println(images.size());
		for(WebElement imgname:images)
		{
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);", imgname);
			System.out.println(imgname.getAttribute("src"));
			imgname.click();
		}

	}
	public static void buttonclick() throws InterruptedException
	{
		Thread.sleep(3000);
		List<WebElement> objbutton = driver.findElements(By.xpath("//a[@class='clsButton']"));
		for(WebElement btn: objbutton)
		{
			btn.click();
		}
	}
	
	public static void controlclick()
	{
//		Video click
		try {
			Thread.sleep(4000);
//			driver.switchTo().frame(1);
			List<WebElement> frames=driver.findElements(By.tagName("iframe"));
			for(WebElement frameid : frames)
			{
				String name=frameid.getAttribute("id");
				System.out.println(name);
				if(name.contains("player_"))
				{
					frameid.click();
					Thread.sleep(4000);
				}
			}
		} catch (Exception e) {
			System.out.println("No Video");
		}
//		Image Click
		try {
			List<WebElement>ifm=driver.findElements(By.tagName("iframe"));
			for(WebElement iff:ifm)
			{
				System.out.println(iff.getAttribute("id"));
			}
			Thread.sleep(5000);
			driver.switchTo().frame(0);
			List<WebElement>images=driver.findElements(By.tagName("img"));
			System.out.println(images.size());
			for(WebElement imgname:images)
			{
				JavascriptExecutor js=(JavascriptExecutor)driver;
				js.executeScript("arguments[0].scrollIntoView(true);", imgname);
				System.out.println(imgname.getAttribute("src"));
				imgname.click();
			}
		} catch (Exception e) {
			System.out.println("No Image");
		}
//	Button Click 	
		try {
			Thread.sleep(3000);
			List<WebElement> objbutton = driver.findElements(By.xpath("//a[@class='clsButton']"));
			for(WebElement btn: objbutton)
			{
				btn.click();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
