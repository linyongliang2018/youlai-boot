package com.youlai.boot.modules.asset.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.youlai.boot.modules.asset.model.entity.AssetClassification;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AssetClassificationMapper extends BaseMapper<AssetClassification> {
}