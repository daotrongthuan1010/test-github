package thayduc.quanlydancu.demo.entity;

import lombok.Data;

@Data
public class ServiceRespone<T> {
    private String status;
    private T data;

    public ServiceRespone(String status, T data) {
        this.status = status;
        this.data = data;
    }
}
