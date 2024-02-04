import java.util.Random;

public class SowThistle extends Plant {
    private static final int sowThistleAttempts = 3;

    public SowThistle(int _x, int _y) {
        this.setY(_y);
        this.setAge(0);
        this.setInitiative(0);
        this.setX(_x);
        this.setStrength(0);
        this.setSign("$");
    }

    public Organism returnNew(int x, int y) {
        SowThistle newST = new SowThistle(x,y);
        newST.setOrganisms(this.getOrganisms());
        return newST;
    }

    @Override
    public void action(int key, int maxX, int maxY) {
        Random random = new Random();
        for (int i = 0; i < sowThistleAttempts; i++) {
            int chances = random.nextInt(100);
            if (chances < defaultSowRate)
                super.action(key, maxX, maxY);
        }
    }
    public SowThistle(int x, int y, int init, int str, int age){
        this.setAge(age);
        this.setInitiative(init);
        this.setStrength(str);
        this.setX(x);
        this.setY(y);
        this.setSign("$");
    }
}


