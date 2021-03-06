package com.zzhj.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzhj.mapper.RoleMapper;
import com.zzhj.mapper.UsersMapper;
import com.zzhj.po.Users;
/**
 * 
 * @author 小白
 * @date 2017年4月25日
 * @Description: TODO
 * @version 1.0
 */
@Service
public class UsersService {
	@Resource(name="usersMapper")
	private UsersMapper um;
	
	@Autowired
	private RoleMapper rm;
	
	
	public Users login(Users users){
		return um.login(users);
	}
	
	public String save(Users users){
		int count =um.exist(users.getName());
		String message="";
		if(count>0){
			message="该用户已存在";
		}else{
			message =um.save(users)+"";
		}
		return message;
		
	}
	public Map<String,Object> queryAll(int page,int rows){
		int startPage =(page-1)*(rows);
		int total=um.totalCount();
		List<Users> roleList =um.roleQueryAll(startPage,rows);
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("rows", roleList);
		map.put("total", total);
		return map;
	}
	
	public String deleteUser(int id,String name){
		Users user = um.query(id);
		if(name.equals(user.getName())){
			return "不可操作当前用户,"+user.getName();
		}
		 um.deleteUser(id);
		return "";
	}
	
	public int updateRole(Users user){
		if(user.getParentId()==0){
			String id=um.queryId("总经理");
			if(id!=null&&!id.equals("")){
				user.setParentId(Integer.parseInt(id));
			}else{
				user.setParentId(0);
			}
			
		}
		String roleName =rm.queryRoleName(user.getRoleId().getId());
		if(!roleName.equals("员工")){
			user.setState("closed");
		}else{
			user.setState("open");
		}
		if(roleName.equals("人事助理")){
			user.setParentId(Integer.parseInt(um.queryId("人事")));
		}else if(roleName.equals("行政助理")){
			user.setParentId(Integer.parseInt(um.queryId("行政")));
		}
		return um.updateRole(user);
	}
	
	/**
	 * 
	 * @Description: 修改用户密码
	 * @param @param user
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年6月22日
	 */
	public int updateUser(Users user){
		
		return um.updateUser(user);
		
	}
	/**
	 * 
	 * @Description: 返回相同部门的主管或部门经理
	 * @param @param departmentName 部门名称
	 * @param @param roleName 角色名称（职位）
	 * @param @return  
	 * @return List<Users>  返回用户集合
	 * @throws
	 * @author 小白
	 * @date 2017年4月25日
	 */
	public List<Users> queryUser(String departmentName,String roleName){
		return  um.queryUser(departmentName, roleName);
	}
	
	public List<Users> roleUser(Users user){
		return um.roleUser(user);
	}
	
	public Users queryUserInfoOne(int id){
		return um.queryUserInfoOne(id);
	}
	
	public int updateUserInfo(Users user){
		return um.updateUserInfo(user);
	}
	
	public Map<String,Users> searchUserInfo(int page,int rows,Users user){
		int startPage =(page-1)*(rows);
		List<Users> list = um.searchUserInfo(startPage, rows,user);
		int total = um.totalCount();
		Map map = new HashMap();
		map.put("rows",list);
		map.put("total", total);
		return map;
	}
	/**
	 * 
	 * @Description: 根据部门返回部门员工名称
	 * @param @param departmentName
	 * @param @return   
	 * @return List<String>  
	 * @throws
	 * @author 小白
	 * @date 2017年8月9日
	 */
	public List<String> querySubclass(String departmentName){
		return um.querySubclass(departmentName);
	}
	/**
	 * 
	 * @Description: 返回当前部门主管
	 * @param @param departmentName
	 * @param @return   
	 * @return List<String>  
	 * @throws
	 * @author 小白
	 * @date 2017年8月9日
	 */
	public List<String> querySubZhuGuan(String departmentName){
		return um.querySubZhuGuan(departmentName);
	}
	/**
	 * 
	 * @Description: 判断用户是否存在，存在则返回用户密保问题
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author 小白
	 * @date 2017年6月22日
	 */
	public String userExist(String userName){
		String a =um.userExist(userName);
		System.out.println(a);
		return a;
	}
	
	/**
	 * 
	 * @Description: 销权，去除某个用户权限
	 * @param @param id
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年8月7日
	 */
	public int removeRole(int id){
		return um.removeRole(id);
	}
	/**
	 * 
	 * @Description: 修改用户部门
	 * @param @param userId
	 * @param @param departmentId
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年8月9日
	 */
	public int updateDepartment(int userId,int departmentId){
		return um.updateDepartment(userId, departmentId);
	}
	/**
	 * 
	 * @Description: 根据用户名返回用户信息
	 * @param @param userName
	 * @param @return   
	 * @return Users  
	 * @throws
	 * @author 小白
	 * @date 2017年8月10日
	 */
	public Users queryOneUserInfo(String userName){
		return um.queryOneUserInfo(userName);
	}
}
