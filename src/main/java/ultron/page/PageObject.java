package ultron.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class PageObject {

    protected WebDriver webDriver;

    public PageObject(WebDriver webDriver) {
        this.webDriver = webDriver;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(webDriver, 15);
        PageFactory.initElements(factory, this);
    }

    public void open() {
        URL url = this.getClass().getAnnotation(URL.class);
        webDriver.get(url.value());
    }
}
