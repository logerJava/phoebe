package com.loger.phoebe.service.api.domain;

import com.oracle.webservices.internal.api.databinding.DatabindingMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.service.api.domain
 * @date 2022/4/20 15:19
 * @description:
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MessageParam {

    /**
     * 接收者
     * 多个用 ',' 逗号分隔
     * 必传
     */
    private String receiver;

    /**
     * 消息内容中可变部分(占位符)
     * 可选
     */
    private Map<String, String> variables;

    /**
     * 扩展参数
     * 可选
     */
    private Map<String, String> extra;

}
