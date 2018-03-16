package com.thougtworks.step;

public class Rectangle implements Shape {
    private final float length;
    private final float breadth;
    
    public Rectangle(float length, float breadth) {
        this.length = length;
        this.breadth = breadth;
    }

    public float area() {
        return length * breadth;   
    }
}