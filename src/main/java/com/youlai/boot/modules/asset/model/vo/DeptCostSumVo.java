package com.youlai.boot.modules.asset.model.vo;

import lombok.Data;

@Data
public class DeptCostSumVo {
    private String costCategoryType;
    private String costCategoryName;
    private String subitemCode;
    private String currentYearAmount;
    private String lastYearYearAmount;
    private String lastYearActYearAmount;

    public DeptCostSumVo(String costCategoryType, String costCategoryName, String subitemCode,
                         String currentYearAmount, String lastYearYearAmount, String lastYearActYearAmount) {
        this.costCategoryType = costCategoryType;
        this.costCategoryName = costCategoryName;
        this.subitemCode = subitemCode;
        this.currentYearAmount = currentYearAmount;
        this.lastYearYearAmount = lastYearYearAmount;
        this.lastYearActYearAmount = lastYearActYearAmount;
    }

    @Override
    public String toString() {
        return "[" + costCategoryType + ", " + costCategoryName + ", " + subitemCode +
               ", 当前金额=" + currentYearAmount +
               ", 上一年金额=" + lastYearYearAmount +
               ", 上一年实际金额=" + lastYearActYearAmount + "]";
    }
}
