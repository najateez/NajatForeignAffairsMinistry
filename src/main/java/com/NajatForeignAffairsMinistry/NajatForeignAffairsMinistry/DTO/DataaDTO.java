package com.NajatForeignAffairsMinistry.NajatForeignAffairsMinistry.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class DataaDTO {

    private Integer totalRequests;
    private Integer totalErrors;
    private Integer responseTime;

    public DataaDTO(Integer totalRequests, Integer totalErrors, Integer responseTime) {
        this.totalRequests = totalRequests;
        this.totalErrors = totalErrors;
        this.responseTime = responseTime;
    }
}
