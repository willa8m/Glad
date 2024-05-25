import java.util.Random;

public class Balance {
    public static Monster balanceLogic(Gladiator glad, String name) {
        Random random = new Random();
        int constitution = glad.getConstitution() - 1;
        int strength = glad.getStrength() + 2;
        int dexterity = glad.getDexterity() - 1;
        int level = glad.getLevel();
        int basicAttack = glad.getBasicAttack() + 1;
        int basicHP = glad.getStrength() + 1;
        double critChance = glad.getCritChance() - 1;
        int charisma = 2;
        boolean haveShield = false;

        Monster monster = new Monster(constitution, strength, dexterity, level, basicAttack, basicHP, critChance,
                charisma, haveShield, name);
        return monster;
    }
}
