package com.thoughtworks.step;

import com.thougtworks.step.Rectangle;
import com.thougtworks.step.Square;

public class Application {
    
    public static void main(String[] args) {
        AreaPrinter printer = new AreaPrinter();
        printer.print(new Rectangle(3,6));
        printer.print(new Square(6));
    }
}