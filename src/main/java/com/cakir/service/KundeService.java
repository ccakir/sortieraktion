package com.cakir.service;

import java.util.List;

import com.cakir.model.Kunde;

public interface KundeService {

	List<Kunde> alleKunden();
	
	boolean speichern(Kunde kunde);
	
	boolean update(Kunde kunde);
	
	boolean delete(long id);
	
	Kunde findById(long id);
}
