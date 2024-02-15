package com.datalinkx.dataserver.bean.domain;

import java.util.Optional;

import com.datalinkx.common.utils.ObjectUtils;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringPath;
import io.micrometer.common.util.StringUtils;
import org.springframework.data.domain.Sort;

public class PageDomain
{
    /** 当前记录起始索引 */
    private Integer pageNum;

    /** 每页显示记录数 */
    private Integer pageSize;

    /** 排序列 */
    private String orderByColumn;

    /** 排序的方向desc或者asc */
    private String isAsc = "asc";

    /** 分页参数合理化 */
    private Boolean reasonable = true;

    public String getOrderBy()
    {
        if (StringUtils.isEmpty(orderByColumn))
        {
            return "";
        }
        return orderByColumn + " " + isAsc;
    }

    public int offset(){
        return (this.pageNum-1)*this.pageSize;
    }

    public Optional<Sort> getJpaOrderBy()
    {
        if (StringUtils.isEmpty(orderByColumn))
        {
            orderByColumn = "id";
        }
        Sort sort = Sort.by(orderByColumn);
        if(isAsc.equals("asc")){
            sort.ascending();
        }else{
            sort.descending();
        }
        return Optional.of(sort);
    }

    public Optional<OrderSpecifier> getDslOrderBy(){
        if (StringUtils.isEmpty(orderByColumn))
        {
            orderByColumn = "id";
        }
        StringPath stringPath = Expressions.stringPath(orderByColumn);
        OrderSpecifier orderSpecifier = null;
        if (isAsc.equals("desc")){
            orderSpecifier = stringPath.desc();
        }else{
            orderSpecifier = stringPath.asc();
        }
        return Optional.of(orderSpecifier);
    }

    public Integer getPageNum()
    {
        return pageNum;
    }

    public void setPageNum(Integer pageNum)
    {
        this.pageNum = pageNum;
    }

    public Integer getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(Integer pageSize)
    {
        this.pageSize = pageSize;
    }

    public String getOrderByColumn()
    {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn)
    {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc()
    {
        return isAsc;
    }

    public void setIsAsc(String isAsc)
    {
        if (StringUtils.isNotEmpty(isAsc))
        {
            // 兼容前端排序类型
            if ("ascending".equals(isAsc))
            {
                isAsc = "asc";
            }
            else if ("descending".equals(isAsc))
            {
                isAsc = "desc";
            }
            this.isAsc = isAsc;
        }
    }

    public Boolean getReasonable()
    {
        if (ObjectUtils.isEmpty(reasonable))
        {
            return Boolean.TRUE;
        }
        return reasonable;
    }

    public void setReasonable(Boolean reasonable)
    {
        this.reasonable = reasonable;
    }
}
