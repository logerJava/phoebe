package com.loger.phoebe.common.constant;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.common.constant
 * @date 2022/7/27 15:40
 * @description:
 */
public class SendAccountConstant {

    /**
     * 邮箱
     */
    public static class EMAIL{
        public static final String PREFIX = "email_";
        /**
         * QQ Email
         */
        public static final String QQ_ACCOUNT_KEY = "emailQQAccount";

    }
    /**
     * 短信
     */
    public static class SMS{
        /**
         * 前缀
         */
        public static final String PREFIX = "sms_";
        /**
         * 权重
         */
        public static final String WEIGHT_KEY = "smsFlowRatio";
        /**
         * 网易云信
         */
        public static final String NETEASE_ACCOUNT_KEY = "smsNetEaseAccount";
        public static final Integer NETEASE_SMS_CODE = 10;
        /**
         * 阿里
         */
        public static final String ALI_ACCOUNT_KEY = "smsAliAccount";
        public static final Integer ALI_SMS_CODE = 20;
    }



}
