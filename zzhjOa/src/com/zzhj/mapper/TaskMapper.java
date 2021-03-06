package com.zzhj.mapper;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.ibatis.annotations.Param;

import com.zzhj.po.Task;

/**
 * 
 * @author asus
 * 工作任务dao层接口
 */
public interface TaskMapper {
	/**
	 * 
	 * @Description:添加一个工作任务信息
	 * @param @param t
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年6月12日
	 */
	int addTask(Task t);
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
	int transmitTask(@Param("taskId")int taskId,@Param("userName")String userName,@Param("successDate")String successDate,@Param("implementDate")String implementDate);
	 /**
	  * 
	  * @Description: 开始执行一个任务
	  * @param @param taskId
	  * @param @param startDate
	  * @param @return   
	  * @return int  
	  * @throws
	  * @author 小白
	  * @date 2017年6月12日
	  */
	int acceptTask(@Param("taskId")int taskId);
	
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
	int successTask(@Param("taskId")int taskId,@Param("successDate")String successDate);
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
	int updateTaskSpeed(@Param("taskId")int taskId,@Param("speed")int speed,@Param("taskPhase")String taskPhase);
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
	List<Task> queryAll(@Param("startPage")int startPage,@Param("rows")int rows,@Param("userName")String userName);
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
	List<Task> queryOwn(@Param("startPage")int startPage,@Param("rows")int rows,@Param("userName")String userName);
	/**
	 * 
	 * @Description: 返回任务条数
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年6月12日
	 */
	int totalCount();
	/**
	 * 
	 * @Description: 返回自己的任务条数
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年6月12日
	 */
	int totalCOuntOwn(String name);
	/**
	 * 
	 * @Description: 修改任务信息某些字段
	 * @param @param t
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年6月13日
	 */
	int updateTask(Task t);
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
	int deleteTask(int taskId);
	/**
	 * 
	 * @Description: 根据id返回接收人姓名
	 * @param @param taskId
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author 小白
	 * @date 2017年6月22日
	 */
	String queryRecipient(int taskId);
	/**
	 * 
	 * @Description: 返回当执行人是当前用户的信息
	 * @param @param userName
	 * @param @return   
	 * @return List<Task>  
	 * @throws
	 * @author 小白
	 * @date 2017年6月22日
	 */
	List<Task> queryImplementOwn(String userName);
	/**
	 * 
	 * @Description: 修改任务时间
	 * @param @param t
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年7月5日
	 */
	int updateTaskTime(Task t);
	/**
	 * 
	 * @Description: 返回任务进度为100%的数据
	 * @param @param startPage
	 * @param @param rows
	 * @param @return   
	 * @return List<Task>  
	 * @throws
	 * @author 小白
	 * @date 2017年7月5日
	 */
	List<Task> querySuccessTask(@Param("startPage")int startPage,@Param("rows")int rows);
	/**
	 * 
	 * @Description: 返回完成任务的总条数
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年7月5日
	 */
	int totalCountSuccess();
	/**
	 * 
	 * @Description: 质量检测合格
	 * @param @param id
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年7月5日
	 */
	int qualifiedTask(@Param("id")int id,@Param("userName")String userName,@Param("message")String message);
	/**
	 * 
	 * @Description: 质量检测不合格
	 * @param @param id
	 * @param @param userName
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年7月5日
	 */
	int UnqualifiedTask(@Param("id")int id,@Param("userName")String userName);
	
	/**
	 * 
	 * @Description: 根据id返回任务指定结束时间
	 * @param @param id
	 * @param @return   
	 * @return String  
	 * @throws
	 * @author 小白
	 * @date 2017年7月5日
	 */
	String queryOverDate(int id);
	/**
	 * 
	 * @Description: 查询所有部门的任务信息
	 * @param @param rows
	 * @param @param startPage
	 * @param @param departmentId
	 * @param @return   
	 * @return List<Task>  
	 * @throws
	 * @author 小白
	 * @date 2017年8月8日
	 */
	List<Task> departmentQueryAll(@Param("rows")int rows,@Param("startPage")int startPage,@Param("departmentId")Integer departmentId);
	/**
	 * 
	 * @Description: 查询某个部门的任务总条数
	 * @param @param departmentId
	 * @param @return   
	 * @return int  
	 * @throws
	 * @author 小白
	 * @date 2017年8月8日
	 */
	int totalCountDepartment(Integer departmentId);
}
