package top.hackchen.secondhandmarket.enums;

public enum SortOrder {
    /**
     * 升序
     */
    ASC("asc"),
    /**
     * 降序
     */
    DESC("desc");
    private final String order;

    SortOrder(String order) {
        this.order = order;
    }

    public String getOrder() {
        return order;
    }
}
