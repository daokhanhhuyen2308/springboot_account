package com.funnycode.react_springboot_account.demoTest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomMath {

    private final SortService sortService;

    public int getMaxValueOrMinValue(int[] number, boolean findMax){
     int[] sortedNumbers =  sortService.sort(number);

     if (findMax){
         return sortedNumbers[sortedNumbers.length - 1];
     }
     else {
         return sortedNumbers[0];
     }
    }

}
