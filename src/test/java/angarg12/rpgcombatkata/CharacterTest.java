package angarg12.rpgcombatkata;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CharacterTest {

    @Test
    public void createdHealth1000() {
        Character c = new Character();
        assertEquals(c.getHealth(), 1000);
    }

    @Test
    public void createdLevel1() {
        Character c = new Character();
        assertEquals(c.getLevel(), 1);
    }

    @Test
    public void createdIsAlive() {
        Character c = new Character();
        assertTrue(c.isAlive());
    }
    
    @Test
    public void sameCharNoDamage() {
        Character c = new Character();
        assertFalse(c.dealDamage(c, 100));
    }
    
    @Test
    public void takeCorrectDamage() {
        Character c1 = new Character();
        Character c2 = new Character();
        c1.dealDamage(c2, 100);
        assertEquals(c2.getHealth(), 900);
    }
    
    @Test
    public void changeStateChar() {
        Character c1 = new Character();
        Character c2 = new Character();
        c1.dealDamage(c2, 1001);
        assertFalse(c2.isAlive());
        assertEquals(c2.getHealth(), 0);
    }
    
    @Test
    public void cannotDealDamageToCharNotAlive() {
        Character c1 = new Character();
        Character c2 = new Character();
        c1.dealDamage(c2, 1000);
        assertFalse(c1.dealDamage(c2, 500));
    }
    
    @Test
    public void charNotAliveCannotDealDamage() {
        Character c1 = new Character();
        Character c2 = new Character();
        c1.dealDamage(c2, 1000);
        assertFalse(c2.dealDamage(c1, 500));
    }
    
    @Test
    public void gainExperience(){
        Character c = new Character();
        c.gainExp(1);
        assertEquals(c.getExperience(), 1);
    }
    
    @Test
    public void gainExperienceAndLevelUp(){
        Character c = new Character();
        c.gainExp(100);
        assertEquals(c.getExperience(), 0);
        assertEquals(c.getLevel(), 2);
    }
    
    @Test
    public void levelNotGreaterThan100() {
        Character c = new Character();
        for (int i = 0; i < 101; i++) {
            int cur = c.getLevel();
            c.gainExp(cur * (cur*10) +1);
            assertEquals(c.getExperience(), 0);
        }
        assertEquals(c.getLevel(), 100);
    }
    
    @Test
    public void staminaDecrease() {
        Character c1 = new Character();
        Character c2 = new Character(100);
        c1.doMove(true, c2);
        assertEquals(c1.getStamina(), 95);
    }
    
    @Test
    public void checkDoMoveWithoutOtherChar() {
        Character c1 = new Character();
        assertFalse(c1.doMove(true));
    }
    
    @Test
    public void noAttackLessThan10Stamina() {
        Character c1 = new Character();
        Character c2 = new Character(100);
        for (int i = 0; i < 19; i++) {
            c1.doMove(true, c2);
        }
        assertFalse(c1.doMove(true, c2));
    }
    
    @Test
    public void notStaminaGreaterThan100() {
        Character c = new Character();
        c.doMove(false);
        assertEquals(c.getStamina(), 100);
    }
    
    @Test
    public void equipNotAllowedByLevelRequirement() {
        Character c = new Character();
        Weapon weapon = new Weapon("sword", 10, 50);
        assertFalse(c.equip(weapon));
    }
    
    @Test
    public void equipAllowedByLevelRequirement() {
        Character c = new Character();
        Weapon weapon = new Weapon("sword", 10, 1);
        assertTrue(c.equip(weapon));
    }
    
    @Test
    public void damageBonusAdded() {
        Character c1 = new Character();
        Character c2 = new Character(100);
        Weapon weapon = new Weapon("sword", 50, 1);
        c1.equip(weapon);
        c1.doMove(true, c2);
        assertEquals(c2.getHealth(), 900);
    }
    
    @Test
    public void unequipWeapon() {
        Character c = new Character();
        Weapon weapon = new Weapon("sword", 10, 1);
        c.equip(weapon);
        c.unequip();
        assertNotEquals(c.getWeaponEquipped(), weapon);
    }
}
