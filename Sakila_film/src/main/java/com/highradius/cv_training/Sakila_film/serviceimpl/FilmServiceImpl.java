package com.highradius.cv_training.Sakila_film.serviceimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.highradius.cv_training.Sakila_film.dto.FilmDto;
import com.highradius.cv_training.Sakila_film.entity.Film;
import com.highradius.cv_training.Sakila_film.service.*;
import com.highradius.cv_training.Sakila_film.manager.*;

public class FilmServiceImpl implements FilmService{
	
	private FilmManager filmManager;
	
	@Override
	public HashMap<Object,Object> getFilm(int page,int limit, String title,String director,int release_year, String language) {
		List<FilmDto> FilmDTOs = new ArrayList<>();
		filmManager.getFilm(page,limit,title,director,release_year,language).stream().forEach(films->{
			FilmDto FilmDTO = new FilmDto();
			BeanUtils.copyProperties(films,FilmDTO);
			FilmDTOs.add(FilmDTO);
		});
		HashMap <Object,Object> Response = new HashMap<Object,Object>();
		Response.put("data", FilmDTOs);
		Response.put("count", filmManager.getCount());
		System.err.println(FilmDTOs);
		return Response;
	}

	@Override
	public FilmDto addFilm(FilmDto filmdto) {
		System.err.println(filmdto);
		Film film =  new Film();
		BeanUtils.copyProperties(filmdto,film);
		film=filmManager.addFilm(film);
		return filmdto;
	}

	@Override
	public FilmDto updateFilm(int film_id, FilmDto filmdto) {
		Film film =  new Film();
		BeanUtils.copyProperties(filmdto,film);
		film=filmManager.updateFilm(film_id,film);
		return filmdto;
	}

	@Override
	public String deleteFilm(int film_id) {
		return filmManager.deleteFilm(film_id);
	}


	

}
