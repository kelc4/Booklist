# My Personal Project

## BookList Application Overview

The application will be a list of books. With this list, the user will be able to add and remove books from the list.
With each book on the list, they will be able to see the title, author, date added to the list, and book number. They 
will also be able to check off the books they've read. The list will be organized by when they added the book to the
list.

This application is for people like books, likes recommending books and those who always have a long reading list. 
This project is interesting to me because I personally have my own list for those exact purposes.

### **User Stories**:
- As a user, I want to be able to add a book to the list
- As a user, I want to be able to see the books on my list
- As a user, I want to be able to delete a book from my list
- As a user, I want to be able to check a book off as read
- As a user, I want to be able to save my to-do list to file
- As a user, I want to be given the option to load my to-do list from file

## Phase 4: Task 2
![Event Log Screenshot](CPSC%20210%20-%20Phase%204%20T2%20EventLog.jpg)

## Phase 4: Task 3
![UML Diagram](CPSC%20210%20-%20Phase%204%20T3%20UML%20Diagram.png)

### Reflection  
- Next time: 
  - Looking at the diagram, it seems like it is already simple
  - Clean up code in classes, since methods and order of code are fairly messy
  - Rename some methods in GUI
  - Rewrite, or fix up JsonReader and JsonWriter

*** Persistence packages (in both main and test) are modeled after the CPSC 210 sample application

*** Event and EventLog are taken directly from CPSC 210 AlarmSystem Application 

*** actionOnClosed() method in BookListAppGUI referenced from user9643348's post on stackoverflow
https://stackoverflow.com/questions/60516720/java-how-to-print-message-when-a-jframe-is-closed 