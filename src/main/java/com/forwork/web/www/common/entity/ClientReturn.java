package com.forwork.web.www.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors
public class ClientReturn {
    private boolean success;
    private String code;
    private String msg;
    private Object content;
}
