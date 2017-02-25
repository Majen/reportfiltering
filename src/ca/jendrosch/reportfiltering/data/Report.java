package ca.jendrosch.reportfiltering.data;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * POJO for report information.
 * 
 * @author Manfred Jendrosch
 *
 */
@JsonPropertyOrder({"client-address", "client-guid", "request-time", "service-guid", "retries-request", "packets-requested",
	"packets-serviced", "max-hole-size"})
public class Report {

	@JsonProperty("client-address")
	private String clientAddress;
	
	@JsonProperty("client-guid")
	private String clientGuid;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss z", timezone="America/Halifax")
	@JsonProperty("request-time")
	private Date requestTime;
	
	@JsonProperty("service-guid")
	private String serviceGuid;
	
	@JsonProperty("retries-request")
	private int retriesRequest;
	
	@JsonProperty("packets-requested")
	private int packetsRequested;
	
	@JsonProperty("packets-serviced")
	private int packetsServiced;
	
	@JsonProperty("max-hole-size")
	private int maxHoleSize;
	
	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}
	
	public String getClientGuid() {
		return clientGuid;
	}
	
	public void setClientGuid(String clientGuid) {
		this.clientGuid = clientGuid;
	}
	
	public Date getRequestTime() {
		return requestTime;
	}
	
	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}
	
	public String getServiceGuid() {
		return serviceGuid;
	}
	
	public void setServiceGuid(String serviceGuid) {
		this.serviceGuid = serviceGuid;
	}
	
	public int getRetriesRequest() {
		return retriesRequest;
	}
	
	public void setRetriesRequest(int retriesRequest) {
		this.retriesRequest = retriesRequest;
	}
	
	public int getPacketsRequested() {
		return packetsRequested;
	}

	public void setPacketsRequested(int packetsRequested) {
		this.packetsRequested = packetsRequested;
	}
	
	public int getPacketsServiced() {
		return packetsServiced;
	}
	
	public void setPacketsServiced(int packetsServiced) {
		this.packetsServiced = packetsServiced;
	}
	
	public int getMaxHoleSize() {
		return maxHoleSize;
	}
	
	public void setMaxHoleSize(int maxHoleSize) {
		this.maxHoleSize = maxHoleSize;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getClientAddress() + "," + getClientGuid() + "," + getRequestTime() + "," + getServiceGuid() + "," 
			+ getRetriesRequest() + "," + getPacketsRequested() + "," + getPacketsServiced() + "," + getMaxHoleSize();
	}
	
}
