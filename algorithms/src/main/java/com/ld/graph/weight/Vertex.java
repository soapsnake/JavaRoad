package com.ld.graph.weight;

import java.util.HashMap;
import java.util.Map;

public class Vertex {

    private static final Map<Integer, Character> MAP;

    static {
        MAP = new HashMap<>();
        for (int i = 0; i < 26; i++) {
            MAP.put(i, (char) (i + 65));
        }
    }

    int num;
    char state;

    Vertex(int num) {
        this.num = num;
        this.state = getByNum(num);
    }

    Vertex(char x) {
        for (Map.Entry entry : MAP.entrySet()) {
            if (entry.getValue().equals(x))
                this.num = (int) entry.getKey();
        }
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public char getByNum(int num) {
        return MAP.get(num);
    }
}
