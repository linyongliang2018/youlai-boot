package com.youlai.boot.modules.asset.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.youlai.boot.modules.asset.mapper.AssetClassificationMapper;
import com.youlai.boot.modules.asset.model.entity.AssetClassification;
import com.youlai.boot.modules.asset.model.vo.AssetClassificationVO;
import com.youlai.boot.modules.asset.service.AssetClassificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssetClassificationServiceImpl extends ServiceImpl<AssetClassificationMapper, AssetClassification> implements AssetClassificationService {

    /**
     * 获取树形结构的 VO
     */
    @Override
    public List<AssetClassificationVO> getTree() {
        List<AssetClassification> allCategories = this.list();
        return buildTree(allCategories, null);
    }

    /**
     * 递归构建树形结构，转换为 VO
     */
    private List<AssetClassificationVO> buildTree(List<AssetClassification> categories, Long parentId) {
        return categories.stream()
                .filter(category -> (parentId == null && category.getParentId() == null) ||
                        (parentId != null && parentId.equals(category.getParentId())))
                .map(category -> {
                    AssetClassificationVO vo = new AssetClassificationVO();
                    vo.setId(category.getId());
                    vo.setBizCode(category.getBizCode());
                    vo.setName(category.getName());
                    vo.setParentId(category.getParentId());
                    vo.setLevel(category.getLevel());
                    vo.setDescription(category.getDescription());
                    vo.setIsActive(category.getIsActive());
                    vo.setCreatedAt(category.getCreatedAt());
                    vo.setUpdatedAt(category.getUpdatedAt());
                    vo.setChildren(buildTree(categories, category.getId()));  // 设置子分类
                    return vo;
                })
                .collect(Collectors.toList());
    }


    /**
     * 创建新的分类
     */
    @Override
    @Transactional
    public boolean create(AssetClassification assetClassification) {
        return this.save(assetClassification);  // MyBatis-Plus save 自动插入数据
    }

    /**
     * 更新分类
     */
    @Override
    @Transactional
    public boolean update(Long id, AssetClassification assetClassification) {
        assetClassification.setId(id);
        return this.updateById(assetClassification);  // MyBatis-Plus updateById 更新数据
    }

    /**
     * 删除分类
     */
    @Override
    @Transactional
    public boolean delete(Long id) {
        return this.removeById(id);  // MyBatis-Plus removeById 删除数据
    }
}
