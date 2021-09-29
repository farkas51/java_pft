package ru.stqa.pft.sandbox;

public class FirstProgram {
    public static void main(String[] args){
        Square sa = new Square(5);
        System.out.println("Площадь квадрата со стороной " + sa.l +"=" + sa.area());
    }
}
