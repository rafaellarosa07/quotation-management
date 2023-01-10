package com.inatel.quotation.management.model.dto;


import com.inatel.quotation.management.model.entity.Quote;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockQuoteDTO {
    private String id;
    private String stockId;
    private Map<LocalDate, BigDecimal> quotes;

}
