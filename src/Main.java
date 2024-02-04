import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.*;

public class Main {
    private static final int defaultSpawnRate = 20;
    private static final int organismsCount = 11;
    private static final int buttonWidth = 46;
    private static final int buttonHeight = 31;
    private final int xSize = 20;
    private final int ySize = 20;
    private JPanel map;
    private JPanel texts;
    private JFrame frame;
    private JSplitPane mainDivision;
    public int getxSize() {return this.xSize; }
    public int getySize() {return this.ySize; }
    private Vector<Organism> organisms;
    private ArrayList<JButton> buttons;
    private JFrame incorrectMessage;
    private JPanel errorPlacing;
    private JLabel label;
    private DefaultListModel comments;
    public JPanel getTexts() {return this.texts; }
    boolean isHuman(){
        for (Organism org : organisms){
            if (Objects.equals(org.getSign(), "H"))
                return true;
        }
        return false;
    }
    public void save() throws IOException {
        JFrame saveFrame = new JFrame("save");
        JPanel savePanel = new JPanel();
        JTextField t = new JTextField(20);
        JButton button = new JButton("confirm");
        JLabel label = new JLabel("input the save name");
        final String[] saveName = new String[1];
        savePanel.add(label);
        savePanel.add(t);
        savePanel.add(button);
        saveFrame.add(savePanel);
        saveFrame.setMinimumSize(new Dimension(250, 130));
        saveFrame.setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("confirm")) {
                    saveName[0] = t.getText();
                    File file = new File(saveName[0]);
                    try {
                        file.createNewFile();
                    } catch (IOException f) {
                        f.printStackTrace();
                    }
                    try (PrintWriter writer = new PrintWriter(new File(saveName[0]))) {
                        writer.println(organisms.size());
                        for (Organism org : organisms) {
                            writer.println(org.getSign());
                            writer.println(org.getAge());
                            writer.println(org.getX());
                            writer.println(org.getY());
                            writer.println(org.getInitiative());
                            writer.println(org.getStrength());
                            if (org instanceof Human) {
                                Human human = (Human) org;
                                writer.println(human.getAbilityActive());
                                writer.println(human.getAbilityCooldown());
                                writer.println(human.getCooldownLength());
                            }
                        }
                    } catch (FileNotFoundException a) {
                        a.printStackTrace();
                    }
                    saveFrame.setVisible(false);
                }
            }
        });
    }
    public void load(){
        JFrame loadFrame = new JFrame("load");
        JPanel loadPanel = new JPanel();
        JTextField t = new JTextField(20);
        JButton button = new JButton("confirm");
        JLabel label = new JLabel("input the save name to load");
        final String[] saveName = new String[1];
        loadPanel.add(label);
        loadPanel.add(t);
        loadPanel.add(button);
        loadFrame.add(loadPanel);
        loadFrame.setMinimumSize(new Dimension(250, 130));
        loadFrame.setVisible(true);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals("confirm")) {
                    saveName[0] = t.getText();
                    try {
                        Scanner scanner = new Scanner(new File(saveName[0]));
                        int numberOfOrganisms = scanner.nextInt();
                        scanner.nextLine();
                        organisms.clear();

                        for (int i = 0; i < numberOfOrganisms; i++) {
                            String signLine = scanner.next();
                            char sign = signLine.charAt(0);
                            int age = scanner.nextInt();
                            int x = scanner.nextInt();
                            int y = scanner.nextInt();
                            int initiative = scanner.nextInt();
                            int strength = scanner.nextInt();
                            if (sign == 'H') {
                                boolean abilityActive = scanner.nextBoolean();
                                boolean abilityCooldown = scanner.nextBoolean();
                                int cooldownLength = scanner.nextInt();
                                organisms.add(new Human(x, y, age,strength,initiative, abilityActive, abilityCooldown, cooldownLength));
                            }
                            switch (sign) {
                                case 'W' -> organisms.add(new Wolf(x, y, initiative, strength, age));
                                case 'S' -> organisms.add(new Sheep(x, y, initiative, strength, age));
                                case 'T' -> organisms.add(new Turtle(x, y, initiative, strength, age));
                                case 'A' -> organisms.add(new Antelope(x, y, age, initiative, strength));
                                case 'F' -> organisms.add(new Fox(x, y, strength, initiative, age));
                                case ',' -> organisms.add(new Grass(x, y, strength, initiative, age));
                                case '*' -> organisms.add(new Belladonna(x, y, strength, initiative, age));
                                case '^' -> organisms.add(new Guarana(x, y, initiative, strength, age));
                                case '%' -> organisms.add(new Hogweed(x, y, initiative, strength, age));
                                case '$' -> organisms.add(new SowThistle(x, y, initiative, strength, age));
                                case 'C' -> organisms.add(new CyberSheep(x,y,initiative, strength,age));
                            }
                        }
                        scanner.close();
                    } catch (FileNotFoundException f) {
                        f.printStackTrace();
                    }
                    resetButtons();
                    printOrganisms();
                }
            }
        });
    }
    public class ActionHandling implements KeyListener{
        public void keyTyped(KeyEvent e) {}
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_S) {
                try {
                    save();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            if (e.getKeyCode() == KeyEvent.VK_L)
                load();
            if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_DOWN) {
                Commentator.resetComments();
                sortOrganisms();
                for (Organism org : organisms)
                    org.setOrganisms(organisms);
                int size = organisms.size();
                for (int i = 0; i < size; i++) {
                    if (i >= organisms.size()) break;
                    if (organisms.get(i) != null) {
                        organisms.get(i).addAge(1);
                        organisms.get(i).action(e.getKeyCode(), getxSize(), getySize());
                    }
                }
                resetButtons();
                printOrganisms();
            }
            if (!isHuman()){
                JFrame endFrame = new JFrame("You lost");
                JPanel endPanel = new JPanel();
                JLabel endLabel = new JLabel("You died, restart to play again");
                endPanel.add(endLabel);
                endFrame.add(endPanel);
                endFrame.setMinimumSize(new Dimension(200, 70));
                endFrame.setVisible(true);
            }
            if (e.getKeyCode() == KeyEvent.VK_P) {
                for (Organism org : organisms) {
                    if (Objects.equals(org.getSign(), "H")) {
                        Human human = (Human) org;
                        human.activateAbility();
                    }
                }
            }
        }
        @Override
        public void keyReleased(KeyEvent e) {}
    }
    static public Organism findOrganismByCoords(int x, int y, Vector<Organism> organisms){
        for (Organism o : organisms){
            if (o.getX() == x && o.getY() == y)
                return o;
        }
        return null;
    }

    private void spawnOrganisms(Organism org, JButton space){
        organisms.add(org);
        space.setText(org.getSign());
    }
    public Main(){
        incorrectMessage = new JFrame("Invalid Placement");
        errorPlacing = new JPanel();
        label = new JLabel("The chosen spot is already taken by another organism, you cannot spawn a new organism here");
        errorPlacing.add(label);
        incorrectMessage.add(errorPlacing);
        incorrectMessage.setSize(new Dimension(600,70));
        incorrectMessage.show(false);
        frame = new JFrame();
        map = new JPanel(null);
        texts = new JPanel();
        buttons = new ArrayList<>();
        for (int i =0; i < getxSize(); i++){
            for (int j = 0; j <getySize(); j++){
                JButton space = new JButton(" ");
                space.setBounds(buttonWidth*i, buttonHeight*j, buttonWidth, buttonHeight);
                space.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!Objects.equals(space.getText(), " ") && !Objects.equals(space.getText(), "H")) {
                            incorrectMessage.show(true);
                            return;
                        }
                        else if (space.getText()=="H") space.requestFocus();
                        else {
                            incorrectMessage.show(false);
                            String[] options = {"Wolf", "Sheep", "Fox", "Turtle", "Antelope", "Grass", "Sow Thistle", "Guarana", "Belladonna", "Hogweed", "CyberSheep"};
                            String selected = String.valueOf(JOptionPane.showOptionDialog(null, "Choose which organism should be spawned.", "Organism Choice", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]));
                            int x = space.getX() / buttonWidth;
                            int y = space.getY() / buttonHeight;
                            switch (selected) {
                                case "0" -> spawnOrganisms(new Wolf(x, y), space);
                                case "1" -> spawnOrganisms(new Sheep(x, y), space);
                                case "2" -> spawnOrganisms(new Fox(x, y), space);
                                case "3" -> spawnOrganisms(new Turtle(x, y), space);
                                case "4" -> spawnOrganisms(new Antelope(x, y), space);
                                case "5" -> spawnOrganisms(new Grass(x, y), space);
                                case "6" -> spawnOrganisms(new SowThistle(x, y), space);
                                case "7" -> spawnOrganisms(new Guarana(x, y), space);
                                case "8" -> spawnOrganisms(new Belladonna(x, y), space);
                                case "9" -> spawnOrganisms(new Hogweed(x, y), space);
                                case "10" -> spawnOrganisms(new CyberSheep(x, y), space);
                            }
                        }
                    }
                });
                map.add(space);
                buttons.add(space);
            }
        }
        JTextArea legend = new JTextArea("""
                use arrows to take a move\s
                click on a cell to spawn a new organism
                P - activate magic potion
                S - save the game
                L - load the game from a save\s
                , - grass\s
                $ - sow thistle\s
                % - hog weed\s
                * - belladonna\s
                ^ - guarana""");
        legend.setEditable(false);
        legend.setBounds(0,0,250,100);
        texts.add(legend);
        texts.setPreferredSize(new Dimension(250, 350));
        map.setPreferredSize(new Dimension(getxSize() * buttonWidth,getySize() * buttonHeight));
        map.setMinimumSize(new Dimension(getxSize()*buttonWidth,getySize()*buttonHeight));
        mainDivision = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, map, texts);
        this.comments = new DefaultListModel();
        frame.add(mainDivision);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Michal Jakielaszek, 193300");
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(getxSize() * buttonWidth + 250,getySize() * buttonHeight + 40));
        frame.pack();
        frame.setVisible(true);
        this.organisms = new Vector<Organism>();
        Random random = new Random();
        int humanX = random.nextInt(getxSize());
        int humanY = random.nextInt(getySize());
        organisms.add(new Human(humanX, humanY));
        for (int i = 0; i<getxSize()*getySize(); i++){
            int rand = random.nextInt(100);
            if (rand < defaultSpawnRate){
                int organismType = random.nextInt(organismsCount);
                int x = i / getxSize();
                int y = i % getxSize();
                if (findOrganismByCoords(x,y,organisms) != null) continue;
                switch (organismType) {
                    case 0 -> organisms.add(new Wolf(x, y));
                    case 1 -> organisms.add(new Sheep(x, y));
                    case 2 -> organisms.add(new Fox(x, y));
                    case 3 -> organisms.add(new Turtle(x, y));
                    case 4 -> organisms.add(new Antelope(x, y));
                    case 5 -> organisms.add(new Grass(x, y));
                    case 6 -> organisms.add(new SowThistle(x, y));
                    case 7 -> organisms.add(new Guarana(x, y));
                    case 8 -> organisms.add(new Belladonna(x, y));
                    case 9 -> organisms.add(new Hogweed(x, y));
                    case 10 -> organisms.add(new CyberSheep(x,y));
                }
            }
        }
        Commentator.innitComments(getTexts());
        printOrganisms();
    }
    public void resetButtons(){
        for (JButton button : buttons){
            KeyListener[] listeners = button.getKeyListeners();
            for (KeyListener listener : listeners)
                button.removeKeyListener(listener);
            button.setText(" ");
        }
    }
    public void printOrganisms(){
        for (Organism organism : organisms){
            JButton button = this.buttons.get(organism.getX() * getxSize() + organism.getY());
            button.setText(String.valueOf(organism.getSign()));
            if (organism instanceof Human) {
                button.requestFocus();
                button.addKeyListener(new ActionHandling());
            }
        }
    }
    public void sortOrganisms(){
        Collections.sort(organisms, new Comparator<>() {
            @Override
            public int compare(Organism o1, Organism o2) {
                if (o1.getInitiative() == o2.getInitiative()) return Integer.compare(o2.getAge(), o1.getAge());
                return Integer.compare(o2.getInitiative(), o1.getInitiative());
            }
        });
    }
    public static void main(String[] args) {
        Main world = new Main();
    }
}