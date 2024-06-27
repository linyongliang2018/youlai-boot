package com.youlai.system.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import com.youlai.system.common.base.BaseEntity;
import lombok.Data;

/**
 * 系统日志 实体类
 *
 * @author Ray
 * @since 2.10.0
 */
@Data
public class SysLog implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 日志类型
     *
     * @see com.youlai.system.enums.LogTypeEnum
     */
    private Integer type;


    /**
     * 日志标题
     */
    private String title;

    /**
     * 请求路径
     */
    private String requestUri;

    /**
     * IP 地址
     */
    private String ip;

    /**
     * 执行时间(毫秒)
     */
    private Long executionTime;

    /**
     * 创建人ID
     */
    private Long createBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
}