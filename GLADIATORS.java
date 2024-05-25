import java.util.Random;
import java.util.Scanner;

class Gladiator {
    private int constitution;
    private int strength;
    private double xp;
    private int dexterity;
    private int level;
    private int basicAttack;
    private int basicHP;
    private double critChance;
    private int charisma;
    private boolean haveShield;
    private int currentHP;
    private int speed;

    public Gladiator(int constitution, int strength, double xp, int dexterity, int level, int basicAttack, int basicHP, double critChance, int charisma, boolean haveShield) {
        this.constitution = constitution;
        this.strength = strength;
        this.xp = xp;
        this.dexterity = dexterity;
        this.level = level;
        this.basicAttack = basicAttack;
        this.basicHP = basicHP;
        this.critChance = critChance;
        this.charisma = charisma;
        this.haveShield = haveShield;
        this.currentHP = this.basicHP + this.constitution * 10;
        this.speed = this.dexterity;
    }

    public int attack() {
        int damage = this.basicAttack + this.strength;
        if (Math.random() < this.critChance) {
            damage *= 2;
            System.out.println("Критичний удар!");
        }
        return damage;
    }

    public void takeDamage(double damage) {
        if (this.haveShield) {
            damage /= 2;
        }
        this.currentHP -= damage;
        if (this.currentHP < 0) {
            this.currentHP = 0;
        }
    }

    public boolean isAlive() {
        return this.currentHP > 0;
    }

    public void winBattle(double monsterXp) {
        this.xp += monsterXp / 2;
    }

    public void loseBattle() {
        this.xp = 0;
    }

    public void negotiate() {
        this.charisma -= 2;
    }

    public int getConstitution() {
        return constitution;
    }

    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getBasicAttack() {
        return basicAttack;
    }

    public void setBasicAttack(int basicAttack) {
        this.basicAttack = basicAttack;
    }

    public int getBasicHP() {
        return basicHP;
    }

    public void setBasicHP(int basicHP) {
        this.basicHP = basicHP;
    }

    public double getCritChance() {
        return critChance;
    }

    public void setCritChance(double critChance) {
        this.critChance = critChance;
    }

    public int getCharisma() {
        return charisma;
    }

    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }

    public boolean isHaveShield() {
        return haveShield;
    }

    public void setHaveShield(boolean haveShield) {
        this.haveShield = haveShield;
    }

    public int getCurrentHP() {
        return currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getXp() {
        return xp;
    }
}

class Monster {
    private int constitution;
    private int strength;
    private double xp;
    private int dexterity;
    private int level;
    private int basicAttack;
    private int basicHP;
    private double critChance;
    private boolean haveShield;
    private int currentHP;
    private String name;

    public Monster(String name, int constitution, int strength, double xp, int dexterity, int level, int basicAttack, int basicHP, double critChance, boolean haveShield) {
        this.name = name;
        this.constitution = constitution;
        this.strength = strength;
        this.xp = xp;
        this.dexterity = dexterity;
        this.level = level;
        this.basicAttack = basicAttack;
        this.basicHP = basicHP;
        this.critChance = critChance;
        this.haveShield = haveShield;
        this.currentHP = this.basicHP + this.constitution * 10;
    }

    public void takeDamage(double damage) {
        this.currentHP -= damage;
        if (this.currentHP < 0) {
            this.currentHP = 0;
        }
    }

    public boolean isAlive() {
        return this.currentHP > 0
    }

    public double getXp() {
        return xp;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        Gladiator gladiator = null;
        while (gladiator == null) {
            showGladiatorClasses();
            System.out.print("Виберіть клас гладіатора (1-3): ");
            int classChoice = scanner.nextInt();
            gladiator = createGladiator(classChoice);
            if (gladiator == null) {
                System.out.println("Спробуйте ще раз.");
            }
        }
    
        while (true) {
            System.out.println("Що хочете зробити?");
            System.out.println("1. Битися");
            System.out.println("2. Підняти рівень");
            System.out.println("3. Змінити характеристику");
            int value = scanner.nextInt();
            if (value == 1) {
                fight(gladiator);
            } else if (value == 2) {
                levelUp(gladiator);
            } else if (value == 3) {
                changeCharacteristic(gladiator, scanner);
            }
        }
    }
    
    private static void showGladiatorClasses() {
        System.out.println("Класи гладіаторів:");
        System.out.println("1. Лучник - більше спритності");
        System.out.println("2. Танк - більше здоров’я");
        System.out.println("3. Воїн - більше сили");
    }
    
    private static Gladiator createGladiator(int classChoice) {
        switch (classChoice) {
            case 1:
                return new Gladiator(5, 5, 3, 10, 1, 7, 50, 0.2, 5, false);
            case 2:
                return new Gladiator(10, 5, 4, 5, 1, 5, 70, 0.1, 5, true);
            case 3:
                return new Gladiator(5, 10, 3, 5, 1, 10, 50, 0.1, 5, false);
            default:
                System.out.println("Невірний вибір класу!");
                return null;
        }
    }
    
    private static void fight(Gladiator gladiator) {
        Random random = new Random();
        String monsterName = monsterNames[random.nextInt(monsterNames.length)];
        Monster monster = new Monster(monsterName, 5, 5, 3, 10, 1, 7, 50, 0.2, false);
    
        Scanner scanner = new Scanner(System.in);
        while (gladiator.isAlive() && monster.isAlive()) {
            System.out.println("Виберіть дію:");
            System.out.println("1. Битися");
            System.out.println("2. Задобрити/домовитись");
            System.out.println("3. Втекти");
            int choice = scanner.nextInt();
    
            switch (choice) {
                case 1:
                    // Битва
                    int damage = gladiator.attack();
                    System.out.println("Гладіатор наносить " + damage + " ушкоджень монстру.");
                    monster.takeDamage(damage);
                    if (!monster.isAlive()) {
                        System.out.println("Гладіатор переміг монстра!");
                        gladiator.winBattle(monster.getXp());
                        break;
                    }
                    damage = monster.attack();
                    System.out.println("Монстр наносить " + damage + " ушкоджень гладіатору.");
                    gladiator.takeDamage(damage);
                    if (!gladiator.isAlive()) {
                        System.out.println("Монстр переміг гладіатора!");
                        gladiator.loseBattle();
                        break;
                    }
                    break;
                case 2:
                    // Задобрити/домовитись
                    if (random.nextDouble() < (double) gladiator.getCharisma() / (gladiator.getCharisma() + 10)) {
                        System.out.println("Ви змогли задобрити монстра.");
                        gladiator.negotiate();
                    } else {
                        System.out.println("Монстр не захотів миритись з вами.");
                        System.out.println("Ви повинні битись.");
                        int monsterDamage = monster.attack();
                        System.out.println("Монстр наносить " + monsterDamage + " ушкоджень гладіатору.");
                        gladiator.takeDamage(monsterDamage);
                        if (!gladiator.isAlive()) {
                            System.out.println("Монстр переміг гладіатора!");
                            gladiator.loseBattle();
                            break;
                        }
                        int gladiatorDamage = gladiator.attack();
                        System.out.println("Гладіатор наносить " + gladiatorDamage + " ушкоджень монстру.");
                        monster.takeDamage(gladiatorDamage);
                        if (!monster.isAlive()) {
                            System.out.println("Гладіатор переміг монстра!");
                            gladiator.winBattle(monster.getXp());
                            break;
                        }
                    }
                    break;
                case 3:
                    // Втекти
                    if (gladiator.getSpeed() > monster.getSpeed() || random.nextDouble() < 0.3) {
                        System.out.println("Ви втекли від монстра!");
                        break;
                    } else {
                        System.out.println("Монстр вас доганяє! Ви повинні битись.");
                        int monsterDamage = monster.attack();
                        System.out.println("Монстр наносить " + monsterDamage + " ушкоджень гладіатору.");
                        gladiator.takeDamage(monsterDamage);
                        if (!gladiator.isAlive()) {
                            System.out.println("Монстр переміг гладіатора!");
                            gladiator.loseBattle();
                            break;
                        }
                        int gladiatorDamage = gladiator.attack();
                        System.out.println("Гладіатор наносить " + gladiatorDamage + " ушкоджень монстру.");
                        monster.takeDamage(gladiatorDamage);
                        if (!monster.isAlive()) {
                            System.out.println("Гладіатор переміг монстра!");
                            gladiator.winBattle(monster.getXp());
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
                    break;
                    }
                    }
                    }
                    private static void levelUp(Gladiator gladiator) {
                        // Додати логіку підняття рівня
                        System.out.println("Level up!");
                    }
                    
                    private static void changeCharacteristic(Gladiator gladiator, Scanner scanner) {
                        System.out.println("Яку характеристику хочете змінити?");
                        System.out.println("1. Constitution");
                        System.out.println("2. Strength");
                        System.out.println("3. Dexterity");
                        System.out.println("4. BasicAttack");
                        System.out.println("5. BasicHP");
                        System.out.println("6. CritChance");
                        System.out.println("7. Charisma");
                    
                        int value = scanner.nextInt();
                        switch (value) {
                            case 1:
                                gladiator.setConstitution(gladiator.getConstitution() + 1);
                                System.out.println("Constitution + 1");
                                break;
                            case 2:
                                gladiator.setStrength(gladiator.getStrength() + 1);
                                System.out.println("Strength + 1");
                                break;
                            case 3:
                                gladiator.setDexterity(gladiator.getDexterity() + 1);
                                System.out.println("Dexterity + 1");
                                break;
                            case 4:
                                gladiator.setBasicAttack(gladiator.getBasicAttack() + 1);
                                System.out.println("BasicAttack + 1");
                                break;
                            case 5:
                                gladiator.setBasicHP(gladiator.getBasicHP() + 1);
                                System.out.println("BasicHP + 1");
                                break;
                            case 6:
                                gladiator.setCritChance(gladiator.getCritChance() + 1);
                                System.out.println("CritChance + 1");
                                break;
                            case 7:
                                gladiator.setCharisma(gladiator.getCharisma() + 1);
                                System.out.println("Charisma + 1");
                                break;
                            default:
                                System.out.println("Невірний вибір. Спробуйте ще раз.");
                                break;
                        }
                    }
                    
        