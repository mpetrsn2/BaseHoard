/*
 * ===IDEAS===
 * Hoard defense and tower defense: have to defend self and base (seperate health)
 * Alter defenses between rounds: turrets and fortified base
 * Shop menu to buy/upgrade defenses, health, armor, weapon upgrades, etc
 * implement interest system
 * 
 * ===FIXES===
 * extend laser beyond mouse
 */
public class Game {

	public static void main(String[] args) {
		
		final int STAGE=1800;
		//final int STAGEX=2400;
		//final int STAGEY=1200;
		final int WIDTH=Zen.getZenWidth();
		final int HEIGHT=Zen.getZenHeight();
		final int PADX=(STAGE-WIDTH)/2;
		final int PADY=(STAGE-HEIGHT)/2;
		final int PSIZE=20;
		final int BSIZE=80;
		final int SPEED=7;
		Player player=new Player(WIDTH/2,HEIGHT/2,0,0,100);
		Base base=new Base(WIDTH/2,HEIGHT/2,100);
		int[][] stars=new int[100][2];
		int score=0;
		int wave=0;
		int money=0;
		
		//Initializing
		for(int i=0;i<stars.length;i++) {
			stars[i][0]=(int)(Math.random()*STAGE-PADX);
			stars[i][1]=(int)(Math.random()*STAGE-PADY);
		}
		
		//Game loop
		while(Zen.isRunning()) {
			
			//put all these into one updateAll method?
			draw(player.getX(),player.getY(),PSIZE,WIDTH,HEIGHT,base,BSIZE,stars,score,wave,money,player.getHp(),base.getHp());
			player.updatePlayer(SPEED);
			base.updateBase(player.getDx(),player.getDy(),PADX,PADY,STAGE);
			updateStars(stars,player.getDx(),player.getDy(),PADX,PADY,STAGE);
			
			Zen.flipBuffer();
			Zen.sleep(20);			
		}
	}
	
	public static void draw(int x, int y, int psize, int width, int height, Base base, int bsize, int[][] stars, int score, int wave, int money, int php, int bhp) {
		//background
		Zen.setColor(255,255,255);
		Zen.fillRect(0,0,Zen.getZenWidth(),Zen.getZenHeight());
		//anti-stars
		Zen.setColor(0,0,0);
		for(int i=0;i<stars.length;i++)
			Zen.fillRect(stars[i][0],stars[i][1],2,2);
		//base
		Zen.setColor(50,50,50);
		Zen.fillOval(base.getX()-bsize/2,base.getY()-bsize/2,bsize,bsize);
		//laser
		Zen.setColor(255,0,0);
		Zen.drawLine(x,y,Zen.getMouseX(),Zen.getMouseY());
		//player
		Zen.setColor(100,100,255);
		Zen.fillOval(x-psize/2,y-psize/2,psize,psize);
		//stats
		Zen.setColor(50,50,50);
		Zen.fillRect(20,100,10,height-160);
		Zen.fillRect(40,100,10,height-160);
		Zen.setColor(0,200,0);
		Zen.fillRect(20,100+(int)((height-160)*(1-php/100.0)),10,(int)((height-160)*(php/100.0)));
		Zen.fillRect(40,100+(int)((height-160)*(1-bhp/100.0)),10,(int)((height-160)*(bhp/100.0)));
		//text
		PixelatedText.draw("score "+score,20,20,4);
		PixelatedText.draw("wave "+wave,20,60,4);
		PixelatedText.draw("money "+money,20,height-40,4);
	}
	
	public static int updatePos(int pos, int vel, int pad, int stage) {
		return (pos+pad-vel+stage)%stage-pad;
	}
	
	public static void updateStars(int[][] stars, int dx, int dy, int padx, int pady, int stage) {
		for(int i=0;i<stars.length;i++) {
			stars[i][0]=updatePos(stars[i][0],dx,padx,stage);
			stars[i][1]=updatePos(stars[i][1],dy,pady,stage);
		}
	}
}
