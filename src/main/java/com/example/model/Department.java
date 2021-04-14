package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;



@Entity
public class Department {

	
//	by right must use the generated value ie. sequence
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int did;
	
	private String dname;
	
	private String address;

	public int getDid() {
		return did;
	}

	public void setDid(int did) {
		this.did = did;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

//	String s;
//	Function<String,String> function = (s) -> s + "sssss";



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + did;
		result = prime * result + ((dname == null) ? 0 : dname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Department other = (Department) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (did != other.did)
			return false;
		if (dname == null) {
			if (other.dname != null)
				return false;
		} else if (!dname.equals(other.dname))
			return false;
		return true;
	}

	public Department(int did, String dname, String address) {
		super();
		this.did = did;
		this.dname = dname;
		this.address = address;
	}

	public Department(int did, String dname) {
		super();
		this.did = did;
		this.dname = dname;
	}

	public Department(int did) {
		super();
		this.did = did;
	}

	public Department(String dname) {
		super();
		this.dname = dname;
	}

	public Department() {
	}

	@Override
	public String toString() {
		return dname;
	}

}
