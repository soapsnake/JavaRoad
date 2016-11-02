package com.vico.license.dao;

import com.vico.license.pojo.RSAKey;

public interface RSAKeyDao {

	int insertRSAKeyPair(RSAKey rsakeypair);
	
	RSAKey selectKeyByID();
	
}
