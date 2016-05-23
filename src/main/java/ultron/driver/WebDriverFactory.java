package ultron.driver;

import ultron.handler.LoggerHandler;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public enum WebDriverFactory {
    Chrome {
        @Override
        public WebDriver create() {
            ChromeDriverManager.getInstance().setup("2.21");
            EventFiringWebDriver webDriver = new EventFiringWebDriver(new ChromeDriver());
            webDriver.register(new LoggerHandler());
            return webDriver;
        }
    },
    FireFox {
        @Override
        public WebDriver create() {
            return null;
        }
    };

    public abstract WebDriver create();
}
