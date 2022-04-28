package com.loger.phoebe.handler.handler.impl;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.google.common.base.Throwables;
import com.loger.phoebe.common.domain.TaskInfo;
import com.loger.phoebe.common.dto.model.EmailContentModel;
import com.loger.phoebe.common.enums.ChannelType;
import com.loger.phoebe.handler.handler.BaseHandler;
import com.loger.phoebe.handler.handler.Handler;
import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.handler.impl
 * @date 2022/4/26 13:25
 * @description:
 */
@Slf4j
@Component
public class EmailHandler extends BaseHandler implements Handler {

    @NacosValue(value = "${email.host}", autoRefreshed = true)
    private String host;
    @NacosValue(value = "${email.port}", autoRefreshed = true)
    private Integer port;
    @NacosValue(value = "${email.auth}", autoRefreshed = true)
    private Boolean auth;
    @NacosValue(value = "${email.from}", autoRefreshed = true)
    private String from;
    @NacosValue(value = "${email.user}", autoRefreshed = true)
    private String user;
    @NacosValue(value = "${email.pass}", autoRefreshed = true)
    private String pass;
    @NacosValue(value = "${email.timeout}", autoRefreshed = true)
    private Integer timeout;
    @NacosValue(value = "${email.connection-timeout}", autoRefreshed = true)
    private Integer connectionTimeout;

    public EmailHandler(){
        channelCode = ChannelType.EMAIL.getCode();
    }

    @Override
    public boolean handler(TaskInfo taskInfo) {
        EmailContentModel emailContentModel = (EmailContentModel) taskInfo.getContentModel();
        MailAccount account = getAccountConfig(taskInfo.getSendAccount());
        try {
            MailUtil.send(account, taskInfo.getReceiver(), emailContentModel.getTitle(),
                    emailContentModel.getContent(), true, null);
        } catch (Exception e) {
            log.error("EmailHandler#handler fail!{},params:{}", Throwables.getStackTraceAsString(e), taskInfo);
            return false;
        }
        return true;
    }

    private MailAccount getAccountConfig(Integer sendAccount) {
        MailAccount account = new MailAccount();
        account.setHost(host);
        account.setPort(port);
        account.setAuth(auth);
        account.setFrom(from);
        account.setUser(user);
        // 邮箱授权码
        account.setPass(pass);

        try {
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            account.setAuth(true).setStarttlsEnable(true).setSslEnable(true).setCustomProperty("mail.smtp.ssl.socketFactory", sf);
            account.setTimeout(timeout).setConnectionTimeout(connectionTimeout);
        } catch (Exception e) {
            log.error("EmailHandler#getAccount fail!{}", Throwables.getStackTraceAsString(e));
        }

        return account;
    }



}
