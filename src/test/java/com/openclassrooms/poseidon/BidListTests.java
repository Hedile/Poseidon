package com.openclassrooms.poseidon;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.poseidon.model.BidList;
import com.openclassrooms.poseidon.repository.BidListRepository;
import com.openclassrooms.poseidon.service.BidListService;

@SpringBootTest
public class BidListTests {

	@Autowired
	private BidListRepository bidListRepository;
	@Autowired
	private BidListService bidListService;
	@AfterEach
	public void tearDown() {
		
		bidListRepository.deleteAll();
	}
	@Test
	public void bidListTest() {
		BidList bid = new BidList("Account", "Type", 10d);

		// Save
		bidListRepository.save(bid);
		assertNotNull(bid.getId());
		assertEquals(bid.getBidQuantity(), 10d);

		// Update
		bid.setBidQuantity(20d);
		bid = bidListRepository.save(bid);
		assertEquals(bid.getBidQuantity(), 20d);

		// Find
		List<BidList> listResult = bidListRepository.findAll();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = bid.getId();
		bidListRepository.delete(bid);
		Optional<BidList> bidList = bidListRepository.findById(id);
		assertFalse(bidList.isPresent());
	}
	 
	@Test
	public void deleteBidListTest() {
		BidList bid = new BidList("Account", "Type", 10);
		bidListRepository.save(bid);
		// Delete
		Integer id = bid.getId();
		bidListService.deleteBidList(id);
		Optional<BidList> bidList = bidListRepository.findById(id);

		assertFalse(bidList.isPresent());


	}

	@Test
	public void updateBidListTest(){
		//given
		BidList bid = new BidList("Account", "Type ", 10);
	bidListRepository.save(bid);
		Integer id = bid.getId();

		BidList bidList = new BidList("Account test", "Type Test", 20);
		
		//when
		assertDoesNotThrow(() -> bidListService.updateBid(id, bidList));
		BidList bidListUpdated= bidListService.getBidListById(id);
		//then
		assertEquals(bidListUpdated.getBidQuantity(), 20d);
		assertEquals(bidListUpdated.getAccount(),"Account test");
	}

	@Test
	public void validateBidListTest() {
		//given
		BidList addBidList = new BidList("NewAccount", "Type", 20);

		//when
		assertDoesNotThrow(() ->bidListService.createNewBidList(addBidList));

		//then
		BidList bid = bidListRepository.findBidListIdByAccount("NewAccount");
		Optional<BidList> bidList = bidListRepository.findById(bid.getId());
		assertTrue(bidList.isPresent());
	}

}