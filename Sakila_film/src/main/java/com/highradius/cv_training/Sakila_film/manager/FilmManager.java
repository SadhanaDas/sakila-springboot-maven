package com.highradius.cv_training.Sakila_film.manager;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.highradius.cv_training.Sakila_film.dao.FilmDao;
import com.highradius.cv_training.Sakila_film.entity.Film;

@Component
public class FilmManager {
	@Autowired
	private FilmDao filmDao;
	
	public List<Film> getFilm(int page,int limit, String title,String director, int release_year, String language){
		return filmDao.getFilm(page, limit, title, director, release_year, language);
	}
	
	@Transactional
	public String deleteFilm(int film_id) {
		return filmDao.deleteFilm(film_id);
	}
	
	@Transactional
	public Film updateFilm(int film_id, Film film) {
		return filmDao.updateFilm(film_id,film);
	}
	
	@Transactional
	public Film addFilm(Film film) {
		return filmDao.addFilm(film);
	}

	public int getCount() {
		return filmDao.getCount();
	}

}
