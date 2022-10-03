package com.provisioning.gateway.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.provisioning.gateway.model.Subscriber;
import com.provisioning.gateway.model.Subscriber_4;


public interface SubscriberRepository_4 extends CrudRepository<Subscriber_4, String>,MainRepository {
	@Query("select new com.provisioning.gateway.model.Subscriber(s.a_party, s.opr_type, s.created_date, s.status, s.status_update_dt,s.service_id, s.sub_date, s.channel, s.hlractivation_date, s.unsub_date,s.unsub_channel, s.hlr_deactivation_date, s.charge_dt, s.next_charge_dt) from Subscriber_4 s  where s.a_party = :a_party")
	Subscriber findByBParty(@Param("a_party") String a_party);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE subscribers_4  SET status = :status,unsub_date=now() ,unsub_channel=:channel WHERE a_party =:a_party", nativeQuery = true)
	int unsubscriber(@Param("a_party") String a_party, @Param("status") int status, @Param("channel") String channel);

	// this method used to update the add vip subscription
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE subscribers_4  SET status = :status,hlractivation_date = now(),sub_date= now() WHERE a_party =:a_party", nativeQuery = true)
	public int addVipCallsubscriber(@Param("a_party") String a_party, @Param("status") int status);

	// this method used to update the add vip subscription
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE subscribers_4  SET status = :status,hlr_deactivation_date = now(),unsub_date= now() WHERE a_party =:a_party", nativeQuery = true)
	public int removeVipCallsubscriber(@Param("a_party") String a_party, @Param("status") int status);

	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE subscribers_4  SET status = :status WHERE a_party =:a_party", nativeQuery = true)
	public int updateTibcoVipCallsubscriberStatus(@Param("a_party") String a_party, @Param("status") int status);
}


