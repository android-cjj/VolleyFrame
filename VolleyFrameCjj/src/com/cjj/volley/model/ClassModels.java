package com.cjj.volley.model;

/**
 * 存放类的实体类
 * @author cjj
 *
 */
public class ClassModels {
	public String classTitle;
	public Class<?> name;
	
	public ClassModels(String classTitle, Class<?> name)
	{
		this.classTitle = classTitle;
		this.name = name;
	}

	public String toString() 
	{
		return classTitle;
	}
}
