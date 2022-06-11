package top.hackchen.secondhandmarket.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.hackchen.secondhandmarket.beans.Goods;
import top.hackchen.secondhandmarket.service.GoodsService;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoodsControllerTest {
    @Autowired
    private GoodsService goodsService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void insertTestData() {
        String[] names = {"苹果", "香蕉", "梨", "葡萄", "西瓜"};
        double[] prices = {5, 3, 3, 10, 0.5};
        String[] adj = {"大", "小", "香", "水晶", "", "终极"};
        String[] locations = {"海南", "新疆", "山东", "云南"};
        for (String location : locations) {
            for (String a : adj) {
                for (int i = 0; i < names.length; i++) {
//                    goodsService.postGoods(1,
//                            location + a + names[i],
//                            location + "的",
//                            BigDecimal.valueOf(prices[i] + 1000),
//                            BigDecimal.valueOf(prices[i]),
//                            "甜美多汁！",
//                            "全新！");
                }
            }
        }
    }
}