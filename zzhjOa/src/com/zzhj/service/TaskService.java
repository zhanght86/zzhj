package com.zzhj.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.collections.SynchronizedQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zzhj.entityCustom.Message;
import com.zzhj.entityCustom.MessageType;
import com.zzhj.mapper.TaskMapper;
import com.zzhj.mapper.UsersMapper;
import com.zzhj.po.Task;
import com.zzhj.webSocket.ServerHandler;

import utils.DateFormater;

@Service
public class TaskService {
		
	
	@Autowired
	private  TaskMapper tm;
	
	@Autowired
	private UsersMapper um;
	
	/**
	 * 
	 * @Description:添加一个工作任务信息（并下达给主管或者部门经理）
	 * @param @param t
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年6月12日
	 */
	public int addTask(Task t){
		Date d = new Date();
		String taskDate=DateFormater.format(d);
		t.setTaskDate(taskDate);
		int result=tm.addTask(t);
		if(result>0){
			Message mes = new Message();
			mes.setFrom(t.getUserName());
			mes.setTargetName("下达任务");
			mes.setViewTarget("task/queryOwnTask.html");
			mes.setType(MessageType.task);
			mes.setContentId(t.getId());
			mes.setTheme("您有未处理的任务消息");
			send(mes,t.getRecipient());
		}

		return result;
	}
	/**
	 * 
	 * @Description: 下达一个任务
	 * @param @param taskId
	 * @param @param userId
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年6月12日
	 */
	public int transmitTask(int taskId,String userName,String successDate,String implementDate){
		int result=tm.transmitTask(taskId, userName,successDate,implementDate);
		String recipient=tm.queryRecipient(taskId);
		if(result>0){
			Message mes = new Message();
			mes.setFrom(recipient);
			mes.setTargetName("个人任务");
			mes.setViewTarget("task/OwnTask.html");
			mes.setType(MessageType.task);
			mes.setContentId(taskId);
			mes.setTheme("您有未处理的任务消息");
			send(mes,userName);
		}
		return result;
	}
	 /**
	  * 
	  * @Description: 接收，开始执行一个任务
	  * @param @param taskId
	  * @param @param startDate
	  * @param @return   
	  * @return int  
	  * @throws
	  * @author 小白
	  * @date 2017年6月12日
	  */
	public int acceptTask(int taskId){
		return tm.acceptTask(taskId);
	}
	
	/**
	 * 
	 * @Description: 完成任务
	 * @param @param taskId
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年6月12日
	 */
	public int successTask(int taskId){
		Date d = new Date();
		String successDate=DateFormater.format(d);
		return tm.successTask(taskId, successDate);
	}
	/**
	 * 
	 * @Description: 修改工作进度
	 * @param @param taskId
	 * @param @param speed
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年6月12日
	 */
	public int updateTaskSpeed(int taskId,int speed,String taskPhase,String userName){
		
		int resoult =tm.updateTaskSpeed(taskId, speed,taskPhase);
		if(resoult>0&&speed>=100){
			Message mes = new Message();
			mes.setFrom(userName);
			mes.setTargetName("工作质量检测");
			mes.setViewTarget("task/qualityTask.html");
			mes.setType(MessageType.task);
			mes.setContentId(taskId);
			mes.setTheme("您有未处理的任务消息");
			List<String> list =um.queryDepartmentAndRole("测绘部","质检");
			for(String name : list){
				send(mes,name);
			}
			
		}		
		return resoult; 
	}
	/**
	 * 
	 * @Description: 查看发布人是当前用户的工作任务信息
	 * @param @param startPage
	 * @param @param rows
	 * @param @return   
	 * @return List<Task>  
	 * @throws
	 * @author 小白
	 * @date 2017年6月12日
	 */
	public Map<String,Object> queryAll(int page,int rows,String userName){
		int startPage=(page-1)*rows;
		List<Task> list= tm.queryAll(startPage, rows,userName);
		int total=tm.totalCount();
		Map<String,Object> map = new HashMap();
		map.put("rows", list);
		map.put("total", total);
		return map;
	}
	/**
	 * 
	 * @Description: 查看属于自己的任务
	 * @param @param startPage
	 * @param @param rows
	 * @param @return   
	 * @return List<Task>  
	 * @throws
	 * @author 小白
	 * @date 2017年6月12日
	 */
	public Map<String,Object> queryOwn(int page,int rows,String userName){
		int startPage=(page-1)*rows;
		List<Task> list= tm.queryOwn(startPage, rows, userName);
		int total =tm.totalCOuntOwn(userName);
		Map<String,Object> map = new HashMap();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	/**
	 * 
	 * @Description: 修改任务信息的某些字段
	 * @param @param t
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年6月13日
	 */
	public int updateTask(Task t){
		return tm.updateTask(t);
	}
	/**
	 * 
	 * @Description: 删除一条任务
	 * @param @param taskId
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年6月21日
	 */
	public int deleteTask(int taskId){
		return tm.deleteTask(taskId);
	}
	
	/**
	 * 
	 * @Description: 返回当前用户执行人是自己的信息
	 * @param @param userName
	 * @param @return   
	 * @return List<Task>  
	 * @throws
	 * @author 小白
	 * @date 2017年6月22日
	 */
	public List<Task> queryImplementOwn(String userName){
		return tm.queryImplementOwn(userName);
	}
	public int updateTaskTime(Task t){
		return tm.updateTaskTime(t);
	}
	/**
	 * 
	 * @Description: 返回任务进度为100%的数据
	 * @param @param page
	 * @param @param rows
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author 小白
	 * @date 2017年7月5日
	 */
	public Map<String,Object> querySuccessTask(int page,int rows){
		int startPage=(page-1)*rows;
		List<Task> list= tm.querySuccessTask(startPage, rows);
		int total=tm.totalCountSuccess();
		Map<String,Object> map = new HashMap();
		map.put("rows", list);
		map.put("total", total);
		return map;
	}
	
	public int qualifiedTask(int id,String userName){
		String date =DateFormater.format(new Date());
		String overDate=tm.queryOverDate(id);
		int resoult =date.compareTo(overDate);
		String message="";
		if(resoult>0){
			message="延期完成";
		}else if(resoult==0){
			message="按时完成";
		}else{
			message="提前完成";
		}
		return tm.qualifiedTask(id, userName, message);
	}
	
	/**
	 * 
	 * @Description: 质检不合格
	 * @param @param userName
	 * @param @param id
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年7月5日
	 */
	public int UnqualifiedTask(String userName,int id){
		return tm.UnqualifiedTask(id, userName);
	}
	
	/**
	 * 
	 * @Description: 通知方法
	 * @param @param mes
	 * @param @param userName   
	 * @return void  
	 * @throws
	 * @author 小白
	 * @date 2017年6月22日
	 */
	private void send(Message mes,String userName){
		ServerHandler.send(userName, mes);
	}
	
	/**
	 * 
	 * @Description: 返回所有部门的任务信息
	 * @param @param rows
	 * @param @param page
	 * @param @param departmentId
	 * @param @return   
	 * @return Map<String,Object>  
	 * @throws
	 * @author 小白
	 * @date 2017年8月8日
	 */
	public Map<String,Object> departmentQueryAll(int rows,int page,Integer departmentId){
		int startPage=(page-1)*rows;
		List<Task> list= tm.departmentQueryAll(rows, startPage, departmentId);
		int total =tm.totalCountDepartment(departmentId);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("total", total);
		map.put("rows", list);
		return map;
	}
	
	
}
