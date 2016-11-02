package com.vico.license.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vico.license.dao.HospitalDao;
import com.vico.license.pojo.Hospital;

@Service
public class HospitalServiceImp implements HospitalService {

	@Autowired
	private HospitalDao hospitaldao;

	@Override
	public List<Hospital> showAllHospitals() {

		List<Hospital> list = hospitaldao.showAll();
		return list;
	}

	@Override
	public int addHospital(Hospital hospital) {

		int i = hospitaldao.insertHospital(hospital);
		return i;
	}

	@Override
	public int deleteHospital(int hospitalnumber) {
		
		int i = hospitaldao.deleteByPrimaryKey(hospitalnumber);
		return i;
	}

	@Override
	public String selectHospitalName(int hospitalNumber) {

		String hospitalName = null;

		if (hospitaldao.selectByPrimaryKey(hospitalNumber) == null) {
			return "无此医院信息";
		}

		hospitalName = hospitaldao.selectByPrimaryKey(hospitalNumber).getHospitalName();

		return hospitalName;
	}

	@Override
	public int updateHospital(Hospital hospital) {

		int i = hospitaldao.updateByPrimaryKey(hospital);
		return i;
	}

	@Override
	public Hospital showOneHospital(int hospitalNumber) {

		return hospitaldao.selectByPrimaryKey(hospitalNumber);
	}

}
