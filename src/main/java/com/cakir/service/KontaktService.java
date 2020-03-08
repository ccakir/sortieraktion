package com.cakir.service;

import java.util.List;

import com.cakir.model.Kontakt;

public interface KontaktService {

	List<Kontakt> alleKontakte();
	
	boolean speichern(Kontakt kontakt);
	
	boolean update(Kontakt kontakt);
	
	boolean delete(long id);
	
	Kontakt findById(long id);
}
