package com.digi.app;

import org.hibernate.cache.spi.support.AbstractCachedDomainDataAccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Test {


    public static void main(String[] args) {
        String value = ",BOOKING.DATE::Booking Date/Trans.ID::Trans.ID/Trans.Type::Trans Type/Cheque.No::Cheque No/Amount::Amount/Narrative::Narrative,\"27 JAN 2019 \" \"FT190278ZK9R\\BNK\" \"Outward Cheque -\" \"4 \" \"-124.00 \" \"CHECK 4 \",\"27 JAN 2019 \" \"TT19027ZLKGN\\BNK\" \"Transfer Debit \" \"3 \" \"-122.00 \" \"CHEQUE CHECK 3 \",\"27 JAN 2019 \" \"TT190270FD94\\BNK\" \"Deposit Cash(Loc\" \"5 \" \"125.00 \" \"O/W Clearing ECC\",\"27 JAN 2019 \" \"TT19027DMNMR\\BNK\" \"Cash Withdrawal(\" \"2 \" \"-121.00 \" \"CHEQUE CHECK 2 \",\"27 JAN 2019 \" \"TT190270HK18\\BNK\" \"Cash Withdrawal(\" \"1 \" \"-120.00 \" \"CHEQUE CHECK \",\"27 JAN 2019 \" \"FT19027D1KFK \" \"Transfer IPS Cre\" \" \" \"1,250.00 \" \"TESTCR \",\"27 JAN 2019 \" \"FT19027109DN \" \"Transfer IPS Cre\" \" \" \"1,251.00 \" \"TESTCR \",\"CURRENT BALANCE \" \" \" \" \" \" \" \"156,303.81 \" \" \"";
        List<List<String>> list = conversion(value);
        System.out.println(list);
    }

    public static List<List<String>> conversion(String response) {

        //deleting unused part
        StringBuilder stringBuilder = new StringBuilder(response);
        int firstQuotationIndex = stringBuilder.indexOf("\"", 1);
        stringBuilder.delete(0, firstQuotationIndex);

        //splitting main data part into list
        String dataAfterRemovingFirstPart = stringBuilder.toString(); //main data working fine
        String replacingCommaaByColons = dataAfterRemovingFirstPart.replace("\",\"", "\"::\"");
        String[] splittedmaindatas = replacingCommaaByColons.split("::");

        List<List<String>> responseList = new ArrayList<>();
        List<String> strings = new ArrayList<>();
        //for first list value of splittedmaindatas

        //TODO: this loop is not working properly, need to create beautiful loop
        for (String data : splittedmaindatas) {
            String pre = "\"";
            String post = "\"";
            strings = Arrays.asList(data.replaceAll("^.*?" + pre, "")
                    .split(post + ".*?(\"|$)"));
            responseList.add(strings);
        }
        return responseList;
    }
}
