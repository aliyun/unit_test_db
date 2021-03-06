package com.aliyun.jtester.hamcrest.iassert.object.intf;

import com.aliyun.jtester.hamcrest.iassert.common.intf.IBaseAssert;
import com.aliyun.jtester.hamcrest.iassert.common.intf.IComparableAssert;

/**
 * 数值型对象断言接口
 * 
 * @author darui.wudr
 * @param <T>
 * @param <E>
 */
public interface INumberAssert<T extends Number & Comparable<T>, E extends INumberAssert<T, ?>>
        extends IBaseAssert<T, E>, IComparableAssert<T, E> {
    E isEqualTo(Number expected);
}
