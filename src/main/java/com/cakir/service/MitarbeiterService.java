package com.cakir.service;

import java.util.List;

import com.cakir.model.Mitarbeiter;

public interface MitarbeiterService {
	
	List<Mitarbeiter> alleMitarbeiter();
	
	boolean speichern(Mitarbeiter mitarbeiter);
	
	boolean update(Mitarbeiter mitarbeiter);
	
	boolean delete(long id);
	
	Mitarbeiter findById(long id);

}
