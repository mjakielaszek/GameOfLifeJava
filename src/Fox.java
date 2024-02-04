import java.util.Random;
import java.util.Vector;

public class Fox extends Animal{
    private class Coords{
        private int x;
        private int y;
        public Coords(int x, int y){
            setX(x);
            setY(y);
        }
        public void setX(int x) {
            this.x = x;
        }
        public void setY(int y) {
            this.y = y;
        }
        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
    }
    public Fox(int x, int y, int str, int init, int age){
        this.setY(y);
        this.setX(x);
        this.setAge(age);
        this.setStrength(str);
        this.setAge(age);
        this.setInitiative(init);
        this.setSign("F");
    }
    public Fox(int _x, int _y){
        this.setX(_x);
        this.setY(_y);
        this.setAge(0);
        this.setInitiative(7);
        this.setStrength(3);
        this.setSign("F");
    }
    public Organism returnNew(int x, int y){
        Fox newF = new Fox(x,y);
        newF.setOrganisms(this.getOrganisms());
        return newF;
    }

    @Override
    public void action(int key, int maxX, int maxY) {
        Vector<Coords> availableCells = new Vector<>();
        int x = this.getX();
        int y = this.getY();
        int foxStrength = this.getStrength();
        Organism newCellOrganism1 = Main.findOrganismByCoords(x-1,y, getOrganisms());
        if (x > 0 && (newCellOrganism1 == null || newCellOrganism1.getStrength() < foxStrength)){
            availableCells.add(new Coords(x-1, y));
        }
        Organism newCellOrganism2 = Main.findOrganismByCoords(x, y-1, getOrganisms());
        if (y>0 && (newCellOrganism2 == null || newCellOrganism2.getStrength() < foxStrength))
            availableCells.add(new Coords(x,y-1));
        Organism newCellOrganism3 = Main.findOrganismByCoords(x+1, y, getOrganisms());
        if (x < maxX-1 && (newCellOrganism3 == null || newCellOrganism3.getStrength() < foxStrength))
            availableCells.add(new Coords(x+1, y));
        Organism newCellOrganism4 = Main.findOrganismByCoords(x, y+1, getOrganisms());
        if (y<maxY-1 && (newCellOrganism4 == null || newCellOrganism4.getStrength() < foxStrength))
            availableCells.add(new Coords(x, y+1));
        Random random = new Random();
        int ammOfAvailCells = availableCells.size();
        if (ammOfAvailCells > 0) {
            int newCell = random.nextInt(ammOfAvailCells);
            int newX = availableCells.get(newCell).getX();
            int newY = availableCells.get(newCell).getY();
            Organism newCellOrganism = Main.findOrganismByCoords(newX, newY,getOrganisms());
            if (newCellOrganism == null){
                this.setX(newX);
                this.setY(newY);
            }
            else newCellOrganism.collision(this, maxX, maxY);
        }
    }
}
