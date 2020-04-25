package com.combat.nameservice;

/**
 * @description:测试类
 * @author: darben
 * @create: 2020-03-31 15:43
 */
public class TestIdMaker {

    private static String address = "192.168.43.175:12181,192.168.43.175:12182,192.168.43.175:12183";

    public static void main(String[] args) throws Exception {

        IdMarker idMarker = new IdMarker(address, "/NameService/IdGen","ID");

        try {
            idMarker.start();
            for (int i=0; i<10; i++){
                String id = idMarker.generateId(IdMarker.RemoveMethod.DELAY);
                System.out.println(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            idMarker.stop();
        }
    }

}
