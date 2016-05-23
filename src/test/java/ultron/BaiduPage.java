package ultron;

import ultron.page.PageObject;
import ultron.page.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@URL("http://www.baidu.com")
public class BaiduPage extends PageObject {

    @FindBy(name = "wd")
    private WebElement key;

    @FindBy(id = "wd")
    private WebElement key2;

    public BaiduPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void type(String using) {
        key.sendKeys(using);
    }
}
