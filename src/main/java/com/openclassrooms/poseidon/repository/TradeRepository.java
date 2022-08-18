package com.openclassrooms.poseidon.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.poseidon.model.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {
	Trade findTradeById(int id);
	 Trade findIdByAccountAndTypeAndBuyQuantity(String account, String type, double buyQuantity);  
}
