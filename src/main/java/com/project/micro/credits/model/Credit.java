package com.project.micro.credits.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Document(collection = "credit")
public class Credit {
    @Id
    private String idCredit;
    private String idCustomer;
    private String creditType;
    private String descriptionType;
    private BigDecimal outstandingBalance;//Saldo Pendiente
    private BigDecimal creditAmount;//Monto del Crédito
    private Date registrationDate;
    private Date modificationDate;
    private Boolean status;

}
