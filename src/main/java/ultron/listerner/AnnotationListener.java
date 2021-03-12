package ultron.listerner;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import ultron.driver.Use;
import ultron.driver.WebDriverFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class AnnotationListener implements ISuiteListener, IClassListener, IInvokedMethodListener {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationListener.class);

    private WebDriver webDriver;

    @Override
    public void onStart(ISuite suite) {
    }

    @Override
    public void onFinish(ISuite suite) {
        if (webDriver != null) {
            logger.info("关闭浏览器:" + webDriver);
            webDriver.quit();
        }
    }

    @Override
    public void onBeforeClass(ITestClass testClass, IMethodInstance mi) {
        Class<?> want = testClass.getRealClass();
        while (want != Object.class && !want.isAnnotationPresent(Use.class)) {
            want = want.getSuperclass();
        }
        if (want != Object.class) {
            webDriver = want.getAnnotation(Use.class).value().create();
        } else {
            webDriver = WebDriverFactory.Chrome.create();
        }


        Stream.of(mi.getInstance().getClass().getDeclaredFields()).forEach(field -> {
            if (field.isAnnotationPresent(Page.class)) {
                try {
                    Constructor<?> constructor = field.getType().getConstructor(WebDriver.class);
                    field.setAccessible(true);
                    field.set(mi.getInstance(), constructor.newInstance(webDriver));
                } catch (IllegalArgumentException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
            if (field.isAnnotationPresent(Suite.class)) {
                try {
                    field.setAccessible(true);
                    field.set(mi.getInstance(), new SuiteProxy(field.getType()).getProxy());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onAfterClass(ITestClass testClass, IMethodInstance mi) {
        if (webDriver != null) {
            logger.info("关闭浏览器:" + webDriver);
            webDriver.quit();
        }
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        logger.info("正在执行测试方法[{}],方法描述为[{}]", method.getTestMethod().getMethodName(), method.getTestMethod().getDescription());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {

    }
}
