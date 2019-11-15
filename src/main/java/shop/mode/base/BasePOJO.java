package shop.mode.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 让 mybatis-generator 自动生成的 pojo 自动继承本类
 */

public class BasePOJO implements Serializable {
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
