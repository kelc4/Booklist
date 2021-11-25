package ui;

import model.Book;
import model.BookList;
import model.EventLog;
import model.Event;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

//GUI for the application
public class BookListAppGUI extends JFrame implements ActionListener {

    private static final int FRAME_WIDTH = 650;
    private static final int FRAME_HEIGHT = 420;

    private ImageIcon imageIcon;
    private ImageIcon image;

    private JPanel topPanel;
    private JPanel bottomPanel;

    private JButton addItemButton;
    private JButton removeItemButton;
    private JButton saveDataButton;
    private JButton loadDataButton;

    private JTable table;
    private BookListTableModel tableModel;
    private DefaultTableCellRenderer cellRenderer;

    private static final String JSON_STORE = "./data/booklist.json";
    private String name;
    private BookList list;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    //constructs new BookListAppGUI
    public BookListAppGUI() {
        //Initialize local variables
        init();
        bookListInit();

        //create components
        createPanels();
        createButtons();

        //makes frame
        setListTitle(name);
        setupTopFrame();
        setupWholeFrame();

        //Add panels, buttons to frame
        addComponentsToFrame();
    }

    //MODIFIES: this
    //EFFECTS: Initializes local variables related to frame
    public void init() {
        imageIcon = new ImageIcon("b.jpg");
        image = new ImageIcon("books.png");
        topPanel = new JPanel();
        bottomPanel = new JPanel(new BorderLayout());

        addItemButton = new JButton();
        removeItemButton = new JButton();
        saveDataButton = new JButton();
        loadDataButton = new JButton();
    }

    //MODIFIES: this
    //EFFECTS: initializes local variables related to booklist
    public void bookListInit() {
        list = new BookList("nameJson");
        name = (String) JOptionPane.showInputDialog(null, "Welcome to your booklist! What \nwould "
                + "you like to name it?", ":D", JOptionPane.PLAIN_MESSAGE, image, null, "");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        tableModel = new BookListTableModel(list, new String[] {"#", "Read", "Title", "Author", "Date Added"});
        table = new JTable(tableModel);
        cellRenderer = new DefaultTableCellRenderer();
    }

    //MODIFIES: this
    //EFFECTS: formats panels, adds table
    public void createPanels() {
        //Top
        topPanel.setBackground(new Color(155, 150, 255));
        topPanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT / 8);

        //Bottom
        bottomPanel.setBounds(0, FRAME_HEIGHT / 8, FRAME_WIDTH, FRAME_HEIGHT * 7 / 8);

        setTableStyle();
        bottomPanel.add(new JScrollPane(table));
    }

    //MODIFIES: this
    //EFFECTS: Changes table style
    public void setTableStyle() {
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(String.class, cellRenderer);
        table.getColumnModel().getColumn(0).setPreferredWidth(8);
        table.getColumnModel().getColumn(1).setPreferredWidth(8);
        table.getColumnModel().getColumn(2).setPreferredWidth((FRAME_WIDTH - 100) / 4);
        table.getColumnModel().getColumn(3).setPreferredWidth((FRAME_WIDTH - 100) / 4);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
    }

    //MODIFIES: this
    //EFFECTS: creates buttons
    public void createButtons() {

        addItemButton.setBounds(0, 0, 0, 0);
        addItemButton.setText("Add Book");
        addItemButton.setFocusable(false);

        removeItemButton.setBounds(0, 0, 0, 0);
        removeItemButton.setText("Remove Book");
        removeItemButton.setFocusable(false);

        loadDataButton.setBounds(0, 0, 0, 0);
        loadDataButton.setText("Load List");
        loadDataButton.setFocusable(false);

        saveDataButton.setBounds(0, 0, 0, 0);
        saveDataButton.setText("Save List");
        saveDataButton.setFocusable(false);
    }

    //MODIFIES: this
    //EFFECTS: changes title of frame to name
    public void setListTitle(String name) {
        setTitle(name);
    }

    //MODIFIES: this
    //EFFECTS: Sets up top of frame
    public void setupTopFrame() {
        setIconImage(imageIcon.getImage());   //Sets icon on top left of frame
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); //makes program stop running after closing frame
        actionOnClose();
    }

    //MODIFIES: this
    //EFFECTS: Prints EventLog to console
    public void actionOnClose() {
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                String log = "";
                for (Event e: EventLog.getInstance()) {
                    log += e.getDate() + "\n" + e.getDescription() + "\n\n";
                }
                log += "Closing " + name + "...";
                System.out.println(log);
                //Exit code
                System.exit(0);
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: sets up around and about the inside of the frame
    public void setupWholeFrame() {
        //About the frame
        setResizable(false); //prevents frame from being resized
        setLayout(null);
        setSize(FRAME_WIDTH, FRAME_HEIGHT); //x, y dimensions of frame
        setLocationRelativeTo(null);  //Centers frame
        setVisible(true); //makes frame visible
    }

    //MODIFIES: this
    //EFFECTS: adds panels and buttons to frame
    public void addComponentsToFrame() {
        addPanelsToFrame();
        addButtonsToFrame();
    }

    //MODIFIES: this
    //EFFECTS: Adds panels to frame
    public void addPanelsToFrame() {
        add(topPanel);
        add(bottomPanel);
    }

    //MODIFIES: this
    //EFFECTS: adds buttons to frame
    public void addButtonsToFrame() {
        topPanel.add(addItemButton);
        addItemButton.addActionListener(this);
        topPanel.add(removeItemButton);
        removeItemButton.addActionListener(this);
        topPanel.add(loadDataButton);
        loadDataButton.addActionListener(this);
        topPanel.add(saveDataButton);
        saveDataButton.addActionListener(this);
    }

    //asks user for input after add button gets clicked
    //MODIFIES: this
    //EFFECTS: adds a new book as long as title and author are not null and is not already in the list
    public void addButtonClicked() {
        String title = JOptionPane.showInputDialog(null, "What is the title?", "Add",
                JOptionPane.QUESTION_MESSAGE);
        String author = JOptionPane.showInputDialog(null, "Who is the author?", "Add",
                JOptionPane.QUESTION_MESSAGE);

        if (title != null && author != null) {
            if (list.addBook(new Book(title, author))) {
                JOptionPane.showMessageDialog(null, title + " has been added!", "Add",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Book is already in " + name, "Add",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    //asks user for input after remove button gets clicked
    //MODIFIES: this
    //EFFECTS: removes book from userList if there and if input is valid
    public void removeButtonClicked() {
        String stringOfIndex = JOptionPane.showInputDialog(null, "What is the book's number?",
                "Remove", JOptionPane.QUESTION_MESSAGE);
        try {
            if (stringOfIndex != null) {
                int index = Integer.parseInt(stringOfIndex);

                if (list.deleteBook(index)) {
                    JOptionPane.showMessageDialog(null, "Book has been removed!", "Remove",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Book number is invalid.", "Remove",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Not a valid input!", "Remove",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    //MODIFIES: this
    //EFFECTS: loads data from file, merges list from file with current list
    public void loadDataButtonClicked() {
        BookList temp = new BookList("nameJson");
        try {
            temp = jsonReader.read();
            JOptionPane.showMessageDialog(null, "Loaded " + list.getNameJson()
                    + " from " + JSON_STORE, "Load Data", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Unable to read from file: " + JSON_STORE,
                    "Load Data", JOptionPane.ERROR_MESSAGE);
        }
        list.removeAllBooks();
        list.merge(temp);
        list.logEvent("Load", JSON_STORE);
    }

    //MODIFIES: this
    //EFFECTS: saves list to file
    public void saveDataButtonClicked() {
        try {
            jsonWriter.open();
            jsonWriter.write(list);
            jsonWriter.close();
            JOptionPane.showMessageDialog(null, "Saved " + list.getNameJson() + " to "
                    + JSON_STORE, "Load Data", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to write to file: " + JSON_STORE,
                    "Save Data", JOptionPane.ERROR_MESSAGE);
        }
        list.logEvent("Save", JSON_STORE);
    }

    //MODIFIES: this
    //EFFECTS: determines how to react when buttons are clicked
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addItemButton) {
            addButtonClicked();
            tableModel.fireTableDataChanged();
        }

        if (ae.getSource() == removeItemButton) {
            removeButtonClicked();
            tableModel.fireTableDataChanged();
        }

        if (ae.getSource() == loadDataButton) {
            loadDataButtonClicked();
            tableModel.fireTableDataChanged();
        }

        if (ae.getSource() == saveDataButton) {
            saveDataButtonClicked();
        }
    }


}
