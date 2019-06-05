import java.awt.Image;

import javax.swing.ImageIcon;

public class Enemy {
	private int health;
	private int direction; //0 or 1. Right when 0, down when 1. Set by random chance.
	private int x;
	private int y;
	private Image sprite;
	
	public Enemy(int health) {
		this.x = 0;
		this.y = 0;
		this.health = health;
		System.out.print(this.health);
		this.sprite = new ImageIcon("enemy.gif").getImage();
		if ((Math.random() * 49) + 1 > 25) 
			direction = 1;
		else 
			direction = 0;
	}
	
	public Enemy(int x, int y, int health) {
		this.x = x;
		this.y = y;
		this.health = health;
		if ((Math.random() * 49) + 1 > 25) 
			direction = 1;
		else 
			direction = 0;
	}
	
	public void updateDirection() {
		if ((Math.random() * 49) + 1 > 25) 
			direction = 1;
		else 
			direction = 0;
	}
	
	public void move() {
		if (direction == 0)
			this.x += 5;
		else
			this.y += 5;
	}
	
	public int subtractHealth(int damageTaken) {
		this.health -= damageTaken;
		return this.health;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public Image getSprite() {
		return this.sprite;
	}
}