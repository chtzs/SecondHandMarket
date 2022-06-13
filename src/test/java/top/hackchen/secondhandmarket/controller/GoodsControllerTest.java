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
                    Goods goods = new Goods();
                    goods.setSellerId(1);
                    goods.setName(location + a + names[i]);
                    goods.setDescription(location + "的");
                    goods.setFakePrice(BigDecimal.valueOf(prices[i] + 1000));
                    goods.setActualPrice(BigDecimal.valueOf(prices[i]));
                    goods.setFunctionality("甜美多汁！");
                    goods.setGoodsCondition("全新！");
                    goodsService.postGoods(goods);
                }
            }
        }
    }
}