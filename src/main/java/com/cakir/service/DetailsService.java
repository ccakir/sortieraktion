package com.cakir.service;

import com.cakir.model.Details;

public interface DetailsService {	
	
	
	boolean speichern(Details details);
	
	boolean update(Details details);
	
	boolean delete(long id);
	
	Details findById(long id);

}
