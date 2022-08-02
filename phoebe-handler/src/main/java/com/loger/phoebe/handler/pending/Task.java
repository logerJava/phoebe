package com.loger.phoebe.handler.pending;

import com.loger.phoebe.handler.handler.Handler;
import com.loger.phoebe.handler.handler.HandlerHolder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.pending
 * @date 2022/8/1 16:21
 * @description:
 */
@Data
@Accessors(chain = true)
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Task implements Runnable{

    @Autowired
    private HandlerHolder handlerHolder;






    @Override
    public void run() {

    }
}
