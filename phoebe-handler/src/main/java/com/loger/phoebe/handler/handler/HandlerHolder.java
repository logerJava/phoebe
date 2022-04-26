package com.loger.phoebe.handler.handler;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.handler
 * @date 2022/4/26 13:21
 * @description:
 */
@Component
public class HandlerHolder {

    private Map<Integer, Handler> handlers = new HashMap<>(128);

    public void putHandler(Integer channelCode, Handler handler) {
        handlers.put(channelCode, handler);
    }

    public Handler route(Integer channelCode) {
        return handlers.get(channelCode);
    }


}
