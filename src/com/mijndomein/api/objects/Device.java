package com.mijndomein.api.objects;
public class Device
{
    private int port;
    private int hubID;
    private String status;
    private String name;
    private String type;
    private int clusterID;
    private int componentID;
    private int componentTypeID;
    
    
	public int getPort() {
		return port;
	}


	public void setPort(int port) {
		this.port = port;
	}


	public int getHubID() {
		return hubID;
	}


	public void setHubID(int hubID) {
		this.hubID = hubID;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public int getClusterID() {
		return clusterID;
	}


	public void setClusterID(int clusterID) {
		this.clusterID = clusterID;
	}


	public int getComponentID() {
		return componentID;
	}


	public void setComponentID(int componentID) {
		this.componentID = componentID;
	}


	public int getComponentTypeID() {
		return componentTypeID;
	}


	public void setComponentTypeID(int componentTypeID) {
		this.componentTypeID = componentTypeID;
	}
    
    
}