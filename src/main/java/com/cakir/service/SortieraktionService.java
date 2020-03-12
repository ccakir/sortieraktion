package com.cakir.service;

import java.util.List;

import com.cakir.model.Breakpoint;
import com.cakir.model.Details;
import com.cakir.model.Sortieraktion;

public interface SortieraktionService {

	List<Sortieraktion> alleSortieraktionen();
	
	boolean speichern(Sortieraktion aktion, Breakpoint bp, Details details);
	
	boolean update(Sortieraktion aktion, Breakpoint bp, Details details);
	
	boolean delete(Sortieraktion aktion);
	
	Sortieraktion findById(String id);
	
	List<Sortieraktion> offenSortieraktionen();
	
	
}
