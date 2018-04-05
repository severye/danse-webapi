package danse.web.models;

import java.util.List;

public class PostAddProduct {

	Long id;
	String name;
	String comment;
	String picture;
	Integer totalQuantity;
	Long idKind;
	Long idType;
	Long idCategory;
	Long idBox;
	Long idColour;
	List<PostAddSizeQuantity> sizeQuantities;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getTotalQuantity() {
		return totalQuantity;
	}
	public void setTotalQuantity(Integer totalQuantity) {
		this.totalQuantity = totalQuantity;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Long getIdKind() {
		return idKind;
	}
	public void setIdKind(Long idKind) {
		this.idKind = idKind;
	}
	public Long getIdType() {
		return idType;
	}
	public void setIdType(Long idType) {
		this.idType = idType;
	}
	public Long getIdCategory() {
		return idCategory;
	}
	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}
	public Long getIdBox() {
		return idBox;
	}
	public void setIdBox(Long idBox) {
		this.idBox = idBox;
	}
	public Long getIdColour() {
		return idColour;
	}
	public void setIdColour(Long idColour) {
		this.idColour = idColour;
	}
	public List<PostAddSizeQuantity> getSizeQuantities() {
		return sizeQuantities;
	}
	public void setSizeQuantities(List<PostAddSizeQuantity> sizeQuantities) {
		this.sizeQuantities = sizeQuantities;
	}
	
	
}
