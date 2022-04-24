package com.niuniu.community.Controller;
public class SingleObject {
    private static SingleObject instance = null;
    private SingleObject(){

    }
    public static  SingleObject getInstance(){
        if(instance==null){
            synchronized (SingleObject.class){
                if(instance==null){
                    instance = new SingleObject();
                }
            }
        }
        return instance;
    }
    public void print(){
        System.out.println("hello");
    }
}
