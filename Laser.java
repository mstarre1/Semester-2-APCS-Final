import java.awt.Color;

public class Laser {
	private Laser head;
	public Laser next;
	private Color c;
	private int upgradeCost;
	private int damage;
	
	public Laser() {
		head = new Laser(Color.RED, 100, 5);
		head.next = new Laser(Color.ORANGE, 250, 10);
		head.next.next = new Laser(Color.YELLOW, 500, 15);
		head.next.next.next = new Laser(Color.GREEN, 1000, 20);
		head.next.next.next.next = new Laser(Color.BLUE, 2000, 25);
		head.next.next.next.next.next = new Laser(Color.MAGENTA, 4000, 50);
	}
	
	public Laser(int start) {
		this();
		while (start > 0) {
			head = head.next;
		}
	}
	
	public Laser(Color c, int costOfUpgrade, int damage) {
		this.c = c; 
		this.upgradeCost = costOfUpgrade;
		this.damage = damage;
	}
	
	public int getDmg() {
		return this.damage;
	}
	
	public void upgrade() {
		this.head = head.next;
	}
	
	public int costOf() {
		return upgradeCost;
	}
}