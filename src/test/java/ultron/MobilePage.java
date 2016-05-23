package ultron;

import ultron.page.URL;
import io.appium.java_client.MobileElement;

@URL
public class MobilePage {

    private MobileElement mobileElement;

    public void click(){
        mobileElement.click();
    }
}
