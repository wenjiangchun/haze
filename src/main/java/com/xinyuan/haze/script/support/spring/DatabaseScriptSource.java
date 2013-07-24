package com.xinyuan.haze.script.support.spring;

import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.scripting.ScriptSource;
import org.springframework.util.StringUtils;

import com.xinyuan.haze.script.support.service.DynamicScriptService;

/**
 * 数据库脚本语言资源类，该类通过dynamicScriptService调用存放数据库中脚本语言代码
 * @author Sofar
 *
 */
public class DatabaseScriptSource implements ScriptSource {

	/**
	 * 资源名称
	 */
	private final String scriptName;
	
	/**
	 * 动态脚本业务操作对象，通过该对象增删改查数据库中存放脚本代码
	 */
	private DynamicScriptService dynamicScriptService;
   
	/**
     * 最后更新时间
     */
    private Timestamp lastKnownUpdate;

    private final Object lastModifiedMonitor = new Object();

    public DatabaseScriptSource(String scriptName, DynamicScriptService dynamicScriptService) {
        this.scriptName = scriptName;
        this.dynamicScriptService = dynamicScriptService;
    }

    /* (non-Javadoc)
     * @see org.springframework.scripting.ScriptSource#getScriptAsString()
     */
    public String getScriptAsString() throws IOException {
        synchronized (this.lastModifiedMonitor) {
            this.lastKnownUpdate = retrieveLastModifiedTime();
        }
        return dynamicScriptService.findByScriptName(scriptName).getScriptSource();
    }

    /* (non-Javadoc)
     * @see org.springframework.scripting.ScriptSource#isModified()
     */
    public boolean isModified() {
        synchronized (this.lastModifiedMonitor) {
            Timestamp lastUpdated = retrieveLastModifiedTime();
            return lastUpdated.after(this.lastKnownUpdate);
        }
    }

    /* (non-Javadoc)
     * @see org.springframework.scripting.ScriptSource#suggestedClassName()
     */
    public String suggestedClassName() {
        return StringUtils.stripFilenameExtension(this.scriptName);
    }

    /**
     * 获取脚本最后更新时间
     * @return 脚本更新时间
     */
    private Timestamp retrieveLastModifiedTime() {
        return (Timestamp) dynamicScriptService.findByScriptName(scriptName).getLastUpdated();
    }
    
}
