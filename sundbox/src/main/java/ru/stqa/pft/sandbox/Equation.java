package ru.stqa.pft.sandbox;

public class Equation {
 private double a;
 private double b;
 private double c;


  public Equation (double a, double b,double c) {
    this.a = a;
    this.b = b;
    this.c = c;
    double d = b*b - 4*a*c;
  }
}
