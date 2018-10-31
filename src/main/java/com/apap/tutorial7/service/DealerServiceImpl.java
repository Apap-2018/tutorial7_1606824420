package com.apap.tutorial7.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.repository.DealerDb;

/**
 * DealerServiceImpl
 */
@Service
@Transactional
public class DealerServiceImpl implements DealerService{
	@Autowired
	private DealerDb dealerDb;
	
	@Override
	public Optional<DealerModel> getDealerDetailById(Long id) {
		return dealerDb.findById(id);
	}
	
	@Override
	public DealerModel addDealer(DealerModel dealer) {
		return dealerDb.save(dealer);
	}

	@Override
	public List<DealerModel> getAllDealer() {
		// TODO Auto-generated method stub
		return dealerDb.findAll();
	}

	@Override
	public DealerDb allDealer() {
		// TODO Auto-generated method stub
		return dealerDb;
	}

	@Override
	public void deleteDealer(DealerModel dealer) {
		dealerDb.delete(dealer);
	}


	@Override
	public void updateDealer(long id, DealerModel dealer) {
		DealerModel updateDealer = dealerDb.getOne(id);
		updateDealer.setAlamat(dealer.getAlamat());
		updateDealer.setNoTelp(dealer.getNoTelp());
		dealerDb.save(updateDealer);

	}

	@Override
	public List<DealerModel> viewAllDealer() {
		// TODO Auto-generated method stub
		return dealerDb.findAll();
	}
}