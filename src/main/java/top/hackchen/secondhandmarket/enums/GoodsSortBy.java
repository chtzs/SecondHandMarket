package top.hackchen.secondhandmarket.enums;

import java.util.HashMap;
import java.util.Map;

public enum GoodsSortBy {
    /**
     * 按照价格排序
     */
    PRICE("actual_price"),
    /**
     * 按照发布日期排序
     */
    POST_DATE("post_date");
    private final String column;

    GoodsSortBy(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
