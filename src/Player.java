
public class Player {

	private int x,y;
	private int dx,dy;
	private int hp;
	//armor,weapons
	
	public Player(int newX, int newY, int newDx, int newDy, int newHp) {
		//are both x,y and dx,dy necessary? maybe only use dx,dy
		x=newX;
		y=newY;
		dx=newDx;
		dy=newDy;
		hp=newHp;
	}
	
	public void updatePlayer(int speed) {
		updateDxDy(speed);
		//updateXY();
		//updateHp();
	}
	
	public void updateDxDy(int speed) {
		dx=0;
		dy=0;
		if(Zen.isKeyPressed('a'))
			dx-=speed;
		if(Zen.isKeyPressed('d'))
			dx+=speed;
		if(Zen.isKeyPressed('w'))
			dy-=speed;
		if(Zen.isKeyPressed('s'))
			dy+=speed;
		if(dx!=0 && dy!=0) {
			dx=dx*5/7;
			dy=dy*5/7;
		}
	}
	
	/*public void updateXY() {
		x+=dx;
		y+=dy;
	}*/
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getDx() {
		return dx;
	}
	
	public int getDy() {
		return dy;
	}
	
	public int getHp() {
		return hp;
	}
}
