package com.example.ebookbackend.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Result {
    private final Integer code;
    private final String message;
    private final Object data;

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success(Object data){
        return new Result(1, "OK", data);
    }

    public static Result failure(String message,Object object) {
        return new Result(0, message, object);
    }
}
