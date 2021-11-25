package ui;

import model.Book;
import model.BookList;
import model.Event;
import model.EventLog;

import javax.swing.table.AbstractTableModel;

//Table model for booklist
public class BookListTableModel extends AbstractTableModel {
    private BookList list;
    private String[] colNames;

    //EFFECTS: constructs a BookListTableModel with a booklist and column names
    public BookListTableModel(BookList list, String[] colNames) {
        this.list = list;
        this.colNames = colNames;
    }

    //EFFECTS: returns number of rows
    @Override
    public int getRowCount() {
        return list.length();
    }

    //EFFECTS: returns number of columns
    @Override
    public int getColumnCount() {
        return colNames.length;
    }

    //EFFECTS: returns value at the row and index passed, otherwise return null
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Book book = list.getBook(rowIndex);
        switch (columnIndex) {
            case 0:
                return rowIndex + 1;
            case 1:
                return book.getStatus();
            case 2:
                return book.getTitle();
            case 3:
                return book.getAuthor();
            case 4:
                return book.getDate();
        }
        return null;
    }

    //EFFECTS: returns column name
    @Override
    public String getColumnName(int col) {
        return colNames[col];
    }

    //EFFECTS: returns column's class
    @Override
    public Class getColumnClass(int c) {
        switch (c) {
            case 0:
                return Integer.class;
            case 1:
                return Boolean.class;
            case 2:
            case 3:
            case 4:
                return String.class;
        }
        return null;
    }

    //MODIFIES: this
    //EFFECTS: sets value of elements of list
    @Override
    public void setValueAt(Object value, int row, int col) {
        Book book = list.getBook(row);
        if (col == 0) {
            System.out.println(0);
        } else if (col == 2) {
            book.setTitle(value.toString());
        } else if (col == 3) {
            book.setAuthor(value.toString());
        } else if (col == 4) {
            book.setDate(value.toString());
        } else {
            if (value == Boolean.FALSE) {
                EventLog.getInstance().logEvent(new Event(book.getTitle() + " was marked as unread"));
                book.setStatus(false);
            } else {
                EventLog.getInstance().logEvent(new Event(book.getTitle() + " was marked as read"));
                book.setStatus(true);
            }
        }
    }

    //EFFECTS: returns true or false depending on if col is 1 or not
    @Override
    public boolean isCellEditable(int row, int col) {
        return col == 1;
    }

}
