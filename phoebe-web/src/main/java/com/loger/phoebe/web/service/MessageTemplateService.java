package com.loger.phoebe.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.loger.phoebe.support.domain.MessageTemplate;

import java.util.Optional;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.web.service
 * @date 2022/4/21 13:05
 * @description:
 */
public interface MessageTemplateService extends IService<MessageTemplate> {

    /**
     * 单个 保存或者更新
     * ID 存在则更新, 不存在则保存
     * @param messageTemplate
     * @return
     */
    @Override
    boolean saveOrUpdate(MessageTemplate messageTemplate);


}
