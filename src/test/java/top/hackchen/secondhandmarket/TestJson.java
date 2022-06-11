package top.hackchen.secondhandmarket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import top.hackchen.secondhandmarket.beans.User;
import top.hackchen.secondhandmarket.beans.UserWant;

public class TestJson {
    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void test() throws JsonProcessingException {
        //这正是我想要的！
        System.out.println(mapper.writeValueAsString(new Object() {
            public final String token = "123456";
            public final User user = new User();
        }));
    }
}
