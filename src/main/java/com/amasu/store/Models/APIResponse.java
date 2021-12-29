package com.amasu.store.Models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class APIResponse {
    private int status;
    private String message;
    private Object data;
}
