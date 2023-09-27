package com.project.micro.credits.dto;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CreditDto {
    private String idCredit;
    private String idCustomer;
    private String creditType;
    private String descriptionType;
    private BigDecimal outstandingBalance;//Saldo Pendiente
    private BigDecimal creditAmount;//Monto del Crédito
}
