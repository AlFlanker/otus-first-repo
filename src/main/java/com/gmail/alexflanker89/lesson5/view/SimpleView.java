package com.gmail.alexflanker89.lesson5.view;

import com.gmail.alexflanker89.lesson5.domain.Author;
import com.gmail.alexflanker89.lesson5.domain.Book;
import com.gmail.alexflanker89.lesson5.domain.Comment;
import com.gmail.alexflanker89.lesson5.domain.Genre;
import org.springframework.stereotype.Component;

import java.sql.SQLOutput;
import java.util.Collection;
import java.util.Set;

@Component
public class SimpleView implements View {

    @Override
    public void showGenres(Collection<Genre> genreList) {
        System.out.println("Genres:");
        genreList.forEach(g->{
            System.out.println(g.getGenreName());
        });
    }

    @Override
    public void showBooks(Collection<Book> books) {
        books.forEach(this::shortShowBook);
    }

    @Override
    public void showAuthors(Collection<Author> authors) {
        authors.forEach(this::showAuthor);
    }

    private void showAuthor(Author author){
        getLine(40,'*');
        System.out.println(author.getName() + " " + author.getLastname());
        System.out.println("DateOfBirth: "+author.getDateOfBirth());
        getLine(40,'*');
    }

    @Override
    public void showBook(Book book) {

        shortShowBook(book);
        System.out.println("genres:");
        book.getGenres().forEach(g->{
            System.out.println(g.getGenreName()+";");
        });

        System.out.println("Description:");
        System.out.println(book.getDescription());

    }


    @Override
    public void shortShowBook(Book book) {
        System.out.println(getLine(40,'*'));
        System.out.println("id: " +book.getId());
        System.out.println("Title: "+book.getTitle());
        System.out.println("Edition: "+book.getEdition());
        System.out.println("Release:"+book.getReleaseDate());
        System.out.println("Authors:");
        book.getAuthors().forEach(author-> System.out.println(author.getName() + " " + author.getLastname()));
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
    public void showComments(Collection<Comment> comments) {
        System.out.println(getLine(40,'*'));
        comments.forEach(comment -> {
            System.out.println("username: " +comment.getUsername());
            System.out.println("text: " +comment.getComment());
            System.out.println(getLine(40,'*'));
        });
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
