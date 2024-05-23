package com.funnycode.react_springboot_account.demoTest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SortServiceTest {

    @Test
    void testSort(){
        SortService sortService = new SortService();
        //given
        int[] numbers = new int[]{1, 4, 3, 5, 2};


        int[] actual = sortService.sort(numbers);
        int[] expected = new int[]{1, 2, 3, 4, 5};

        for (int i = 0; i < expected.length; i++){
            assertEquals(expected[i], actual[i]);
        }
    }


}