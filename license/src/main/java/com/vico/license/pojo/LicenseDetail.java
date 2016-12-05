package com.vico.license.pojo;

import java.sql.Date;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.vico.license.util.annotation.NotEmpty;

/**
 * 
 * @ClassName: LicenseDetail
 * @Description: 序列号信息
 * @author: Liu.Dun
 * @date: 2016年6月27日 下午8:47:32
 */
@Component
public class LicenseDetail {
	private Integer serialNumberId;
	
	@NotEmpty
	private String sourceNumber;

	private Date createDate;
	
	@NotEmpty
	private Date expiredDate;
	
	@NotEmpty
	private String encryptedNumber;
	
	@NotEmpty
	private Integer hospitalNumber;

	private Integer licenseState;
	
	/**给该序列号加密的RSAKey的ID**/
	private Integer keyId;
	
	public Integer getKeyId() {
		return keyId;
	}

	public void setKeyId(Integer keyId) {
		this.keyId = keyId;
	}

	/**
	 * Hospital对象，用于连接查询
	 */
	private Hospital hospital;
	
	/**
	 * RSAKey对象，用于连接查询
	 */
	private RSAKey rsaKey;
	
	public RSAKey getRsaKey() {
		return rsaKey;
	}

	public void setRsaKey(RSAKey rsaKey) {
		this.rsaKey = rsaKey;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	public Integer getLicenseState() {
		return licenseState;
	}

	public void setLicenseState(Integer licenseState) {
		this.licenseState = licenseState;
	}

	public Integer getSerialNumberId() {
		return serialNumberId;
	}

	public void setSerialNumberId(Integer serialNumberId) {
		this.serialNumberId = serialNumberId;
	}

	public String getSourceNumber() {
		return sourceNumber;
	}

	public void setSourceNumber(String sourceNumber) {
		this.sourceNumber = sourceNumber == null ? null : sourceNumber.trim();
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public String getEncryptedNumber() {
		return encryptedNumber;
	}

	public void setEncryptedNumber(String encryptedNumber) {
		this.encryptedNumber = encryptedNumber == null ? null : encryptedNumber.trim();
	}


	public Integer getHospitalNumber() {
		return hospitalNumber;
	}
	
	public void setHospitalNumber(Integer hospitalNumber) {
		this.hospitalNumber = hospitalNumber;
	}
}