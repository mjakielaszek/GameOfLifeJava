public class Guarana extends Plant{
    public Guarana(int _x, int _y){
        this.setY(_y);
        this.setAge(0);
        this.setInitiative(0);
        this.setX(_x);
        this.setStrength(0);
        this.setSign("^");
    }
    public Organism returnNew(int x, int y){
        Guarana newG = new Guarana(x,y);
        newG.setOrganisms(this.getOrganisms());
        return newG;
    }

    public Guarana(int x, int y, int init, int str, int age){
        this.setAge(age);
        this.setInitiative(init);
        this.setStrength(str);
        this.setX(x);
        this.setY(y);
        this.setSign("^");
    }
}
