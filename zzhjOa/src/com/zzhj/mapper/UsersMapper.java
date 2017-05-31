package com.zzhj.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.zzhj.po.Users;

public interface UsersMapper {
	Users login(Users users);
	int save(Users users);
	List<Users> queryAll(@Param("startPage") int startPage,@Param("rows") int rows);
	int totalCount();
	int deleteUser(int id);
	Users query(int id);
	int queryId(String roleName);
	int exist(String name);
	List<Users> roleQueryAll(@Param("startPage") int startPage,@Param("rows") int rows);
	int updateRole(Users user);
	int updateUser(Users user);
	List<Users> queryUser(@Param("departmentName") String departmentName,@Param("roleName") String roleName);
	/**
	 * 
	 * @Description: 返回子用户（自己的下属）
	 * @param @param id
	 * @param @return   
	 * @return List<Users>  
	 * @throws
	 * @author 小白
	 * @date 2017年5月3日
	 */
	List<Users> roleUser(Users user);
	/**
	 * 
	 * @Description: 根据部门返回部门的副总
	 * @param @param departmentName
	 * @param @return   
	 * @return Users  
	 * @throws
	 * @author 小白
	 * @date 2017年5月11日
	 */
	Users userId(String departmentName);
	/**
	 * 
	 * @Description: 根据用户id返回parentName
	 * @param @param userId
	 * @param @return   
	 * @return Users  
	 * @throws
	 * @author 小白
	 * @date 2017年5月11日
	 */
	Users parentId(int userId);
	/**
	 * 
	 * @Description: 根据部门返回部门所有员工
	 * @param @param departmentName
	 * @param @return   
	 * @return List<Users>  
	 * @throws
	 * @author 小白
	 * @date 2017年5月31日
	 */
	List<Users> departmentUser(String departmentName);

}
