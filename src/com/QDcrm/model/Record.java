package com.QDcrm.model;

/**
 * 树袋老师
 * 
 * @author 作者 xpp
 * @version 创建时间：2018-1-29 下午8:43:08 类说明 跟踪记录表Record
 */

public class Record {

	private String uuid;
	private String createDate;
	private String modifyDate;
	private String createPeople;
	private String modifyPeople;

	private String ditchUuid;// 渠道uuid
	private String ditchName;// 渠道名
	private String recordDate;// 跟踪日期
	private String remarkText;// 跟踪备注

	private String openAndclose;// 开关
	
	private String userUuid;//用户uuid 
	private String userName;//用户名 

	public Record() {
		super();
	}

	public Record(String uuid, String ditchUuid, String recordDate,
			String remarkText) {
		super();
		this.uuid = uuid;
		this.ditchUuid = ditchUuid;
		this.recordDate = recordDate;
		this.remarkText = remarkText;
	}

	@Override
	public String toString() {
		return "Record跟踪记录表 [uuid=" + uuid + ", ditchUuid=" + ditchUuid
				+ ", recordDate=" + recordDate + ", remarkText=" + remarkText
				+ "]结束";
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getCreatePeople() {
		return createPeople;
	}

	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}

	public String getModifyPeople() {
		return modifyPeople;
	}

	public void setModifyPeople(String modifyPeople) {
		this.modifyPeople = modifyPeople;
	}

	public String getDitchUuid() {
		return ditchUuid;
	}

	public void setDitchUuid(String ditchUuid) {
		this.ditchUuid = ditchUuid;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getRemarkText() {
		return remarkText;
	}

	public void setRemarkText(String remarkText) {
		this.remarkText = remarkText;
	}

	/**
	 * @return the ditchName
	 */
	public String getDitchName() {
		return ditchName;
	}

	/**
	 * @param ditchName
	 *            the ditchName to set
	 */
	public void setDitchName(String ditchName) {
		this.ditchName = ditchName;
	}

	public String getOpenAndclose() {
		return openAndclose;
	}

	public void setOpenAndclose(String openAndclose) {
		this.openAndclose = openAndclose;
	}

  public String getUserUuid() {
    return userUuid;
  }

  public void setUserUuid(String userUuid) {
    this.userUuid = userUuid;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

}// end class

