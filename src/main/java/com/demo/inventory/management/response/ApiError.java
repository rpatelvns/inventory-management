package com.demo.inventory.management.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiError {
    private Date timestamp;
    private String message;
    private String details;
}