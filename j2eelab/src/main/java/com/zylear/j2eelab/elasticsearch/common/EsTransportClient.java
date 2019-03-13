package com.zylear.j2eelab.elasticsearch.common;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;

import java.util.ArrayList;
import java.util.List;

public class EsTransportClient {
	private Client client;
	
	@SuppressWarnings("resource")
	public EsTransportClient(String clusterName, String ipAddr){
		Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", clusterName).put("client.transport.sniff", true).build();
		List<InetSocketTransportAddress> addList = new ArrayList<InetSocketTransportAddress>();
		String []ipArr = ipAddr.split(",");
		for (String ip : ipArr) {
			if( !StringUtils.isEmpty(ip.trim()) ) {
				InetSocketTransportAddress addr = new InetSocketTransportAddress(ip.trim(), 9300);
				addList.add(addr);
			}
		}
		this.client = new TransportClient(settings).addTransportAddresses(addList.toArray(new InetSocketTransportAddress[]{}));
	}
	
	@SuppressWarnings("resource")
	public EsTransportClient(String clusterName, String ipAddr, Integer port){
		Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", clusterName).put("client.transport.sniff", true).build();
		List<InetSocketTransportAddress> addList = new ArrayList<InetSocketTransportAddress>();
		String []ipArr = ipAddr.split(",");
		for (String ip : ipArr) {
			if( !StringUtils.isEmpty(ip.trim()) ) {
				InetSocketTransportAddress addr = new InetSocketTransportAddress(ip.trim(), port);
				addList.add(addr);
			}
		}
		this.client = new TransportClient(settings).addTransportAddresses(addList.toArray(new InetSocketTransportAddress[]{}));
	}
	
	public void closeClient(){
		this.client.close();
	}

	public Client getClient() {
		return client;
	}
}
