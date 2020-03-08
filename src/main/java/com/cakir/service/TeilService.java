package com.cakir.service;

import java.util.List;

import com.cakir.model.Teil;

public interface TeilService {
	
	List<Teil> alleTeile();
	
	boolean speichern(Teil teil);
	
	boolean update(Teil teil);
	
	boolean delete(long id);
	
	Teil findById(long id);

}
