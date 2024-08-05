import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private int publicationYear;
    private int pages;
    private String category;
    private boolean isOnLoan;
    private LocalDate loanDate;

    //Creating a constructor to initialize the Book object
    public Book(String title, String author, int publicationYear, int pages, String category) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.pages = pages;
        this.category = category;
        this.isOnLoan = false;
        this.loanDate = null;
    }

    //Getters and Setters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public int getPages() { return pages; }
    public String getCategory() { return category; }
    public boolean isOnLoan() { return isOnLoan; }
    public LocalDate getLoanDate() { return loanDate; }

    public void setOnLoan(boolean isOnLoan) { this.isOnLoan = isOnLoan; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }

    //Overriding toString to return a string representation of the book
    @Override
    public String toString() {
        return title + " by " + author + " (" + publicationYear + ")";
    }
}
