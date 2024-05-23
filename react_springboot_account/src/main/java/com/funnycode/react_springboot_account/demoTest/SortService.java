package com.funnycode.react_springboot_account.demoTest;

import org.springframework.stereotype.Component;

@Component
public class SortService {

    public int[] sort(int[] numbers){

        if (numbers == null) {
            System.out.println("Array is null");
            return new int[]{};
        }

        return new int[]{1, 2, 3, 4, 5};
    }
}
