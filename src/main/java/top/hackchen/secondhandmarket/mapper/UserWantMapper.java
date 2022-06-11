package top.hackchen.secondhandmarket.mapper;

import org.springframework.stereotype.Repository;
import top.hackchen.secondhandmarket.beans.UserWant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author wsbch
* @description 针对表【users_want】的数据库操作Mapper
* @createDate 2022-06-10 21:39:17
* @Entity top.hackchen.secondhandmarket.beans.UserWant
*/
@Repository
public interface UserWantMapper extends BaseMapper<UserWant> {

}




