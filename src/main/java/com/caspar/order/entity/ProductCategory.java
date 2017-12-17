package com.caspar.order.entity;

import com.caspar.order.entity.base.BaseDomain;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "product_category")
@Data
public class ProductCategory extends BaseDomain implements Serializable {
    private static final long serialVersionUID = 5391439616461208694L;

    @Id
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 类目名字
     */
    @Column(name = "category_name")
    private String categoryName;

    /**
     * 类目编号
     */
    @Column(name = "category_type")
    private Integer categoryType;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private Date updateTime;

}