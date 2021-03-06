package top.hackchen.secondhandmarket.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import top.hackchen.secondhandmarket.beans.User;
import top.hackchen.secondhandmarket.service.UserService;
import top.hackchen.secondhandmarket.mapper.UserMapper;
import org.springframework.stereotype.Service;
import top.hackchen.secondhandmarket.util.EncryptUtils;
import top.hackchen.secondhandmarket.util.JsonResult;
import top.hackchen.secondhandmarket.util.RandomUtils;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final UserMapper userMapper;
    private final Object lock = new Object();

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean checkPasswordValid(Long phoneNumber, String password) {
        List<User> users = userMapper.selectList(new QueryWrapper<User>().eq("phone_number", phoneNumber));
        if (users.size() != 1) return false;
        User user = users.get(0);
        String compare = user.getPassword();
        String salt = user.getSalt();
        return EncryptUtils.verifyPassword(password, salt, compare);
    }

    @Override
    public boolean notExist(Long phoneNumber) {
        List<User> users =
                userMapper.selectList(new QueryWrapper<User>().eq("phone_number", phoneNumber));
        return users.size() == 0;
    }

    @Override
    public boolean isExist(Integer userId) {
        List<User> users =
                userMapper.selectList(new QueryWrapper<User>().eq("id", userId));
        return users.size() == 1;
    }

    @Override
    public boolean isExist(String phoneNumber) {
        List<User> users =
                userMapper.selectList(new QueryWrapper<User>().eq("phone_number", phoneNumber));
        return users.size() == 1;
    }

    @Override
    public JsonResult<IPage<User>> searchAll(IPage<User> page, String content) {
        return JsonResult.success("????????????", userMapper.searchAll(page, "%" + content + "%"));
    }

    @Override
    @Transactional(rollbackFor = Exception.class, isolation = Isolation.SERIALIZABLE)
    public JsonResult<Object> register(String nickname, Long phoneNumber, String password) {
        if (notExist(phoneNumber)) {
            synchronized (lock) {
                if (notExist(phoneNumber)) {
                    User user = new User();
                    user.setIdentity(0);
                    user.setNickname(nickname);
                    user.setPhoneNumber(phoneNumber);
                    user.setRegisterDate(new Date());
                    user.setSalt(RandomUtils.createRandomUUID());
                    user.setPassword(EncryptUtils.passwordEncrypt(password, user.getSalt()));
                    userMapper.insert(user);
                    return JsonResult.success("???????????????");
                }
            }
        }
        return JsonResult.USER_EXISTED;
    }

    @Override
    public JsonResult<Object> fuckOff(int userId) {
        userMapper.deleteById(userId);
        return JsonResult.success("????????????");
    }
}




