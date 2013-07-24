package com.xinyuan.haze.script.support.spring;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.ResourceLoader;
import org.springframework.scripting.ScriptSource;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.scripting.support.ScriptFactoryPostProcessor;
import org.springframework.scripting.support.StaticScriptSource;

import com.xinyuan.haze.script.support.service.DynamicScriptService;

/**
 * 数据库脚本资源工厂处理类，默认的ScriptFactoryPostProcessor只处理内联和基于资源的脚本，
 * 通过扩展ScriptFactoryPostProcessor并覆盖 convertToScriptSource()方法，
 * 从而使用 DatabaseScriptSource从数据库装载脚本。
 * 
 * @author Sofar
 *
 */
public class DatabaseScriptFactoryPostProcessor extends ScriptFactoryPostProcessor {

	/**
	 * 脚本资源源代码定位符
	 */
	public static final String DATABASE_SCRIPT_PREFIX = "database/";

	private DynamicScriptService dynamicScriptService;

	@Required
	public void setDynamicScriptService(DynamicScriptService dynamicScriptService) {
		this.dynamicScriptService = dynamicScriptService;
	}

	@Override
	protected ScriptSource convertToScriptSource(String beanName,
			String scriptSourceLocator, ResourceLoader resourceLoader) {
		if (scriptSourceLocator.startsWith(INLINE_SCRIPT_PREFIX)) {
			return new StaticScriptSource(
					scriptSourceLocator.substring(INLINE_SCRIPT_PREFIX.length()),beanName);
		} else if (scriptSourceLocator.startsWith(DATABASE_SCRIPT_PREFIX)) {
			return new DatabaseScriptSource(
					scriptSourceLocator.substring(DATABASE_SCRIPT_PREFIX.length()), dynamicScriptService);
		} else {
			return new ResourceScriptSource(resourceLoader.getResource(scriptSourceLocator));
		}
	}
}
