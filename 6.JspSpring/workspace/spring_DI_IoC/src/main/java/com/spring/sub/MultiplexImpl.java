package com.spring.sub;

public class MultiplexImpl implements Multiplex {

	@Override
	public int multiplex(int a, int b) {
		return a * b;
	}

	@Override
	public int multiplex(int a, int b, int c) {
		return a * b * c;
	}
}
