public class Sheep extends Animal{
    public Sheep(int _x, int _y){
        this.setX(_x);
        this.setY(_y);
        this.setAge(0);
        this.setInitiative(4);
        this.setStrength(4);
        this.setSign("S");
    }
    public Organism returnNew(int x, int y){
        Sheep newS = new Sheep(x,y);
        newS.setOrganisms(this.getOrganisms());
        return newS;
    }
    public Sheep(int x, int y, int init, int str, int age){
        this.setAge(age);
        this.setInitiative(init);
        this.setStrength(str);
        this.setX(x);
        this.setY(y);
        this.setSign("S");
    }
}
