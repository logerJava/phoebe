package com.loger.phoebe.handler.supplier.sms;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.supplier
 * @date 2022/7/26 16:18
 * @description:
 */
@Component
public class SupplierHolder {

    private Map<String, SupplierHandler> handlers = new HashMap<>(8);

    public void putHandler(String supplierName, SupplierHandler handler) {
        handlers.put(supplierName, handler);
    }
    public SupplierHandler route(String supplierName) {
        return handlers.get(supplierName);
    }

}
