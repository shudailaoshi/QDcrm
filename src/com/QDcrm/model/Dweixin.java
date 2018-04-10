package com.QDcrm.model;
/**
 *树袋老师
 * @author 作者 xpp
 * @version 创建时间：2018-2-8 上午10:47:15
 * 类说明
 */

public class Dweixin {
  
  private String uuid;
  private String createDate;
  private String modifyDate;
  private String createPeople;
  private String modifyPeople;
  
  private String weixinNO;//微信号码
  private String ditchUuid;//渠道uuid  
  private String empDitUuid;//负责人uuid 
  private String ditchName;//渠道名称
  private String empDitName;//负责人名称
  private String checkResult;//查询结果2种:1、存在，2、无
  
  public Dweixin() {
    super();
  }

  public Dweixin(String uuid, String weixinNO, String ditchUuid,
      String empDitUuid, String ditchName, String empDitName, String checkResult) {
    super();
    this.uuid = uuid;
    this.weixinNO = weixinNO;
    this.ditchUuid = ditchUuid;
    this.empDitUuid = empDitUuid;
    this.ditchName = ditchName;
    this.empDitName = empDitName;
    this.checkResult = checkResult;
  }

  @Override
  public String toString() {
    return "Dweixin 微信号码库[uuid=" + uuid + ", weixinNO=" + weixinNO 
        + ", ditchUuid=" + ditchUuid + ", empDitUuid=" + empDitUuid + ", ditchName=" + ditchName
        + ", empDitName=" + empDitName + ", checkResult=" + checkResult + "]结束";
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

  public String getWeixinNO() {
    return weixinNO;
  }

  public void setWeixinNO(String weixinNO) {
    this.weixinNO = weixinNO;
  }


  public String getDitchUuid() {
    return ditchUuid;
  }

  public void setDitchUuid(String ditchUuid) {
    this.ditchUuid = ditchUuid;
  }

  public String getEmpDitUuid() {
    return empDitUuid;
  }

  public void setEmpDitUuid(String empDitUuid) {
    this.empDitUuid = empDitUuid;
  }

  public String getDitchName() {
    return ditchName;
  }

  public void setDitchName(String ditchName) {
    this.ditchName = ditchName;
  }

  public String getEmpDitName() {
    return empDitName;
  }

  public void setEmpDitName(String empDitName) {
    this.empDitName = empDitName;
  }

  public String getCheckResult() {
    return checkResult;
  }

  public void setCheckResult(String checkResult) {
    this.checkResult = checkResult;
  }
  
  
  
  

}//end class
