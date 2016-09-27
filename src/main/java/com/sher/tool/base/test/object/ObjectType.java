package com.sher.tool.base.test.object;

/**
 * Created by Administrator on 2016/9/26.
 *
 *  object type
 */
public class ObjectType {

    public static void main(String args[]){
        ObjectType type = new ObjectType();
        User user = type.new User();
        user.setValue("1111");
        Object value = user.getValue();
    }


    class User{
        Object value;

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }
    }



}
