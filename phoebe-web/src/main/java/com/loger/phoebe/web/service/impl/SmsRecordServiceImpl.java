package com.loger.phoebe.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.loger.phoebe.support.dao.SmsRecordDao;
import com.loger.phoebe.support.domain.MessageTemplate;
import com.loger.phoebe.support.domain.SmsRecord;
import com.loger.phoebe.web.service.SmsRecordService;
import org.springframework.stereotype.Service;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.web.service.impl
 * @date 2022/7/28 13:32
 * @description:
 */
@Service
public class SmsRecordServiceImpl extends ServiceImpl<SmsRecordDao, SmsRecord> implements SmsRecordService {
}
