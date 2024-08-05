import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Library {
    //List to store books in the library
    private List<Book> books;
    //Map to quickly access books by title
    private Map<String, Book> bookMap;
    //List to store registered users
    private List<User> users;

    //Creating a constructor to initialize the Library object
    public Library() {
        this.books = new ArrayList<>();
        this.bookMap = new HashMap<>();
        this.users = new ArrayList<>();
    }

    //Adding a book to the library
    public void addBook(Book book) {
        //Adds a book to the list
        books.add(book);
        //Adds a book to the map for quick access
        bookMap.put(book.getTitle(), book);
    }

    //Removing a book from the library by title
    public void removeBook(String title) {
        //Removes a book from the map
        Book book = bookMap.remove(title);
        //Removes a book from the list
        if (book != null) {
            books.remove(book);
        }
    }

    //Finding all books published in a specific year
    public List<Book> findBooksByPublicationYear(int year) {
        return books.stream()
                //Filters books by year
                .filter(b -> b.getPublicationYear() == year)
                //Collects the results into a list
                .collect(Collectors.toList());
    }

    //Finding all books by a specific author
    public List<Book> findBooksByAuthor(String author) {
        return books.stream()
                //Filters books by a specific author
                .filter(b -> b.getAuthor().equalsIgnoreCase(author))
                //Collects the results into a list
                .collect(Collectors.toList());
    }

    //Finding the book with the most pages
    public Book findBookWithMostPages() {
        return books.stream()
                //Find the book with the maximum number of pages
                .max(Comparator.comparingInt(Book::getPages))
                //Returns null if no book is found
                .orElse(null);
    }

    //Finding all books with more than n number of pages
    public List<Book> findBooksWithMoreThanNPages(int n) {
        return books.stream()
                //Filters the books with more than n number of pages
                .filter(b -> b.getPages() > n)
                //Collects the results into a list
                .collect(Collectors.toList());
    }

    //Printing all book titles in the library, sorted alphabetically
    public void printAllBookTitles() {
        books.stream()
                //Extracts the book titles
                .map(Book::getTitle)
                //Sorts the titles alphabetically
                .sorted()
                //Prints each title
                .forEach(System.out::println);
    }

    //Finding all books in a specific category
    public List<Book> findBooksByCategory(String category) {
        return books.stream()
                //Filters books by category
                .filter(b -> b.getCategory().equalsIgnoreCase(category))
                //Collects the results into a list
                .collect(Collectors.toList());
    }

    //Loaning out a book to a user
    public boolean loanBook(String title, User user) {
        //Retrieves a book by its title
        Book book = bookMap.get(title);
        //Checks if the book is available
        if (book != null && !book.isOnLoan()) {
            book.setOnLoan(true); //Marks the book as loaned
            book.setLoanDate(LocalDate.now()); //Sets the loan date
            user.loanBook(book); //Adds the book to the user's loaned books
            return true; //Returns true if the book is now loaned out
        }
        return false; //Returns false if the book could not be loaned
    }

    //Returning a book from a user
    public boolean returnBook(String title, User user) {
        //Retrieves a book by its title
        Book book = bookMap.get(title);
        //Checks if the book is on loan
        if (book != null && book.isOnLoan()) {
            book.setOnLoan(false); //Marks the book as returned
            book.setLoanDate(null); //Clears the loan date
            user.returnBook(book); //Removes the book from the user's loaned books
            return true; //Returns true if the book is now returned
        }
        return false; //Returns false if the book is not returned
    }

    //Registering a user with the library
    public void registerUser(User user) {
        //Adds the user to the list
        users.add(user);
    }

    //Calculating the late fees for a book
    public double calculateLateFee(Book book) {
        if (book.isOnLoan()) {
            LocalDate now = LocalDate.now();
            //Calculates the number of days late (2 weeks grace period)
            long daysLate = java.time.Duration.between(book.getLoanDate().atStartOfDay(), now.atStartOfDay()).toDays() - 14;
            //Calculates the late fee (1.0 unit of currency per day)
            return daysLate > 0 ? daysLate * 1.0 : 0;
        }
        return 0; //Returns 0 if no fee
    }
}
