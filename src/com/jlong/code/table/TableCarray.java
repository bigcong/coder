package com.jlong.code.table;

/**
 * 表字段
 * 
 *@author jlon
 * 
 */
public class TableCarray {
	private String carrayName;// 原名称
	private String carrayName_d;// 首字母大写
	private String carrayName_x;// 首字母小写
	private String carrayType;// 字段类型
	private String remark; //字段注释

	public TableCarray(String carrayName, String carrayNameD,
			String carrayNameX, String carrayType,String remark) {
		super();
		this.carrayName = carrayName;
		carrayName_d = carrayNameD;
		carrayName_x = carrayNameX;
		this.setRemark(remark);
		this.carrayType = carrayType;
	}

	public String getCarrayName() {
		return carrayName;
	}

	public void setCarrayName(String carrayName) {
		this.carrayName = carrayName;
	}

	public String getCarrayName_d() {
		return carrayName_d;
	}

	public void setCarrayName_d(String carrayNameD) {
		carrayName_d = carrayNameD;
	}

	public String getCarrayName_x() {
		return carrayName_x;
	}

	public void setCarrayName_x(String carrayNameX) {
		carrayName_x = carrayNameX;
	}

	public String getCarrayType() {
		return carrayType;
	}

	public void setCarrayType(String carrayType) {
		this.carrayType = carrayType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
