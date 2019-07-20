package com.gmail.alexflanker89.Lesson_10.services;

import com.gmail.alexflanker89.Lesson_10.domain.Author;
import com.gmail.alexflanker89.Lesson_10.dto.AuthorDTO;
import com.gmail.alexflanker89.Lesson_10.exceptions.author.AuthorAlreadyExistExceptions;
import com.gmail.alexflanker89.Lesson_10.repo.AuthorRepo;
import com.gmail.alexflanker89.Lesson_10.services.interfaces.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthosServiceImpl implements AuthorService {
    private final AuthorRepo authorRepo;

    @Override
    public List<Author> getAll() {
        return authorRepo.findAll();
    }

    private void ifExist(Author author) throws AuthorAlreadyExistExceptions {
        if (authorRepo.findByNameAndLastnameAndDateOfBirth(author.getName(), author.getLastname(), author.getDateOfBirth()).size() > 0) {
            throw new AuthorAlreadyExistExceptions();
        }
    }

    @Override
    public Author save(AuthorDTO authorDTO) throws AuthorAlreadyExistExceptions {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setLastname(authorDTO.getLastname());
        author.setDateOfBirth(authorDTO.getDateOfBirth());
        ifExist(author);
        return authorRepo.save(author);
    }
}
