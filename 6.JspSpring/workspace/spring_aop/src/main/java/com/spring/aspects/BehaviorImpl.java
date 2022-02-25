package com.spring.aspects;

public class BehaviorImpl implements Behavior {

	@Override
	public void 잠자기() {
		System.out.println("잠을 잡니다");
	}

	@Override
	public void 공부하기() {
		System.out.println("공부합니다.");
	}

	@Override
	public void 밥먹기() {
		System.out.println("밥 먹습니다.");
	}

	@Override
	public void 데이트() {
		System.out.println("데이트합니다.");
	}

	@Override
	public void 운동() {
		System.out.println("운동합니다.");
	}

	@Override
	public void 놀기() {
		System.out.println("놉니다.");
	}

	@Override
	public void 정신수양() {
		System.out.println("정신수양합니다.");
	}
}
