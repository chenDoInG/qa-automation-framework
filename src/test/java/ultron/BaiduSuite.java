package ultron;

import ultron.driver.Use;
import ultron.listerner.BaseSuite;
import ultron.listerner.Page;
import org.testng.annotations.Test;

@Use
public class BaiduSuite extends BaseSuite {

    public BaiduSuite(){

    }
    @Page
    private BaiduPage baidu;

    @Test(description = "测试",dependsOnMethods = "getDesc")
    public void click() {
        baidu.open();
        baidu.type("测试");
    }

    @Test(description = "测试2")
    public void getDesc(){
        System.out.println("click的依赖方法");
    }
}
