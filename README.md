# qa-automation-framework

  a test automation framework develop with Selenium&TestNG.

The purpose is making the script easier for everyone and with no xml to run TestNG.

## TODO
 
  complete annotations of TestNG.

## Usage

  1. 		git clone git@github.com:chenDoInG/qa-automation-framework.git
  2. import into your ideas
  3. mvn clean install
  4. create an new maven project or gradle project
  5. add dependen to pom or build.gradle : com.chendoing:ultron:1.0-SNAPSHOT

## Example


		@URL("http://www.baidu.com")
		public class BaiduPage extends PageObject {

	    		@FindBy(name = "wd")
	    		private WebElement key;
	
	    		public BaiduPage(WebDriver webDriver) {
	        		super(webDriver);
	    		}	
	
	    		public void type(String using) {
	        		key.sendKeys(using);
	    		}
		}

		@Use
		public class BaiduSuite extends BaseSuite {

			@Page
    			private BaiduPage baidu;

    			@Test(description = "test",dependsOnMethods = "getDesc")
    			public void click() {
        			baidu.open();
        			baidu.type("test");
    			}

		}
		
		@Use(WebDriverFactory.FireFox)
		public class GoogleSuite extends BaseSuite{

    			@Suite(invokeDependsOn = true)
    			private BaiduSuite baiduSuite;

    			@Test
    			public void searchInBaidu(){
        			baiduSuite.click();
        			System.out.println("continue");
    			}		
		}
