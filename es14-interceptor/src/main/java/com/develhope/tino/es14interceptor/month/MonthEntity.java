package com.develhope.tino.es14interceptor.month;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthEntity {

    private int monthNumber;
    private String englishName;
    private String italianName;
    private String germanName;
}
