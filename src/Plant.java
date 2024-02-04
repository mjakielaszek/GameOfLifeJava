import java.util.Random;

public class Plant extends Organism{
    public static final int defaultSowRate = 20;
    public Plant(){this.setInitiative(0);}
    public void action(int key, int maxX, int maxY) {
        Random random = new Random();
        int sow = random.nextInt(100);
        if (sow < defaultSowRate) {
            spawnNew(maxX, maxY);
        }
    }
}
