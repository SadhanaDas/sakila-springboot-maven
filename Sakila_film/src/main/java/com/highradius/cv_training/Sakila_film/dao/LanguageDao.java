package com.highradius.cv_training.Sakila_film.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.highradius.cv_training.Sakila_film.config.SakilaConfig;
import com.highradius.cv_training.Sakila_film.entity.Language;

@Repository
public class LanguageDao {
	@PersistenceContext(unitName=SakilaConfig.SAKILA_PU)
	private EntityManager entityManager;
	
	public static Language getLanguageById() {
		return null;
	}
	
	public HashMap<Integer,String> getLanguageByIds (Set<Integer> ids){
		HashMap<Integer,String> lanaguageIdNameMap = new HashMap<Integer,String>();
		List<Language> languageList = entityManager.createQuery("FROM Language where id in(:ids)")
				.setParameter("ids", ids).getResultList();
		
		languageList.forEach(lang->{
			 lanaguageIdNameMap.put(lang.getLanguage_id(), lang.getName());
		});
		
		return  lanaguageIdNameMap;
	}
	
	public Integer  getLanaguageIdByName(String name) {
		Language language = (Language)entityManager.createQuery("FROM Language where name=: namr")
				.setParameter("name", name).getSingleResult();
		System.err.println(language);
		return language.getLanguage_id();
	}
}