public class Grass extends Plant{
    public Grass(int _x, int _y){
        this.setY(_y);
        this.setAge(0);
        this.setInitiative(0);
        this.setX(_x);
        this.setStrength(0);
        this.setSign(",");
    }
    public Organism returnNew(int x, int y){
        Grass newG = new Grass(x,y);
        newG.setOrganisms(this.getOrganisms());
        return newG;
    }
    public Grass(int x, int y, int str, int init, int age){
        this.setX(x);
        this.setY(y);
        this.setInitiative(init);
        this.setAge(age);
        this.setStrength(str);
        this.setSign(",");
    }
}
