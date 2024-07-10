package org.hoshi.maplay.service;

import org.hoshi.maplay.DAO.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {
	
	@Autowired
	private TestDAO dao;

	public int test(int no) {
		return dao.test(no);
	}

	
	
}
