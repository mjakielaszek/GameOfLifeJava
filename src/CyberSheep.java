import java.util.Vector;

public class CyberSheep extends Animal{
    private class Hogweeds{
        private int x;
        private int y;
        private int distance;
        public Hogweeds(int x, int y, int currentX, int currentY){
            this.setX(x);
            this.setY(y);
            this.setDistance(currentX, currentY);
        }
        public void setX(int _x){this.x=_x;}
        public void setY(int _y) {this.y = _y;}
        public void setDistance(int currentX, int currentY){
            int xDistance = Math.abs(currentX - this.x);
            int yDistance = Math.abs(currentY- this.y);
            this.distance = (int) Math.sqrt(Math.pow(xDistance,2) + Math.pow(yDistance,2));
        }
        public int getDistance() {return this.distance; }
        public int getX() {return this.x; }
        public int getY() {return this.y; }
    }
    public CyberSheep(int _x, int _y){
        this.setX(_x);
        this.setY(_y);
        this.setAge(0);
        this.setSign("C");
        this.setStrength(11);
        this.setInitiative(4);
    }
    public CyberSheep(int _x, int _y, int init, int str, int _age){
        this.setStrength(str);
        this.setInitiative(init);
        this.setX(_x);
        this.setY(_y);
        this.setAge(_age);
        this.setSign("C");
    }

    @Override
    public void action(int key, int maxX, int maxY) {
        Vector<Organism> organisms = getOrganisms();
        Vector<Hogweeds> hogweeds = new Vector<>();
        for (Organism org : organisms){
            if (org instanceof Hogweed){
                hogweeds.add(new Hogweeds(org.getX(), org.getY(), this.getX(), this.getY()));
            }
        }
        if (!hogweeds.isEmpty()){
            Hogweeds closestHogweed = hogweeds.get(0);
            for (Hogweeds hogweed : hogweeds) {
                if (closestHogweed.getDistance() > hogweed.getDistance())
                    closestHogweed = hogweed;
            }
            if (Math.abs(closestHogweed.getX() - this.getX()) >= Math.abs(closestHogweed.getY() - this.getY())) {
                if (this.getX() >= closestHogweed.getX()) {
                    Organism newPosition1 = Main.findOrganismByCoords(this.getX()-1, this.getY(), this.getOrganisms());
                    if (newPosition1 != null)
                        newPosition1.collision(this, maxX, maxY);
                    else this.addX(-1);
                }
                else {
                    Organism newPosition2 = Main.findOrganismByCoords(this.getX()+1, this.getY(), this.getOrganisms());
                    if (newPosition2 != null)
                        newPosition2.collision(this, maxX, maxY);
                    else this.addX(1);
                }
            }
            else {
                if (this.getY() >= closestHogweed.getY()) {
                    Organism newPosition3 = Main.findOrganismByCoords(this.getX(), this.getY()-1, this.getOrganisms());
                    if (newPosition3 != null)
                        newPosition3.collision(this, maxX, maxY);
                    else this.addY(-1);
                }
                else {
                    Organism newPosition4 = Main.findOrganismByCoords(this.getX(), this.getY()+1, this.getOrganisms());
                    if (newPosition4 != null)
                        newPosition4.collision(this, maxX, maxY);
                    else this.addY(1);
                }
            }
        }
        else super.action(key, maxX, maxY);
    }

    public Organism returnNew(int x, int y){
        CyberSheep newCS = new CyberSheep(x,y);
        newCS.setOrganisms(this.getOrganisms());
        return newCS;
    }
}
