package com.digi.app.statement.config;

import com.digi.app.telnet.config.TelnetConfig;

public class StatementCommands {
    public static String miniStatementParamAccountNo="ENQUIRY.SELECT,,"+ TelnetConfig.username +"/"+ TelnetConfig.password+"/,ADBL.IPS.ACCT.MINI.STMT,ACCOUNT.NO:=";
}
