import java.util.Random;

public class Animal extends Organism{
    public Animal(){}
    public void action(int key, int maxX, int maxY){
        Random random = new Random();
        int dir = random.nextInt(moveOptions);
        switch (dir) {
            case 0 -> {
                Organism newCell = Main.findOrganismByCoords(this.getX() - 1, this.getY(), getOrganisms());
                if (getX() > 0 && newCell == null)
                    this.addX(-1);
                else if (newCell != null)
                    newCell.collision(this, maxX, maxY);
            }
            case 1 -> {
                Organism newCell2 = Main.findOrganismByCoords(this.getX(), this.getY() - 1, getOrganisms());
                if (getY() > 0 && newCell2 == null)
                    this.addY(-1);
                else if (newCell2 != null)
                    newCell2.collision(this, maxX, maxY);
            }
            case 2 -> {
                Organism newCell3 = Main.findOrganismByCoords(this.getX() + 1, this.getY(), getOrganisms());
                if (getX() < maxX - 1 && newCell3 == null)
                    addX(1);
                else if (newCell3 != null)
                    newCell3.collision(this, maxX, maxY);
            }
            case 3 -> {
                Organism newCell4 = Main.findOrganismByCoords(this.getX(), this.getY() + 1, getOrganisms());
                if (getY() < maxY - 1 && newCell4 == null)
                    addY(1);
                else if (newCell4 != null)
                    newCell4.collision(this, maxX, maxY);
            }
        }
    }
}
