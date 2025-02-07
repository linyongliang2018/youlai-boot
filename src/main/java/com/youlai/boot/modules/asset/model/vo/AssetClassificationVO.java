package com.youlai.boot.modules.asset.model.vo;

import lombok.Data;
import java.util.List;

@Data
public class AssetClassificationVO {
    private Long id;
    private String bizCode;
    private String name;
    private Long parentId;
    private Integer level;
    private String description;
    private Boolean isActive;
    private java.sql.Timestamp createdAt;
    private java.sql.Timestamp updatedAt;
    private List<AssetClassificationVO> children;  // 子分类
}
