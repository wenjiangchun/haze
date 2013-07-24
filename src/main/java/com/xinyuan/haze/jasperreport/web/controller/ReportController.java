package com.xinyuan.haze.jasperreport.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xinyuan.haze.jasperreport.view.JasperReportView;

@Controller
public class ReportController {

	@RequestMapping(value = "/show-report")
	public ModelAndView showReport(String reportName, String format) {
		ModelAndView mv = new ModelAndView();
		Date now = new Date();
		mv.setViewName(reportName); // 要调用的jasperreports的模板文件名(不包括后缀)，该文件名必须以-report结尾
		mv.addObject(JasperReportView.DEFAULT_FORMAT_KEY, format); // 显示格式：html、pdf、xls、csv
		mv.addObject(JasperReportView.ATTACHEMT_FILE_NAME_KEY, now);// 当为pdf、xls、csv时的附件名
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("reportName", reportName);
		params.put("format", format);
		mv.addAllObjects(params);
		return mv;
	}
}
