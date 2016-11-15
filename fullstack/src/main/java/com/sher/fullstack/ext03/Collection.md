### Collection

* Collection接口，Jdk集合接口类，抽象整个集合行为，利用抽象思想对外提供集合类应用的行为方法

    JDK1.8 Collection接口类:

    int size();     // 返回的是int值，说明集合的最大值就是Integer.MAX_VALUE;

    boolean isEmpty();  //判断是否为空

    /**
    *   接口中实现默认方法，1:1.8新特性提供接口默认实现类，主要目的是为了在原来接口实现类的基础上增加额外的方法，
        避免由于接口中方法行为的增加而导致其他所有子类都必须去实现
    *
    */
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }






