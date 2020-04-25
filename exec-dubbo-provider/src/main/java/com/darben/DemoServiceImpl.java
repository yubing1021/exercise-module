package com.darben;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: darben
 * @create: 2020-04-25 17:13
 */
public class DemoServiceImpl implements DemoService  {
    @Override
    public List<String> getPermissions(Long id) {
        List<String> demo = new ArrayList<String>();
        demo.add(String.format("Permission_%d", id - 1));
        demo.add(String.format("Permission_%d", id));
        demo.add(String.format("Permission_%d", id + 1));
        return demo;
    }
}
