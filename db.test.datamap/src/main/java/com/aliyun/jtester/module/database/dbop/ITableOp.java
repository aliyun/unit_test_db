package com.aliyun.jtester.module.database.dbop;

import com.aliyun.jtester.hamcrest.iassert.object.intf.ICollectionAssert;
import com.aliyun.jtester.hamcrest.iassert.object.intf.INumberAssert;
import com.aliyun.jtester.hamcrest.iassert.object.intf.IObjectAssert;
import com.aliyun.jtester.module.ICore;
import com.aliyun.jtester.tools.datagen.DataSet;

@SuppressWarnings("rawtypes")
public interface ITableOp {
    /**
     * 清空数据表
     * 
     */
    ITableOp clean();

    /**
     * 根据json插入数据
     * 
     */
    ITableOp insert(String json, String... more);

    /**
     * 批量插入count条数据
     * 
     * @param count 需要插入的数据的数量
     * @param data 需要插入的数据 Map，key:字段，value：n条数据的数组集合和策略
     * @return
     */
    ITableOp insert(int count, ICore.DataMap data);

    /**
     * 根据Map的key（表字段）插入数据
     * 
     */
    ITableOp insert(ICore.DataMap data, ICore.DataMap... datas);

    /**
     * 插入数据集
     * 
     */
    ITableOp insert(DataSet dataset);

    /**
     * 提交数据
     */
    void commit();

    /**
     * 回滚数据
     */
    void rollback();

    /**
     * 查询表数据，做数据断言
     * 
     */
    ICollectionAssert query();

    /**
     * 根据条件查询数据，并返回数据断言器
     * 
     */
    ICollectionAssert queryWhere(String where);

    /**
     * 根据条件查询数据，并返回数据断言器
     * 
     */
    ICollectionAssert queryWhere(ICore.DataMap where);

    /**
     * 查询表中所有的数据，并且每条数据填充到PoJo中 返回 List 的断言器
     * 
     */
    ICollectionAssert queryList(Class pojo);

    /**
     * 查询单条数据，转换为PoJo对象。并且返回对象断言器
     * 
     */
    IObjectAssert queryAs(Class pojo);

    /**
     * 查询表count(*)的值，并且返回断言器
     * 
     */
    INumberAssert count();
}
