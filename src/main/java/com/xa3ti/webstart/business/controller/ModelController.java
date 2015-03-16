package com.xa3ti.webstart.business.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xa3ti.webstart.base.util.WebUitl;
import com.xa3ti.webstart.base.util.XaResult;
import com.xa3ti.webstart.base.util.XaUtil;
import com.xa3ti.webstart.business.entity.Model;
import com.xa3ti.webstart.business.service.ModelService;
import com.xa3ti.webstart.business.service.ProjectService;
import com.xa3ti.webstart.business.service.XaVelocityService;

/**
 * @Title: ModelController.java
 * @Package com.xa3ti.webstart.business.controller
 * @Description: 模型控制器
 * @author hchen
 * @date 2014年10月14日 下午6:35:35
 * @version V1.0
 */
@Controller
@RequestMapping("model")
public class ModelController {

	@Autowired
	private ModelService modelService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private XaVelocityService xaVelocityService;

	static String PROJECTS_PATH = "D://template//projects//";

	// public ModelController(){
	// //xaVelocityService.init("");
	// }
	/**
	 * @Title: findModelPage
	 * @Description: 分页查询model
	 * @param nextPage
	 * @param pageSize
	 * @param sortDate
	 * @param jsonFilter
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public XaResult<Page<Model>> findModelPage(
			@RequestParam(defaultValue = "0") Integer nextPage,
			@RequestParam(defaultValue = "10") Integer pageSize,
			String sortDate, String jsonFilter) {
		Pageable pageable = WebUitl.buildPageRequest(nextPage, pageSize,
				sortDate);
		Map<String, Object> filterParams = WebUitl.getParametersStartingWith(
				jsonFilter, "search_");
		return modelService.findModelByFilter(filterParams, pageable);
	}

	/**
	 * @Title: findModelById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "{modelId}", method = RequestMethod.GET)
	public XaResult<Model> findModelById(@PathVariable Long modelId) {
		return modelService.findModel(modelId);
	}

	/**
	 * @Title: save
	 * @Description: 新增一条实体
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.POST)
	public XaResult<Model> save(@RequestBody Model model,
			HttpServletRequest request) {
		Long userId = WebUitl.getCmsLoginedUserId(request);
		model.setCreateUser(userId);
		model.setModifyUser(userId);
		return modelService.createModel(model);
	}

	/**
	 * @Title: updateModel
	 * @Description: 修改一条实体
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(method = RequestMethod.PUT)
	public XaResult<Model> updateModel(@RequestBody Model model,
			HttpServletRequest request) {
		Long userId = WebUitl.getCmsLoginedUserId(request);
		model.setModifyUser(userId);
		return modelService.updateModel(model);
	}

	/**
	 * @Title: deleteModelById
	 * @Description: 删除一个实体
	 * @param modelId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "{modelId}", method = RequestMethod.DELETE)
	public XaResult<Model> deleteModelById(@PathVariable Long modelId,
			HttpServletRequest request) {
		Long userId = WebUitl.getCmsLoginedUserId(request);
		return modelService.deleteModel(modelId, userId);
	}

	@ResponseBody
	@RequestMapping(value = "pub/{modelId}", method = RequestMethod.PUT)
	public XaResult<Model> publishModelById(@PathVariable Long modelId,
			HttpServletRequest request) {
		Long userId = WebUitl.getCmsLoginedUserId(request);
		return modelService.publishModel(modelId, userId);
	}

	/**
	 * @Title: findModelById
	 * @Description: 根据ID查找单条实体
	 * @param modelId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "{modelId}/publish/{type}", method = RequestMethod.GET)
	public void publicModel(HttpServletResponse response,
			@PathVariable Long modelId, @PathVariable String type) {
		Long projectId = modelService.findModel(modelId).getObject()
				.getProjectId();
		OutputStream ops = null;
		try {
			ops = response.getOutputStream();
			Map<String, String> fm = null;
			// switch (type) {
			// case "Model":
			// fm = xaVelocityService.publishModel(projectId, modelId);
			// break;
			// case "Repository":
			// fm = xaVelocityService.publishRepository(projectId, modelId);
			// break;
			// case "Service":
			// fm = xaVelocityService.publishService(projectId, modelId);
			// break;
			// case "ServiceImpl":
			// fm = xaVelocityService.publishServiceImpl(projectId, modelId);
			// break;
			// case "Controller":
			// fm = xaVelocityService.publishController(projectId, modelId);
			// break;
			// case "Project":
			// fm = xaVelocityService.publishProject(projectId);
			// }
			fm = xaVelocityService.publishModel(projectId, modelId);
			if (XaUtil.isEmpty(fm)) {
				return;
			}
			// if ("Project".equals(type)) {
			// Project prj = projectService.findProject(projectId).getObject();
			// String root = PROJECTS_PATH + prj.getIdentify();
			// XaUtil.makeDir(new File(root + "/com/xa3ti/"
			// + prj.getIdentify().toLowerCase() + "/business/entity/"));
			// XaUtil.makeDir(new File(root + "/com/xa3ti/"
			// + prj.getIdentify().toLowerCase()
			// + "/business/controller/"));
			// XaUtil.makeDir(new File(root + "/com/xa3ti/"
			// + prj.getIdentify().toLowerCase()
			// + "/business/repository/"));
			// XaUtil.makeDir(new File(root + "/com/xa3ti/"
			// + prj.getIdentify().toLowerCase()
			// + "/business/service/impl/"));
			// Set<String> fset = fm.keySet();
			// for (String fn : fset) {
			// wirteToFile(prj.getIdentify().toLowerCase(), root, fn,
			// fm.get(fn));
			// }
			// String fileName = prj.getIdentify() + ".zip";
			// try {
			// ZipUtils.zip(root, PROJECTS_PATH + fileName);
			// } catch (Exception e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// File zip = new File(PROJECTS_PATH + fileName);
			// FileInputStream in = new FileInputStream(zip);
			// byte[] buffer = new byte[(int) zip.length()];
			// in.read(buffer);
			//
			// ops.write(buffer);
			// response.setContentType("application/zip; charset=utf-8");
			// response.setHeader("Content-Disposition",
			// "attachment; filename=" + fileName);
			// ops.flush();
			// in.close();
			// } else {
			String fileNam = fm.keySet().iterator().next();
			ops.write(fm.get(fileNam).getBytes());
			response.setContentType("text/plain; charset=utf-8");
			response.setHeader("Content-Disposition", "attachment; filename="
					+ fileNam);
			ops.flush();
			// }

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ops != null) {
					ops.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void wirteToFile(String projIde, String rootpath, String fileName,
			String content) throws IOException {
		if(fileName.indexOf("Vo") > -1){
			XaUtil.WriteFile(content, rootpath + "/com/xa3ti/" + projIde
					+ "/remote/vo/", fileName);
		}else if (fileName.indexOf("Api") > -1) {
			XaUtil.WriteFile(content, rootpath + "/com/xa3ti/" + projIde
					+ "/remote/controller/", fileName);
		}else if (fileName.indexOf("ServiceImpl") > -1) {
			XaUtil.WriteFile(content, rootpath + "/com/xa3ti/" + projIde
					+ "/business/service/impl/", fileName);
		} else if (fileName.indexOf("Service") > -1) {
			XaUtil.WriteFile(content, rootpath + "/com/xa3ti/" + projIde
					+ "/business/service", fileName);
		} else if (fileName.indexOf("Controller") > -1) {
			XaUtil.WriteFile(content, rootpath + "/com/xa3ti/" + projIde
					+ "/business/controller/", fileName);
		} else if (fileName.indexOf("Repository") > -1) {
			XaUtil.WriteFile(content, rootpath + "/com/xa3ti/" + projIde
					+ "/business/repository/", fileName);
		} else {
			XaUtil.WriteFile(content, rootpath + "/com/xa3ti/" + projIde
					+ "/business/entity/", fileName);
		}

	}
}
