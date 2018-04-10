package com.QDcrm.model;
/**
 *树袋老师
 * @author 作者 xpp
 * @version 创建时间：2018-1-29 下午7:44:34
 * 类说明
 * 渠道Ditch
 */

public class Ditch {
  
  private String uuid;
  private String createDate;
  private String modifyDate;
  private String createPeople;
  private String modifyPeople;
  
  private String name;//姓名
  private String sex;//性别
  private String phone;//手机号
  private String weixin;//微信
  private String company;//单位   
  private String job;//职位   
  
  private String empUuid;//负责人  (员工uuid) 
  private String empName;//负责人名字  (员工name) 
  private String rank;//渠道等级
  private String source;//来源
  private String remark;//备注    
  private String openAndclose;//开关 
  
  private String moneyRate;//佣金比例 ，(02-08新增字段)
  
  private String userUuid;//用户uuid
  private String userName;//用户名 
  
  public Ditch() {
    super();
  }


  public Ditch(String uuid, String name, String sex, String phone, String weixin, String company,
      String job, String empUuid, String rank, String source, String remark,String moneyRate) {
    super();
    this.uuid = uuid;
    this.name = name;
    this.sex = sex;
    this.phone = phone;
    this.weixin = weixin;
    this.company = company;
    this.job = job;
    this.empUuid = empUuid;
    this.rank = rank;
    this.source = source;
    this.remark = remark;
    this.moneyRate=moneyRate;
  }


  


  @Override
  public String toString() {
    return "Ditch 渠道[uuid=" + uuid + ", name=" + name + ", sex=" + sex + ", phone=" + phone
        + ", weixin=" + weixin + ", company=" + company + ", job=" + job + ", empUuid=" + empUuid
        + ", empName=" + empName + ", rank=" + rank + ", source=" + source + ", remark=" + remark
        + ", openAndclose=" + openAndclose + ", moneyRate=" + moneyRate + "]结束";
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


  public String getName() {
    return name;
  }


  public void setName(String name) {
    this.name = name;
  }


  public String getSex() {
    return sex;
  }


  public void setSex(String sex) {
    this.sex = sex;
  }


  public String getPhone() {
    return phone;
  }


  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getWeixin() {
    return weixin;
  }


  public void setWeixin(String weixin) {
    this.weixin = weixin;
  }


  public String getCompany() {
    return company;
  }


  public void setCompany(String company) {
    this.company = company;
  }


  public String getJob() {
    return job;
  }


  public void setJob(String job) {
    this.job = job;
  }


  public String getEmpUuid() {
    return empUuid;
  }


  public void setEmpUuid(String empUuid) {
    this.empUuid = empUuid;
  }


  public String getRank() {
    return rank;
  }


  public void setRank(String rank) {
    this.rank = rank;
  }


  public String getSource() {
    return source;
  }


  public void setSource(String source) {
    this.source = source;
  }


  public String getRemark() {
    return remark;
  }


  public void setRemark(String remark) {
    this.remark = remark;
  }


  public String getOpenAndclose() {
    return openAndclose;
  }


  public void setOpenAndclose(String openAndclose) {
    this.openAndclose = openAndclose;
  }


  public String getEmpName() {
    return empName;
  }


  public void setEmpName(String empName) {
    this.empName = empName;
  }


  public String getMoneyRate() {
    return moneyRate;
  }


  public void setMoneyRate(String moneyRate) {
    this.moneyRate = moneyRate;
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
  
  
  

}//end class
