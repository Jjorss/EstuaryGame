package control;

import java.io.Serializable;

public enum Image implements Serializable{
	GRASS1(0), BLUECRAB(1), OYSTER(2), SKY(3),SUN(4), GABION(5),
	GABION1(6), GABION2(7), GABION3(8), GABION4(9), GABION5(10),
	WAVE1(11), WAVE2(12), WAVE3(13), GABIONFULL(14), HAND(15),
	GRASS2(16), GRASS3(17), BLUECRAB2(18), BLUECRAB3(19), WALL(20),
	RUNOFF(21), TUTORIAL(22), PLAY(23), CREDITS(24), A(25), APLUS(26),
	B(27), C(28), D(29), YOUWIN(30), GABIONFADE1(31), GABIONFADE2(32),
	YOULOSE(33), FAIL(34);
	
	private final int index;
	Image(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return this.index;
	}
}
