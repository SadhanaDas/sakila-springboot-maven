package com.highradius.cv_training.Sakila_film.service;

import java.util.HashMap;
import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.highradius.cv_training.Sakila_film.dto.*;

@Path("/film")
public interface FilmService {
	
		@GET
		@Produces({MediaType.APPLICATION_JSON})
		HashMap<Object,Object> getFilm(@QueryParam("page")int page,@QueryParam("limit")int limit,@QueryParam("title")String title,@QueryParam("director") String director, @QueryParam("release_year") int release_year, @QueryParam("language") String language);

		
		@POST
		@Produces({MediaType.APPLICATION_JSON})
		@Consumes({MediaType.APPLICATION_JSON})
		public FilmDto addFilm (FilmDto filmdto);
		
		@PUT
		@Path(value="/{film_id}")
		@Produces({MediaType.APPLICATION_JSON})
		@Consumes({MediaType.APPLICATION_JSON})
		public FilmDto updateFilm(@PathParam("film_id") int film_id, FilmDto filmdto);
		
		@DELETE
		@Path(value="/{film_id}")
		@Produces({MediaType.APPLICATION_JSON})
		public String deleteFilm(@PathParam("film_id") int film_id);

		
		
		
		
		
}
