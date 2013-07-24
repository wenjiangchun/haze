package com.xinyuan.haze.web.ui.bootstrap;

import java.util.Set;

import com.xinyuan.haze.web.ui.bootstrap.component.Button;
import com.xinyuan.haze.web.ui.bootstrap.component.Component;
import com.xinyuan.haze.web.ui.bootstrap.component.Link;
import com.xinyuan.haze.web.ui.bootstrap.component.Span;
import com.xinyuan.haze.web.ui.bootstrap.css.SpanType;

/**
 * BootStrap组建生成类
 * @author Sofar
 *
 */
public class BootStrapComponentUtils {

	/**
	 * 根据提供Id和文本text创建Span组件，其中SpanType默认为SpanType.DEFAULT
	 * @param id 组件Id
	 * @param text 组件文本内容
	 * @return Component
	 */
	public static Component createSpan(String id, String text) {
		 return new Span(id, text);
	}
	
	/**
	 * 根据提供Id,Span类别，文本text创建Span组件，其中生成的SpanType为SpanType.DEFAULT加上spanType
	 * @param id 组件Id
	 * @param spanType Span类别
	 * @param text 组件文本内容
	 * @return Component
	 */
	public static Component createSpan(String id, SpanType spanType, String text) {
	   return new Span(id,spanType,text);
	}
	
	/**
	 * 创建Span组件，其中Span组件的SpanType为SpanType.DEFAULT,文本内容为text
	 * @param text 文本内容
	 * @return Component
	 */
	public static Component createSpan(String text) {
		   return new Span(text);
	}
	
	public static Component createButton(String id, Set<String> classNames,String clickEvent,String text) {
		return new Button(id, classNames, clickEvent, text);
	}
	public static Component createButton(String text) {
		return new Button(null, null, null, text);
	}
	public static Component createButton(String id, String text) {
		return new Button(id, null, null, text);
	}
	public static Component createButton(String id,Set<String> classNames, String text) {
		return new Button(id, classNames, null, text);
	}
	
	public static Component createLink(String id, String href, Set<String> classNames, String text) {
		return new Link(id, href, classNames, null, text);
	}
	
	public static Component createLink(String id, String text) {
		return new Link(id, null, null, null, text);
	}
	public static Component createLink(String id, String href, String text) {
		return new Link(id, href, null, null, text);
	}
	public static Component createLink(String id, String href, String clickEvent, String text) {
		return new Link(id, href, null, clickEvent, text);
	}
}
