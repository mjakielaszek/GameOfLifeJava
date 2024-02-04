import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Human extends Animal{
    private boolean abilityActive;
    private boolean abilityCooldown;
    private int cooldownLength = 0;
    public int getCooldownLength() {return this.cooldownLength; }
    public boolean getAbilityActive() {return this.abilityActive; }
    public boolean getAbilityCooldown() {return this.abilityCooldown; }
    public Human(int x, int y, int age, int str, int init, boolean abilityActive, boolean abilityCooldown, int cooldownLength){
        this.setX(x);
        this.setStrength(str);
        this.setY(y);
        this.setInitiative(init);
        this.setAge(age);
        this.abilityCooldown = abilityCooldown;
        this.abilityActive = abilityActive;
        this.cooldownLength = cooldownLength;
        this.setSign("H");
    }
    public Human(int _x, int _y){
        this.setX(_x);
        this.setY(_y);
        this.setAge(0);
        this.setInitiative(4);
        this.setStrength(5);
        this.setSign("H");
        this.abilityActive = false;
        this.abilityCooldown = false;
    }

    @Override
    public void action(int key, int maxX, int maxY) {
        if (key == KeyEvent.VK_LEFT && getX() > 0) {
            Organism newCell = Main.findOrganismByCoords(this.getX()-1, this.getY(), getOrganisms());
            if (newCell ==null)
                this.addX(-1);
            else newCell.collision(this, maxX, maxY);
        }
        if (key == KeyEvent.VK_UP && getY() > 0) {
            Organism newCell1 = Main.findOrganismByCoords(this.getX(), this.getY()-1, getOrganisms());
            if (newCell1 == null)
                this.addY(-1);
            else newCell1.collision(this,maxX, maxY);
        }
        if (key == KeyEvent.VK_RIGHT && getX() < maxX-1) {
            Organism newCell2 = Main.findOrganismByCoords(this.getX() + 1, this.getY(), getOrganisms());
            if (newCell2 == null)
                this.addX(1);
            else newCell2.collision(this, maxX, maxY);
        }
        if (key == KeyEvent.VK_DOWN && getY() < maxY-1) {
            Organism newCell3 = Main.findOrganismByCoords(this.getX(), this.getY()+1, getOrganisms());
            if (newCell3 == null)
                this.addY(1);
            else newCell3.collision(this, maxX, maxY);
        }
        if (abilityActive){
            if (this.getStrength() > 5) this.addStrength(-1);
            if (this.getStrength() == 5) {
                this.abilityCooldown = true;
                this.abilityActive = false;
            }
        }
        if (abilityCooldown){
            cooldownLength++;
            if (cooldownLength == 5)  abilityCooldown = false;
        }
    }
    public void activateAbility(){
        if (!this.abilityCooldown && !this.abilityActive) {
            this.abilityActive = true;
            this.setStrength(10);
        }
        else if (this.abilityActive){
            JFrame alreadyActive = new JFrame();
            JPanel active = new JPanel();
            JLabel label = new JLabel("The ability already is active");
            active.add(label);
            alreadyActive.add(active);
            alreadyActive.setMinimumSize(new Dimension(200, 70));
            alreadyActive.setVisible(true);
        }
        else if (this.abilityCooldown){
            JFrame onCooldown = new JFrame();
            JPanel cooldown = new JPanel();
            JLabel label = new JLabel("The ability is currently on cooldown");
            cooldown.add(label);
            onCooldown.add(cooldown);
            onCooldown.setMinimumSize(new Dimension(250, 70));
            onCooldown.setVisible(true);
        }
    }
}
