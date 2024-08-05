import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String libraryCardNumber;
    private List<Book> booksOnLoan;

    //Creating a constructor to initialize the User object
    public User(String name, String libraryCardNumber) {
        this.name = name;
        this.libraryCardNumber = libraryCardNumber;
        this.booksOnLoan = new ArrayList<>();
    }

    //Getters
    public String getName() { return name; }
    public String getLibraryCardNumber() { return libraryCardNumber; }
    public List<Book> getBooksOnLoan() { return booksOnLoan; }

    //Loaning a book to this user
    public void loanBook(Book book) {
        //Adding the book to the user's list of loaned books
        booksOnLoan.add(book);
    }

    //Returning a book from a specific user
    public void returnBook(Book book) {
        //Removes the book from the user's loaned books list
        booksOnLoan.remove(book);
    }
}
