package com.hdjunction.tinyERMService.dto;

public enum CrudEnum {
    CREATE(1, "CREATE"),
    UPDATE(2, "UPDATE"),
    DELETE(3, "DELETE"),
    GET(4, "GET"),
    GET_ALL(5, "GET_ALL");

    int crudCode;
    String code;

    CrudEnum(int crudCode, String code) {
        this.crudCode = crudCode;
        this.code = code;
    }
}
