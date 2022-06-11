package top.hackchen.secondhandmarket.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import top.hackchen.secondhandmarket.beans.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author wsbch
 * @description 针对表【goods】的数据库操作Mapper
 * @createDate 2022-06-06 14:33:29
 * @Entity top.hackchen.secondhandmarket.beans.Good
 */
@Repository
public interface GoodsMapper extends BaseMapper<Goods> {
    IPage<Goods> searchGoods(IPage<Goods> page,
                             @Param("content") String content,
                             @Param("column") String column,
                             @Param("method") String method);

    IPage<Goods> searchGoodsInRange(IPage<Goods> page,
                                    @Param("content") String content,
                                    @Param("min") Integer min,
                                    @Param("max") Integer max,
                                    @Param("order") String order);
}




