package com.highradius.cv_training.Sakila_film.dao;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.highradius.cv_training.Sakila_film.config.SakilaConfig;
import com.highradius.cv_training.Sakila_film.entity.*;

@Repository
public class FilmDao {
	@PersistenceContext(unitName=SakilaConfig.SAKILA_PU)
	private EntityManager entityManager;
	
	@Autowired
	private LanguageDao languageDao;
	List <Film> films=null;
	String s;
	int count;
	
	public List<Film> getFilm(int page,int limit, String title,String director, int release_year, String language){
		System.err.println("TITLE"+release_year);
		HashMap<Integer,Integer> languageMap = new HashMap<Integer,Integer>();
		int start=(page-1)*limit;
		System.err.println(start);
		
		Integer language_id=0;
		if(language!=null && language.length()>0) {
			System.err.println("language_id"+ language.length());
			language_id= languageDao.getLanaguageIdByName(language);
		}
		
		s= "from film where title like :a and director like :b";
		
		if(release_year>0) {
			s+= "and release_year =: c";
		}
		if (language_id>0) {
			s+= "and language_id = :d";
		}
		Query q= entityManager.createQuery(s,Film.class)
				.setParameter("a", "%" + (title != null ? title : "")+ "%")
				.setParameter("b", "%" + (director !=null ? director: "" + "%"));
		
		Query query= entityManager.createQuery(s,Film.class)
				.setParameter("a", "%" + (title != null ? title : "")+ "%")
				.setParameter("b", "%" + (director !=null ? director: "" + "%"));
		
		if(release_year>0) {
			query.setParameter("c", release_year);
			q.setParameter("c", release_year);
		}
		
		if(language_id>0) {
			query.setParameter("c", language_id);
			q.setParameter("c", language_id);
		}
		
		films= query.setFirstResult(start).setMaxResults(limit).getResultList();
		
		count = q.getResultList().size();
		System.err.println("Query"+films);
		films.forEach(film->{
			languageMap.put(film.getLanguage_id(), film.getLanguage_id());
		});
		
		HashMap <Integer,String> languageIdNameMap = languageDao.getLanguageByIds(languageMap.keySet());
		
		films.forEach(film->{
			film.setLanguage(languageIdNameMap.get(film.getLanguage_id()));
		});
		
		//return entityManager.createQuery("from Film", Film.class).getResultList();
		return films;
	}

	public int getCount() {
		System.err.println("COUNT"+count);
		return count;
	}
	
	public Film addFilm(Film film) {
		System.err.println(film);
		int id=languageDao.getLanaguageIdByName(film.getLanguage());
		film.setLanguage_id(id);
		entityManager.persist(film);
		List<Film> films = entityManager.createQuery("FROM FIlm f ORDER f.film_id DESC", Film.class).setMaxResults(1).getResultList();
		return films.get(0);
	}

	public Film updateFilm(int film_id, Film film) {
		film.setFilm_id(film_id);
		entityManager.merge(film);
		return entityManager.find(Film.class, film.getFilm_id());
	}

	public String deleteFilm(int film_id) {
		Film film = entityManager.find(Film.class, film_id);
		System.err.println(film);
		entityManager.remove(film);
		return "Deleted Successfully";
	}
}
