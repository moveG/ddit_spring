package com.spring.sub;

public class MultiplexImpl2 extends MultiplexImpl {

	@Override
	public int multiplex(int a, int b) {
		return super.multiplex(a, b) * 10;
	}

	@Override
	public int multiplex(int a, int b, int c) {
		return super.multiplex(a, b, c) * 10;
	}
}
