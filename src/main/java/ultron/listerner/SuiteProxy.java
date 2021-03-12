package ultron.listerner;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SuiteProxy implements MethodInterceptor {

    private final Enhancer enhancer = new Enhancer();

    public SuiteProxy(Class<?> clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
    }

    public Object getProxy() {
        return enhancer.create();
    }


    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        TestNG testNG = new TestNG();
        testNG.setDefaultSuiteName(obj.getClass().getSuperclass().getSimpleName());
        testNG.setXmlSuites(Collections.singletonList(createSuite(obj.getClass().getSuperclass(), method.getName())));
        testNG.run();
        return null;
    }

    private XmlSuite createSuite(Class<?> clazz, String methodName) {
        XmlSuite suite = new XmlSuite();
        suite.setName("测试suite");
        XmlTest test = new XmlTest(suite);
        test.setName("测试test - " + clazz);

        XmlClass testClass = new XmlClass(clazz);
        List<XmlInclude> includes = new ArrayList<>();
        fillIncludesWithAllDependsOnMethods(clazz, methodName, includes);
        test.setXmlClasses(Collections.singletonList(testClass));

        return suite;
    }

    private void fillIncludesWithAllDependsOnMethods(Class<?> clazz, String methodName, List<XmlInclude> includes) {
        try {
            Method method = clazz.getMethod(methodName);
            if (method.isAnnotationPresent(Test.class)) {
                includes.add(new XmlInclude(methodName));
                for (String dependsOn : method.getAnnotation(Test.class).dependsOnMethods()) {
                    fillIncludesWithAllDependsOnMethods(clazz, dependsOn, includes);
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
