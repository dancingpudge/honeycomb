package com.honeycomb.common.util;


import java.util.ArrayList;
import java.util.List;

/**
 * @description: com.honeycomb.common.util <br>
 * @date: 2021/2/26 3:29 下午 <br>
 * @author: liuhui <br>
 * @version: 0.0.1-SNAPSHOT <br>
 */
class ObjectFeaturesFunctionTest {

    public static void main(String[] args) {
        test(new ReformerFeatures());
    }

    static void test(ObjectFeaturesFunction off) {
        List list = new ArrayList();
        list.add(new ReformerTestObj(1));
        list.add(new ReformerTestObj(2));
        list.add(new ReformerTestObj(3));
        list.add(new ReformerTestObj(4));

        list.stream().map(off::features).forEach(a -> System.out.println(a));
    }

    static class ReformerTestObj {
        private int id;

        public ReformerTestObj(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
