package ultron.listerner;

import ultron.BaiduSuite;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import java.util.Collections;

public class SuiteProxyTest {

    @Test
    public void testGetProxy() throws Exception {
        TestNG testNG = new TestNG();
        testNG.setXmlSuites(Collections.singletonList(createSuite(BaiduSuite.class,"click")));
        testNG.run();
    }

    private XmlSuite createSuite(Class<?> className, String methodName) {
        XmlSuite suite = new XmlSuite();
        suite.setName("`11");

        XmlTest test = new XmlTest(suite);
        XmlClass testClass = new XmlClass(className);
        testClass.setIncludedMethods(Collections.singletonList(new XmlInclude(methodName)));
        test.setXmlClasses(Collections.singletonList(testClass));

        XmlInclude include = new XmlInclude("1");
        return suite;
    }
}