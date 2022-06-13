package top.hackchen.secondhandmarket.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hackchen.secondhandmarket.service.impl.UserWantServiceImpl;

import java.util.concurrent.CountDownLatch;

@SpringBootTest
class UserWantControllerTest {
    @Autowired
    private UserWantServiceImpl userWantService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void test() throws InterruptedException {
        final int count = 1000;
        System.out.println("开始！");
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread(() -> {
                userWantService.addWantCount(27);
                countDownLatch.countDown();
            });
            thread.start();
        }

        countDownLatch.await();
        System.out.println("结束！");
    }

    private int count;
    private final Object lock = new Object();

    private void add() {
        synchronized (lock) {
            count++;
        }
    }

    @Test
    void testLocal() throws InterruptedException {
        final int maxCount = 1000;
        int s = 0;
        System.out.println("开始！");
        CountDownLatch countDownLatch = new CountDownLatch(maxCount);
        for (int i = 0; i < maxCount; i++) {
            Thread thread = new Thread(() -> {
                add();
                countDownLatch.countDown();
            });
            thread.start();
        }

        countDownLatch.await();
        System.out.println("结束！");
        System.out.println(count);
    }
}