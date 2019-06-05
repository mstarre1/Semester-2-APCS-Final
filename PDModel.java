import java.util.ArrayList;

public class PDModel {
	private ArrayList<Enemy> enemies;
	private ArrayList<Defender> defenders;
	private int wave;
	private Defender selectedDefender;
	private boolean waveInProgress;
	private int cash;
	private int planetHealth;
	
	public PDModel() {
		enemies = new ArrayList<Enemy>();
		defenders = new ArrayList<Defender>();
		selectedDefender = null;
		wave = 1;
		waveInProgress = false;
		cash = 500;
		planetHealth = 5000;
	}
	
	public void setupNextWave() {
		for (int i = 0; i < wave * 10; ++i) {
			enemies.add(new Enemy((int)Math.random() * wave * 100));
		}
	}
	
	public int advanceWave() {
		for (int i = 0; i < enemies.size(); ++i) { //Move
			enemies.get(i).move();
		}
		for (int j = 0; j < defenders.size(); ++j) { //Attack
			defenders.get(j).enemiesInRange(enemies);
		}
		for (int i = 0; i < enemies.size(); ++i) { 
			if (enemies.get(i).getHealth() <= 0) {
				enemies.remove(i);
				cash += (int)Math.random() * wave * 25;
			}
		}
		for (int i = 0; i < enemies.size(); ++i) {
			if (Math.sqrt(Math.pow((420 - enemies.get(i).getX()), 2) + Math.pow((420 - enemies.get(i).getY()), 2)) < 100) {
				planetHealth -= enemies.get(i).getHealth();
				enemies.remove(i);
			}
		}
		if (planetHealth < 0)
			return 1;
		else if (enemies.size() == 0) {
			this.wave++;
			return 2;
		}
		return 0;
	}
	
	public void upgrade(Defender t) throws Exception {
		cash -= t.costOfUpgrade();
		if (cash < 0)
			throw new Exception();
		else
			t.upgrade();
	}
	
	public Defender getDefender(int x, int y) throws Exception { //highlights selected defender or makes new at location
		selectedDefender = null;
		for (int i = 0; i < defenders.size(); ++i) {
			if (defenders.get(i).getX() == x && defenders.get(i).getY() == y)
				return defenders.get(i);
		}
		cash -= new Defender(x, y).costOf();
		if (cash <= 0)
			throw new Exception();
		defenders.add(new Defender(x, y));
		defenders.get(defenders.size() - 1).select();
		selectedDefender = defenders.get(defenders.size() - 1);
		return defenders.get(defenders.size() - 1);
	}
	
	public ArrayList<Defender> getDefenders() {
		return this.defenders;
	}
	
	public ArrayList<Enemy> getEnemies() {
		return this.enemies;
	}
	
	public Defender getSelectedDefender() {
		return selectedDefender;
	}
	
	public int getWave() {
		return this.wave;
	}
}