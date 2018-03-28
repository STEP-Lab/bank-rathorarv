package com.thoughtworks.step.bank;

import java.io.PrintWriter;
import java.util.Arrays;

public class CsvPrinter {
    private final PrintWriter printer;
    private final String[] headers;

    public CsvPrinter(PrintWriter printWriter, String[] headers) {
        this.printer = printWriter;
        this.headers = headers;
    }
    public void close(){
        printer.close();
    }
    public void writeHeaders(){
        printer.println(String.join(",", Arrays.asList(headers)));
    }
    public void writer(Transaction transaction){
        writeHeaders();
        printer.println(transaction.toCsv());
    }
}
