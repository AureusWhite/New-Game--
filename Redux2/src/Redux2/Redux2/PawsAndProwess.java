package Redux2;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

public class PawsAndProwess extends JFrame {

    private static JTextArea displayArea;
    private JButton attackButton, defendButton, playCardButton, drawCardButton, priorityButton;
    private static final ArrayList<Paw> paws = new ArrayList<>();
    private static final ArrayList<Card> cards = new ArrayList<>();
    private static final ArrayList<PawAbility> pawAbilities = new ArrayList<>();
    private static final HashMap<Integer, Card> pawDeck = new HashMap<>();
    private static Paw playerPaw;
    private static Paw oppnentPaw;
    private boolean newPick=true;
    private JLabel pawStatsLabel;
    private static final Random rand = new Random();
    private static int turn=1;
    public PawsAndProwess() {
        initComponents();
        initializeGame();
    }
    public void resetPawHP(){
        playerPaw.setHp(25);
        oppnentPaw.setHp(25);
    }
    public final void initializeGame() {
        createPawGame();
        createPaws();
        pickAPaw();
        resetPawHP();
    }
    public void savePaws(){
        File file = new File("pawData.ser");
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } try (FileOutputStream fileOut = new FileOutputStream(file); ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
                out.writeObject(paws);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    public void loadPaws(){
        File file = new File("pawData.ser");
        if(file.exists()){
            try {
                paws.clear();
                try (FileInputStream fileIn = new FileInputStream(file); ObjectInputStream in = new ObjectInputStream(fileIn)) {
                    paws.addAll((ArrayList<Paw>) in.readObject());
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }   
    }
    public void pickAPaw() {
        String[] chooseCollection = {"Open a Paw Figure", "My Paws", "Choose a Paw"};
        int choice = JOptionPane.showOptionDialog(null, "Choose a Paw", "Choose a Paw", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, chooseCollection, chooseCollection[0]);
        if (choice == 0) {
            openFigure();
        } else if(choice == 1){
            String[] pawNames = new String[Player.getPaws().size()];
            for (int i = 0; i < Player.getPaws().size(); i++) {
                pawNames[i] = Player.getPaws().get(i).getName();
            }
            String choice1 = (String) JOptionPane.showInputDialog(null, "Choose a Paw", "Choose a Paw", JOptionPane.QUESTION_MESSAGE, null, pawNames, pawNames[0]);
            for (Paw paw : Player.getPaws()) {
                if (paw.getName().equals(choice1)) {
                    playerPaw = paw;
                    newPick = false;
                    break;
                }
            }
        } while (newPick) {
                if (paws.isEmpty()) {
                    display("No more Paws available.");
                    return;
                }
    
                String[] pawNames = new String[paws.size()];
                for (int i = 0; i < paws.size(); i++) {
                    pawNames[i] = paws.get(i).getName();
                }
    
                int pawChoice = JOptionPane.showOptionDialog(null, "Choose a paw", "Choose a paw", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, pawNames, pawNames[0]);
                if (pawChoice == -1) {
                    display("No Paw chosen.");
                    return;
                }
    
                display("You chose " + paws.get(pawChoice).getName());
                playerPaw = paws.get(pawChoice);
                paws.remove(pawChoice);
    
                if (paws.isEmpty()) {
                    display("No more Paws available for the opponent.");
                    return;
                }
    
                int opponentChoice = (int) (Math.random() * paws.size());
                display("Your opponent chose " + paws.get(opponentChoice).getName());
                oppnentPaw = paws.get(opponentChoice);
                paws.remove(opponentChoice);
    
                newPick = false;
            }
        }
    private void initComponents() {
        setTitle("Paw and Prowess");
        setSize(800, 450);
        setDefaultCloseOperation(endGame());
        setLocationRelativeTo(null); // Center window
        setLayout(new BorderLayout());

        // Display area for game information
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        displayArea.setFont(new Font("Serif", Font.PLAIN, 16));
        displayArea.setText("Welcome to Paw and Prowess!\n");

        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.CENTER);

        // Panel for buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // Attack button
        attackButton = new JButton("Attack");
        attackButton.addActionListener((ActionEvent e) -> {
            if(turn == 1){
                performAttack(playerPaw, oppnentPaw);
                setPriority();
            }
            else{
                display("It is not your turn.");
            }
        updateStatsLabel();});

        // Defend button
        defendButton = new JButton("Defend");
        defendButton.addActionListener((ActionEvent e) -> {
            if(turn == 1){
                performDefend(playerPaw, oppnentPaw);
                setPriority();
            }
            else{
                display("It is not your turn.");
            }
        updateStatsLabel();});
        drawCardButton = new JButton("Draw Card");
        drawCardButton.addActionListener((ActionEvent e) -> {
            if(playerPaw.getEnergy() > 2){
                drawCard();
            } else {
                display("You do not have enough energy to draw a card.");
            }
            if(turn == 1){
                drawCard();
            }
            else{
                display("It is not your turn.");
            }
        updateStatsLabel();});
        playCardButton = new JButton("Play Card");
        playCardButton.addActionListener((ActionEvent e) -> {
            if(turn == 1){
                if(playerPaw.getEnergy() > 0){
                playerPaw.setEnergy(playerPaw.getEnergy() - 1);
                playCard(playerPaw, oppnentPaw);
            } else {
                display("You do not have enough energy to play a card.");
            }
            }
            else{
                display("It is not your turn.");
            }
        updateStatsLabel();});
        priorityButton = new JButton("Priority");
        priorityButton.addActionListener((ActionEvent e) -> {
            playerPaw.setTapped(true);
            playerPaw.setEnergy(playerPaw.getEnergy() + 5);
            setPriority();
        updateStatsLabel();});

        // Add buttons to the panel
        buttonPanel.add(priorityButton);
        buttonPanel.add(attackButton);
        buttonPanel.add(drawCardButton);
        buttonPanel.add(playCardButton);
        buttonPanel.add(defendButton);

        //panel for the paw stats
        JPanel pawStats = new JPanel();
        pawStatsLabel = new JLabel("Paw Stats");
        pawStats.add(pawStatsLabel);
        pawStats.setLayout(new FlowLayout());

        // Add panel to the frame
        add(pawStats, BorderLayout.NORTH);


        // Add button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);
        

        // Set visibility
        setVisible(true);
    }

    protected void performDefend(Paw playerPaw, Paw opponentPaw) {
        playerPaw.defend(opponentPaw);
    }
    protected void performAttack(Paw playerPaw, Paw opponentPaw) {
        playerPaw.attack(opponentPaw);
    }

    public static ArrayList<PawAbility> getPawAbilities() {
        return pawAbilities;
    }

    public static ArrayList<Card> getCards() {
        return cards;
    }

    public static ArrayList<Paw> getPaws() {
        return paws;
    }

    public void createPawGame() {
        pickLibarary();
        createPawAbilities();
        createPawCards();
        Player.setPawDeck(PawsAndProwess.shuffleDeck());
        Player.setHand();
    }

    private void createPawCards() {
        cards.clear();
        pawDeck.clear();
        Card TrickstersHat = new Card("Trickster's Hat", "Debuff/Trap", (Paw playerPaw1, Paw opponentPaw) -> {
            opponentPaw.setTapped(true);
            display("Trickster's Hat has been played on " + opponentPaw.getName() + " and they are now tapped.");
            display(" Tapped = " + opponentPaw.isTapped() + "");
        updateStatsLabel();});
        Card SpeedySneakers = new Card("Speedy Sneakers", "Buff", (Paw playerPaw1, Paw opponentPaw) -> {
            playerPaw1.setSpeed(playerPaw1.getSpeed() + 5);
            display("Speedy Sneakers has been played on " + playerPaw1.getName() + " and they now have increased speed.");
            display(" Speed = " + playerPaw1.getSpeed() + "");
        updateStatsLabel();});
        Card PowerPaw = new Card("Power Paw", "Buff", (Paw playerPaw1, Paw opponentPaw) -> {
            playerPaw1.setPower(playerPaw1.getPower() + 1);
            display("Power Paw has been played on " + playerPaw1.getName() + " and they now have increased power.");
            display(" Power = " + playerPaw1.getPower() + "");
        updateStatsLabel();});
        Card ShieldingShell = new Card("Shielding Shell", "Buff", (Paw playerPaw1, Paw opponentPaw) -> {
            playerPaw1.setDefense(playerPaw1.getDefense() + 1);
            display("Shielding Shell has been played on " + playerPaw1.getName() + " and they now have increased defense.");
            display(" Defense = " + playerPaw1.getDefense() + "");
        updateStatsLabel();});
        Card HealingHeart = new Card("Healing Heart", "Buff", (Paw playerPaw1, Paw opponentPaw) -> {
            playerPaw1.setHp(playerPaw1.getHp() + 1);
            display("Healing Heart has been played on " + playerPaw1.getName() + " and they now have increased health.");
            display(" Health = " + playerPaw1.getHp() + "");
        updateStatsLabel();});
        Card SneakySneakers = new Card("Sneaky Sneakers", "Buff", (Paw playerPaw1, Paw opponentPaw) -> {
            playerPaw1.setStealth(playerPaw1.getStealth() + 1);
            display("Sneaky Sneakers has been played on " + playerPaw1.getName() + " and they now have increased stealth.");
            display(" Stealth = " + playerPaw1.getStealth() + "");
        updateStatsLabel();});
        Card ConfusionCollar = new Card("Confusion Collar", "Debuff/Trap", (Paw playerPaw1, Paw opponentPaw) -> {
            opponentPaw.setConfused(true);
            display("Confusion Collar has been played on " + opponentPaw.getName() + " and they are now confused.");
            display(" Confused = " + opponentPaw.isConfused() + "");
        updateStatsLabel();});
        Card EarthquakeEgg = new Card("Earthquake Egg", "Debuff/Trap", (Paw playerPaw1, Paw opponentPaw) -> {
            playerPaw1.setTapped(true);
            opponentPaw.setTapped(true);
            display("Earthquake Egg has been played on " + playerPaw1.getName() + " and " + opponentPaw.getName() + " and they are now both tapped.");
            display(" Tapped = " + playerPaw1.isTapped() + " and " + opponentPaw.isTapped() + "");
        updateStatsLabel();});
        cards.add(TrickstersHat);
        cards.add(SpeedySneakers);
        cards.add(PowerPaw);
        cards.add(ShieldingShell);
        cards.add(HealingHeart);
        cards.add(ConfusionCollar);
        cards.add(EarthquakeEgg);
        cards.add(SneakySneakers);
        pawDeck.put(1, SpeedySneakers);
        pawDeck.put(2, PowerPaw);
        pawDeck.put(3, ShieldingShell);
        pawDeck.put(4, HealingHeart);
        pawDeck.put(5, ConfusionCollar);
        pawDeck.put(6, EarthquakeEgg);
        pawDeck.put(7, SneakySneakers);
        pawDeck.put(8, TrickstersHat);

    }

    public static ArrayList<Card> shuffleDeck() {
        ArrayList<Card> shuffledDeck = new ArrayList<>();
        ArrayList<Integer> deckKeys = new ArrayList<>(pawDeck.keySet());
        Collections.shuffle(deckKeys);
        for (int i = 0; i < deckKeys.size(); i++) {
            shuffledDeck.add(pawDeck.get(deckKeys.get(i)));
        }
        return shuffledDeck;
    }
    public static void setPriority() {
        if (turn == 1) {
            if (playerPaw.getSpeed() + rand.nextInt(10) > oppnentPaw.getSpeed() + 5) {
                display("You outspeed the opponent and get another action.");
            } else {
                turn = 2;
                display("It is now the opponent's turn.");
                oppnentPaw.takeTurn(playerPaw);
            }
        } else {
            turn = 1;
            display("It is now your turn.");
        }
    }
    private void createPaws() {
        if (!paws.isEmpty()) {
            display("Paws already created.");
            return;
        }
        int totalStats = 28;  // Total of a + d + s
       
        // Speedy: The lightning-fast hare
        int aSpeedy = totalStats / 3 - 1; // Lower attack
        int dSpeedy = totalStats / 3 - 1; // Lower defense
        int sSpeedy = totalStats - aSpeedy - dSpeedy; // Better speed
        Paw speedy = new Paw("Speedy", "A blur of fur and lightning paws. Speedy strikes before most even see him coming, but lacks the strength for a prolonged fight.", aSpeedy, dSpeedy,  25, sSpeedy, PawsColor.BLUE, PawsAbility.SPEED, PawsRarity.COMMON);
    
        // Cheese Thief: The sneaky rat
        int aCheeseThief = totalStats / 3;  // Balanced attack
        int dCheeseThief = totalStats / 3 - 2; // Lower defense
        int sCheeseThief = totalStats - aCheeseThief - dCheeseThief; // Better speed
        Paw cheeseThief = new Paw("Cheese Thief", "With nimble paws and a sly grin, the Cheese Thief darts in for a surprise critical hit, leaving confusion in his wake.", aCheeseThief, dCheeseThief,  25, sCheeseThief, PawsColor.YELLOW, PawsAbility.CRITICAL, PawsRarity.COMMON);
    
        // Princess: The spoiled royal
        int aPrincess = totalStats / 3 + 1; // Better attack
        int dPrincess = totalStats / 3;     // Balanced defense
        int sPrincess = totalStats - aPrincess - dPrincess; // Lower speed
        Paw princess = new Paw("Princess", "With a tiara perched atop her head, Princess rules the battlefield with dazzling charm and status effects, though she’s not the fastest in the kingdom.", aPrincess, dPrincess,  25, sPrincess, PawsColor.RED, PawsAbility.CONFUSION, PawsRarity.COMMON);
    
        // Cookie: The shy strategist
        int aCookie = totalStats / 3 - 2;   // Lower attack
        int dCookie = totalStats / 3 + 1;   // Better defense
        int sCookie = totalStats - aCookie - dCookie; // Balanced speed
        Paw cookie = new Paw("Cookie", "This tiny mouse may seem timid, but her keen intellect allows her to turn the tide with well-placed strategies. What she lacks in strength, she makes up in planning.", aCookie, dCookie,  25, sCookie, PawsColor.GREEN, PawsAbility.STUDY, PawsRarity.COMMON);
    
        // Queen: The imposing guardian
        int aQueen = totalStats / 3;        // Balanced attack
        int dQueen = totalStats / 3 + 1;    // Better defense
        int sQueen = totalStats - aQueen - dQueen; // Lower speed
        Paw queen = new Paw("Queen", "A majestic, imposing dog with a regal air. Queen's sheer size and resilience make her a formidable defender, standing tall even when others falter.", aQueen, dQueen,  25, sQueen, PawsColor.PURPLE, PawsAbility.DEFENCE, PawsRarity.COMMON);
    
        // Shadow: The elusive fox
        int aShadow = totalStats / 3;       // Balanced attack
        int dShadow = totalStats / 3 - 1;   // Lower defense
        int sShadow = totalStats - aShadow - dShadow; // Better speed
        Paw shadow = new Paw("Shadow", "A cunning fox with a shadowy presence, blending into the night and striking from unexpected angles. Hard to pin down, even harder to catch.", aShadow, dShadow,  25, sShadow, PawsColor.BLACK, PawsAbility.EVASION, PawsRarity.UNCOMMON);
    
        // Brutus: The brawny bear
        int aBrutus = totalStats / 3 + 2;   // High attack
        int dBrutus = totalStats / 3;       // Balanced defense
        int sBrutus = totalStats - aBrutus - dBrutus; // Lower speed
        Paw brutus = new Paw("Brutus", "A lumbering bear with unmatched strength. Brutus may be slow, but when he hits, he hits like a freight train.", aBrutus, dBrutus,  25, sBrutus, PawsColor.BROWN, PawsAbility.HEAVY_HIT, PawsRarity.RARE);
    
        // Blossom: The delicate butterfly
        int aBlossom = totalStats / 3 - 1;  // Lower attack
        int dBlossom = totalStats / 3 + 1;  // Better defense
        int sBlossom = totalStats - aBlossom - dBlossom; // Balanced speed
        Paw blossom = new Paw("Blossom", "A gentle butterfly whose wings shimmer with magic. Blossom uses her grace and charm to daze and defend, floating just out of reach.", aBlossom, dBlossom,  25, sBlossom, PawsColor.PINK, PawsAbility.HEALING_WIND, PawsRarity.RARE);
    
        // Fang: The fierce wolf
        int aFang = totalStats / 3 + 2;     // High attack
        int dFang = totalStats / 3 - 1;     // Lower defense
        int sFang = totalStats - aFang - dFang; // Balanced speed
        Paw fang = new Paw("Fang", "A snarling wolf with razor-sharp teeth. Fang excels in quick, powerful strikes, but his aggression sometimes leaves him vulnerable.", aFang, dFang,  25, sFang, PawsColor.WHITE, PawsAbility.TRANSFORM, PawsRarity.UNCOMMON);
    
        // Add all paws to collection
        paws.add(speedy);
        paws.add(cheeseThief);
        paws.add(princess);
        paws.add(cookie);
        paws.add(queen);
        paws.add(shadow);
        paws.add(brutus);
        paws.add(blossom);
        paws.add(fang);
    }
    private void createPawAbilities() {
pawAbilities.clear();

        // Create the Quickness ability
        PawAbility quickness = new PawAbility("Quickness", 1, (Paw playerPaw1, Paw opponentPaw) -> {
            playerPaw1.setSpeed(playerPaw1.getSpeed() + 1);
            display("Quickness has been activated on " + playerPaw1.getName() + " and they now have increased speed.");
        updateStatsLabel();});
        // Create the Sneak ability
        PawAbility sneak = new PawAbility("Sneak", 1, (Paw playerPaw1, Paw opponentPaw) -> {
            playerPaw1.setStealth(playerPaw1.getStealth() + 1);
            display("Sneak has been activated on " + playerPaw1.getName() + " and they now have increased stealth.");
        updateStatsLabel();});
        // Create the Power ability
        PawAbility power = new PawAbility("Power", 1, (Paw playerPaw1, Paw opponentPaw) -> {
            playerPaw1.setPower(playerPaw1.getPower() + 1);
            display("Power has been activated on " + playerPaw1.getName() + " and they now have increased power.");
        updateStatsLabel();});
        // Create the Shield ability
        PawAbility shield = new PawAbility("Shield", 1, (Paw playerPaw1, Paw opponentPaw) -> {
            playerPaw1.setDefense(playerPaw1.getDefense() + 1);
            display("Shield has been activated on " + playerPaw1.getName() + " and they now have increased defense.");
        updateStatsLabel();});
        // Create the Heal ability
        PawAbility heal = new PawAbility("Heal", 1, (Paw playerPaw1, Paw opponentPaw) -> {
            playerPaw1.setHp(playerPaw1.getHp() + 1);
            display("Heal has been activated on " + playerPaw1.getName() + " and they now have increased health.");
        updateStatsLabel();});
        // Create the Confuse ability
        PawAbility confuse = new PawAbility("Confuse", 1, (Paw playerPaw1, Paw opponentPaw) -> {
            opponentPaw.setConfused(true);
            display("Confuse has been activated on " + opponentPaw.getName() + " and they are now confused.");
        updateStatsLabel();});
        // Create the Tap ability
        PawAbility pounce = new PawAbility("Pounce", 1, (Paw playerPaw1, Paw opponentPaw) -> {
            opponentPaw.setTapped(true);
            display("Pounce has been activated on " + opponentPaw.getName() + " and they are now tapped.");
        updateStatsLabel();});
        // Create the Earthquake ability
        PawAbility earthquake = new PawAbility("Earthquake", 1, (Paw playerPaw1, Paw opponentPaw) -> {
            playerPaw1.setTapped(true);
            opponentPaw.setTapped(true);
            display("Earthquake has been activated on " + playerPaw1.getName() + " and " + opponentPaw.getName() + " and they are now both tapped.");
        updateStatsLabel();});
        pawAbilities.add(quickness);
        pawAbilities.add(sneak);
        pawAbilities.add(power);
        pawAbilities.add(shield);
        pawAbilities.add(heal);
        pawAbilities.add(confuse);
        pawAbilities.add(pounce);
        pawAbilities.add(earthquake);
    }

    static void display(String string) {
        displayArea.append(string + "\n");
    }
    public void updateStatsLabel(){
        pawStatsLabel.setText("<html><b>Paw name:</b> <font color='blue'>" + playerPaw.getName() + "</font>" +
    " || <b>Attack:</b> <font color='red'>" + playerPaw.getAttack() + "</font>" +
    " || <b>Defense:</b> <font color='green'>" + playerPaw.getDefense() + "</font>" +
    " || <b>HP:</b> <font color='purple'>" + playerPaw.getHp() + "</font>" +
    " || <b>Speed:</b> <font color='orange'>" + playerPaw.getSpeed() + "</font>" +
    " || <b>Energy:</b> <font color='blue'>" + playerPaw.getEnergy() + "</font>" +
    " || <b>Stealth:</b> <font color='gray'>" + playerPaw.getStealth() + "</font>" +
    " || <b>Power:</b> <font color='gold'>" + playerPaw.getPower() + "</font>" +
    " || <b>Tapped:</b> <font color='brown'>" + playerPaw.isTapped() + "</font>" +
    " || <b>Confused:</b> <font color='pink'>" + playerPaw.isConfused() + "</font></html>");

    }
    public static void drawCard() {
        if (Player.getPawDeck().isEmpty()) {
            // Reshuffle the deck
            Player.setPawDeck(shuffleDeck());
        }
        if (Player.getHand().size() >= 5) {
            display("You have too many cards in your hand");
            String[] options = {"Discard a card", "Keep your hand"};
            int choice = JOptionPane.showOptionDialog(null, "You have too many cards in your hand",
                    "Choose an option", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                    null, options, options[0]);

            if (choice == 0) {
                playerPaw.setEnergy(playerPaw.getEnergy() - 2);
                display("Which card would you like to discard?");
                String[] cardDraw = new String[Player.getHand().size()];
                for (int i = 0; i < Player.getHand().size(); i++) {
                    cardDraw[i] = Player.getHand().get(i).getName();
                }
                int cardChoice = JOptionPane.showOptionDialog(null,
                        "Which card would you like to discard?",
                        "Choose a card", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE, null, cardDraw, cardDraw[0]);

                // Discard the chosen card
                Card discardedCard = Player.getHand().remove(cardChoice);
                display("You discarded " + discardedCard.getName());

                // Draw a new card
                Random random = new Random();
                int index = random.nextInt(Player.getPawDeck().size());
                Card drawnCard = Player.getPawDeck().get(index);
                Player.getHand().add(drawnCard);
                Player.getPawDeck().remove(index);
                display("You drew " + drawnCard.getName());
            }
            return;
        }

        // If hand size is fine, just draw a card
        if (Player.getPawDeck().isEmpty()) {
            Player.setPawDeck(shuffleDeck());
        }
        Random random = new Random();
        int index = random.nextInt(Player.getPawDeck().size());
        Card drawnCard = Player.getPawDeck().get(index);
        Player.getHand().add(drawnCard);
        Player.getPawDeck().remove(index);

        display("You drew a card: " + drawnCard.getName());
        display("You have " + Player.getHand().size() + " cards in your hand");
    }

    public static void playCard(Paw playerPaw, Paw opponentPaw) {
        if (Player.getHand().isEmpty()) {
            display("You have no cards to play.");
            return;
        }
        // Present card names as options for the player to choose
        String[] cardNames = new String[Player.getHand().size()];
        for (int i = 0; i < Player.getHand().size(); i++) {
            cardNames[i] = Player.getHand().get(i).getName();
        }
    
        // Show a dialog to select which card to play
        String choice = (String) JOptionPane.showInputDialog(null,
                "Which card would you like to play?",
                "Play Card", JOptionPane.QUESTION_MESSAGE, null, cardNames, cardNames[0]);
    
        if (choice != null) {
            for (Card card : Player.getHand()) {
                if (card.getName().equals(choice)) {
                    card.play(playerPaw, opponentPaw);  // Pass player and opponent paws to the card's action
                    Player.getHand().remove(card);
                    display("You played " + card.getName());
                    break;
                }
            }
        }
    }

    public void openFigure() {
        PawFigure pawFigure1 = Player.getPawFigure();
        pawFigure1.generateRandomPaw();
    }

    private void pickLibarary() {
        if(paws.isEmpty()){
            paws.clear();
            loadPaws();
        } else {
            display("Paws already created.");
        }   

    }

    private int endGame() {
        PawsAndProwess.getPaws().addAll(Player.getPaws());
        savePaws();
        return DISPOSE_ON_CLOSE;
    }
    
}
