public class Hogweed extends Plant{
    public Hogweed(int _x, int _y){
        this.setY(_y);
        this.setAge(0);
        this.setInitiative(0);
        this.setX(_x);
        this.setStrength(10);
        this.setSign("%");
    }
    public Organism returnNew(int x, int y){
        Hogweed newH = new Hogweed(x,y);
        newH.setOrganisms(this.getOrganisms());
        return newH;
    }

    @Override
    public void action(int key, int maxX, int maxY) {
        int x = this.getX();
        int y = this.getY();
        Organism left = Main.findOrganismByCoords(this.getX()-1, this.getY(), getOrganisms());
        Organism right = Main.findOrganismByCoords(this.getX()+1, this.getY(), getOrganisms());
        Organism top = Main.findOrganismByCoords(this.getX(), this.getY()+1, getOrganisms());
        Organism bottom = Main.findOrganismByCoords(this.getX(), this.getY()-1, getOrganisms());
        if (x>0 && left != null && !(left instanceof CyberSheep))
            deleteOrganismByCoords(x-1,y);
        if (y>0 && top != null && !(top instanceof CyberSheep))
            deleteOrganismByCoords(x,y-1);
        if(x<maxX-1 && right != null && !(right instanceof CyberSheep))
            deleteOrganismByCoords(x+1,y);
        if (y<maxY-1 && bottom != null && !(bottom instanceof CyberSheep))
            deleteOrganismByCoords(x,y+1);
    }
    public Hogweed(int x, int y, int init, int str, int age){
        this.setAge(age);
        this.setInitiative(init);
        this.setStrength(str);
        this.setX(x);
        this.setY(y);
        this.setSign("%");
    }
}
