package com.other;

/**
 * @description: 子类
 * @author: darben
 * @create: 2020-08-22 20:29
 */
public class ChildrenClass extends ParentClass {

    private int a=222;

    public ChildrenClass(String s) {
        super(s);
        System.out.println("D");
    }

    @Override
    public int getA() {
        return a;
    }

    public static void main(String[] args) throws Exception {
        //ChildrenClass childrenClass = new ChildrenClass("C");
        String s = "hello";
        String t = "hello";
        char c [ ] = {'h','e','l','l','o'};
        System.out.println(s.equals(t));
        System.out.println(t.equals(c));
        System.out.println(s==t);
        System.out.println(t.equals(new String("hello")));

        try {

        } catch (Exception e){

        } finally {
            throw new Exception();
        }
    }

    static class ClassA{
        public static void main(String[] args) {
        }
    }
}
