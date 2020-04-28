package ru.otus.springframework05.service;

import org.springframework.stereotype.Service;
import ru.otus.springframework05.dao.GenreDao;
import ru.otus.springframework05.domain.Genre;
import ru.otus.springframework05.exception.GenreAlreadyExistsException;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService{

    private final GenreDao genreDao;
    private final ShellService shellService;
    private final MessageService messageService;

    public GenreServiceImpl(GenreDao genreDao, ShellService shellService, MessageService messageService) {
        this.genreDao = genreDao;
        this.shellService = shellService;
        this.messageService = messageService;
    }

    @Override
    public void insert() {
        Genre genre = shellService.genreInsert();
        if (genre != null){
            try {
                genreDao.insert(genre);
                messageService.messagePrintOut("genre.success.insert", new Object[] {genre.getGenreID(), genre.getName()});
            } catch (GenreAlreadyExistsException e) {
                messageService.messagePrintOut("genre.error.insert", e.getMessage());
            }
        }
        else{
            messageService.messagePrintOut("genre.error.insert");
        }
    }

    @Override
    public void update() {
        Genre genre = shellService.genreUpdate();
        if (genre != null){
            genreDao.update(genre);
            messageService.messagePrintOut("genre.success.update", new Object[] {genre.getGenreID(), genre.getName()});
        }
        else{
            messageService.messagePrintOut("genre.error.update");
        }
    }

    @Override
    public void delete() {
        Genre genre = shellService.genreDelete();
        if (genre.getGenreID() > 0){
            genreDao.delete(genre);
            messageService.messagePrintOut("genre.success.delete", new Object[] {genre.getGenreID()});
        }
        else{
            messageService.messagePrintOut("genre.error.delete");
        }
    }

    @Override
    public void findAll() {
        List<Genre> list = genreDao.findAll();
        shellService.genreListOutput(list);
    }

}
