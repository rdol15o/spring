package com.larionov_dd.spring.book.service;

import com.larionov_dd.spring.book.entity.BookEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class BookService {
    static List<BookEntity> bookStorage = new ArrayList<>();

    public BookService(){
        fillStorage();
    }

    public void fillStorage(){
        Random random = new Random();
        for (int i=0; i<100; i++){
            BookEntity book = new BookEntity();
            book.setId(i);
            book.setTitle("Book #" + random.nextInt(100, 999));
            book.setDescription("Description");
            bookStorage.add(book);
        }
    }

    public List<BookEntity> all(){
        return bookStorage;
    }

    public Optional<BookEntity> byId(Integer id){
        return bookStorage.stream().filter((book -> book.getId().equals(id))).findFirst();
    }

    public BookEntity create(String title, String description){
        BookEntity book = new BookEntity();
        book.setId(bookStorage.size());
        book.setTitle(title);
        book.setDescription(description);
        bookStorage.add(book);
        return book;
    }

    public Optional<BookEntity> edit(BookEntity book){
        Optional<BookEntity> oldBookOptional = byId(book.getId());
        if (oldBookOptional.isEmpty()){
            return Optional.empty();
        }

        BookEntity oldBookEntity = oldBookOptional.get();
        oldBookEntity.setTitle(book.getTitle());
        oldBookEntity.setDescription(book.getDescription());
        return Optional.of(oldBookEntity);
    }

    public Boolean delete(Integer id){
        Optional<BookEntity> book = byId(id);
        if (book.isEmpty()){
            return false;
        }

        bookStorage.remove(book.get());
        return true;
    }


}
