package com.inatel.quotation.management.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterDTO {

    private String host;

    private String port;
}
