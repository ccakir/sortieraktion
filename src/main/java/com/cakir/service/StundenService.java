package com.cakir.service;

import java.util.List;

import com.cakir.model.Stunden;

public interface StundenService {
	
	List<Stunden> alleStunden();
	
	long speichern(Stunden stunden);
	
	boolean update(Stunden stunden);
	
	boolean delete(long id);
	
	Stunden findById(long id);
	
	List<Stunden> stundenByDatum(String monat, String jahr);

}
