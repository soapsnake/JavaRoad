package com.soapsnake.lab.excel;

import lombok.Data;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-05-01
 */
@Data
public class TenantInit {

    private String department;

    private String tenantCode;

    private String devId;

    private String devName;

    private String queueName;
}
