package top.hackchen.secondhandmarket.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayConfig;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static com.alipay.api.AlipayConstants.*;

import static top.hackchen.secondhandmarket.util.FileUtils.loadFileFromResource;

@Configuration
public class MyAlipayConfig {
    private static final String PRIVATE_KEY_PATH = "alipay/private-key.txt";
    private static final String PUBLIC_KEY_PATH = "alipay/private-key.txt";

    /***必要的参数***/

    private static final String APP_ID = "2021000121604088";
    private static final String PRIVATE_KEY;
    private static final String PUBLIC_KEY;
    private static final String NOTIFY_URL = "http://3mhrhd.natappfree.cc/api/pay/notify";
    private static final String GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";

    static {
        PRIVATE_KEY = loadFileFromResource(PRIVATE_KEY_PATH);
        PUBLIC_KEY = loadFileFromResource(PUBLIC_KEY_PATH);
    }

    @Bean
    @ConditionalOnMissingBean(AlipayConfig.class)
    @Primary
    public AlipayConfig alipayConfig() {
        AlipayConfig config = new AlipayConfig();
        config.setAppId(APP_ID);
        config.setPrivateKey(PRIVATE_KEY);
        config.setAlipayPublicKey(PUBLIC_KEY);
        config.setServerUrl(GATEWAY_URL);
        config.setFormat(FORMAT_JSON);
        config.setCharset(CHARSET_UTF8);
        System.out.println("构建完成");
        return config;
    }
    //http://3mhrhd.natappfree.cc/api/pay/test?outTradeNo=123465&totalAmount=0.1&subject=water

    @Bean
    @ConditionalOnMissingBean(AlipayClient.class)
    @Primary
    public AlipayClient alipayClient(@Autowired AlipayConfig alipayConfig) throws AlipayApiException {
        return new DefaultAlipayClient(alipayConfig);
    }
}
