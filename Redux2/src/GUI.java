import java.awt.*;
import java.io.IOException;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.HyperlinkEvent;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;


public class GUI  extends JFrame {
    
    private static  JTextField jTextField;
    private final JTextPane jTextPane;
    private final JPanel statsPanel;
    private final JPanel inputPanel;
    private final JPanel btnPanel;
    private final JLabel statsLabel;
    private final Color periwinkle = new Color(204, 204, 255);
    private Color lightBlue;
    private Color lightPink;
    private final JButton takeButton, moveButton, dialogButton, inspectButton, inventoryButton, carebutton, specialButton, playButton;


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
            filePath = filePath.substring(0,filePath.indexOf(".txt"));
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
    inspectButton = new JButton("Interact");
    inspectButton.setFont(new Font("Arial", Font.BOLD, 16));
    inventoryButton = new JButton("Inventory");
    inventoryButton.setFont(new Font("Arial", Font.BOLD, 16));
    carebutton = new JButton("Care");
    carebutton.setFont(new Font("Arial", Font.BOLD, 16));
    specialButton = new JButton("Special");
    specialButton.setFont(new Font("Arial", Font.BOLD, 16));
    playButton = new JButton("Play");
    playButton.setFont(new Font("Arial", Font.BOLD, 16));

    // Set button size
    Dimension buttonSize = new Dimension(100, 50);
    moveButton.setPreferredSize(buttonSize);
    dialogButton.setPreferredSize(buttonSize);
    inspectButton.setPreferredSize(buttonSize);
    inventoryButton.setPreferredSize(buttonSize);
    carebutton.setPreferredSize(buttonSize);
    specialButton.setPreferredSize(buttonSize);
    takeButton.setPreferredSize(buttonSize);
    playButton.setPreferredSize(buttonSize);

    // Add buttons to panel
    btnPanel.add(playButton);
    btnPanel.add(specialButton);
    btnPanel.add(moveButton);
    btnPanel.add(dialogButton);
    btnPanel.add(jTextField);
    btnPanel.add(inspectButton);
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
inspectButton.setBorder(new LineBorder(Color.BLACK, 2));
inventoryButton.setBorder(new LineBorder(Color.BLACK, 2));
carebutton.setBorder(new LineBorder(Color.BLACK, 2));
specialButton.setBorder(new LineBorder(Color.BLACK, 2));
takeButton.setBorder(new LineBorder(Color.BLACK, 2));
playButton.setBorder(new LineBorder(Color.BLACK, 2));

    // Add action listeners to buttons
    moveButton.addActionListener(e -> {
        if (Game.isRunning()) {
        synchronized (this) {
            notify();
            String[] exits = Player.room.getExits();
            for (int i = 0; i < exits.length; i++) {
                exits[i] = exits[i].replace("_"," ");
                
            }
            
            String selectedExit = (String) JOptionPane.showInputDialog(null,
            "Choose an exit",
            "Exits",
            JOptionPane.QUESTION_MESSAGE,
            null, exits, exits[0]);
        
        if (selectedExit != null) {
            GameHandler.getGui().display("You move to the " + selectedExit + ".", "Black");
            Player.setRoom(GameHandler.getRoomByName(selectedExit.replace(" ","_")));
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
            String[] npcs = Player.room.getNPCChoises();
            for (int i = 0; i < npcs.length; i++) {
                npcs[i] = npcs[i].replace("_"," ");    
            }
            String selectedNPC = (String) JOptionPane.showInputDialog(
                null,
                "Choose an NPC",
                "Available NPCs",
                JOptionPane.QUESTION_MESSAGE,
                null,
                npcs,
                npcs[0]);
        
        if (selectedNPC != null&& !selectedNPC.equals("Nobody")) {
            GameHandler.getGui().display("You talk to the " + selectedNPC + ".", "Black");
            NPC npc = GameHandler.getNPCByName(selectedNPC.replace(" ","_"));
            GameHandler.getGui().display(npc.getDialog(), "Black");
        } else {
            notify();
        }
    }
}
    });
    inspectButton.addActionListener(e -> {
        if (Game.isRunning()) {
        synchronized (this) {
            notify();
        }
    }
    });
    inventoryButton.addActionListener(e -> {
        if (Game.isRunning()) {
        synchronized (this) {
            notify();
            String[] options = {"Use", "Drop","Throw Away","Put up"};
            String[] inventory = Player.getItemChoises();
                String selectedItem = (String) JOptionPane.showInputDialog(
                    null,
                    "What's in the bag?",
                    "Inventory",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    inventory,
                    inventory[0]);
            if (selectedItem != null&& !selectedItem.equals("Nothing")) {
                int action = JOptionPane.showOptionDialog(null,
                "What do you want to do with the " + selectedItem + "?",
                "Inventory",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
                switch (action) {
                    case 0 ->{
                            notify();
                            Item item = GameHandler.getItemByName(selectedItem);
                            item.use();
                        }
                    case 1 ->{
                            notify();
                            Item item = GameHandler.getItemByName(selectedItem);
                            Player.removeItem(item);
                            GameHandler.getGui().display("You drop the " + selectedItem + ".", "Black");
                        }
                    case 2 ->{
                            notify();
                            Item item = GameHandler.getItemByName(selectedItem);
                            Player.removeItem(item);
                            GameHandler.getGui().display("You throw away the " + selectedItem + ".", "Black");
                        }
                    case 3 ->{
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
        }
    }
    });
    specialButton.addActionListener(e -> {
        if (Game.isRunning()) {
        synchronized (this) {
            notify();
        }
    }
    });
    takeButton.addActionListener(e -> {
        if (Game.isRunning()) {
        synchronized (this) {
            notify();
        }
    }
    });
    playButton.addActionListener(e -> {
        if (Game.isRunning()) {
        synchronized (this) {
            notify();
        }
    }
    });
    jTextField.addActionListener(e -> {
        synchronized (this) {
            notify();
            if(Game.isRunning()){
            Commands.execute(jTextField.getText());
            }else{
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
            editorKit.insertHTML(doc, doc.getLength(), "<p style=\"font-size: 16; color: " + color + ";\">" + message + "<br>", 0, 0, null);
        } catch (BadLocationException | IOException ex) {
        }
        jTextPane.setCaretPosition(doc.getLength());
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
    public String getInput() {
        return jTextField.getText();
        
    }
    public static  JTextField getJTextField() {
        return jTextField;
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
}
