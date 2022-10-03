package com.provisioning.gateway.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subscribers_7")
public class Subscriber_7 {
	@Id
	String a_party;
	int opr_type;
	Timestamp created_date;
	int status;
	Timestamp status_update_dt;
	int service_id;
	Timestamp sub_date;
	String channel;
	Timestamp hlractivation_date;
	Timestamp unsub_date;
	String unsub_channel;
	Timestamp hlr_deactivation_date;
	Timestamp charge_dt;
	Timestamp next_charge_dt;

	public String geta_party() {
		return a_party;
	}

	public void seta_party(String a_party) {
		this.a_party = a_party;
	}

	public int getOpr_type() {
		return opr_type;
	}

	public void setOpr_type(int opr_type) {
		this.opr_type = opr_type;
	}

	public Timestamp getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Timestamp created_date) {
		this.created_date = created_date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getStatus_update_dt() {
		return status_update_dt;
	}

	public void setStatus_update_dt(Timestamp status_update_dt) {
		this.status_update_dt = status_update_dt;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public Timestamp getSub_date() {
		return sub_date;
	}

	public void setSub_date(Timestamp sub_date) {
		this.sub_date = sub_date;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Timestamp getHlractivation_date() {
		return hlractivation_date;
	}

	public void setHlractivation_date(Timestamp hlractivation_date) {
		this.hlractivation_date = hlractivation_date;
	}

	public Timestamp getUnsub_date() {
		return unsub_date;
	}

	public void setUnsub_date(Timestamp unsub_date) {
		this.unsub_date = unsub_date;
	}

	public String getUnsub_channel() {
		return unsub_channel;
	}

	public void setUnsub_channel(String unsub_channel) {
		this.unsub_channel = unsub_channel;
	}

	public Timestamp getHlr_deactivation_date() {
		return hlr_deactivation_date;
	}

	public void setHlr_deactivation_date(Timestamp hlr_deactivation_date) {
		this.hlr_deactivation_date = hlr_deactivation_date;
	}

	public Timestamp getCharge_dt() {
		return charge_dt;
	}

	public void setCharge_dt(Timestamp charge_dt) {
		this.charge_dt = charge_dt;
	}

	public Timestamp getNext_charge_dt() {
		return next_charge_dt;
	}

	public void setNext_charge_dt(Timestamp next_charge_dt) {
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
