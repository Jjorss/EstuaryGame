package control;

public enum Image {
	GRASS(0), BLUECRAB(1), OYSTER(2), SKY(3),SUN(4), WAVE(5), HAND(6);
	
	private final int index;
	Image(int index) {
		this.index = index;
	}
	
	public int getIndex() {
		return this.index;
	}
}
