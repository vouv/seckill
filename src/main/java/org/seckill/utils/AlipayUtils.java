package org.seckill.utils;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipayTradeCreateRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipayTradeCreateResponse;

public class AlipayUtils {

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2017073107972045";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDyvyyPMlV8CA3rczLrb+9iCze7i2Y4CnvQyGx8VjxcWVZjmhCw9KUB9CxJ3cKZ9lnt3osnmLzxKAXmpVz6rnZNQZeduJmdHFfxbeSj8en9sXcWWegyt6BtQkhXpR68qaXqGrh0qxlb4eRWDoj2QUtL4kLGaNdAFlJHfRZoso+jLavQ0S5/GTzzIoPO9esPVSEpGFsoFee5gjeIfLelJ8zKnQUoFg7Ox/NT37wUUz20rgKceGsTb5lIhbKygbDQZrxpcVPW43RqO388BRuF7iTABiEu5qJK5jAAZKTPjE2MBZZFnyCX9lYWSr7WGW9qjoj+vzH7YX8xrkS/wNE99nGnAgMBAAECggEAImQtZvhFnjXx6uq+SxbnvmdCdSKxz7zEf/b1T8RT9u19TM02EiwG+LjkUW7GtFNvuECwTGLaWfxGob/J5YY0IovpdCIEOXsbhowAOa37bcLn0VXZjN3xL6mVYD13llketYUlnvnitN7TH9IdW24ywcSDm2KdJGpFK/Wv6BNsQDzgmNuorY+VD2aZomDY7sq2aetJ96K09BEwB2ztDetm9FiT1lWHvkZ+gvU6WTPABX+hmGdsX+IyxoY6KcQnJrl1dNlEi+7OoUHjw8hBIcD/pUzKnrOmfVgGqGZDpbPWMAf1cW6h6r7o+KdgR9uX5yRL3Cfp58JIPrXw+4DgJGf24QKBgQD5nDszUfR/e4h3pPkodxWaoD8+N6WCEGFOQBsCas4MVQsorgiS7/QMubADnKWu0cVj6JThAt4WfDvHhV7mgBijGjwT26VW+c1IBIqN5CP7HxuapjuwrrPfC1ZkS5PRZYn0sT8RXgGCD3oEDubwOzodzgE7vPVuHokOcf1kDQ8fNwKBgQD49fbaI8omrhCFSRokgrJ2/pYB698GUz1q2eeNuPIWRKvNAv1MH0hzZY9UXyMBhWx9ufQ54PbmctHTk05+P2oiSgg+Gd1aAvxKTh1BEO0csHtcK7CQJURgHBlOjNzjmgfKdQZHDEaAm5SuVkS6Y0vYDqR7+VK6gD10r3rsc98ZEQKBgQC8nTOW2gz47+31CrJDw4iNqR1g9EeaeqosqQA/2VkNtI2UFb3g2nT90KqScCI6ccTIdA1b4gDT2NiLMdq+LnlvdfmDv+u2R1i5XMUd5xDRR3Zia86a+chteHG1o+50wld2kSDpDLHzB6LZwjD7wqVttCb2bxi+N1C1m6kvmzacDQKBgCUn6GiUENaHAfYye9FZJDN4OYEd59I02CS7UsCnCpJiO1XEjXMv9Ass7CBGdp4CgLee6iE9PvwTxNcT1BIperiVXfeOuqd1bJvWXq1YTlsKx85wwtAwj6zcZLaBdXIOif0gTAiMf3UwQxdlzqoWqbocLVZHVp55BzMEAY+Kl5BRAoGBAIxcvcSwP/bYhWmsSrWrU7HXHMi+gg8hku+h+C9pwv5gKOU4Mq2qncNVkCVagCSnKMYATBbWQ4c76OLWTPFYXNHdPXhii2IneYSQJulTL5U8bhi8yeJ/Eq/TChWGzSGdwplYsNIXaXeuwUJ/piDXL3Vk+GcaBjnaKxfkfyMTl3lw";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAvpZ6vbq9Ge0Nmi7p109qz/8LqGSssL6ShAefMvYpIXvKFFUoV0dgy7jGuLIqvrCbtnC0IyjfyldW+tD044g28YKcx+M/b7dQBVfeyHSrYjfpxt4y3+n/78tDdrGYQBi40GuuRH90XXr1bv1j/rW9yBJRFQx84wedmUT7kb6+WR+88qtS6+SbpbsT7yGII9XqWitenVsCm0yluJOZEvOpCrVMziPeCc2BxCjmVv6gClehhdUlOgOPIdLq4fClteLjfW65UG3Ya54E7GDc/Ayta1FZy1X4CL+RqP/SwXAzImi48GzmLCDz7WmeydpmwEwvWJ2M4QPTPnuyiRVwr60VMQIDAQAB";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipay.com/gateway.do";


    public void test(){
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, app_id, merchant_private_key, "json", charset, alipay_public_key, sign_type);
            AlipayTradeCreateRequest request = new AlipayTradeCreateRequest();
            request.setBizContent("{" +
                    "\"out_trade_no\":\"20170320010101001\"," +
                    "\"seller_id\":\"2088102146225135\"," +
                    "\"total_amount\":88.88," +
                    "\"discountable_amount\":8.88," +
                    "\"subject\":\"Iphone6 16G\"," +
                    "\"body\":\"Iphone6 16G\"," +
                    "\"buyer_id\":\"2088102146225135\"," +
                    "      \"goods_detail\":[{" +
                    "        \"goods_id\":\"apple-01\"," +
                    "\"goods_name\":\"ipad\"," +
                    "\"quantity\":1," +
                    "\"price\":2000," +
                    "\"goods_category\":\"34543238\"," +
                    "\"body\":\"特价手机\"," +
                    "\"show_url\":\"http://www.alipay.com/xxx.jpg\"" +
                    "        }]," +
                    "\"operator_id\":\"Yx_001\"," +
                    "\"store_id\":\"NJ_001\"," +
                    "\"terminal_id\":\"NJ_T_001\"," +
                    "\"extend_params\":{" +
                    "\"sys_service_provider_id\":\"2088511833207846\"" +
                    "    }," +
                    "\"timeout_express\":\"90m\"," +
                    "\"business_params\":\"{\\\"tableNumber\\\":\\\"xx001”}\"" +
                    "  }");
            AlipayTradeCreateResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                System.out.println("调用成功");
                System.out.println(response.getMsg());
            } else {
                System.out.println("调用失败");

                System.out.println(response.getMsg());
                System.out.println(response.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }





}
