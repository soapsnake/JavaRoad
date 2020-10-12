package com.soapsnake.lab.collections.map;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassMates {

    int id;
    String name;
    int age;


    @Override
    public int hashCode() {
        if (id <= 5) {
            return 1;
        } else if (id <= 10) {
            return 2;
        } else if (id <= 15) {
            return 3;
        } else
            return 4;
    }

}
