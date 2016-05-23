package ultron;

import ultron.listerner.BaseSuite;
import ultron.listerner.Suite;
import ultron.driver.Use;
import ultron.driver.WebDriverFactory;
import org.testng.annotations.Test;

@Use(WebDriverFactory.FireFox)
public class GoogleSuite extends BaseSuite{

    @Suite(invokeDependsOn = true)
    private BaiduSuite baiduSuite;

    @Test
    public void searchInBaidu(){
        baiduSuite.click();
        System.out.println("继续执行了");
    }
}
