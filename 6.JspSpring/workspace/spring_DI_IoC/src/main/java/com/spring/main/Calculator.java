package com.spring.main;

import com.spring.sub.Divide;
import com.spring.sub.Minus;
import com.spring.sub.Multiplex;
import com.spring.sub.Summation;

public class Calculator {
	
	private Summation summation;
	private Minus minus;
	private Multiplex multiplex;
	private Divide divice;
	
	public void setSummation(Summation summation) {
		this.summation = summation;
	}
	public void setMinus(Minus minus) {
		this.minus = minus;
	}
	public void setMultiplex(Multiplex multiplex) {
		this.multiplex = multiplex;
	}
	public void setDivice(Divide divice) {
		this.divice = divice;
	}
	
	public int sum(int a, int b) {
		return summation.sum(a, b);
	}
	
	public int sum(int a, int b, int c) {
		return summation.sum(a, b, c);
	}
	
	public int minus(int a, int b) {
		return minus.minus(a, b);
	}
	
	public int minus(int a, int b, int c) {
		return minus.minus(a, b, c);
	}
	
	public int multi(int a, int b) {
		return multiplex.multiplex(a, b);
	}
	
	public int multi(int a, int b, int c) {
		return multiplex.multiplex(a, b, c);
	}
	
	public int div(int a, int b) {
		return divice.div(a, b);
	}
	
	public int div(int a, int b, int c) {
		return divice.div(a, b, c);
	}
}
