package com.gmail.alexflanker89.lesson5.view;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SimpleView implements View {

    @Override
    public void showGenres(List<Genre> genreList) {
        System.out.println("Genres:");
        genreList.forEach(g->{
            System.out.println(g.getKindOf());
        });
    }

    @Override
    public void showBooks(List<Book> books) {
        books.forEach(this::shortShowBook);
    }

    @Override
    public void showAuthors(List<Author> authors) {
        authors.forEach(this::showAuthor);
    }

    private void showAuthor(Author author){
        getLine(40,'*');
        System.out.println(author.getName() + " " + author.getLastname());
        System.out.println("DateOfBirth->"+author.getDateOfBirth());
        getLine(40,'*');
    }

    @Override
    public void showBook(Book book) {

        shortShowBook(book);
        System.out.println("authors:");
        book.getAuthors().forEach(this::showAuthor);
        System.out.println("genres:");
        book.getGenres().forEach(g->{
            System.out.println(g.getKindOf()+";");
        });

        System.out.println("Description:");
        System.out.println(book.getDescription());

    }


    @Override
    public void shortShowBook(Book book) {
        System.out.println(getLine(40,'*'));
        System.out.println("id ->" +book.getId());
        System.out.println("Title -> "+book.getTitle());
        System.out.println("Edition -> "+book.getEdition());
        System.out.println("Release -> "+book.getReleaseDate());
        System.out.println(getLine(40,'*'));
    }

    private String getLine(int lenght,char c){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lenght ; i++) {
            sb.append(c);
        }
        return sb.toString();
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
