package com.xinyuan.haze.web.ui.datatable;

import java.util.List;

import org.springframework.data.domain.Page;

/**
 * DataTable表格分页类
 * @author Sofar
 *
 */
public class DataTablePage {

	private String sEcho;
	
	private int iTotalRecords;
	
	private int iTotalDisplayRecords;
	
	/**
	 * 分页数据
	 */
	private List<String[]> aaData;

	public DataTablePage() {
		
	}
	
	public DataTablePage(String sEcho, int iTotalRecords,
			int iTotalDisplayRecords) {
		super();
		this.sEcho = sEcho;
		this.iTotalRecords = iTotalRecords;
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public List<String[]> getAaData() {
		return aaData;
	}

	public void setAaData(List<String[]> aaData) {
		this.aaData = aaData;
	}

	
	/**
	 * 根据Page对象和查询参数对象生成DataTable分页对象
	 * @param page 从数据库查询数据分页对象
	 * @param dataTableParames 分页参数 主要设置前台传递sEcho值
	 * @return DataTablePage
	 */
	public static DataTablePage newDataTablePage(Page<?> page,DataTableParames dataTableParames) {
		int totalRecords = Integer.parseInt(String.valueOf(page.getTotalElements()));
		return new DataTablePage(dataTableParames.getsEcho(),totalRecords,totalRecords);
	}
}
