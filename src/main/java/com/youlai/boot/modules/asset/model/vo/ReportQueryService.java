package com.youlai.boot.modules.asset.model.vo;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class ReportQueryService {

    private final ExecutorService executorService = Executors.newFixedThreadPool(6); // 创建线程池

    public static void main(String[] args) throws Exception {
        ReportQueryService service = new ReportQueryService();
        service.fetchReportData("DOC123");
    }

    public void fetchReportData(String documentNo) throws Exception {
        // 1. 创建异步查询任务，返回不同的实体类
        CompletableFuture<List<DetailTable1>> detail1 = CompletableFuture.supplyAsync(() -> queryDetailTable1(documentNo), executorService);
        CompletableFuture<List<DetailTable2>> detail2 = CompletableFuture.supplyAsync(() -> queryDetailTable2(documentNo), executorService);
        CompletableFuture<List<DetailTable3>> detail3 = CompletableFuture.supplyAsync(() -> queryDetailTable3(documentNo), executorService);
        CompletableFuture<List<DetailTable4>> detail4 = CompletableFuture.supplyAsync(() -> queryDetailTable4(documentNo), executorService);
        CompletableFuture<List<DetailTable5>> detail5 = CompletableFuture.supplyAsync(() -> queryDetailTable5(documentNo), executorService);
        CompletableFuture<List<DetailTable6>> detail6 = CompletableFuture.supplyAsync(() -> queryDetailTable6(documentNo), executorService);

        // 2. 等待所有任务完成
        CompletableFuture<Void> allOf = CompletableFuture.allOf(detail1, detail2, detail3, detail4, detail5, detail6);
        allOf.get(); // 阻塞等待所有查询完成

        // 3. 获取各个查询结果
        List<DetailTable1> result1 = detail1.get();
        List<DetailTable2> result2 = detail2.get();
        List<DetailTable3> result3 = detail3.get();
        List<DetailTable4> result4 = detail4.get();
        List<DetailTable5> result5 = detail5.get();
        List<DetailTable6> result6 = detail6.get();

        // 4. 处理最终结果
        System.out.println("明细表1数据：" + result1);
        System.out.println("明细表2数据：" + result2);
        System.out.println("明细表3数据：" + result3);
        System.out.println("明细表4数据：" + result4);
        System.out.println("明细表5数据：" + result5);
        System.out.println("明细表6数据：" + result6);

        executorService.shutdown(); // 关闭线程池
    }

    // 模拟数据库查询的方法（实际应替换为 MyBatis 或 JDBC 查询）
    private List<DetailTable1> queryDetailTable1(String documentNo) {
        simulateDatabaseDelay();
        return List.of(new DetailTable1() {{ setFieldA("A1"); setFieldB("B1"); }});
    }

    private List<DetailTable2> queryDetailTable2(String documentNo) {
        simulateDatabaseDelay();
        return List.of(new DetailTable2() {{ setName("Item1"); setCount(10); }});
    }

    private List<DetailTable3> queryDetailTable3(String documentNo) {
        simulateDatabaseDelay();
        return List.of(new DetailTable3() {{ setProduct("Product1"); setPrice(99.99); }});
    }

    private List<DetailTable4> queryDetailTable4(String documentNo) {
        simulateDatabaseDelay();
        return List.of(new DetailTable4() {{ setDepartment("IT"); setManager("John Doe"); }});
    }

    private List<DetailTable5> queryDetailTable5(String documentNo) {
        simulateDatabaseDelay();
        return List.of(new DetailTable5() {{ setItemCode("CODE123"); setQuantity(50); }});
    }

    private List<DetailTable6> queryDetailTable6(String documentNo) {
        simulateDatabaseDelay();
        return List.of(new DetailTable6() {{ setTransactionId("TXN456"); setStatus("Completed"); }});
    }

    // 模拟数据库延迟
    private void simulateDatabaseDelay() {
        try {
            Thread.sleep(1000); // 模拟 1 秒查询延迟
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
