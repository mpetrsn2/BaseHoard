
public class Base {
	
	private int x,y;
	private int hp;
	//armor,weapons
	
	public Base(int newX, int newY, int newHp) {
		x=newX;
		y=newY;
		hp=newHp;
	}
	
	public void updateBase(int dx, int dy, int padx, int pady, int stage) {
		updateXY(dx,dy,padx,pady,stage);
		//updateHp();
	}
	
	public void updateXY(int dx, int dy, int padx, int pady, int stage) {
		x=Game.updatePos(x,dx,padx,stage);
		y=Game.updatePos(y,dy,pady,stage);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getHp() {
		return hp;
	}
}
