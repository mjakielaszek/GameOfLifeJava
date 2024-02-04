import java.util.Random;
import java.util.Vector;

public class Organism {
    private Vector<Organism> organisms;
    public static final int moveOptions = 4;
    private int x;
    private int y;
    private int initiative;
    private int strength;
    private int age;
    private String sign;
    public Organism(){
        this.x = -1;
        this.y = -1;
        this.strength =0;
        this.initiative = Integer.MAX_VALUE;
        this.age = Integer.MAX_VALUE;
        this.sign = " ";
    }
    public void setX(int _x){this.x = _x; }
    public void setY(int _y){this.y = _y;}
    public void setInitiative(int _init){this.initiative = _init;}
    public void setAge(int _age){this.age = _age;}
    public void setStrength(int _strength) {this.strength = _strength; }
    public void setSign(String _sign) {this.sign = _sign; }
    public int getX() {return this.x;}
    public int getY() {return  this.y;}
    public int getInitiative() {return this.initiative;}
    public int getStrength() {return this.strength;}
    public int getAge() {return this.age;}
    public String getSign() { return this.sign; }
    public void addAge(int _add) { this.age+= _add; }
    public void addX(int _add) {this.x += _add;}
    public void addY(int _add) {this.y += _add;}
    public void addStrength(int _add) {this.strength+= _add; }
    public void action(int key, int maxX, int maxY){}
    public void collision(Organism attacker, int maxX, int maxY) {
        if (attacker.getClass() == this.getClass()){
            this.spawnNew(maxX, maxY);
        }
        else if (this.getStrength() > attacker.getStrength()) {
            String winnerName = this.getClass().getName();
            String loserName = attacker.getClass().getName();
            getOrganisms().remove(attacker);
            Commentator.addComment(winnerName + " eliminated " + loserName);
        }
        else if (this.getStrength() <= attacker.getStrength()) {
            boolean isGuarana = false;
            if (this instanceof Guarana)   {
                attacker.addStrength(3);
                isGuarana = true;
                Commentator.addComment(attacker.getClass().getName()+ " obtained guarana boost");
            }
            int newX = this.getX();
            int newY = this.getY();
            String winnerName = attacker.getClass().getName();
            String loserName = this.getClass().getName();
            if (!isGuarana) Commentator.addComment(winnerName + " eliminated " + loserName);
            getOrganisms().remove(this);
            if (attacker instanceof Animal) {
                attacker.setX(newX);
                attacker.setY(newY);
            }
        }
    }
    Organism returnNew(int x, int y){return new Organism();}

    void spawnNew(int maxX, int maxY) {
        Random random = new Random();
        int dir = random.nextInt(moveOptions);
        int x = this.getX();
        int y = this.getY();
        switch (dir) {
            case 0 -> {
                if (x > 0 && Main.findOrganismByCoords(this.getX() - 1, this.getY(), getOrganisms()) == null) {
                    getOrganisms().add(this.returnNew(x - 1, y));
                    Commentator.addComment(this.getClass().getName() + " breeded");
                }
            }
            case 1 -> {
                if (y > 0 && Main.findOrganismByCoords(this.getX(), this.getY() - 1, getOrganisms()) == null) {
                    getOrganisms().add(this.returnNew(x, y - 1));
                    Commentator.addComment(this.getClass().getName() + " breeded");
                }
            }
            case 2 -> {
                if (x < maxX - 1 && Main.findOrganismByCoords(this.getX() + 1, this.getY(), getOrganisms()) == null) {
                    getOrganisms().add(this.returnNew(x + 1, y));
                    Commentator.addComment(this.getClass().getName() + " breeded");
                }
            }
            case 3 -> {
                if (y < maxY - 1 && Main.findOrganismByCoords(this.getX(), this.getY() + 1, getOrganisms()) == null) {
                    getOrganisms().add(this.returnNew(x, y + 1));
                    Commentator.addComment(this.getClass().getName() + " breeded");
                }
            }
        }
    }
    public void setOrganisms(Vector<Organism> org){this.organisms = org;}
    public Vector<Organism> getOrganisms() {return this.organisms; }
    public void deleteOrganismByCoords(int x, int y){
        Vector<Organism> organisms = getOrganisms();
        for (Organism org : organisms){
            if (org.getX() == x && org.getY() == y){
                organisms.remove(org);
                return;
            }
        }
    }
}
