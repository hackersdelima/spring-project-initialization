package com.digi.app.statement.controller;

import com.digi.app.message.HttpResponses;
import com.digi.app.message.Messages;
import com.digi.app.statement.component.StatementComponent;
import com.digi.app.telnet.component.TelnetConnectionComponent;
import com.digi.app.statement.config.StatementCommands;
import com.digi.app.statement.dto.StatementDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("statements")
public class StatementController {

    @GetMapping(path = "/search-form")
    public ModelAndView searchForm() {
        ModelAndView modelAndView = new ModelAndView("statement/statementSearchForm");
        modelAndView.addObject("pagetitle", "MINI STATEMENT SEARCH");
        return modelAndView;
    }

    @GetMapping(path = "/datatable")
    public ModelAndView searchResultTable() {
        ModelAndView modelAndView = new ModelAndView("statement/statementTable");
        modelAndView.addObject("pagetitle", "STATEMENT");
        return modelAndView;
    }

    @Autowired
    TelnetConnectionComponent telnetConnectionComponent;

    @Autowired
    StatementComponent statementComponent;

    /*STEPS FOLLOWED:
    1. get command from StatementCommands
    2. send command to telnet socket component
    3. we get List<List<String>> as response (String converted to List<List<String>>)
    4. conversion List<List<String>> to List<StatementDto> from StatementComponent->setMiniStatementResponse
    */
    @GetMapping(value = "/telnetStatement")
    public ResponseEntity<Messages> searchResult(String accountNumber, String dateFrom, String dateTo) {
        String command = StatementCommands.miniStatementParamAccountNo + accountNumber;
        //TODO: comment out below
         List<List<String>> telnetResponse = telnetConnectionComponent.telnetConnectionAndResponseList(command);
        System.out.println(command);
        //TODO: remove 2 lines below
        //String value = ",BOOKING.DATE::Booking Date/Trans.ID::Trans.ID/Trans.Type::Trans Type/Cheque.No::Cheque No/Amount::Amount/Narrative::Narrative,\"27 JAN 2019 \" \"FT190278ZK9R\\BNK\" \"Outward Cheque -\" \"4 \" \"-124.00 \" \"CHECK 4 \",\"27 JAN 2019 \" \"TT19027ZLKGN\\BNK\" \"Transfer Debit \" \"3 \" \"-122.00 \" \"CHEQUE CHECK 3 \",\"27 JAN 2019 \" \"TT190270FD94\\BNK\" \"Deposit Cash(Loc\" \"5 \" \"125.00 \" \"O/W Clearing ECC\",\"27 JAN 2019 \" \"TT19027DMNMR\\BNK\" \"Cash Withdrawal(\" \"2 \" \"-121.00 \" \"CHEQUE CHECK 2 \",\"27 JAN 2019 \" \"TT190270HK18\\BNK\" \"Cash Withdrawal(\" \"1 \" \"-120.00 \" \"CHEQUE CHECK \",\"27 JAN 2019 \" \"FT19027D1KFK \" \"Transfer IPS Cre\" \" \" \"1,250.00 \" \"TESTCR \",\"27 JAN 2019 \" \"FT19027109DN \" \"Transfer IPS Cre\" \" \" \"1,251.00 \" \"TESTCR \",\"CURRENT BALANCE \" \" \" \" \" \" \" \"156,303.81 \" \" \"";
        //List<List<String>> telnetResponse = telnetConnectionComponent.conversion(value);
        if (telnetResponse != null || !telnetResponse.isEmpty()) {
            List<StatementDto> statementDtos=statementComponent.setMiniStatementResponse(telnetResponse);
            if(statementDtos!=null || !statementDtos.isEmpty()){
                return new ResponseEntity<Messages>(HttpResponses.fetched(statementDtos), HttpStatus.OK);
            }
        }
        return new ResponseEntity<Messages>(HttpResponses.notfound(), HttpStatus.NOT_FOUND);
    }

}
