package com.gayratrakhimov.multithreadingexamples.recycler.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MockGenerator {

    public static List<Mock> generate(int amount){
        
        List<Mock> mocks = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < amount; i++) {
            Mock mock = new Mock(UUID.randomUUID().toString(), random.nextInt(200));
            mocks.add(mock);
        }

        return mocks;

    }

}
