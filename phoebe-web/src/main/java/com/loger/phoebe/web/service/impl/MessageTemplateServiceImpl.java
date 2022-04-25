package com.loger.phoebe.web.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loger.phoebe.common.constant.PhoebeConstant;
import com.loger.phoebe.common.enums.MessageStatus;
import com.loger.phoebe.common.enums.PhoebeStatus;
import com.loger.phoebe.support.dao.MessageTemplateDao;
import com.loger.phoebe.support.domain.MessageTemplate;
import com.loger.phoebe.web.service.MessageTemplateService;
import com.loger.phoebe.web.vo.MessageTemplateParam;
import com.sun.media.sound.SoftTuning;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.web.service.impl
 * @date 2022/4/21 13:38
 * @description:
 */
@Slf4j
@Service
public class MessageTemplateServiceImpl extends ServiceImpl<MessageTemplateDao, MessageTemplate> implements MessageTemplateService {

    @Override
    public boolean saveOrUpdate(MessageTemplate messageTemplate) {
        if(null == messageTemplate.getId()){
            initStatus(messageTemplate);
        }else{
            resetStatus(messageTemplate);
        }
        messageTemplate.setUpdated(Math.toIntExact(DateUtil.currentSeconds()));

        return super.saveOrUpdate(messageTemplate);
    }

    @Override
    public Page<MessageTemplate> queryTemplateList(MessageTemplateParam messageTemplateParam) {
        QueryWrapper<MessageTemplate> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", 0);
        if(messageTemplateParam.getId() != null){
            wrapper.eq("id", messageTemplateParam.getId());
        }
        if(StrUtil.isNotEmpty(messageTemplateParam.getReceiver())){

        }
        if(StrUtil.isNotEmpty(messageTemplateParam.getMsgContent())){
            wrapper.eq("msg_content", messageTemplateParam.getMsgContent());
        }
        Page<MessageTemplate> producePage = new Page<>(messageTemplateParam.getPage(),messageTemplateParam.getPerPage());

        return super.page(producePage, wrapper);
    }

    @Override
    public MessageTemplate queryById(Long id) {
        return baseMapper.selectById(id);
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
                .setCreator("loger").setUpdator("loger").setTeam("loger").setAuditor("loger")
                .setCreated(Math.toIntExact(DateUtil.currentSeconds()))
                .setIsDeleted(PhoebeConstant.FALSE);
    }


}
