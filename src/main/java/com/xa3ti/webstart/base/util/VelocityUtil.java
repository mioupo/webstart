package com.xa3ti.webstart.base.util;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import com.xa3ti.webstart.business.entity.Model;
import com.xa3ti.webstart.business.entity.Project;
import com.xa3ti.webstart.business.entity.Property;

/**
 * @author chenhao
 * @see velocity 模板加载工具类
 *
 */
public class VelocityUtil {

	/**
	 * 初始化velocity模板引擎
	 * @param templateRootPath  存放模板文件夹的绝对路径
	 */
	private static void init(String templateRootPath){
		Properties prop = new Properties();
		prop.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,templateRootPath);
		prop.setProperty(Velocity.ENCODING_DEFAULT, "utf-8");
		prop.setProperty(Velocity.INPUT_ENCODING, "utf-8");
		prop.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
		Velocity.init(prop);
	}
	
	/**
	 * 加载模板和数据，合并后返回字符串内容
	 * @param templateRootPath 存放模板文件夹的绝对路径
	 * @param templateName 要加载的模板
	 * @param keyName	模板中的key名称，一般为模板中${keyName},也可以是${obj.key}中的obj
	 * @param obj	要合并的数据
	 * @return
	 */
	public static String megreTemplateAndData(String templateRootPath,String templateName,List<String> keyNameList,Map<String, Object> objMap){
		init(templateRootPath);
		Template template = Velocity.getTemplate(templateName);
		StringWriter sw = new StringWriter();
		VelocityContext context = new VelocityContext();
		for(String keyName : keyNameList){
			context.put(keyName, objMap.get(keyName));
		}
		template.merge(context, sw);
		return sw.toString();
	}
	
	public static void main(String[] str){
		List<String> list = new ArrayList<String>();
		list.add("project");
		list.add("model");
		Project project = new Project();
		project.setIdentify("Xiaohaigou");
		Property property = new Property();
		property.setIdentify("name");
		property.setName("类别名称");
		property.setDescription("类别名称");
		property.setType("String");
		property.setLength(100);
		List<Property> propertyList = new ArrayList<Property>();
		propertyList.add(property);
		Model model = new Model();
		model.setIdentify("Company");
		model.setName("商户");
		model.setProperties(propertyList);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("project", project);
		map.put("model", model);
		String s = megreTemplateAndData("D:/template", "Model.vm", list, map);
		System.out.println(s);
	}
}
