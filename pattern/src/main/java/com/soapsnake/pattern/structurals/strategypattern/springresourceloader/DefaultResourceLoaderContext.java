package com.soapsnake.pattern.structurals.strategypattern.springresourceloader;

public class DefaultResourceLoaderContext implements ResourceLoaderContext {

    //策略模式中的context的角色
    //spring中到处都是各种context,不用怀疑,这些都是策略模式中的context

    private LoadStrategy loadStrategy;

    private OtherStrategy otherStrategy = new DefaultOtherThingsStrategy();

    public DefaultResourceLoaderContext(LoadStrategy loadStrategy) {
        this.loadStrategy = loadStrategy;
    }

    @Override
    public Object loadResource() {
        return loadStrategy.doLoad();
    }

    @Override
    public void doOthers() {
        otherStrategy.doOthers();
    }
}
