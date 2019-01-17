package com.mijndomein.api.objects;
public class Cluster
{
    private int hubID;
    private String name;
    private int clusterID;
     
	public int getHubID() {
		return hubID;
	}
	public void setHubID(int hubID) {
		this.hubID = hubID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getClusterID() {
		return clusterID;
	}
	public void setClusterID(int clusterID) {
		this.clusterID = clusterID;
	}
    
}