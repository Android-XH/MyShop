package shop.mode.mapper;

import shop.mode.Product;

import java.util.ArrayList;
import java.util.List;

/**
 *  配合通用 Mapper，在 Mybatis-generator 中 使 生成的 Mapper 自动继承该 Mapper
 *  并且指定 p : pojo 和 e: example
 */
public interface BaseMapper<P,E> {
    List<P> selectBySql(String sql);
    int insertOfList(ArrayList list);
}
