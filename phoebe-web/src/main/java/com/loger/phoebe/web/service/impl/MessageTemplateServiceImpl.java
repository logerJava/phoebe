package com.loger.phoebe.web.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.loger.phoebe.common.constant.PhoebeConstant;
import com.loger.phoebe.common.enums.PhoebeStatus;
import com.loger.phoebe.common.enums.MessageStatus;
import com.loger.phoebe.support.dao.MessageTemplateDao;
import com.loger.phoebe.support.domain.MessageTemplate;
import com.loger.phoebe.web.service.MessageTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.web.service.impl
 * @date 2022/4/21 13:38
 * @description:
 */
@Service
public class MessageTemplateServiceImpl implements MessageTemplateService {

    @Autowired
    private MessageTemplateDao messageTemplateDao;

    @Override
    public boolean saveOrUpdate(MessageTemplate messageTemplate) {
        if(null == messageTemplate.getId()){
            initStatus(messageTemplate);
        }else{
            resetStatus(messageTemplate);
        }

        messageTemplate.setUpdated(Math.toIntExact(DateUtil.currentSeconds()));

        return messageTemplateDao.saveOrUpdate(messageTemplate);
    }

    /**
     * 重置模板的状态
     * @param messageTemplate
     */
    private void resetStatus(MessageTemplate messageTemplate) {
        messageTemplate.setUpdator(messageTemplate.getUpdator())
                .setMsgStatus(MessageStatus.INIT.getCode()).setAuditStatus(PhoebeStatus.WAIT_AUDIT.getCode());
    }

    /**
     * 初始化状态信息
     * @param messageTemplate
     */
    private void initStatus(MessageTemplate messageTemplate) {
        messageTemplate.setFlowId(StrUtil.EMPTY)
                .setMsgStatus(MessageStatus.INIT.getCode()).setAuditStatus(PhoebeStatus.WAIT_AUDIT.getCode())
                .setCreator("Java3y").setUpdator("Java3y").setTeam("公众号Java3y").setAuditor("3y")
                .setCreated(Math.toIntExact(DateUtil.currentSeconds()))
                .setIsDeleted(PhoebeConstant.FALSE);

    }


}
