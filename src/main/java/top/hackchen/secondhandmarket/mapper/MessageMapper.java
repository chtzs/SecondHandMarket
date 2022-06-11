package top.hackchen.secondhandmarket.mapper;

import org.springframework.stereotype.Repository;
import top.hackchen.secondhandmarket.beans.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author wsbch
* @description 针对表【messages】的数据库操作Mapper
* @createDate 2022-06-06 14:33:29
* @Entity top.hackchen.secondhandmarket.beans.Message
*/
@Repository
public interface MessageMapper extends BaseMapper<Message> {

}




