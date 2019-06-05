import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Defender {
	private int x;
	private int y;
	private Laser damage;
	private int range;
	private boolean selected;
	private Image sprite;

	public Defender(int x, int y) {
		this.x = x;
		this.y = y;
		this.damage = new Laser(0);
		int range = 50;
		this.selected = false;
		this.sprite = new ImageIcon("defender;.gif").getImage();
	}
	
	public Defender(int x, int y, int damageTier) {
		this.x = x;
		this.y = y;
		this.damage = new Laser(damageTier);
		int range = 50;
		this.selected = false;
	}
	
	public void enemiesInRange(ArrayList<Enemy> a) {
		for (int i = 0; i < a.size(); ++i) {
			if (Math.sqrt(Math.pow((this.getX() - a.get(i).getX()), 2) + Math.pow((this.getY() - a.get(i).getY()), 2)) <= 50) {
				this.attack(a.get(i));
			}
		}
	}
	
	public void attack(Enemy e) {
		e.subtractHealth(damage.getDmg());
	}
	
	public void upgrade() {
		damage.upgrade();
		this.range += 10;
	}
	
	public int getX () {
		return this.x;
	}
	
	public int getY () {
		return this.y;
	}
	
	public void select() {
		this.selected = !this.selected;
	}
	
	public int costOf() {
		return damage.costOf();
	}
	
	public int costOfUpgrade() {
		return damage.next.costOf();
	}
	
	public Image getSprite() {
		return this.sprite;
	}
 }