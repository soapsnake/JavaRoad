package com.soapsnake.lab.cycle;

/**
 * 
 * Created on 2020-09-02
 */
public class ObjectA {

    private ObjectB objectB;

    public ObjectA(ObjectB objectB) {
        this.objectB = objectB;
    }

    public ObjectB getObjectB() {
        return objectB;
    }

    public void setObjectB(ObjectB objectB) {
        this.objectB = objectB;
    }
}
