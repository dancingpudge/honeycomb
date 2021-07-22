package com.honeycomb.common.util;

public class BeanCopierUtilTest {

    public static void main(String[] args) {
        A a = new A();
        a.setId(1);
        B b = new B();
        b.setName("b");
        BeanCopierUtil.copy(a,b);
        System.out.println(b.getId()+b.getName());
    }

    static class A {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    static class B {
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
