import com.ld.dubbo.provider.loader.KevinExtensionLoader;
import com.ld.dubbo.provider.log.Logger;
import org.junit.Test;

/**
 * @author soapsnake
 * @date 2018/10/29
 */
public class LogTest {

    @Test
    public void test() throws Exception {
        //获取默认实现类(LogSpi注解里面的value即为默认值)
        Logger defaultExtension = KevinExtensionLoader.
                getExtensionLoader(Logger.class).
                getDefaultExtension();
        defaultExtension.log();

        //指定特定的实现类,例如配置的tobyLog
        Logger log = KevinExtensionLoader.
                getExtensionLoader(Logger.class).
                getExtension("snake");
        log.log();

    }


}
