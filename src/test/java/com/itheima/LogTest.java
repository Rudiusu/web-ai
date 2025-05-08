package com.itheima;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
@Slf4j
public class LogTest {

    @Test
    public void testLog(){
        log.info("{} : 开始计算...", LocalDateTime.now());


        int sum = 0;
        int[] nums = {1, 5, 3, 2, 1, 4, 5, 4, 6, 7, 4, 34, 2, 23};
        for (int num : nums) {
            sum += num;
        }
        

        log.info("计算结果为:{} ",sum);
        log.info("{} 结束计算: ...", LocalDateTime.now());
    }

}
