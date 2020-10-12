package com.soapsnake.lab.cycle;

import java.util.Objects;

/**
 * @author liudun <liudun@kuaishou.com>
 * Created on 2020-09-02
 */
public class ObjectB {

    ObjectA objectA;

    public ObjectB(ObjectA objectA) {
        this.objectA = objectA;
    }

    public ObjectA getObjectA() {
        return objectA;
    }

    public void setObjectA(ObjectA objectA) {
        this.objectA = objectA;
    }
}
