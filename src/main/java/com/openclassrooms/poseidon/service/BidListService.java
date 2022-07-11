package com.openclassrooms.poseidon.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.openclassrooms.poseidon.model.BidList;
import com.openclassrooms.poseidon.repository.BidListRepository;



@Service
public class BidListService {
	 @Autowired
	  private  BidListRepository bidListRepository;

	    public BidListService() {
		super();
		
	}

		public BidListService(BidListRepository bidListRepository) {
			super();
			this.bidListRepository = bidListRepository;
		}

		public Iterable<BidList> getBidLists() {
	        return bidListRepository.findAll();
	    }

	    public BidList createNewBidList(BidList bidList) {
	        return bidListRepository.save(bidList);
	    }

	    public BidList getBidListById(Integer id) {
	        return bidListRepository.findBidListById(id);
	    }

	    public void deleteBid(Integer id) {
	        bidListRepository.deleteById(id);
	    }
	    public void validate(BidList bidList)  {
	        BidList bid = new BidList();
	        bid.setAccount(bidList.getAccount());
	        bid.setType(bidList.getType());
	        bid.setBidQuantity(bidList.getBidQuantity());
	        bid.setCreationDate(Timestamp.valueOf(LocalDateTime.now()));
	        bidListRepository.save(bid);
	    }
	    public void updateBid(Integer id, BidList bidList)  {
	       
	        BidList bidListInDb = bidListRepository.findBidListById(id);

	        bidListInDb.setAccount(bidList.getAccount());
	        bidListInDb.setType(bidList.getType());
	        bidListInDb.setBidQuantity(bidList.getBidQuantity());
	        bidListRepository.save(bidListInDb);
	    }
}
