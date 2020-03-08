package com.cakir.service;

import java.util.List;
import com.cakir.model.Sortieraktion;

public interface SortieraktionService {

	List<Sortieraktion> alleSortieraktionen();
	
	boolean speichern(Sortieraktion aktion);
	
	boolean update(Sortieraktion aktion);
	
	boolean delete(Sortieraktion aktion);
	
	Sortieraktion findById(long id);
	
	List<Sortieraktion> offenSortieraktionen();
}
