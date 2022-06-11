package top.hackchen.secondhandmarket.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import top.hackchen.secondhandmarket.beans.User;
import top.hackchen.secondhandmarket.service.UserService;
import top.hackchen.secondhandmarket.util.EncryptUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    private MockMvc mockMvc;
    @Autowired
    private UserController userController;
    @Autowired
    private UserService userService;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void register() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/test")
                        .param("number", "1"))
                .andDo(print())
                .andExpect(status().is(400));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/delete"))
                .andDo(print());
    }

    @Test
    void search() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/user/search")
                        .param("current", "0")
                        .param("size", "10")
                        .param("content", "陈浩天"))
                .andDo(print());
    }

    @Test
    void addTestUser() {
        final int offset = 1;
        final int addCount = 10;
        for (int i = offset; i < offset + addCount; i++) {
            String nickname = "陈浩天_" + i;
            String password = EncryptUtils.md5(String.valueOf(i));
            Long phone = (long) (110 + i);
            userService.register(phone, password);
            User user = userService.getOne(new QueryWrapper<User>().eq("phone_number", phone));
            user.setNickname(nickname);
            userService.updateById(user);
        }
    }
}