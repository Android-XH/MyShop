package shop.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import shop.controller.RequestCommon;
import shop.exception.RequestException;
import shop.mode.Product;
import shop.mode.base.BaseExample;
import shop.mode.base.BasePOJO;
import shop.service.BaseService;
import shop.util.Pagination;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;


/**
 * 该类继承 Service4DAOImpl，会自动识别继承子类的前缀，自动预装载表（使用createCriteria()函数）
 * 该类是 BaseService 的实现，接口要求见BaseService
 *
 * @see Service4DAOImpl
 * @see BaseService
 */

public class BaseServiceImpl<M, E> extends Service4DAOImpl<M, E> implements BaseService {

    /**
     * @see BaseService
     */
    @Override
    public List list(Object... paramAndObjects) throws Exception {
        BaseExample example = getExample();
        Object criteria = example.createCriteria();

        if (paramAndObjects.length % 2 != 0) {
            return null;
        }
        Pagination pagination = null;
        //默认按id排序
        example.setOrderByClause("id desc");
        //默认对查询进行两次遍历查询
        int depth = 2;
        for (int i = 0; i < paramAndObjects.length; i += 2) {
            if (paramAndObjects[i].equals("order") && paramAndObjects[i + 1] instanceof String) {
                example.setOrderByClause(paramAndObjects[i + 1].toString());
                continue;
            }
            if (paramAndObjects[i].equals("depth") && paramAndObjects[i + 1] instanceof Integer) {
                depth = (Integer) paramAndObjects[i + 1];
                continue;
            }
            if (paramAndObjects[i].toString().endsWith("_like") && paramAndObjects[i + 1] instanceof String) {
                String column = StringUtils.replace(paramAndObjects[i].toString(), "_like", "");
                criteria.getClass()
                        .getMethod("and" + StringUtils.capitalize(column) + "Like", String.class)
                        .invoke(criteria, "%" + paramAndObjects[i + 1].toString() + "%");
                continue;
            }
            if (paramAndObjects[i].toString().endsWith("_orLike") && paramAndObjects[i + 1] instanceof String) {
                String column = StringUtils.replace(paramAndObjects[i].toString(), "_orLike", "");
                Object criteria2 =  example.getClass().getMethod("or",null).invoke(example);
                criteria2.getClass()
                        .getMethod("and" + StringUtils.capitalize(column) + "Like", String.class)
                        .invoke(criteria2, "%" + paramAndObjects[i + 1].toString() + "%");
                continue;
            }
            //andIdNotEqualTo
            if (paramAndObjects[i].toString().endsWith("_notEq") ) {
                String column = StringUtils.replace(paramAndObjects[i].toString(), "_notEq", "");
                criteria.getClass()
                        .getMethod( "and" +StringUtils.capitalize(column) + "NotEqualTo",paramAndObjects[i + 1].getClass())
                        .invoke(criteria, paramAndObjects[i + 1]);
                continue;
            }
            // =value
            if (paramAndObjects[i].toString().endsWith("_eq") ) {
                String column = StringUtils.replace(paramAndObjects[i].toString(), "_eq", "");
                criteria.getClass()
                        .getMethod( "and" +StringUtils.capitalize(column) + "EqualTo",paramAndObjects[i + 1].getClass())
                        .invoke(criteria, paramAndObjects[i + 1]);
                continue;
            }
            // >value
            if (paramAndObjects[i].toString().endsWith("_gt")) {
                String column = StringUtils.replace(paramAndObjects[i].toString(), "_gt", "");
                criteria.getClass()
                        .getMethod("and" + StringUtils.capitalize(column) + "GreaterThan",paramAndObjects[i + 1].getClass())
                        .invoke(criteria, paramAndObjects[i + 1]);
                continue;
            }
            //>=value
            if (paramAndObjects[i].toString().endsWith("_gt&eq")) {
                String column = StringUtils.replace(paramAndObjects[i].toString(), "_gt&eq", "");
                criteria.getClass()
                        .getMethod("and" + StringUtils.capitalize(column) + "GreaterThanOrEqualTo",paramAndObjects[i + 1].getClass())
                        .invoke(criteria, paramAndObjects[i + 1]);
                continue;
            }
            // <value
            if (paramAndObjects[i].toString().endsWith("_lt") ) {
                String column = StringUtils.replace(paramAndObjects[i].toString(), "_lt", "");
                criteria.getClass()
                        .getMethod("and" + StringUtils.capitalize(column) + "LessThan", paramAndObjects[i + 1].getClass())
                        .invoke(criteria, paramAndObjects[i + 1]);
                continue;
            }
            // <=value
            if (paramAndObjects[i].toString().endsWith("_lt&et") ) {
                String column = StringUtils.replace(paramAndObjects[i].toString(), "_lt&et", "");
                criteria.getClass()
                        .getMethod("and" + StringUtils.capitalize(column) + "LessThanOrEqualTo", paramAndObjects[i + 1].getClass())
                        .invoke(criteria, paramAndObjects[i + 1]);
                continue;
            }

            if (paramAndObjects[i].equals("pagination") && paramAndObjects[i + 1] instanceof Pagination) {
                pagination = (Pagination) paramAndObjects[i + 1];
                continue;
            }
        }
        List list;
        if (pagination != null) {
            PageHelper.offsetPage(pagination.getStart(), pagination.getSize());
            list = mapper.selectByExample(example, depth);
            pagination.setTotal((int) new PageInfo<>(list).getTotal());
        } else {
            list = mapper.selectByExample(example, depth);
        }
        return list;
    }

    @Override
    public List selectBySql(String sql) throws Exception {
        return mapper.selectBySql(sql);
    }

    @Override
    public int insertOfList(ArrayList list) throws Exception {
        return mapper.insertOfList(list);
    }


    @Override
    public BasePOJO getOne(Object... paramAndObjects) {
        BasePOJO object = null;
        try {
            List data= list(paramAndObjects);
            if(!data.isEmpty()){
                object = (BasePOJO) list(paramAndObjects).get(0);
            }
        }catch (InvocationTargetException e) {
            System.out.println("此处接收被调用方法内部未被捕获的异常");
            Throwable t = e.getTargetException();// 获取目标异常
            t.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public Integer add(BasePOJO object) throws Exception {
        return mapper.insert(object);
    }

    @Override
    public void update(BasePOJO object) throws Exception {
        mapper.updateByPrimaryKey(object);
    }

    @Override
    public void update(Integer[] ids, String[] values, String changeFiledName) throws Exception {
        for (int i = 0; i < ids.length; i++) {
            int id = ids[i];
            String newValue = values[i];
            //从数据库获取旧对象
            Object objectFromDB = get(id);
            //把新值插入旧对象
            objectFromDB.getClass()
                    .getMethod("set" + StringUtils.capitalize(changeFiledName), newValue.getClass())
                    .invoke(objectFromDB, newValue);
            //更新旧对象
            update((BasePOJO) objectFromDB);
        }
    }

    @Override
    public BasePOJO get(int id) throws Exception {
        BasePOJO object = (BasePOJO) mapper.selectByPrimaryKey(id);
        if (object == null) {
            throw new RequestException(RequestCommon.ERROR_ID_NOT_FOUND);
        }
        return object;
    }

    @Override
    public BasePOJO get(int id, int depth) throws Exception {
        BasePOJO object = (BasePOJO) mapper.selectByPrimaryKey(id, depth);
        if (object == null) {
            throw new RequestException(RequestCommon.ERROR_ID_NOT_FOUND);
        }
        return object;
    }

    @Override
    public BasePOJO get(Class mapperInterface, int id) throws Exception {
        mapper = getMapper(mapperInterface);
        BasePOJO object = (BasePOJO) mapper.selectByPrimaryKey(id);
        return object;
    }

    /**
     * @see BaseService
     */
    @Override
    public void delete(BasePOJO object) throws Exception {
        mapper.deleteById(object.getId());
    }

    @Override
    public void deleteById(int id) throws Exception {
        mapper.deleteById(id);
    }
    public void deleteDBData(int id)throws Exception {
        mapper.deleteById(id);
    }

}