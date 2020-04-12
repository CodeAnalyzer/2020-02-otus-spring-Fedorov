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

    public GenreServiceImpl(GenreDao genreDao, ShellService shellService) {
        this.genreDao = genreDao;
        this.shellService = shellService;
    }

    @Override
    public void insert() {
        Genre genre = shellService.genreInsert();
        if (genre != null){
            try {
                genreDao.insert(genre);
                shellService.genreInsertSuccess(genre);
            } catch (GenreAlreadyExistsException e) {
                shellService.genreInsertError(e.getMessage());
            }
        }
        else{
            shellService.genreInsertError("");
        }
    }

    @Override
    public void update() {
        Genre genre = shellService.genreUpdate();
        if (genre != null){
            genreDao.update(genre);
            shellService.genreUpdateSuccess(genre);
        }
        else{
            shellService.genreUpdateError();
        }
    }

    @Override
    public void delete() {
        Long genreID = shellService.genreDelete();
        if (genreID > 0){
            genreDao.delete(genreID);
            shellService.genreDeleteSuccess(genreID);
        }
        else{
            shellService.genreDeleteError();
        }
    }

    @Override
    public void findAll() {
        List<Genre> list = genreDao.findAll();
        shellService.genreListOutput(list);
    }

}
