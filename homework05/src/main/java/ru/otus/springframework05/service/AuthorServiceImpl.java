package ru.otus.springframework05.service;

import org.springframework.stereotype.Service;
import ru.otus.springframework05.dao.AuthorDao;
import ru.otus.springframework05.domain.Author;
import ru.otus.springframework05.exception.AuthorAlreadyExistsException;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;
    private final ShellService shellService;

    public AuthorServiceImpl(AuthorDao authorDao, ShellService shellService) {
        this.authorDao = authorDao;
        this.shellService = shellService;
    }

    @Override
    public void insert() {
        Author author = shellService.authorInsert();
        if (author != null){
            try {
                authorDao.insert(author);
                shellService.authorInsertSuccess(author);
            } catch (AuthorAlreadyExistsException e) {
                shellService.authorInsertError(e.getMessage());
            }
        }
        else{
            shellService.authorInsertError("");
        }
    }

    @Override
    public void update() {
        Author author = shellService.authorUpdate();
        if (author != null){
            authorDao.update(author);
            shellService.authorUpdateSuccess(author);
        }
        else{
            shellService.authorUpdateError();
        }
    }

    @Override
    public void delete() {
        Long authorID = shellService.authorDelete();
        if (authorID > 0){
            authorDao.delete(authorID);
            shellService.authorDeleteSuccess(authorID);
        }
        else{
            shellService.authorDeleteError();
        }
    }

    @Override
    public void findAll() {
        List<Author> list = authorDao.findAll();
        shellService.authorListOutput(list);
    }
}
