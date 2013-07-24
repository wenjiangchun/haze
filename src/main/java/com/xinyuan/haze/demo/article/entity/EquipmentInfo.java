package com.xinyuan.haze.demo.article.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.xinyuan.haze.system.entity.Group;

/**
 * 装备信息实体类
 * 
 * @author 李震
 * 
 */
@Entity
@Table(name = "EQU_INFO")
public class EquipmentInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private String equModel;

	private Double equPrice;

	private Integer equCount;

	private String equNumber;
	
	private String equBrand;
	
	private Long equPurchaseDate;
	
	private Long equProduceDate;
	
	@ManyToOne
	@JoinColumn(name = "GROUP_ID")
	private Group group;
	
	private String equExpiredDate;
	
	private String equRegisterDate;
	
	private String equRegistrant;
	
	private String equManufacturer;
	
	private String equPlateNumber;
	
	private String equOwnerGroup;
	
	private String equUsedGroup;
	
	private String equCarModel;
	
	private String equEngineNumber;
	
	private String equFrameNumber;
	
	private String remark;

	public EquipmentInfo(){
		
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getEquModel() {
		return equModel;
	}

	public void setEquModel(String equModel) {
		this.equModel = equModel;
	}

	public Double getEquPrice() {
		return equPrice;
	}

	public void setEquPrice(Double equPrice) {
		this.equPrice = equPrice;
	}

	public Integer getEquCount() {
		return equCount;
	}

	public void setEquCount(Integer equCount) {
		this.equCount = equCount;
	}

	public String getEquNumber() {
		return equNumber;
	}

	public void setEquNumber(String equNumber) {
		this.equNumber = equNumber;
	}

	public String getEquBrand() {
		return equBrand;
	}

	public void setEquBrand(String equBrand) {
		this.equBrand = equBrand;
	}

	public Long getEquPurchaseDate() {
		return equPurchaseDate;
	}

	public void setEquPurchaseDate(Long equPurchaseDate) {
		this.equPurchaseDate = equPurchaseDate;
	}

	public Long getEquProduceDate() {
		return equProduceDate;
	}
	public void setEquProduceDate(Long equProduceDate) {
		this.equProduceDate = equProduceDate;
	}
	
	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getEquExpiredDate() {
		return equExpiredDate;
	}

	public void setEquExpiredDate(String equExpiredDate) {
		this.equExpiredDate = equExpiredDate;
	}

	public String getEquRegisterDate() {
		return equRegisterDate;
	}

	public void setEquRegisterDate(String equRegisterDate) {
		this.equRegisterDate = equRegisterDate;
	}

	public String getEquRegistrant() {
		return equRegistrant;
	}

	public void setEquRegistrant(String equRegistrant) {
		this.equRegistrant = equRegistrant;
	}

	public String getEquManufacturer() {
		return equManufacturer;
	}

	public void setEquManufacturer(String equManufacturer) {
		this.equManufacturer = equManufacturer;
	}

	public String getEquPlateNumber() {
		return equPlateNumber;
	}

	public void setEquPlateNumber(String equPlateNumber) {
		this.equPlateNumber = equPlateNumber;
	}

	public String getEquOwnerGroup() {
		return equOwnerGroup;
	}

	public void setEquOwnerGroup(String equOwnerGroup) {
		this.equOwnerGroup = equOwnerGroup;
	}

	public String getEquUsedGroup() {
		return equUsedGroup;
	}

	public void setEquUsedGroup(String equUsedGroup) {
		this.equUsedGroup = equUsedGroup;
	}

	public String getEquCarModel() {
		return equCarModel;
	}

	public void setEquCarModel(String equCarModel) {
		this.equCarModel = equCarModel;
	}

	public String getEquEngineNumber() {
		return equEngineNumber;
	}

	public void setEquEngineNumber(String equEngineNumber) {
		this.equEngineNumber = equEngineNumber;
	}

	public String getEquFrameNumber() {
		return equFrameNumber;
	}

	public void setEquFrameNumber(String equFrameNumber) {
		this.equFrameNumber = equFrameNumber;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Equipment [equModel=" + equModel
				+ ",equPrice=" + equPrice + ",equNumber=" + equNumber
				+ ",equCount=" + equCount + ",equBrand=" + equBrand
				+ ",equPurchaseDate=" + equPurchaseDate + ",equProduceDate="
				+ equProduceDate + ",group=" + group + ",equExpiredDate="
				+ equExpiredDate + ",equRegisterDate=" + equRegisterDate
				+ ",equRegistrant=" + equRegistrant + ",equManufacturer="
				+ equManufacturer + ",equPlateNumber=" + equPlateNumber
				+ ",equOwnerGroup=" + equOwnerGroup + ",equUsedGroup="
				+ equUsedGroup + ",equCarModel=" + equCarModel
				+ ",equEngineNumber=" + equEngineNumber + ",equFrameNumber="
				+ equFrameNumber + ",remark=" + remark + "]";
	}

}
