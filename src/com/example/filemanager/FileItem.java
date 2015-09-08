package com.example.filemanager;

public class FileItem {
	private String path;
	private Boolean isDir;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Boolean getIsDir() {
		return isDir;
	}
	public void setIsDir(Boolean isDir) {
		this.isDir = isDir;
	}
	public FileItem(String path, Boolean isDir) {
		super();
		this.path = path;
		this.isDir = isDir;
	}
	
	public FileItem(){}
	@Override
	public String toString() {
		return "FileItem [path=" + path + ", isDir=" + isDir + "]";
	}
	
	
	
}
