package com.cakir.service;

import java.util.List;

import com.cakir.model.StundenDetails;

public interface StundenDetailsService {
	
	boolean speichern(StundenDetails stundenDetails);
	
	boolean delete(long id);
	
	boolean deleteAll(long stundenId);
	
	List<StundenDetails> findByStunden(long stundeId);
	
	

}
