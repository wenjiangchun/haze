package com.xinyuan.haze.jasperreport.view;

import java.util.Map;
import java.util.Properties;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperPrint;
import org.springframework.web.servlet.view.jasperreports.JasperReportsMultiFormatView;

/**
 *
 * @author sofar
 *
 */
public class JasperReportView extends JasperReportsMultiFormatView {

	public static final String ATTACHEMT_FILE_NAME_KEY = "attachmentFileName";

	@Override
	protected void renderReport(JasperPrint populatedReport,
			Map<String, Object> model, HttpServletResponse response)
			throws Exception {
		Object format = model.get(DEFAULT_FORMAT_KEY);
		if (format == null) {
			throw new IllegalArgumentException(
					"model中未找到指定的输出格式(format:html、pdf、xls、csv)");
		}
		if (!format.equals("html")) {
			Object attachmentFileName = model.get(ATTACHEMT_FILE_NAME_KEY);
			if (attachmentFileName == null) {
				throw new IllegalArgumentException(
						"model中未指定输出文件名(attachmentFileName)");
			}
			Properties contentDispositionMappings = getContentDispositionMappings();
			contentDispositionMappings.put(format.toString(), "attachment; filename="
							+ attachmentFileName + "." + format);
		}
		super.renderReport(populatedReport, model, response);
	}

}
