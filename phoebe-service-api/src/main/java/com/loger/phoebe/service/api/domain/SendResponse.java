package com.loger.phoebe.service.api.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.service.api.domain
 * @date 2022/4/20 15:24
 * @description:
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class SendResponse {

    /**
     * 响应状态
     */
    private String code;

    /**
     * 响应编码
     */
    private String msg;
}
