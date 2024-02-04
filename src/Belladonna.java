public class Belladonna extends Plant{
    public Belladonna(int _x, int _y){
        this.setY(_y);
        this.setAge(0);
        this.setInitiative(0);
        this.setX(_x);
        this.setStrength(99);
        this.setSign("*");
    }
    public Belladonna(int x, int y, int str, int init, int age){
        this.setY(y);
        this.setX(x);
        this.setAge(age);
        this.setInitiative(init);
        this.setStrength(str);
        this.setSign("*");
    }
    public Organism returnNew(int x, int y){
        Belladonna newB = new Belladonna(x,y);
        newB.setOrganisms(this.getOrganisms());
        return newB;
    }
}
