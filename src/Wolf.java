public class Wolf extends Animal{
    public Wolf(int _x, int _y){
        this.setX(_x);
        this.setY(_y);
        this.setAge(0);
        this.setInitiative(5);
        this.setStrength(9);
        this.setSign("W");
    }
    public Organism returnNew(int x, int y){
        Wolf newW = new Wolf(x,y);
        newW.setOrganisms(this.getOrganisms());
        return newW;
    }
    public Wolf(int x, int y, int init, int str, int age){
        this.setAge(age);
        this.setInitiative(init);
        this.setStrength(str);
        this.setX(x);
        this.setY(y);
        this.setSign("W");
    }
}
