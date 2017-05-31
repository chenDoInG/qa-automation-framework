package ultron.driver;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ultron.handler.LoggerHandler;

public enum WebDriverFactory {
    Chrome {
        @Override
        public WebDriver create() {
            ChromeDriverManager.getInstance().setup();
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
