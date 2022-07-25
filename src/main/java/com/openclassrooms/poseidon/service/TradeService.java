package com.openclassrooms.poseidon.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.openclassrooms.poseidon.model.Trade;
import com.openclassrooms.poseidon.repository.TradeRepository;
@Service
public class TradeService {
	@Autowired
    TradeRepository tradeRepository;
	
	 public void validateTrade(Trade trade)  {
	        Trade addTrade = new Trade();
	        addTrade.setAccount(trade.getAccount());
	        addTrade.setType(trade.getType());
	        addTrade.setBuyQuantity(trade.getBuyQuantity());
	        addTrade.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
	        tradeRepository.save(addTrade);
	    }

	  
	    public void updateTrade(Integer id, Trade trade)  {
	       
	        Trade tradeInDb = tradeRepository.findTradeById(id);
	        tradeInDb.setAccount(trade.getAccount());
	        tradeInDb.setType(trade.getType());
	        tradeInDb.setBuyQuantity(trade.getBuyQuantity());
	        tradeRepository.save(tradeInDb);
	    }

	    public List<Trade> getTradeList() {
	        return tradeRepository.findAll();
	    }

	    public Trade getTradeById(Integer id) {
	        return tradeRepository.findTradeById(id);
	    }

	   
	    public void deleteTrade(Integer id) {
	        tradeRepository.deleteById(id);
	    }
}
