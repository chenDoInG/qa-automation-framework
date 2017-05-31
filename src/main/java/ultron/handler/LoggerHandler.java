package ultron.handler;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.PatternSyntaxException;

public class LoggerHandler implements WebDriverEventListener {

    private final static Logger logger = LoggerFactory.getLogger(LoggerHandler.class);

    @Override
    public void beforeAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertAccept(WebDriver driver) {

    }

    @Override
    public void afterAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeAlertDismiss(WebDriver driver) {

    }

    @Override
    public void beforeNavigateTo(String url, WebDriver driver) {

    }

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        logger.info("open [{}]", url);
    }

    @Override
    public void beforeNavigateBack(WebDriver driver) {

    }

    @Override
    public void afterNavigateBack(WebDriver driver) {
    }

    @Override
    public void beforeNavigateForward(WebDriver driver) {

    }

    @Override
    public void afterNavigateForward(WebDriver driver) {

    }

    @Override
    public void beforeNavigateRefresh(WebDriver driver) {

    }

    @Override
    public void afterNavigateRefresh(WebDriver driver) {
        logger.info("refresh the web");
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        logger.info("finding element by [{}]", by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
        logger.info("found element [{}]", by);
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {

    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
        logger.info("click on [{}]", getWebElementContent(element));
    }

    @Override
    public void beforeChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        element.clear();
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        logger.info("type [{}] to element [{}]", element.getAttribute("value"), getWebElementContent(element));
    }

    @Override
    public void beforeScript(String script, WebDriver driver) {

    }

    @Override
    public void afterScript(String script, WebDriver driver) {

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
    }

    /**
     * 获取webElement内容
     *
     * @param webElement 要获取内容的元素
     * @return 返回webElement内容 String 如linktext = “内容”
     */
    private String getWebElementContent(WebElement webElement) {
        try {
            String name = webElement.toString().split("->")[1];
            return name.substring(0, name.length() - 1).trim();
        } catch (PatternSyntaxException e) {
            return webElement.toString();
        } catch (Exception e) {
            return "could not parse this Element!";
        }
    }
}
