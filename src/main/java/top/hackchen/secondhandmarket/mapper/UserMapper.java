package top.hackchen.secondhandmarket.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.hackchen.secondhandmarket.beans.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author wsbch
 * @description 针对表【users】的数据库操作Mapper
 * @createDate 2022-06-06 14:33:29
 * @Entity top.hackchen.secondhandmarket.beans.User
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    IPage<User> searchAll(IPage<User> page, @Param("content") String content);
}




