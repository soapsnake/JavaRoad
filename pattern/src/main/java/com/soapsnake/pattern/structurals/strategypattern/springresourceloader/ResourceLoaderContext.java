package com.soapsnake.pattern.structurals.strategypattern.springresourceloader;

public interface ResourceLoaderContext {
    Object loadResource();

    //context还允许在我这里干点其他的事情
    void doOthers();
}
