package com.youlai.boot.modules.asset.model.vo;

import lombok.Data;

@Data
class DetailTable1 {
    private String fieldA;
    private String fieldB;
}

@Data
class DetailTable2 {
    private String name;
    private int count;
}

@Data
class DetailTable3 {
    private String product;
    private double price;
}

@Data
class DetailTable4 {
    private String department;
    private String manager;
}

@Data
class DetailTable5 {
    private String itemCode;
    private int quantity;
}

@Data
class DetailTable6 {
    private String transactionId;
    private String status;
}
