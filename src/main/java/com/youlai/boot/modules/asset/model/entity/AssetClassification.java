package com.youlai.boot.modules.asset.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

@Data
@TableName("asset_classification")
public class AssetClassification {

    /**
     * 自增主键
     */
    @TableId
    private Long id;

    /**
     * 分类编码
     */
    private String bizCode;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 父级ID，根节点为NULL
     */
    private Long parentId;

    /**
     * 层级（1-门类，2-大类，3-中类，4-小类）
     */
    private Integer level;

    /**
     * 分类描述
     */
    private String description;

    /**
     * 是否启用
     */
    private Boolean isActive;

    /**
     * 创建时间
     */
    private Timestamp createdAt;

    /**
     * 更新时间
     */
    private Timestamp updatedAt;
}