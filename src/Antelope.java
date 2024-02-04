import java.util.Random;

public class Antelope extends Animal{
    private static final int antelopeMovement = 2;
    private static final int escapeChances = 50;
    public Antelope(int _x, int _y){
        this.setX(_x);
        this.setY(_y);
        this.setAge(0);
        this.setInitiative(4);
        this.setStrength(4);
        this.setSign("A");
    }

    public Organism returnNew(int x, int y){
        Antelope newA = new Antelope(x,y);
        newA.setOrganisms(this.getOrganisms());
        return newA;
    }
    public Antelope(int x, int y, int age, int init, int str){
        this.setX(x);
        this.setY(y);
        this.setInitiative(init);
        this.setStrength(str);
        this.setAge(age);
        this.setSign("A");
    }

    @Override
    public void action(int key, int maxX, int maxY) {
        Random random = new Random();
        int dir = random.nextInt(moveOptions);
        int x = this.getX();
        int y = this.getY();
        switch (dir) {
            case 0 -> {
                Organism newCell = Main.findOrganismByCoords(x - antelopeMovement, y, getOrganisms());
                if (getX() > 1 && newCell == null)
                    this.addX(-antelopeMovement);
                else if (newCell != null)
                    newCell.collision(this, maxX, maxY);
            }
            case 1 -> {
                Organism newCell2 = Main.findOrganismByCoords(x, y - antelopeMovement, getOrganisms());
                if (getY() > 1 && newCell2 == null)
                    this.addY(-antelopeMovement);
                else if (newCell2 != null)
                    newCell2.collision(this, maxX, maxY);
            }
            case 2 -> {
                Organism newCell3 = Main.findOrganismByCoords(x + antelopeMovement, y, getOrganisms());
                if (getX() < maxX - 2 && newCell3 == null)
                    addX(antelopeMovement);
                else if (newCell3 != null)
                    newCell3.collision(this, maxX, maxY);
            }
            case 3 -> {
                Organism newCell4 = Main.findOrganismByCoords(x, y + antelopeMovement, getOrganisms());
                if (getY() < maxY - 2 && newCell4 == null)
                    addY(antelopeMovement);
                else if (newCell4 != null)
                    newCell4.collision(this, maxX, maxY);
            }
        }
    }

    @Override
    public void collision(Organism attacker, int maxX, int maxY) {
        Random random = new Random();
        int rand = random.nextInt(100);
        if (rand > escapeChances) {
            this.action(0, maxX, maxY);
        }
        else super.collision(attacker, maxX, maxY);
    }
}
