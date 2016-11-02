package com.vico.license.pojo;

import java.io.Serializable;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @ClassName: ProcessResult
 * @Description: 数据库操作结果JSON模板
 * 对应状态码及状态描述：1：成功         0：失败     -1：异常
 * 需要附加在模板中返回前台的信息为resultmessage
 * 需要附加在模板中返回前台的对象为resultobject
 * @author: Liu.Dun
 * @date: 2016年7月5日 下午3:42:52
 */

@Component
//@Scope(value = "request",  proxyMode = ScopedProxyMode.TARGET_CLASS)

public class ProcessResult implements Serializable {
	
	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 状态码
	 */
	private Integer resultcode = 1;
	
	/**
	 * 模板携带的业务信息
	 */
	private String resultmessage = "";
	
	/**
	 * 模板携带对象
	 */
	private Object resultobject = null;
	
	/**
	 * 状态码描述
	 */
	private String resultdesc = "";
	
	public Object getResultobject() {
		return resultobject;
	}
	public void setResultobject(Object resultobject) {
		this.resultobject = resultobject;
	}
	public String getResultmessage() {
		return resultmessage;
	}
	public void setResultmessage(String resultmessage) {
		this.resultmessage = resultmessage;
	}
	public int getResultcode() {
		return resultcode;
	}
	public void setResultcode(int resultcode) {
		this.resultcode = resultcode;
	}
	public String getResultdesc() {
		return resultdesc;
	}
	public void setResultdesc(String resultdesc) {
		this.resultdesc = resultdesc;
	}
	@Override
	public String toString() {
		return "ProcessResult [resultcode=" + resultcode + ", resultmessage=" + resultmessage + ", resultobject="
				+ resultobject + ", resultdesc=" + resultdesc + "]";
	}
}
