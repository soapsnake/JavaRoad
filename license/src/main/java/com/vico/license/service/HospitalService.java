package com.vico.license.service;

import com.vico.license.pojo.DatatableModel;
import com.vico.license.pojo.Hospital;

import java.util.List;

public interface HospitalService {

    /**
     * @return
     * @param:
     * @return: List<Hospital>
     * @Title: showAllHospitals
     * @Description: 查
     */
    public List<Hospital> showAllHospitals();

    public String selectHospitalName(int hospitalNumber);

    public Hospital showOneHospital(int hospitalNumber);


    /**
     * @param hospital,hospitalNumber
     * @return: int
     * @Description: 增, 删, 改
     */
    public int addHospital(Hospital hospital);

    public int deleteHospital(int hospitalNumber);

    public int updateHospital(Hospital hospital);

    public DatatableModel getHospitalByPage(Integer draw, Integer start, Integer length);
}
