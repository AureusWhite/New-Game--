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

    public GUI() {
        setTitle("Busy Beavers by Jackal Face games");
        setSize(1200, 900);
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
            if (Game.isRunning()) {
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
                            if (npc.isFollower()) {
                                npc.setRoom(Player.getRoom());
                            }
                        }
                        Player.getRoom().update();
                    } else {
                        notify();
                    }
                }
            }
        });
        dialogButton.addActionListener(e -> {
            if (Game.isRunning()) {
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

                    if (selectedNPC != null && !selectedNPC.equals("Nobody")) {
                        GameHandler.getGui().display("You talk to the " + selectedNPC + ".", "Black");
                        NPC npc = GameHandler.getNPCByName(selectedNPC.replace(" ", "_"));
                        GameHandler.getGui().display(npc.getDialog(), "Black");
                        String[] options = {"Sarcastic", "Nice", "Mean", "Neutral"};
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
                                GameHandler.getGui().display(npc.getResponse("Sarcastic"), "Black");
                            }
                            case 1 -> {
                                GameHandler.getGui().display(npc.getResponse("Nice"), "Black");
                            }
                            case 2 -> {
                                GameHandler.getGui().display(npc.getResponse("Mean"), "Black");
                            }
                            case 3 -> {
                                GameHandler.getGui().display(npc.getResponse("Neutral"), "Black");
                            }
                        }

                    } else {
                        notify();
                    }
                }
            }
        });
        learnButton.addActionListener(e -> {
            if (Game.isRunning()) {
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
                                GameHandler.getGui().display("You read a story", "Black");
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

        });
        inventoryButton.addActionListener(e -> {
            if (Game.isRunning()) {
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
        });
        carebutton.addActionListener(e -> {
            if (Game.isRunning()) {
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
                                Player.displayQuests();
                            }
                            case "Potty" -> {
                                GameHandler.getGui().display("You take the beaver to the potty", "Black");
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
        });
        socializeButton.addActionListener(e -> {
            if (Game.isRunning()) {
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
                                String[] choises = Player.room.getToyChoices();
                                if (choises.length == 0) {
                                    GameHandler.getGui().display("There are no toys to play with", "Black");
                                    return;
                                }
                                String selectedToy = (String) JOptionPane.showInputDialog(
                                        null,
                                        "What do you want to play with?",
                                        "Play",
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        choises,
                                        choises[0]);
                                if (selectedToy != null) {
                                    Player.getRoom().play(selectedToy);
                                } else {
                                    notify();
                                }

                            }
                            case "Help" -> {
                                GameHandler.getGui().display("You help the beaver", "Black");
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
                                if (Player.getQuests().contains(GameHandler.getQuest("Fetch Quest"))) {
                                    GameHandler.getGui().display("You have already taken this quest", "Black");
                                } else {
                                    for (NPC npc : Player.room.getNPCs()) {
                                        GameHandler.giveQuestToPlayer(GameHandler.getQuest(npc.getQuest().getName()));
                                        GameHandler.getGui().display("You have taken the " + npc.getQuest().getName(), "Black");
                                    }
                                }

                                Player.displayQuests();
                            }

                            case "Dance" -> {
                                Quest completedQuest = null;
                                for (Quest quest : Player.getQuests()) {

                                    if (quest.checkCompletion()) {
                                        GameHandler.getGui().display("You completed the " + quest.getName(), "Black");
                                        completedQuest = quest;
                                    } else {
                                        GameHandler.getGui().display("You did not complete the quest", "Black");
                                    }
                                }
                                GameHandler.getGui().display("removing quest", "Black");
                                Player.removeQuest(completedQuest);
                                Player.displayQuests();

                            }

                        }
                    } else {
                        notify();
                    }
                }
            }
        });

        takeButton.addActionListener(e -> {
            if (Game.isRunning()) {
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
        });
        mischiefButton.addActionListener(e -> {
            if (Game.isRunning()) {
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

        });
        jTextField.addActionListener(e -> {
            synchronized (this) {
                notify();
                if (Game.isRunning()) {
                    Commands.execute(jTextField.getText());
                } else {
                    notify();
                }
            }
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
