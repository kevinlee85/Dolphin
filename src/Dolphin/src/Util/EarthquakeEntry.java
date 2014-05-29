/**
 * 
 */
package Dolphin.src.Util;

import java.util.Date;

import android.location.Location;

/**
 * @author Administrator
 * 
 */
public class EarthquakeEntry {
	double magnitude;
	String place;
	private Date qdate;
	private Location location;
	private double evel;
	private String webLink;

	/**
	 * @param magnitude
	 */
	public void setMagnitude(double magnitude) {
		// TODO Auto-generated method stub
		this.magnitude = magnitude;

	}

	/**
	 * @param place
	 */
	public void setPlace(String place) {
		// TODO Auto-generated method stub
		this.place = place;

	}

	/**
	 * @param qdate
	 */
	public void setDate(Date qdate) {
		// TODO Auto-generated method stub
		this.qdate = qdate;

	}

	/**
	 * @param location
	 */
	public void setLocation(Location location) {
		// TODO Auto-generated method stub
		this.location = location;

	}

	/**
	 * @param evel
	 */
	public void setElev(double evel) {
		// TODO Auto-generated method stub
		this.evel = evel;

	}

	/**
	 * @param webLink
	 */
	public void setLink(String webLink) {
		// TODO Auto-generated method stub
		this.webLink = webLink;

	}

	public double getMagnitude() {
		return this.magnitude;
	}

	public String getPlace() {
		return this.place;
	}

	public Date getDate() {
		return this.qdate;

	}

	public Location getLocation() {
		return this.location;
	}

	public double getEvel() {
		return this.evel;
	}

	public String getWebLink() {
		return this.webLink;
	}

	@Override  
	public String toString() {  
		return "(" + place + ")" + qdate.toString()+"  "+webLink;  
	}}
