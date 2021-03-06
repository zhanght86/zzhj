package com.zzhj.po;

import java.io.Serializable;

public class Users implements Serializable{	
	private int id;
	private String name;			//用户名
	private String password;		//密码
	private String sex;				//性别
	private String birthday;		//出生日期
	private String positiveDate;	//转正时间
	private Roles roleId;			//所属角色
	private Department departmentId;//所属部门
	private String phone;	//手机号
	private int idCard;		//身份证号
	private String national;/*名族*/
	private float height;	/*身高*/
	private String marital;/*婚否*/
	private String face;	/*政治面貌*/
	private String address;	/*户口地址*/
	private String graduated;/*毕业学校*/
	private String education;/*文化程度*/
	private String professional;/*专业*/
	private Integer parentId;	//父id
	private String state;		//用户层级别状态（工作日志）
	private String inductionDate;//入职时间
	private String imageName; //用户头像
	private SecurityQuestion securityQuestionId;//密保问题
	private String securityAnswer;//密保答案
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPositiveDate() {
		return positiveDate;
	}
	public void setPositiveDate(String positiveDate) {
		this.positiveDate = positiveDate;
	}

	public Roles getRoleId() {
		return roleId;
	}
	public void setRoleId(Roles roleId) {
		this.roleId = roleId;
	}
	public Department getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Department departmentId) {
		this.departmentId = departmentId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getIdCard() {
		return idCard;
	}
	public void setIdCard(int idCard) {
		this.idCard = idCard;
	}
	public String getNational() {
		return national;
	}
	public void setNational(String national) {
		this.national = national;
	}
	public float getHeight() {
		return height;
	}
	public void setHeight(float height) {
		this.height = height;
	}
	public String getMarital() {
		return marital;
	}
	public void setMarital(String marital) {
		this.marital = marital;
	}
	public String getFace() {
		return face;
	}
	public void setFace(String face) {
		this.face = face;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGraduated() {
		return graduated;
	}
	public void setGraduated(String graduated) {
		this.graduated = graduated;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getProfessional() {
		return professional;
	}
	public void setProfessional(String professional) {
		this.professional = professional;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getInductionDate() {
		return inductionDate;
	}
	public void setInductionDate(String inductionDate) {
		this.inductionDate = inductionDate;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	public SecurityQuestion getSecurityQuestionId() {
		return securityQuestionId;
	}
	public void setSecurityQuestionId(SecurityQuestion securityQuestionId) {
		this.securityQuestionId = securityQuestionId;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	
	
		
}
