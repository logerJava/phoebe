package com.loger.phoebe.common.enums;

import com.loger.phoebe.common.dto.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.common.enums
 * @date 2022/4/22 13:43
 * @description:
 */
@Getter
@ToString
@AllArgsConstructor
public enum ChannelType {

    IM(10, "IM(站内信)", ImContentModel.class, "im"),
    PUSH(20, "push(通知栏)", PushContentModel.class, "push"),
    SMS(30, "sms(短信)", SmsContentModel.class, "sms"),
    EMAIL(40, "email(邮件)", EmailContentModel.class, "email"),
    OFFICIAL_ACCOUNT(50, "OfficialAccounts(服务号)", OfficialAccountsContentModel.class, "official_accounts"),
    MINI_PROGRAM(60, "miniProgram(小程序)", MiniProgramContentModel.class, "mini_program"),
    ENTERPRISE_WE_CHAT(70, "EnterpriseWeChat(企业微信)", EnterpriseWeChatContentModel.class, "enterprise_we_chat"),
    DING_DING_ROBOT(80, "dingDingRobot(钉钉机器人)", DingDingContentModel.class, "ding_ding_robot"),
    DING_DING_WORK_NOTICE(90, "dingDingWorkNotice(钉钉工作通知)", DingDingContentModel.class, "ding_ding_work_notice"),
    ;

    /**
     * 编码值
     */
    private Integer code;

    /**
     * 描述
     */
    private String description;

    /**
     * 内容模型Class
     */
    private Class contentModelClass;

    /**
     * 英文标识
     */
    private String codeEn;

    /**
     * 通过code获取class
     * @param code
     * @return
     */
    public static Class getChanelModelClassByCode(Integer code) {
        ChannelType[] values = values();
        for (ChannelType value : values) {
            if (value.getCode().equals(code)) {
                return value.getContentModelClass();
            }
        }
        return null;
    }

    /**
     * 通过code获取enum
     * @param code
     * @return
     */
    public static ChannelType getEnumByCode(Integer code) {
        ChannelType[] values = values();
        for (ChannelType value : values) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

}
