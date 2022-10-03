package com.provisioning.gateway.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author mateen
 *
 */
@Entity
@Table(name = "charge_process")
public class Chargeprocess {
	@Id
	int id;
	String b_party;
	BigDecimal amount;
	int status;
	int charge_type;
	Timestamp created;
	Timestamp updated;
	int retry_count;
	int opr_type;
	int sub_type;
	String ref_id;
	String error_code;
	int service_id;
	int processed;

	public Chargeprocess() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getB_party() {
		return b_party;
	}

	public void setB_party(String b_party) {
		this.b_party = b_party;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCharge_type() {
		return charge_type;
	}

	public void setCharge_type(int charge_type) {
		this.charge_type = charge_type;
	}

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getUpdated() {
		return updated;
	}

	public void setUpdated(Timestamp updated) {
		this.updated = updated;
	}

	public int getRetry_count() {
		return retry_count;
	}

	public void setRetry_count(int retry_count) {
		this.retry_count = retry_count;
	}

	public int getOpr_type() {
		return opr_type;
	}

	public void setOpr_type(int opr_type) {
		this.opr_type = opr_type;
	}

	public int getSub_type() {
		return sub_type;
	}

	public void setSub_type(int sub_type) {
		this.sub_type = sub_type;
	}

	public String getRef_id() {
		return ref_id;
	}

	public void setRef_id(String ref_id) {
		this.ref_id = ref_id;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

	public int getService_id() {
		return service_id;
	}

	public void setService_id(int service_id) {
		this.service_id = service_id;
	}

	public int getProcessed() {
		return processed;
	}

	public void setProcessed(int processed) {
		this.processed = processed;
	}

	@Override
	public String toString() {
		return "Chargeprocess [id=" + id + ", b_party=" + b_party + ", amount=" + amount + ", status=" + status
				+ ", charge_type=" + charge_type + ", created=" + created + ", updated=" + updated + ", retry_count="
				+ retry_count + ", opr_type=" + opr_type + ", sub_type=" + sub_type + ", ref_id=" + ref_id
				+ ", error_code=" + error_code + ", service_id=" + service_id + ", processed=" + processed + "]";
	}

}
