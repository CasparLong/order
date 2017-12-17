package com.caspar.order.entity;

import com.caspar.order.entity.base.BaseDomain;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "seller_info")
@Data
public class SellerInfo extends BaseDomain implements Serializable {
    private static final long serialVersionUID = -6436301786833785662L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String username;

    private String password;

    /**
     * 微信openid
     */
    private String openid;

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