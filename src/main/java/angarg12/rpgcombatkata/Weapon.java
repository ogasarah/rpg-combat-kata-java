package angarg12.rpgcombatkata;

public class Weapon {
    private String nameOfWeapon;
    private int weaponAttackVal;
    private int levelRequirement;
    
    public Weapon(String nameOfWeapon, int weaponAttackVal, int levelRequirement) {
        this.nameOfWeapon = nameOfWeapon;
        this.weaponAttackVal = weaponAttackVal;
        this.levelRequirement = levelRequirement;
    }
    
    public String getNameOfWeapon() {
        return nameOfWeapon;
    }
    
    public int getWeaponAttackVal() {
        return weaponAttackVal;
    }
    
    public int getLevelRequirement() {
        return levelRequirement;
    }
    
}