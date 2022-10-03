package com.provisioning.gateway.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.provisioning.gateway.model.Chargeprocess;
import com.provisioning.gateway.repository.ChargeprocessRepository;

@Service
@Transactional
public class ChargeprocessService {
	@Autowired
	private ChargeprocessRepository chargeProcessRepo;

	public List<Chargeprocess> listAll() {
		return chargeProcessRepo.findAll();
	}

	public void save(Chargeprocess Chargeprocess) {
		chargeProcessRepo.save(Chargeprocess);
	}

	public Chargeprocess get(int id) {
		Optional<Chargeprocess> optionalEntity = chargeProcessRepo.findById(id);
		Chargeprocess Chargeprocess = optionalEntity.get();
		return Chargeprocess;
	}

	public void delete(int id) {
		chargeProcessRepo.deleteById(id);
	}
}
