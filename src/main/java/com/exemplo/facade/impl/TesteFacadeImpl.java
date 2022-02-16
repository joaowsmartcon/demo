package com.exemplo.facade.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exemplo.dao.TesteDao;
import com.exemplo.facade.TesteFacade;
import com.exemplo.model.Teste;

@Service("testeFacade")
public class TesteFacadeImpl implements TesteFacade {

	@Autowired
	TesteDao testeDao;
	
	@Override
	public Teste findById(Long id) {
		return testeDao.findById(id).orElse(null);
	}

}
