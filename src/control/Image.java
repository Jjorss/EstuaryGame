package control;

public enum Image {
	GRASS(0), BLUECRAB(1), OYSTER(2), SKY(3),SUN(4), GABION(5),
	GABION1(6), GABION2(7), GABION3(8), GABION4(9), GABION5(10),
	WAVE1(11), WAVE2(12), WAVE3(13), GABIONFULL(14), HAND(15);
	
	private final int index;
	Image(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return this.index;
	}
}
