package com.youlai.boot.modules.asset.controller;

import com.youlai.boot.modules.asset.model.entity.AssetClassification;
import com.youlai.boot.modules.asset.model.vo.AssetClassificationVO;
import com.youlai.boot.modules.asset.service.AssetClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asset-classifications")
public class AssetClassificationController {

    @Autowired
    private AssetClassificationService service;

    /**
     * 获取分类树形结构（返回 VO）
     */
    @GetMapping("/tree")
    public List<AssetClassificationVO> getTree() {
        return service.getTree();
    }

    /**
     * 获取所有分类
     */
    @GetMapping
    public List<AssetClassification> getAll() {
        return service.list();
    }

    /**
     * 根据 ID 获取分类
     */
    @GetMapping("/{id}")
    public AssetClassification getById(@PathVariable Long id) {
        return service.getById(id);
    }

    /**
     * 创建新的分类
     */
    @PostMapping
    public boolean create(@RequestBody AssetClassification assetClassification) {
        return service.create(assetClassification);
    }

    /**
     * 更新分类
     */
    @PutMapping("/{id}")
    public boolean update(@PathVariable Long id, @RequestBody AssetClassification assetClassification) {
        return service.update(id, assetClassification);
    }

    /**
     * 删除分类
     */
    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
