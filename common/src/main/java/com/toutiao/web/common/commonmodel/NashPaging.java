package com.toutiao.web.common.commonmodel;



import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * zhangjinglei 2017/9/1 上午10:01
 *
 * 此类用在接口的返回结果里，对返回结果做了封装，体现出分页的几个基本属性
 */
@Data
public class NashPaging<T> implements Serializable {
    //当前页
    private int pageNum=0;
    //每页的数量
    private int pageSize=0;
    //总量
    private long total=0;

    private T data;

    private NashPaging() {
    }

    public static <T> NashPaging<T> build(T list){
        NashPaging<T> tNashPaging = new NashPaging<T>();
        Page page = (Page) list;
        tNashPaging.setPageNum(page.getPageNum());
        tNashPaging.setPageSize(page.getPageSize());

        tNashPaging.setTotal(page.getTotal());
        tNashPaging.setData(list);
        return tNashPaging;
    }
    public static <T> NashPaging<T> pageInfobuild(T list){
        NashPaging tNashPaging = new NashPaging();
        PageInfo pageInfo = (PageInfo)list;
        tNashPaging.setPageNum(pageInfo.getPageNum());
        tNashPaging.setPageSize(pageInfo.getSize());
        tNashPaging.setTotal(pageInfo.getTotal());
        tNashPaging.setData(pageInfo.getList());
        return tNashPaging;
    }
    public static <T> NashPaging<T> noPagebuild(T list){
        NashPaging<T> tNashPaging = new NashPaging<T>();

        List nList = (List) list;

        tNashPaging.setPageNum(1);
        tNashPaging.setPageSize(1);

        tNashPaging.setTotal(nList.size());
        tNashPaging.setData(list);
        return tNashPaging;
    }
}
