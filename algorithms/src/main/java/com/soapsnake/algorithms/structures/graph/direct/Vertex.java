package com.soapsnake.algorithms.structures.graph.direct;

import java.util.HashMap;
import java.util.Map;

/**
 * 顶点
 */
public class Vertex {

    /**
     *  0 -> Vertex(0, 'A')
     *  1 -> Vertex(0, 'B')
     */
    private static final Map<Integer, Vertex> MAP;
    static {
        MAP = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            MAP.put(i, new Vertex(i));
        }
    }

    private int num;
    private char state;

    Vertex(int num) {
        this.num = num;
        this.state = (char) (num + 65);
    }

    public static Vertex getByNum(int num) {
        return MAP.get(num);
    }

    public static Vertex getByChar(char c) {
        return MAP.get(c - 65);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "num=" + num +
                ", state=" + state +
                '}';
    }
}
