package Redux2;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class GUI extends JFrame {

    private static JTextField jTextField;

    public static JTextField getJTextField() {
        return jTextField;
    }

    public static JTextField getjTextField() {
        return jTextField;
    }

    public static void setjTextField(JTextField jTextField) {
        GUI.jTextField = jTextField;
    }
    private final JTextPane jTextPane;
    private JPanel statsPanel;
    private final JPanel inputPanel;
    private final JPanel btnPanel;
    private final JLabel statsLabel;
    private final Color periwinkle = new Color(204, 204, 255);

    private Color lightBlue;

    private Color lightPink;

    private final JButton takeButton, moveButton, dialogButton, learnButton, inventoryButton, carebutton, socializeButton, mischiefButton;
    private boolean locked;
    private JPanel npcPanel, itemPanel, extraPanel;
    private JPanel panelContainer;

    public GUI() {
        setTitle("Busy Beavers by Jackal Face games");
        setSize(1600, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        statsPanel = new JPanel();
        statsPanel.setBackground(periwinkle);
        statsPanel.setPreferredSize(new Dimension(1200, 35));
        statsLabel = new JLabel();
        statsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        statsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statsLabel.setForeground(Color.BLACK);
        statsPanel.add(statsLabel);
        add(statsPanel, BorderLayout.NORTH);

        npcPanel = new JPanel();
        npcPanel.setBackground(periwinkle);
        npcPanel.setLayout(new BoxLayout(npcPanel, BoxLayout.Y_AXIS));
        npcPanel.setBorder(BorderFactory.createLineBorder(Color.PINK, 5));
        npcPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the panel
        npcPanel.setPreferredSize(new Dimension(100, 1));

        // Add NPCs label
        JLabel npcsLabel = new JLabel("NPCs:", JLabel.CENTER); // Center the label text
        npcsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        npcsLabel.setOpaque(true);
        npcsLabel.setBackground(periwinkle);
        npcsLabel.setBorder(BorderFactory.createLineBorder(Color.PINK, 5)); // Thicker border
        npcsLabel.setPreferredSize(new Dimension(100, 1)); // Adjust size if needed
        npcPanel.add(npcsLabel);

        itemPanel = new JPanel();
        itemPanel.setBackground(periwinkle);
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
        itemPanel.setBorder(BorderFactory.createLineBorder(Color.PINK, 5));
        itemPanel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the panel
        itemPanel.setPreferredSize(new Dimension(100, 1));

        // Add ITEMS label
        JLabel itemsLabel = new JLabel("ITEMS:", JLabel.CENTER); // Center the label text
        itemsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        itemsLabel.setOpaque(true);
        itemsLabel.setBackground(periwinkle);
        itemsLabel.setBorder(BorderFactory.createLineBorder(Color.PINK, 5)); // Thicker border
        itemsLabel.setPreferredSize(new Dimension(100, 1)); // Adjust size if needed
        itemPanel.add(itemsLabel);

        extraPanel = new JPanel();
        extraPanel.setBackground(periwinkle);
        extraPanel.setLayout(new BoxLayout(extraPanel, BoxLayout.Y_AXIS));

        // Add panels to the main container
        panelContainer = new JPanel();
        panelContainer.setLayout(new BoxLayout(panelContainer, BoxLayout.Y_AXIS));
        panelContainer.add(npcPanel);
        panelContainer.add(extraPanel);
        panelContainer.add(itemPanel);

        add(panelContainer, BorderLayout.WEST);

        // Initialize buttonPanel
        btnPanel = new JPanel();
        btnPanel.setBackground(periwinkle);
        add(btnPanel, BorderLayout.SOUTH);

        // Initialize inputPanel
        inputPanel = new JPanel();
        inputPanel.setBackground(periwinkle);

        // Initialize jTextPane
        jTextPane = new JTextPane();
        jTextPane.setContentType("text/html");
        jTextPane.setEditable(false);
        jTextPane.setFont(new Font("Monospaced", Font.PLAIN, 24));
        jTextPane.setBackground(lightBlue);
        jTextPane.setForeground(lightPink);
        jTextPane.addHyperlinkListener((HyperlinkEvent e) -> {
            if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                if (e.getURL() != null) {
                    String filePath = e.getURL().getPath();
                    filePath = filePath.substring(0, filePath.indexOf(".txt"));
                    String content = GameHandler.readFile(filePath);
                    showPopupWithContent(content);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(jTextPane);
        scrollPane.setPreferredSize(new Dimension(700, 400));
        add(scrollPane, BorderLayout.CENTER);
        jTextPane.setEditable(false);

        // Initialize jTextField
        jTextField = new JTextField(20);
        jTextField.setFont(new Font("Arial", Font.PLAIN, 16));

        // Create buttons
        takeButton = new JButton("Take");
        takeButton.setFont(new Font("Arial", Font.BOLD, 16));
        moveButton = new JButton("Move");
        moveButton.setFont(new Font("Arial", Font.BOLD, 16));
        dialogButton = new JButton("Dialog");
        dialogButton.setFont(new Font("Arial", Font.BOLD, 16));
        learnButton = new JButton("Learn");
        learnButton.setFont(new Font("Arial", Font.BOLD, 16));
        inventoryButton = new JButton("Inventory");
        inventoryButton.setFont(new Font("Arial", Font.BOLD, 16));
        carebutton = new JButton("Care");
        carebutton.setFont(new Font("Arial", Font.BOLD, 16));
        socializeButton = new JButton("Socialize");
        socializeButton.setFont(new Font("Arial", Font.BOLD, 16));
        mischiefButton = new JButton("Mischief");
        mischiefButton.setFont(new Font("Arial", Font.BOLD, 16));

        // Set button size
        Dimension buttonSize = new Dimension(100, 50);
        moveButton.setPreferredSize(buttonSize);
        dialogButton.setPreferredSize(buttonSize);
        learnButton.setPreferredSize(buttonSize);
        inventoryButton.setPreferredSize(buttonSize);
        carebutton.setPreferredSize(buttonSize);
        socializeButton.setPreferredSize(buttonSize);
        takeButton.setPreferredSize(buttonSize);
        mischiefButton.setPreferredSize(buttonSize);

        // Add buttons to panel
        btnPanel.add(mischiefButton);
        btnPanel.add(socializeButton);
        btnPanel.add(moveButton);
        btnPanel.add(dialogButton);
        btnPanel.add(jTextField);
        btnPanel.add(learnButton);
        btnPanel.add(inventoryButton);
        btnPanel.add(carebutton);
        btnPanel.add(takeButton);

        // Add borders to components
        statsPanel.setBorder(new LineBorder(Color.BLACK, 2));
        inputPanel.setBorder(new LineBorder(Color.BLACK, 2));
        jTextPane.setBorder(new LineBorder(Color.BLACK, 2));
        jTextField.setBorder(new LineBorder(Color.BLACK, 2));
        moveButton.setBorder(new LineBorder(Color.BLACK, 2));
        dialogButton.setBorder(new LineBorder(Color.BLACK, 2));
        learnButton.setBorder(new LineBorder(Color.BLACK, 2));
        inventoryButton.setBorder(new LineBorder(Color.BLACK, 2));
        carebutton.setBorder(new LineBorder(Color.BLACK, 2));
        socializeButton.setBorder(new LineBorder(Color.BLACK, 2));
        takeButton.setBorder(new LineBorder(Color.BLACK, 2));
        mischiefButton.setBorder(new LineBorder(Color.BLACK, 2));

        // Add action listeners to buttons
        moveButton.addActionListener(e -> {
            if (!locked) {
                synchronized (this) {
                    notify();
                    String[] exits = Player.room.getExits();
                    for (int i = 0; i < exits.length; i++) {
                        exits[i] = exits[i].replace("_", " ");

                    }

                    String selectedExit = (String) JOptionPane.showInputDialog(null,
                            "Choose an exit",
                            "Exits",
                            JOptionPane.QUESTION_MESSAGE,
                            null, exits, exits[0]);

                    if (selectedExit != null) {
                        Room tempRoom = Player.getRoom();
                        GameHandler.getGui().display("You move to the " + selectedExit + ".", "Black");
                        Player.setRoom(GameHandler.getRoomByName(selectedExit.replace(" ", "_")));
                        for (NPC npc : tempRoom.getNPCs()) {
                            npc.setSuspicion(0);
                            if (npc.isFollower()) {
                                npc.setRoom(Player.getRoom());
                            }
                        }
                    } else {
                        notify();
                    }
                }
            }
            updateSidePanels();
        });
        dialogButton.addActionListener(e -> {
            if (!locked) {
                synchronized (this) {
                    notify();
                    if (Player.room.getNPCs().isEmpty()) {
                        GameHandler.getGui().display("There is no one to talk to.", "Black");
                        return;
                    }
                    String[] npcs = Player.room.getNPCChoises();
                    for (int i = 0; i < npcs.length; i++) {
                        npcs[i] = npcs[i].replace("_", " ");
                    }
                    String selectedNPC = (String) JOptionPane.showInputDialog(
                            null,
                            "Choose an NPC",
                            "Available NPCs",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            npcs,
                            npcs[0]);

                    if (selectedNPC != null) {
                        GameHandler.getGui().display("You talk to the " + selectedNPC + ".", "Black");
                        NPC npc = GameHandler.getNPCByName(selectedNPC.replace(" ", "_"));
                        GameHandler.getGui().display(npc.getDialog(), "Black");
                        String[] options = {"Persuade", "Ask a question.", "Make a statement", "Greetings"};
                        int selectedOption = JOptionPane.showOptionDialog(null,
                                "What do you want to say?",
                                "Dialog",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);
                        switch (selectedOption) {
                            case 0 -> {
                                String persuasions[] = {"Cry", "Pout", "Silly", "Mediate"};
                                String selectedPersuasion = (String) JOptionPane.showInputDialog(
                                        null,
                                        "How do you persuasde ? " + npc.getName(),
                                        "Dialog",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        persuasions,
                                        persuasions[0]);
                                switch (selectedPersuasion) {
                                    case "Cry" -> {
                                        GameHandler.getGui().display(npc.getResponse("persuasion", "Cry"), "Black");

                                    }
                                    case "Pout" -> {
                                        GameHandler.getGui().display(npc.getResponse("persuasion", "Pout"), "Black");
                                    }
                                    case "Silly" -> {
                                        GameHandler.getGui().display(npc.getResponse("persuasion", "Silly"), "Black");
                                    }
                                    case "Mediate" -> {
                                        GameHandler.getGui().display(npc.getResponse("persuasion", "Mediate"), "Black");
                                    }
                                }

                            }
                            case 1 -> {
                                String questions[] = {"What is your name?", "How old are you?", "What do you like?", "What do you dislike?"};
                                String selectedQuestion = (String) JOptionPane.showInputDialog(
                                        null,
                                        "What do you want to ask?",
                                        "Dialog",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        questions,
                                        questions[0]);
                                switch (selectedQuestion) {
                                    case "What is your name?" -> {
                                        GameHandler.getGui().display(npc.getResponse("question", "name"), "Black");
                                    }
                                    case "How old are you?" -> {
                                        GameHandler.getGui().display(npc.getResponse("question", "age"), "Black");
                                    }
                                    case "What do you like?" -> {
                                        GameHandler.getGui().display(npc.getResponse("question", "like"), "Black");
                                    }
                                    case "What do you dislike?" -> {
                                        GameHandler.getGui().display(npc.getResponse("question", "dislike"), "Black");
                                    }
                                }
                            }
                            case 2 -> {
                                String statements[] = {"I like you", "I don't like you", "You are nice", "You are mean"};
                                String selectedStatement = (String) JOptionPane.showInputDialog(
                                        null,
                                        "What do you want to say?",
                                        "Dialog",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        statements,
                                        statements[0]);
                                switch (selectedStatement) {
                                    case "I like you" -> {
                                        GameHandler.getGui().display(npc.getResponse("statement", "like"), "Black");
                                    }
                                    case "I don't like you" -> {
                                        GameHandler.getGui().display(npc.getResponse("statement", "dislike"), "Black");
                                    }
                                    case "You are nice" -> {
                                        GameHandler.getGui().display(npc.getResponse("statement", "nice"), "Black");
                                    }
                                    case "You are mean" -> {
                                        GameHandler.getGui().display(npc.getResponse("statement", "mean"), "Black");
                                    }
                                }
                            }
                            case 3 -> {
                                String greetings[] = {"Hello", "Goodbye", "Good Morning", "Good Night"};
                                String selectedGreeting = (String) JOptionPane.showInputDialog(
                                        null,
                                        "What do you want to say?",
                                        "Dialog",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        greetings,
                                        greetings[0]);
                                switch (selectedGreeting) {
                                    case "Hello" -> {
                                        GameHandler.getGui().display(npc.getResponse("greeting", "hello"), "Black");
                                    }
                                    case "Goodbye" -> {
                                        GameHandler.getGui().display(npc.getResponse("greeting", "goodbye"), "Black");
                                    }
                                    case "Good Morning" -> {
                                        GameHandler.getGui().display(npc.getResponse("greeting", "morning"), "Black");
                                    }
                                    case "Good Night" -> {
                                        GameHandler.getGui().display(npc.getResponse("greeting", "night"), "Black");
                                    }
                                }
                            }
                        }

                    } else {
                        notify();
                    }
                }
            }
            updateSidePanels();
        });
        learnButton.addActionListener(e -> {
            if (!locked) {
                synchronized (this) {
                    notify();
                    String[] topics = {"Story Time", "Arts & Crafts", "Educational Games", "Language", "Puzzles"};
                    String selectedTopic = (String) JOptionPane.showInputDialog(
                            null,
                            "What do you want to study?",
                            "Learning",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            topics,
                            topics[0]);
                    if (selectedTopic != null) {
                        switch (selectedTopic) {
                            case "Story Time" -> {
                                GameHandler.storyTime();
                            }
                            case "Arts & Crafts" -> {
                                GameHandler.getGui().display("You do arts & crafts", "Black");
                                GameHandler.artsCrafts();
                            }
                            case "Educational Games" -> {
                                GameHandler.getGui().display("You play educational games", "Black");
                                GameHandler.educationalGames();
                            }
                            case "Language" -> {
                                GameHandler.getGui().display("You study language", "Black");
                                GameHandler.language();
                            }
                            case "Puzzles" -> {
                                GameHandler.getGui().display("You solve puzzles", "Black");
                                GameHandler.puzzles();
                            }
                        }
                    } else {
                        notify();
                    }
                }
            }
            updateSidePanels();
        });
        inventoryButton.addActionListener(e -> {
            if (!locked) {
                synchronized (this) {
                    notify();
                    String[] options = {"Use", "Drop", "Throw Away", "Put up", "Give"};
                    String[] inventory = Player.getItemChoises();
                    if (inventory.length == 0) {
                        GameHandler.getGui().display("You have nothing in your pockets", "Black");
                        return;
                    }
                    String selectedItem = (String) JOptionPane.showInputDialog(
                            null,
                            "What's in the bag?",
                            "Inventory",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            inventory,
                            inventory[0]);
                    if (selectedItem != null && !selectedItem.equals("Nothing")) {
                        int action = JOptionPane.showOptionDialog(null,
                                "What do you want to do with the " + selectedItem + "?",
                                "Inventory",
                                JOptionPane.DEFAULT_OPTION,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);
                        switch (action) {
                            case 0 -> {
                                notify();
                                Item item = GameHandler.getItemByName(selectedItem);
                                item.use();
                            }
                            case 1 -> {
                                notify();
                                Item item = GameHandler.getItemByName(selectedItem);
                                Player.removeItem(item);
                                Player.getRoom().addItem(item);
                                GameHandler.getGui().display("You drop the " + selectedItem + ".", "Black");
                            }
                            case 2 -> {
                                notify();
                                Item item = GameHandler.getItemByName(selectedItem);
                                Player.removeItem(item);
                                GameHandler.getGui().display("You throw away the " + selectedItem + ".", "Black");
                            }
                            case 3 -> {
                                notify();
                                Item[] containers = Player.getRoom().getContainers();
                                String[] containerNames = new String[containers.length];
                                if (containerNames.length == 0) {
                                    GameHandler.getGui().display("There are no containers in this room.", "Black");
                                    return;

                                }
                                for (int i = 0; i < containers.length; i++) {
                                    containerNames[i] = containers[i].getName();
                                }
                                String selectedContainer = (String) JOptionPane.showInputDialog(
                                        null,
                                        "Where do you want to put the " + selectedItem + "?",
                                        "Containers",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        containerNames,
                                        containerNames[0]);
                                if (selectedContainer != null) {
                                    for (Item container : containers) {
                                        if (container.getName().equals(selectedContainer)) {
                                            container.addItem(GameHandler.getItemByName(selectedItem));
                                            GameHandler.getGui().display("You put the " + selectedItem + " in the " + selectedContainer + ".", "Black");
                                        }
                                    }
                                }
                                Player.removeItem(GameHandler.getItemByName(selectedItem));
                                GameHandler.getGui().display("You put up the " + selectedItem + ".", "Black");
                            }
                            case 4 -> {
                                notify();
                                String[] npcs = Player.room.getNPCChoises();
                                for (int i = 0; i < npcs.length; i++) {
                                    npcs[i] = npcs[i].replace("_", " ");
                                }
                                String selectedNPC = (String) JOptionPane.showInputDialog(
                                        null,
                                        "Who do you want to give the " + selectedItem + " to?",
                                        "Available NPCs",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        npcs,
                                        npcs[0]);
                                if (selectedNPC != null && !selectedNPC.equals("Nobody")) {
                                    NPC npc = GameHandler.getNPCByName(selectedNPC.replace(" ", "_"));
                                    Item item = GameHandler.getItemByName(selectedItem);
                                    npc.reciveItem(item);
                                } else {
                                    notify();
                                }
                            }
                            default -> {
                                notify();
                                GameHandler.getGui().display("You have nothing in your pockets", "Black");
                            }
                        }
                    } else {
                        notify();
                    }
                }
            }
            updateSidePanels();
        });
        carebutton.addActionListener(e -> {
            if (!locked) {
                synchronized (this) {
                    notify();
                    String[] care = {"Nap", "Potty", "Tantrum", "Eat/Drink", "Reflect"};
                    String selectedCare = (String) JOptionPane.showInputDialog(
                            null,
                            "What do you want to do?",
                            "Care",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            care,
                            care[0]
                    );
                    if (selectedCare != null) {
                        switch (selectedCare) {
                            case "Nap" -> {
                                Player.nap();
                            }
                            case "Potty" -> {
                                Player.potty();
                            }
                            case "Tantrum" -> {
                                GameHandler.getGui().display("You calm the beaver down", "Black");
                                Player.tantrum();
                            }
                            case "Eat/Drink" -> {
                                GameHandler.getGui().display("You feed the beaver", "Black");
                                Player.eatDrink();
                            }
                            case "Reflect" -> {
                                GameHandler.getGui().display("You reflect on the beaver's behavior", "Black");
                                Player.reflect();
                            }
                        }
                    } else {
                        notify();
                    }
                }
            }
            updateSidePanels();
        });
        socializeButton.addActionListener(e -> {
            if (!locked) {
                synchronized (this) {
                    notify();
                    String[] socialize = {"Play", "Help", "Lead", "Pretend", "Dance"};
                    String selectedSocialize = (String) JOptionPane.showInputDialog(
                            null,
                            "What do you want to do?",
                            "Socialize",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            socialize,
                            socialize[0]
                    );
                    if (selectedSocialize != null) {
                        switch (selectedSocialize) {
                            case "Play" -> {
                                String[] npcChoises = Player.room.getNPCChoises();
                                if (npcChoises.length == 0) {
                                    GameHandler.getGui().display("There is no one to play with", "Black");
                                    return;
                                }
                                for (int i = 0; i < npcChoises.length; i++) {
                                    npcChoises[i] = npcChoises[i].replace("_", " ");
                                }
                                String selectedNPC = (String) JOptionPane.showInputDialog(
                                        null,
                                        "Who do you want to play with?",
                                        "Play",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        npcChoises,
                                        npcChoises[0]);
                                if (selectedNPC != null) {
                                    String[] toyChoises = Player.room.getToyChoices();
                                    if (toyChoises.length == 0) {
                                        GameHandler.getGui().display("There are no toys to play with", "Black");
                                        Player.getRoom().play(selectedNPC, null);
                                    } else {
                                        for (int i = 0; i < toyChoises.length; i++) {
                                            toyChoises[i] = toyChoises[i].replace("_", " ");
                                        }
                                        String selectedToy = (String) JOptionPane.showInputDialog(
                                                null,
                                                "What do you want to play with?",
                                                "Play",
                                                JOptionPane.QUESTION_MESSAGE,
                                                null,
                                                toyChoises,
                                                toyChoises[0]);
                                        if (selectedToy != null) {
                                            Player.getRoom().play(selectedNPC, selectedToy);
                                        } else {
                                            notify();
                                        }
                                    }
                                } else {
                                    notify();
                                }
                            }
                            case "Help" -> {
                                for (NPC npc : Player.room.getNPCs()) {
                                    if (npc.getQuest() != null) {
                                        GameHandler.getGui().display("You help " + npc.getName() + " with their quest", "Black");
                                        npc.getQuest().getDescription();
                                    } else {
                                        GameHandler.getGui().display("There is no one to help", "Black");
                                    }
                                }
                            }
                            case "Lead" -> {
                                String[] choises = Player.room.getNPCChoises();
                                if (choises.length == 0) {
                                    GameHandler.getGui().display("There is no one to lead", "Black");
                                    return;
                                } else {
                                    for (int i = 0; i < choises.length; i++) {
                                        choises[i] = choises[i].replace("_", " ");
                                    }
                                    String selectedLead = (String) JOptionPane.showInputDialog(
                                            null,
                                            "Who do you want to lead?",
                                            "Lead",
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            choises,
                                            choises[0]);
                                    NPC npc = GameHandler.getNPCByName(selectedLead.replace(" ", "_"));
                                    if (!selectedLead.equals("Nobody") && npc.getAge() < 5) {
                                        if (npc.isFollower()) {
                                            GameHandler.getGui().display("You stop leading " + selectedLead, "Black");
                                            npc.follower = false;
                                        } else {
                                            GameHandler.getGui().display("You ask " + selectedLead + "to follow you. ", "Black");
                                            NPC.followPlayer(GameHandler.getNPCByName(selectedLead.replace(" ", "_")));

                                        }
                                    } else {
                                        notify();
                                    }
                                }
                            }
                            case "Pretend" -> {
                                String[] pretendChoices = {"Pirate", "Princess", "Superhero", "Villain"};
                                String selectedPretend = (String) JOptionPane.showInputDialog(
                                        null,
                                        "What do you want to pretend to be?",
                                        "Pretend",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        pretendChoices,
                                        pretendChoices[0]);
                                switch (selectedPretend) {
                                    case "Pirate" -> {
                                        GameHandler.getGui().display("You pretend to be a pirate", "Black");
                                    }
                                    case "Princess" -> {
                                        GameHandler.getGui().display("You pretend to be a princess", "Black");
                                    }
                                    case "Superhero" -> {
                                        GameHandler.getGui().display("You pretend to be a superhero", "Black");
                                    }
                                    case "Villain" -> {
                                        GameHandler.getGui().display("You pretend to be a villain", "Black");
                                    }
                                }
                            }

                            case "Dance" -> {
                                String[] danceChoices = {"Ballet", "Breakdance", "Hip Hop", "Tap"};
                                String selectedDance = (String) JOptionPane.showInputDialog(
                                        null,
                                        "What do you want to dance?",
                                        "Dance",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        danceChoices,
                                        danceChoices[0]);
                                switch (selectedDance) {
                                    case "Ballet" -> {
                                        GameHandler.getGui().display("You dance ballet", "Black");
                                    }
                                    case "Breakdance" -> {
                                        GameHandler.getGui().display("You breakdance", "Black");
                                    }
                                    case "Hip Hop" -> {
                                        GameHandler.getGui().display("You dance hip hop", "Black");
                                    }
                                    case "Tap" -> {
                                        GameHandler.getGui().display("You tap dance", "Black");
                                    }
                                }

                            }

                        }
                    } else {
                        notify();
                    }
                }
            }
            updateSidePanels();
        });

        takeButton.addActionListener(e -> {
            if (!locked) {
                synchronized (this) {
                    notify();
                    if (Player.room.getArrayInventory().isEmpty()) {
                        GameHandler.getGui().display("There is nothing to take", "Black");
                        return;
                    }
                    String[] items = Player.room.getItemChoises();
                    for (int i = 0; i < items.length; i++) {
                        items[i] = items[i].replace("_", " ");
                    }
                    String selectedItem = (String) JOptionPane.showInputDialog(
                            null,
                            "What do you want to take?",
                            "Items",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            items,
                            items[0]);
                    if (selectedItem != null) {
                        Item item = GameHandler.getItemByName(selectedItem);
                        Player.addItem(item);
                        GameHandler.getGui().display("You take the " + selectedItem + ".", "Black");
                        Player.getRoom().removeItem(item);
                    } else {
                        notify();
                    }
                }
            }
            updateSidePanels();
        });
        mischiefButton.addActionListener(e -> {
            if (!locked) {
                synchronized (this) {
                    notify();
                    String[] mischief = {"Sneak", "Prank", "Steal", "Sabotage", "Vandalize"};
                    String selectedMischief = (String) JOptionPane.showInputDialog(
                            null,
                            "What do you want to do?",
                            "Mischief",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            mischief,
                            mischief[0]
                    );
                    if (selectedMischief != null) {
                        switch (selectedMischief) {
                            case "Sneak" -> {
                                Player.sneak();
                            }
                            case "Prank" -> {
                                String[] choises = Player.room.getNPCChoises();
                                if (choises.length == 0) {
                                    GameHandler.getGui().display("There is no one to prank", "Black");
                                    return;
                                }
                                for (int i = 0; i < choises.length; i++) {
                                    choises[i] = choises[i].replace("_", " ");
                                }
                                String selectedPrank = (String) JOptionPane.showInputDialog(
                                        null,
                                        "Who do you want to prank?",
                                        "Prank",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        choises,
                                        choises[0]);
                                if (selectedPrank != null) {
                                    GameHandler.getGui().display("You prank " + selectedPrank, "Black");
                                    GameHandler.getNPCByName(selectedPrank.replace(" ", "_")).getPranked();
                                } else {
                                    display("No one here to prank.", "Black");
                                    notify();
                                }
                            }
                            case "Steal" -> {
                                String[] choises = Player.room.getContraband();
                                if (choises.length == 0) {
                                    GameHandler.getGui().display("There is nothing to steal", "Black");
                                    return;
                                }
                                String selectedSteal = (String) JOptionPane.showInputDialog(
                                        null,
                                        "What do you want to steal?",
                                        "Steal",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        choises,
                                        choises[0]);
                                if (selectedSteal != null) {
                                    GameHandler.getGui().display("You steal something", "Black");
                                    Player.getRoom().steal(selectedSteal);
                                } else {
                                    notify();
                                }
                            }
                            case "Sabotage" -> {
                                String[] choises = Player.room.getFurniture();
                                if (choises.length == 0) {
                                    GameHandler.getGui().display("There is nothing to sabotage", "Black");
                                    return;
                                }
                                String selectedSabotage = (String) JOptionPane.showInputDialog(
                                        null,
                                        "What do you want to sabotage?",
                                        "Sabotage",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        choises,
                                        choises[0]);
                                Item item = GameHandler.getItemByName(selectedSabotage);
                                if (item != null) {
                                    GameHandler.getGui().display("You sabotage something", "Black");
                                    Player.getRoom().sabotage(item);
                                } else {
                                    notify();
                                }
                            }
                            case "Vandalize" -> {
                                String[] choises = Player.room.getFurniture();
                                if (choises.length == 0) {
                                    GameHandler.getGui().display("There is nothing to vandalize", "Black");
                                    return;
                                }
                                String selectedVandalize = (String) JOptionPane.showInputDialog(
                                        null,
                                        "What do you want to vandalize?",
                                        "Vandalize",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        choises,
                                        choises[0]);
                                Item item = GameHandler.getItemByName(selectedVandalize);
                                if (item != null) {
                                    GameHandler.getGui().display("You vandalize something", "Black");
                                    Player.getRoom().vandalize(item);
                                } else {
                                    notify();
                                }
                            }
                        }
                    } else {
                        notify();
                    }
                }
            }
            updateSidePanels();
        });
        jTextField.addActionListener(e -> {
            synchronized (this) {
                notify();
                if (!locked) {
                    Commands.execute(jTextField.getText());
                } else {
                    notify();
                }
            }
            updateSidePanels();
        });

        setVisible(true);
    }

    public void display(String message, String color) {
        HTMLEditorKit editorKit = (HTMLEditorKit) jTextPane.getEditorKit();
        HTMLDocument doc = (HTMLDocument) jTextPane.getDocument();
        try {
            editorKit.insertHTML(doc, doc.getLength(), "<p>" + message + "</p>", 0, 0, null);
        } catch (BadLocationException | IOException ex) {
        }
        jTextPane.setCaretPosition(doc.getLength());
        GameHandler.updateStatus();
        updateSidePanels();
    }

    public String getInput() {
        return jTextField.getText();

    }

    public void waitForInput() { //waits for the user to input something
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }

    }

    public JLabel getStatsLabel() {
        return statsLabel;
    }

    public JTextPane getjTextPane() {
        return jTextPane;
    }

    public JPanel getStatsPanel() {
        return statsPanel;
    }

    public void setStatsPanel(JPanel statsPanel) {
        this.statsPanel = statsPanel;
    }

    public JPanel getInputPanel() {
        return inputPanel;
    }

    public JPanel getBtnPanel() {
        return btnPanel;
    }

    public Color getPeriwinkle() {
        return periwinkle;
    }

    public Color getLightBlue() {
        return lightBlue;
    }

    public void setLightBlue(Color lightBlue) {
        this.lightBlue = lightBlue;
    }

    public Color getLightPink() {
        return lightPink;
    }

    public void setLightPink(Color lightPink) {
        this.lightPink = lightPink;
    }

    public JButton getTakeButton() {
        return takeButton;
    }

    public JButton getMoveButton() {
        return moveButton;
    }

    public JButton getDialogButton() {
        return dialogButton;
    }

    public JButton getLearnButton() {
        return learnButton;
    }

    public JButton getInventoryButton() {
        return inventoryButton;
    }

    public JButton getCarebutton() {
        return carebutton;
    }

    public JButton getSocializeButton() {
        return socializeButton;
    }

    public JButton getMischiefButton() {
        return mischiefButton;
    }

    public void unlockButtons() {
        locked = false;
    }

    public void lockButtons() {
        locked = true;
    }

    public void clearTextPane() {
        jTextPane.setText("");
    }

    public void updateSidePanels() {
        npcPanel.removeAll();
        itemPanel.removeAll();
        extraPanel.removeAll();

        // Set GridBagLayout for npcPanel and itemPanel
        npcPanel.setLayout(new GridBagLayout());
        itemPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH; // Anchor to the top
        gbc.insets = new Insets(0, 0, 0, 0); // Adjust insets to reduce spacing
        gbc.weighty = 0.0; // No extra vertical space

        // Add ITEMS label
        JLabel itemsLabel = new JLabel("ITEMS:", JLabel.CENTER);
        itemsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        itemsLabel.setOpaque(true);
        itemsLabel.setBackground(periwinkle);
        itemsLabel.setBorder(BorderFactory.createLineBorder(Color.PINK, 5));
        itemsLabel.setPreferredSize(new Dimension(200, 1));
        itemPanel.add(itemsLabel, gbc);

        // Add Items to itemPanel
        for (Item item : Player.room.getArrayInventory()) {
            if (item != null) {
                JLabel itemLabel = new JLabel(item.getName(), JLabel.CENTER);
                itemLabel.setFont(new Font("Arial", Font.BOLD, 16));
                itemLabel.setOpaque(true);
                itemLabel.setBackground(periwinkle);
                itemLabel.setBorder(BorderFactory.createLineBorder(Color.PINK, 5));
                itemLabel.setPreferredSize(new Dimension(200, 30));
                itemLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                    @Override
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        GameHandler.getGui().display(item.getDescription(), "Black");
                    }
                });
                itemPanel.add(itemLabel, gbc);
            }
        }

        // Add NPCs label
        JLabel npcsLabel = new JLabel("NPCs:", JLabel.CENTER);
        npcsLabel.setFont(new Font("Arial", Font.BOLD, 16));
        npcsLabel.setOpaque(true);
        npcsLabel.setBackground(periwinkle);
        npcsLabel.setBorder(BorderFactory.createLineBorder(Color.PINK, 5));
        npcsLabel.setPreferredSize(new Dimension(200, 30));
        npcPanel.add(npcsLabel, gbc);

        // Add NPCs to npcPanel
        for (NPC npc : Player.room.getNPCs()) {
            JLabel npcLabel = new JLabel(npc.getName(), JLabel.CENTER);
            npcLabel.setFont(new Font("Arial", Font.BOLD, 16));
            npcLabel.setOpaque(true);
            npcLabel.setBackground(periwinkle);
            npcLabel.setBorder(BorderFactory.createLineBorder(Color.PINK, 5));
            npcLabel.setPreferredSize(new Dimension(200, 30));
            npcLabel.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    GameHandler.getGui().display(npc.getDialog(), "Black");
                }
            });
            npcPanel.add(npcLabel, gbc);
        }
        npcPanel.revalidate();
        npcPanel.repaint();
        itemPanel.revalidate();
        itemPanel.repaint();
        extraPanel.revalidate();
        extraPanel.repaint();
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public JPanel getNpcPanel() {
        return npcPanel;
    }

    public void setNpcPanel(JPanel npcPanel) {
        this.npcPanel = npcPanel;
    }

    public JPanel getItemPanel() {
        return itemPanel;
    }

    public void setItemPanel(JPanel itemPanel) {
        this.itemPanel = itemPanel;
    }

    public JPanel getExtraPanel() {
        return extraPanel;
    }

    public void setExtraPanel(JPanel extraPanel) {
        this.extraPanel = extraPanel;
    }

    public JPanel getPanelContainer() {
        return panelContainer;
    }

    public void setPanelContainer(JPanel panelContainer) {
        this.panelContainer = panelContainer;
    }

    private void showPopupWithContent(String content) {
        JTextPane textPane = new JTextPane();
        textPane.setContentType("text/html");
        textPane.setText(content);
        textPane.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textPane);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JDialog dialog = new JDialog((Frame) null, "File Content", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.add(scrollPane, BorderLayout.CENTER);
        dialog.pack();
        dialog.setLocationRelativeTo(null); // Center the dialog
        dialog.setVisible(true);
    }

}
