package com.vico.license.service;

import java.util.List;

import com.vico.license.pojo.DatatableModel;
import com.vico.license.pojo.Hospital;

public interface HospitalService {
	
	/**
	 * @param:
	 * @return: List<Hospital>
	 * @Title: showAllHospitals
	 * @Description: 查
	 * @return
	 *
	 */
	public List<Hospital> showAllHospitals();

	public String selectHospitalName(int hospitalNumber);
	
	public Hospital showOneHospital(int hospitalNumber);
	
	
	/**
	 * @return: int
	 * @Description: 增,删,改
	 * @param hospital,hospitalNumber
	 *
	 */
	public int addHospital(Hospital hospital);

	public int deleteHospital(int hospitalNumber);

	public int updateHospital(Hospital hospital);

	public DatatableModel getHospitalByPage(Integer draw, Integer start, Integer length);
}
