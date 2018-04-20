package com.article.recommend.entity;

import java.util.List;

/**
 * 
 * @ClassName: MenuInfo  
 * @Description: 菜单信息  
 * @author sw  
 * @date 2018年4月17日
 */
public class MenuInfo{
	private Long id;
    private String menuName;
    private String display;
    private Long parentId;
    private Long ownerId;
    private Long ownerOriginId;
    private String leaf;
    private String path;
    private String state;
    private List<MenuInfo> childMenuModels;
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getDisplay() {
        return display;
    }
    public void setDisplay(String display) {
        this.display = display;
    }
    public Long getParentId() {
        return parentId;
    }
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
    public Long getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
    public Long getOwnerOriginId() {
        return ownerOriginId;
    }
    public void setOwnerOriginId(Long ownerOriginId) {
        this.ownerOriginId = ownerOriginId;
    }
   
    public String getLeaf() {
        return leaf;
    }
    public void setLeaf(String leaf) {
        this.leaf = leaf;
    }
   
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
	public List<MenuInfo> getChildMenuModels() {
		return childMenuModels;
	}
	public void setChildMenuModels(List<MenuInfo> childMenuModels) {
		this.childMenuModels = childMenuModels;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
    
}
