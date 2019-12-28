package com.digi.app.statement.component;

import com.digi.app.statement.dto.StatementDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StatementComponent {

    public List<StatementDto> setMiniStatementResponse(List<List<String>> telnetResponse){
        List<StatementDto> statementDtos = new ArrayList<>();
        for (List<String> oneList : telnetResponse) {
            StatementDto statementDto = new StatementDto();
            statementDto.setBookingDateEn(oneList.get(0));
            statementDto.setTransactionId(oneList.get(1));
            statementDto.setTransactionType(oneList.get(2));
            statementDto.setChequeNo(oneList.get(3));
            statementDto.setAmount(oneList.get(4));
            statementDto.setNarrative(oneList.get(5));
            statementDtos.add(statementDto);
        }
        if(statementDtos!=null || !statementDtos.isEmpty())
        return statementDtos;

        return null;
    }
}
