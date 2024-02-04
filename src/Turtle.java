import java.util.Random;

public class Turtle extends Animal{
    private static final int turtleStaysChances = 75;
    private static final int turtleProtection = 5;
    public Turtle(int _x, int _y){
        this.setX(_x);
        this.setY(_y);
        this.setAge(0);
        this.setInitiative(1);
        this.setStrength(2);
        this.setSign("T");
    }
    public Organism returnNew(int x, int y){
        Turtle newT = new Turtle(x,y);
        newT.setOrganisms(this.getOrganisms());
        return newT;
    }

    @Override
    public void action(int key, int maxX, int maxY) {
        Random random = new Random();
        int move = random.nextInt(100);
        if (move > turtleStaysChances)  super.action(key, maxX, maxY);
    }

    @Override
    public void collision(Organism attacker, int maxX, int maxY) {
        if (attacker.getStrength() < turtleProtection) return;
        else if (attacker.getStrength() >= turtleProtection) super.collision(attacker, maxX, maxY);
    }

    public Turtle(int x, int y, int init, int str, int age){
        this.setAge(age);
        this.setInitiative(init);
        this.setStrength(str);
        this.setX(x);
        this.setY(y);
        this.setSign("T");
    }
}
