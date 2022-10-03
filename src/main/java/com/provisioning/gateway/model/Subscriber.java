package com.provisioning.gateway.model;

import java.util.Date;

public class Subscriber {

	String a_party;
	int opr_type;
	Date created_date;
	int status;
	Date status_update_dt;
	int service_id;
	Date sub_date;
	String channel;
	Date hlractivation_date;
	Date unsub_date;
	String unsub_channel;
	Date hlr_deactivation_date;
	Date charge_dt;
	Date next_charge_dt;

	
	



	public Subscriber(String a_party, int opr_type, Date created_date) {
		super();
		this.a_party = a_party;
		this.opr_type = opr_type;
		this.created_date = created_date;

	}



	public Subscriber(String a_party, int opr_type, Date created_date, int status, Date status_update_dt,
			int service_id, Date sub_date, String channel, Date hlractivation_date, Date unsub_date,
			String unsub_channel, Date hlr_deactivation_date, Date charge_dt, Date next_charge_dt) {
		super();
		this.a_party = a_party;
		this.opr_type = opr_type;
		this.created_date = created_date;
		this.status = status;
		this.status_update_dt = status_update_dt;
		this.service_id = service_id;
		this.sub_date = sub_date;
		this.channel = channel;
		this.hlractivation_date = hlractivation_date;
		this.unsub_date = unsub_date;
		this.unsub_channel = unsub_channel;
		this.hlr_deactivation_date = hlr_deactivation_date;
		this.charge_dt = charge_dt;
		this.next_charge_dt = next_charge_dt;
	}



	public Subscriber() {
		super();
		}



	
	
	public String getA_party() {
		return a_party;
	}



	public void setA_party(String a_party) {
		this.a_party = a_party;
	}



	public int getOpr_type() {
		return opr_type;
	}



	public void setOpr_type(int opr_type) {
		this.opr_type = opr_type;
	}



	public Date getCreated_date() {
		return created_date;
	}



	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public Date getStatus_update_dt() {
		return status_update_dt;
	}



	public void setStatus_update_dt(Date status_update_dt) {
		this.status_update_dt = status_update_dt;
	}



	public int getService_id() {
		return service_id;
	}



	public void setService_id(int service_id) {
		this.service_id = service_id;
	}



	public Date getSub_date() {
		return sub_date;
	}



	public void setSub_date(Date sub_date) {
		this.sub_date = sub_date;
	}



	public String getChannel() {
		return channel;
	}



	public void setChannel(String channel) {
		this.channel = channel;
	}



	public Date getHlractivation_date() {
		return hlractivation_date;
	}



	public void setHlractivation_date(Date hlractivation_date) {
		this.hlractivation_date = hlractivation_date;
	}



	public Date getUnsub_date() {
		return unsub_date;
	}



	public void setUnsub_date(Date unsub_date) {
		this.unsub_date = unsub_date;
	}



	public String getUnsub_channel() {
		return unsub_channel;
	}



	public void setUnsub_channel(String unsub_channel) {
		this.unsub_channel = unsub_channel;
	}



	public Date getHlr_deactivation_date() {
		return hlr_deactivation_date;
	}



	public void setHlr_deactivation_date(Date hlr_deactivation_date) {
		this.hlr_deactivation_date = hlr_deactivation_date;
	}



	public Date getCharge_dt() {
		return charge_dt;
	}



	public void setCharge_dt(Date charge_dt) {
		this.charge_dt = charge_dt;
	}



	public Date getNext_charge_dt() {
		return next_charge_dt;
	}



	public void setNext_charge_dt(Date next_charge_dt) {
		this.next_charge_dt = next_charge_dt;
	}



	@Override
	public String toString() {
		return "Subscriber [a_party=" + a_party + ", opr_type=" + opr_type + ", created_date=" + created_date
				+ ", status=" + status + ", status_update_dt=" + status_update_dt + ", service_id=" + service_id
				+ ", sub_date=" + sub_date + ", channel=" + channel + ", hlractivation_date=" + hlractivation_date
				+ ", unsub_date=" + unsub_date + ", unsub_channel=" + unsub_channel + ", hlr_deactivation_date="
				+ hlr_deactivation_date + ", charge_dt=" + charge_dt + ", next_charge_dt=" + next_charge_dt + "]";
	}
	
	
	
	

}

