package com.loger.phoebe.handler.supplier.sms;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author chao
 * @project phoebe
 * @package com.loger.phoebe.handler.supplier
 * @date 2022/7/26 15:10
 * @description:
 */
public abstract class BaseSupplierHandler implements SupplierHandler {

    /**
     * 供应商名称
     */
    protected String supplierName;

    @Autowired
    private SupplierHolder supplierHolder;

    @PostConstruct
    public void init() {
        supplierHolder.putHandler(supplierName, this);
    }





}
