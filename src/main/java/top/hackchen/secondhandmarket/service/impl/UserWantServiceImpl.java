package top.hackchen.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.hackchen.secondhandmarket.beans.UserWant;
import top.hackchen.secondhandmarket.service.UserWantService;
import top.hackchen.secondhandmarket.mapper.UserWantMapper;
import org.springframework.stereotype.Service;

/**
* @author wsbch
* @description 针对表【users_want】的数据库操作Service实现
* @createDate 2022-06-10 21:39:17
*/
@Service
public class UserWantServiceImpl extends ServiceImpl<UserWantMapper, UserWant>
    implements UserWantService{

}




