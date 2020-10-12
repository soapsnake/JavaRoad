package com.soapsnake.pattern.structurals.strategypattern.springresourceloader;

public class FileSystemResourceLoader implements LoadStrategy {
    @Override
    public Object doLoad() {
        System.out.println("filesystem loader do the load work!!!!");
        return null;
    }
}
