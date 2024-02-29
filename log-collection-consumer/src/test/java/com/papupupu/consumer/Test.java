package com.papupupu.consumer;

import lombok.Synchronized;

public class Test {
    @org.junit.Test
    public void test01(){
//        Son son = new Son();
        Son son1 = new Son("有参构造");
    }
}

class Father{
    Father(){
        System.out.println("父类无参数构造");
    }
    Father(String msg){
        System.out.println(msg);
    }
}

class Son extends Father{
    Son(){
        //隐藏调用了super();
    }
    Son(String msg){
        //隐藏调用了super();
        super(msg);
    }
}


class Singleton{
    private  Singleton(){};

    //需要加volatile原因是，
    /*
     1. allocate内存
     2. 初始化内存
     3. singleton引用指向内存

     如果被jvm优化，导致：
     1. allocate内存
     2. singleton引用指向内存，singleton  != null
     3. 初始化内存

     */
    private volatile Singleton singleton;

    public Singleton getSingleton(){

        //防止多次频繁加锁
        if(singleton == null){
            synchronized (Singleton.class){
                //初始化的时候多个线程一起进去，导致需要再次判断是否是null
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
