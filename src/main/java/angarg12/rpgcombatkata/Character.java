package angarg12.rpgcombatkata;

public class Character {
	private int health = 1000;
	private int level = 1;
	private boolean alive = true;
	private int experience = 0;
	private int stamina = 100;
	private int damage;
	private Weapon weapon;

	public Character(Weapon weapon, int damage) {
		this.weapon = weapon;
		this.damage = damage;
	}

	public Character() {
		this(new Weapon("None", 0, 1));
	}

	public Character(int damage) {
		this(new Weapon("None", 0, 1), damage);
	}

	public Character(Weapon weapon) {
		this(weapon, 50);
	}

	public boolean equip(Weapon weapon) {
		if (weapon.getLevelRequirement() > level) {
			return false;
		}
		this.weapon = weapon;
		return true;
	}

	public boolean unequip() {
		return equip(new Weapon("None", 0, 1));
	}

	// return value of doMove indicates whether they have attacked that turn
	public boolean doMove(boolean attack, Character other) {
		if (attack && stamina >= 10 && dealDamage(other, weapon.getWeaponAttackVal() + damage)) {
			stamina -= 10;
			return updateStamina();
		}
		return !updateStamina();
	}

	public boolean doMove(boolean attack) {
		if (attack) {
			System.out.println("Must provide a character to attack");
			return false;
		}
		return !updateStamina();
	}

	private boolean updateStamina() {
		if (stamina <= 95) {
			stamina += 5;
		} else {
			stamina = 100;
		}
		return true;
	}

	protected void takeDamage(int damage) {
		if (damage >= health) {
			health = 0;
			alive = false;
			return;
		}
		health -= damage;
	}

	public boolean dealDamage(Character other, int damage) {
		if (this == other || !other.isAlive() || !alive || damage < 0) {
			return false;
		}
		other.takeDamage(damage);
		return true;
	}

	public void gainExp(int exp) {
		if (exp >= 0 && level < 100) {
			experience += exp;
			levelUp();
		}
	}
	
	private void levelUp() {
		if (experience > (level * (level * 10))) {
			level += 1;
			experience = 0;
		}
	}

	public int getDamage() {
		return damage;
	}

	public int getHealth() {
		return health;
	}

	public int getLevel() {
		return level;
	}

	public boolean isAlive() {
		return alive;
	}

	public int getExperience() {
		return experience;
	}

	public int getStamina() {
		return stamina;
	}

	public Weapon getWeaponEquipped() {
		return weapon;
	}

}
