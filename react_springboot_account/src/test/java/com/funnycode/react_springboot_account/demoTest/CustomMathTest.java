package com.funnycode.react_springboot_account.demoTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomMathTest {

    @InjectMocks
    private CustomMath customMathTest;

    @Mock
    private SortService sortService;


    @Test
    void testGetMaxValueOrMinValue_Return_Max_Value(){
        //given
        int[] numbers = new int[]{1, 4, 2, 5, 3};
        boolean findMax = true;

        int maxValue = customMathTest.getMaxValueOrMinValue(numbers, findMax);

        //when
        when(sortService.sort(numbers)).thenReturn(new int[]{1, 2, 3, 4, 5});

        //then - verify result
        assertEquals(5, maxValue);
    }

    @Test
    void testGetMaxValueOrMinValue_Return_Min_Value(){
        //given
        int[] numbers = new int[]{1, 4, 2, 5, 3};
        boolean findMin = false;

        int minValue = customMathTest.getMaxValueOrMinValue(numbers, findMin);

        //when
        when(sortService.sort(numbers)).thenReturn(new int[]{1, 2, 3, 4, 5});

        //then - verify result
        assertEquals(1, minValue);
    }

}