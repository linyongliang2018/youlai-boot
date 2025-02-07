package com.youlai.boot.modules.asset.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.youlai.boot.modules.asset.model.entity.AssetClassification;
import com.youlai.boot.modules.asset.model.vo.AssetClassificationVO;

import java.util.List;

public interface AssetClassificationService extends IService<AssetClassification> {

    // 获取树形结构
    List<AssetClassificationVO> getTree();

    // 创建新的分类
    boolean create(AssetClassification assetClassification);

    // 更新分类
    boolean update(Long id, AssetClassification assetClassification);

    // 删除分类
    boolean delete(Long id);
}
