package com.loger.phoebe.service.api.impl.action;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import com.loger.phoebe.common.constant.PhoebeConstant;
import com.loger.phoebe.common.domain.TaskInfo;
import com.loger.phoebe.common.dto.model.ContentModel;
import com.loger.phoebe.common.enums.ChannelType;
import com.loger.phoebe.common.enums.RespStatusEnum;
import com.loger.phoebe.common.vo.BasicResultVO;
import com.loger.phoebe.service.api.domain.MessageParam;
import com.loger.phoebe.service.api.impl.domain.SendTaskModel;
import com.loger.phoebe.support.dao.MessageTemplateDao;
import com.loger.phoebe.support.domain.MessageTemplate;
import com.loger.phoebe.support.pipeline.BusinessProcess;
import com.loger.phoebe.support.pipeline.ProcessContext;
import com.loger.phoebe.support.utils.ContentHolderUtil;
import com.loger.phoebe.support.utils.TaskInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.service.api.impl.action
 * @date 2022/4/20 17:17
 * @description: 拼装参数
 */
@Slf4j
@Service
public class AssembleAction implements BusinessProcess<SendTaskModel> {

    @Autowired
    private MessageTemplateDao messageTemplateDao;

    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        SendTaskModel sendTaskModel = context.getProcessModel();
        Long messageTemplateId = sendTaskModel.getMessageTemplateId();

        try {
            MessageTemplate messageTemplate = messageTemplateDao.selectById(messageTemplateId);
            if (messageTemplate == null || messageTemplate.getIsDeleted().equals(PhoebeConstant.TRUE)) {
                context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.TEMPLATE_NOT_FOUND));
                return;
            }

            List<TaskInfo> taskInfos = assembleTaskInfo(sendTaskModel, messageTemplate);
            sendTaskModel.setTaskInfo(taskInfos);
        } catch (Exception e) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.SERVICE_ERROR));
            log.error("assemble task fail! templateId:{}, e:{}", messageTemplateId, Throwables.getStackTraceAsString(e));
        }

    }

    /**
     * 组装 TaskInfo 任务消息
     *
     * @param sendTaskModel
     * @param messageTemplate
     */
    private List<TaskInfo> assembleTaskInfo(SendTaskModel sendTaskModel, MessageTemplate messageTemplate) {
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();
        List<TaskInfo> taskInfoList = new ArrayList<>();

        for (MessageParam messageParam : messageParamList) {

            TaskInfo taskInfo = TaskInfo.builder()
                    .messageTemplateId(messageTemplate.getId())
                    .businessId(TaskInfoUtils.generateBusinessId(messageTemplate.getId(), messageTemplate.getTemplateType()))
                    .receiver(new HashSet<>(Arrays.asList(messageParam.getReceiver().split(String.valueOf(StrUtil.C_COMMA)))))
                    .idType(messageTemplate.getIdType())
                    .sendChannel(messageTemplate.getSendChannel())
                    .templateType(messageTemplate.getTemplateType())
                    .msgType(messageTemplate.getMsgType())
                    .shieldType(messageTemplate.getShieldType())
                    .sendAccount(messageTemplate.getSendAccount())
                    .contentModel(getContentModelValue(messageTemplate, messageParam)).build();

            taskInfoList.add(taskInfo);
        }

        return taskInfoList;

    }

    /**
     * 获取 contentModel，替换模板msgContent中占位符信息
     */
    private static ContentModel getContentModelValue(MessageTemplate messageTemplate, MessageParam messageParam) {

        // 得到真正的ContentModel 类型
        Integer sendChannel = messageTemplate.getSendChannel();
        Class contentModelClass = ChannelType.getChanelModelClassByCode(sendChannel);


        // 得到模板的 msgContent 和 入参
        Map<String, String> variables = messageParam.getVariables();
        JSONObject jsonObject = JSON.parseObject(messageTemplate.getMsgContent());


        // 通过反射 组装出 contentModel
        Field[] fields = ReflectUtil.getFields(contentModelClass);
        ContentModel contentModel = (ContentModel) ReflectUtil.newInstance(contentModelClass);
        for (Field field : fields) {
            String originValue = jsonObject.getString(field.getName());

            if (StrUtil.isNotBlank(originValue)) {
                String resultValue = ContentHolderUtil.replacePlaceHolder(originValue, variables);
                ReflectUtil.setFieldValue(contentModel, field, resultValue);
            }
        }

        // 如果 url 字段存在，则在url拼接对应的埋点参数
        String url = (String) ReflectUtil.getFieldValue(contentModel, "url");
        if (StrUtil.isNotBlank(url)) {
            String resultUrl = TaskInfoUtils.generateUrl(url, messageTemplate.getId(), messageTemplate.getTemplateType());
            ReflectUtil.setFieldValue(contentModel, "url", resultUrl);
        }
        return contentModel;
    }








}
