package com.xa3ti.webstart.business.service.impl;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xa3ti.webstart.base.util.XaDbStatus;
import com.xa3ti.webstart.base.util.XaUtil;
import com.xa3ti.webstart.business.entity.Model;
import com.xa3ti.webstart.business.entity.Project;
import com.xa3ti.webstart.business.entity.Property;
import com.xa3ti.webstart.business.repository.ModelRepository;
import com.xa3ti.webstart.business.repository.ProjectRepository;
import com.xa3ti.webstart.business.repository.PropertyRepository;
import com.xa3ti.webstart.business.service.XaVelocityService;

@Service("XaVelocityServiceImpl")
public class XaVelocityServiceImpl implements XaVelocityService {

	@Autowired
	private PropertyRepository propertyRepository;

	@Autowired
	private ModelRepository modelRepository;
	
	@Autowired
	private ProjectRepository projectRepository;

	private VelocityContext context;

	public XaVelocityServiceImpl() {
		Properties prop = new Properties();
		//String path = this.getServletContext().getRealPath("/");
		//prop.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,path+"/template/");
		String templateRootPath=this.getClass().getResource("/").getPath();		//模板的根路径
//		prop.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,"D://template//");
		prop.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH,templateRootPath);
		prop.setProperty(Velocity.ENCODING_DEFAULT, "utf-8");
		prop.setProperty(Velocity.INPUT_ENCODING, "utf-8");
		prop.setProperty(Velocity.OUTPUT_ENCODING, "utf-8");
		Velocity.init(prop);
		context = new VelocityContext();
	}

	private String initUpperCase(String str){
		return str.toUpperCase().substring(0,1)+str.substring(1);
	}

	private String initLowerCase(String str){
		return str.toLowerCase().substring(0,1)+str.substring(1);
	}
	
	
	public Map<String,String> publishModel(Long projectId,Long modelId) {
		Project project = projectRepository.findByIdAndStatusNot(projectId, XaDbStatus.DB_STATUS_DELETE);
		if(XaUtil.isNotEmpty(project)){
			context.put("project", project);
		}else{
			return null;
		}
		Model model = modelRepository.findByIdAndStatusNot(modelId,
				XaDbStatus.DB_STATUS_DELETE);
		Map<String,String> rt = new HashMap<String,String>();
		if (XaUtil.isNotEmpty(model)) {
			context.put("model", model);
			rt.put(initUpperCase(model.getIdentify())+".java", templateModel());
		}
		return rt;
	}

	
	public Map<String,String> publishRepository(Long projectId,Long modelId) {
		Project project = projectRepository.findByIdAndStatusNot(projectId, XaDbStatus.DB_STATUS_DELETE);
		if(XaUtil.isNotEmpty(project)){
			context.put("project", project);
		}else{
			return null;
		}
		Model model = modelRepository.findByIdAndStatusNot(modelId,
				XaDbStatus.DB_STATUS_DELETE);
		Map<String,String> rt = new HashMap<String,String>();
		if (XaUtil.isNotEmpty(model)) {
			context.put("model", model);
			rt.put(initUpperCase(model.getIdentify())+"Repository.java", templateModel());
		}
		return rt;
	}

	
	public Map<String,String> publishService(Long projectId,Long modelId) {
		Project project = projectRepository.findByIdAndStatusNot(projectId, XaDbStatus.DB_STATUS_DELETE);
		if(XaUtil.isNotEmpty(project)){
			context.put("project", project);
		}else{
			return null;
		}
		Model model = modelRepository.findByIdAndStatusNot(modelId,
				XaDbStatus.DB_STATUS_DELETE);
		Map<String,String> rt = new HashMap<String,String>();
		if (XaUtil.isNotEmpty(model)) {
			context.put("model", model);
			rt.put(initUpperCase(model.getIdentify())+"Service.java", templateService());
		}
		return rt;
	}

	
	public Map<String,String> publishServiceImpl(Long projectId,Long modelId) {
		Project project = projectRepository.findByIdAndStatusNot(projectId, XaDbStatus.DB_STATUS_DELETE);
		if(XaUtil.isNotEmpty(project)){
			context.put("project", project);
		}else{
			return null;
		}
		Model model = modelRepository.findByIdAndStatusNot(modelId,
				XaDbStatus.DB_STATUS_DELETE);
		Map<String,String> rt = new HashMap<String,String>();
		if (XaUtil.isNotEmpty(model)) {
			context.put("model", model);
			rt.put(initUpperCase(model.getIdentify())+"ServiceImpl.java", templateServiceImpl());
		}
		return rt;
	}

	
	public Map<String,String> publishController(Long projectId,Long modelId) {
		Project project = projectRepository.findByIdAndStatusNot(projectId, XaDbStatus.DB_STATUS_DELETE);
		if(XaUtil.isNotEmpty(project)){
			context.put("project", project);
		}else{
			return null;
		}
		Model model = modelRepository.findByIdAndStatusNot(modelId,
				XaDbStatus.DB_STATUS_DELETE);
		Map<String,String> rt = new HashMap<String,String>();
		if (XaUtil.isNotEmpty(model)) {
			context.put("model", model);
			rt.put(initUpperCase(model.getIdentify())+"Controller.java", templateController());
		}
		return rt;
	}

	
	public Map<String,String> publishProject(Long projectId) {
		Project project = projectRepository.findByIdAndStatusNot(projectId, XaDbStatus.DB_STATUS_DELETE);
		if(XaUtil.isNotEmpty(project)){
			context.put("project", project);
		}else{
			return null;
		}
		List<Model> models = modelRepository.findByProjectIdAndStatusNot(projectId,
				XaDbStatus.DB_STATUS_DELETE);
		Map<String,String> rt = new HashMap<String,String>();
		if (XaUtil.isNotEmpty(models)) {
			for(Model model:models){
				removePropertyDelStatus(model.getProperties());
				context.put("model", model);
				rt.put(initUpperCase(model.getIdentify())+".java", templateModel());
				rt.put(initUpperCase(model.getIdentify())+"Repository.java", templateRepository());
				rt.put(initUpperCase(model.getIdentify())+"Service.java", templateService());
				rt.put(initUpperCase(model.getIdentify())+"ServiceImpl.java", templateServiceImpl());
				rt.put(initUpperCase(model.getIdentify())+"Controller.java", templateController());
				rt.put(initUpperCase(model.getIdentify())+"Vo.java", templateVo());
				rt.put(initLowerCase(model.getIdentify())+"List.html", templateHtmlList());
				rt.put(initLowerCase(model.getIdentify())+"Edit.html", templateHtmlEdit());
				rt.put(initUpperCase("Api"+model.getIdentify())+"Controller.java", merge("ApiModelController.vm"));
				rt.put(initUpperCase("Test"+model.getIdentify())+"Service.java", merge("TestModelService.vm"));
				rt.put(initUpperCase("Test"+model.getIdentify())+"Controller.java", merge("TestModelController.vm"));
			}
		}
		return rt;
	}
	private void removePropertyDelStatus(List<Property> properties){
		Iterator<Property> iter=properties.iterator();
		while(iter.hasNext()){
			Property property = iter.next();
			if(XaDbStatus.DB_STATUS_DELETE==property.getStatus()){
				iter.remove();
			}
		}
	}
	
	public Map<String, String> publishVo(Long projectId, Long modelId) {
		Project project = projectRepository.findByIdAndStatusNot(projectId, XaDbStatus.DB_STATUS_DELETE);
		if(XaUtil.isNotEmpty(project)){
			context.put("project", project);
		}else{
			return null;
		}
		Model model = modelRepository.findByIdAndStatusNot(modelId,
				XaDbStatus.DB_STATUS_DELETE);
		Map<String,String> rt = new HashMap<String,String>();
		if (XaUtil.isNotEmpty(model)) {
			context.put("model", model);
			rt.put(initUpperCase(model.getIdentify())+"Vo.java", templateVo());
		}
		return rt;
	}
	private String merge(String templateFile) {
		Template template = null;
		try {
			template = Velocity.getTemplate(templateFile);
		} catch (ResourceNotFoundException rnfe) {
			System.err.println(rnfe.getMessage());
		} catch (ParseErrorException pee) {
			System.err.println(pee.getMessage());
		} catch (MethodInvocationException mie) {
			System.err.println(mie.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

		StringWriter sw = new StringWriter();

		template.merge(context, sw);
		return sw.getBuffer().toString();
	}

	public String templateModel() {
		return merge("Model.vm");
	}

	public String templateRepository() {
		return merge("ModelRepository.vm");
	}

	public String templateService() {
		return merge("ModelService.vm");
	}

	public String templateServiceImpl() {
		return merge("ModelServiceImpl.vm");
	}

	public String templateController() {
		return merge("ModelController.vm");
	}

	public String templateVo() {
		return merge("ModelVo.vm");
	}

	public String templateHtmlList() {
		return merge("ModelList.vm");
	}

	public String templateHtmlEdit() {
		return merge("ModelEdit.vm");
	}



	private Map<String, String>  changeSet(Long projectId, Long modelId,String sName,String eName,String type){
		Project project = projectRepository.findByIdAndStatusNot(projectId, XaDbStatus.DB_STATUS_DELETE);
		if(XaUtil.isNotEmpty(project)){
			context.put("project", project);
		}else{
			return null;
		}
		Model model = modelRepository.findByIdAndStatusNot(modelId,
				XaDbStatus.DB_STATUS_DELETE);
		Map<String,String> rt = new HashMap<String,String>();
		if (XaUtil.isNotEmpty(model)) {
			context.put("model", model);
			String content = "";
			if(type.equals("Api")){
				content= merge("ApiModelController.vm");
			}else if(type.equals("TestService")){
				content=merge("TestModelService.vm");
			}else if(type.equals("TestController")){
				content=merge("TestModelController.vm");
			}
			rt.put(sName+initUpperCase(model.getIdentify())+eName+".java", content);
		}
		return rt;
	}
	
	public Map<String, String> publishApi(Long projectId, Long modelId) {
		return changeSet(projectId,modelId,"Api","Controller","Api");
	}

	
	public Map<String, String> publishTestService(Long projectId, Long modelId) {
		return changeSet(projectId,modelId,"Test","Service","TestService");
	}

	
	public Map<String, String> publishTestController(Long projectId,
			Long modelId) {
		return changeSet(projectId,modelId,"Test","Controller","TestController");
	}

	
	public Map<String, String> publishTestProject(Long projectId) {
		Project project = projectRepository.findByIdAndStatusNot(projectId, XaDbStatus.DB_STATUS_DELETE);
		if(XaUtil.isNotEmpty(project)){
			context.put("project", project);
		}else{
			return null;
		}
		List<Model> models = modelRepository.findByProjectIdAndStatusNot(projectId,
				XaDbStatus.DB_STATUS_DELETE);
		Map<String,String> rt = new HashMap<String,String>();
		if (XaUtil.isNotEmpty(models)) {
			for(Model model:models){
				removePropertyDelStatus(model.getProperties());
				context.put("model", model);
				rt.put("Test"+initUpperCase(model.getIdentify())+"Service.java", merge("TestModelService.vm"));
				rt.put("Test"+initUpperCase(model.getIdentify())+"Controller.java", merge("TestModelController.vm"));
			}
		}
		return rt;
	}
	


}
