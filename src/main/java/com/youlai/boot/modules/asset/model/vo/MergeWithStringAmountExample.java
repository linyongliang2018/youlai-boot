package com.youlai.boot.modules.asset.model.vo;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class MergeWithStringAmountExample {

    public static void main(String[] args) {
        List<DeptCostSumVo> dataList = Arrays.asList(
            new DeptCostSumVo("A", "Category1", "001", "100", "50", "40"),
            new DeptCostSumVo("A", "Category1", null, "200", "80", "70"),
            new DeptCostSumVo("A", "Category1", "002", "150", "60", "50"),
            new DeptCostSumVo("A", "Category1", "001", "120", "55", "45"), // 重复项
            new DeptCostSumVo("B", "Category2", null, "300", "90", "85"),
            new DeptCostSumVo("B", "Category2", "003", "180", "70", "60"),
            new DeptCostSumVo("C", "Category3", "004", "250", "100", "95")
        );

        // 集合 1：按 costCategoryType + costCategoryName 合并金额
        List<DeptCostSumVo> group1 = dataList.stream()
            .filter(item -> item.getSubitemCode() == null)
            .collect(Collectors.collectingAndThen(
                Collectors.toMap(
                    item -> item.getCostCategoryType() + "-" + item.getCostCategoryName(),
                    item -> item,
                    (item1, item2) -> mergeAmounts(item1, item2)
                ),
                map -> new ArrayList<>(map.values())
            ));

        // 集合 2：按 costCategoryType + costCategoryName + subitemCode 合并金额
        List<DeptCostSumVo> group2 = dataList.stream()
            .filter(item -> item.getSubitemCode() != null)
            .collect(Collectors.collectingAndThen(
                Collectors.toMap(
                    item -> item.getCostCategoryType() + "-" + item.getCostCategoryName() + "-" + item.getSubitemCode(),
                    item -> item,
                    (item1, item2) -> mergeAmounts(item1, item2)
                ),
                map -> new ArrayList<>(map.values())
            ));

        // 输出结果
        System.out.println("集合 1（按 costCategoryType + costCategoryName 合并金额）：");
        group1.forEach(System.out::println);

        System.out.println("\n集合 2（按 costCategoryType + costCategoryName + subitemCode 合并金额）：");
        group2.forEach(System.out::println);
    }

    // 合并金额方法
    private static DeptCostSumVo mergeAmounts(DeptCostSumVo item1, DeptCostSumVo item2) {
        item1.setCurrentYearAmount(addStrings(item1.getCurrentYearAmount(), item2.getCurrentYearAmount()));
        item1.setLastYearYearAmount(addStrings(item1.getLastYearYearAmount(), item2.getLastYearYearAmount()));
        item1.setLastYearActYearAmount(addStrings(item1.getLastYearActYearAmount(), item2.getLastYearActYearAmount()));
        return item1;
    }

    // 将两个字符串金额相加
    private static String addStrings(String amount1, String amount2) {
        BigDecimal a1 = (amount1 == null || amount1.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(amount1);
        BigDecimal a2 = (amount2 == null || amount2.isEmpty()) ? BigDecimal.ZERO : new BigDecimal(amount2);
        return a1.add(a2).toPlainString();
    }
}
