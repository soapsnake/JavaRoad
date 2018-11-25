package com.soapsnake.thinkinjava.math;

/**
 * Created by soapsnake on 2017/6/11.
 */
public class BiteMathTest {

    public static void main(String[] args) {
//
//        int i = -1;
//        i >>>= 10;
//        System.out.println(i);
//
//        Integer j = Integer.MAX_VALUE;
//        j >>>= 10;
//        System.out.println(j);
//
//        System.out.println(0 < 0);

        for (TestEnum testEnum : TestEnum.values()) {
            System.out.println(testEnum.getValue());
        }


    }

    public enum TestEnum {
        NOT_BILLED((short) 0, "未开票"),
        BILLING((short) 1, "开票中"),
        BILLED((short) 2, "已开票"),
        BILLING_ERROR((short) 3, "开票失败");
        private Short value;
        private String desc;

        TestEnum(Short value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public Short getValue() {
            return value;
        }

        public void setValue(Short value) {
            this.value = value;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
