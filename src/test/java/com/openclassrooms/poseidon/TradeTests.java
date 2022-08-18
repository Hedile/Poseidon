package com.openclassrooms.poseidon;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.openclassrooms.poseidon.model.Trade;
import com.openclassrooms.poseidon.repository.TradeRepository;
import com.openclassrooms.poseidon.service.TradeService;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class TradeTests {

	@Autowired
	private TradeRepository tradeRepository;
	@Autowired
	private TradeService tradeService;
	@AfterEach
	public void tearDown() {
		
		tradeRepository.deleteAll();
	}
	@Test
	public void tradeTest() {
		Trade trade = new Trade("Trade Account", "Type",10);

		// Save
		trade = tradeRepository.save(trade);
		assertNotNull(trade.getId());
		assertTrue(trade.getAccount().equals("Trade Account"));

		// Update
		trade.setAccount("Trade Account Update");
		trade = tradeRepository.save(trade);
		assertTrue(trade.getAccount().equals("Trade Account Update"));

		// Find
		List<Trade> listResult = tradeRepository.findAll();
		assertTrue(listResult.size() > 0);

		// Delete
		Integer id = trade.getId();
		tradeRepository.delete(trade);
		Optional<Trade> tradeList = tradeRepository.findById(id);
		assertFalse(tradeList.isPresent());
	}
	@Test
	public void deleteTradeTest() {
		Trade trade = new Trade("Trade Account", "Type",10);
		trade = tradeRepository.save(trade);
		Integer id = trade.getId();

		tradeService.deleteTrade(id);
		Optional<Trade> tradeId = tradeRepository.findById(id);

		assertFalse(tradeId.isPresent());
	}
	@Test
	public void updateTradeTest() {
		//given
		Trade trade = new Trade("Trade Account", "Type", 10);
		trade = tradeRepository.save(trade);
		Integer id = trade.getId();

		Trade tradeToUpdate = new Trade("Test", "Test",10);
		

		//when
		assertDoesNotThrow(() -> tradeService.updateTrade(id, tradeToUpdate));
		Trade updatedTrade=tradeService.getTradeById(id);
		//then
		assertEquals(updatedTrade.getAccount(),"Test");
		assertEquals(updatedTrade.getType(),"Test");
		assertEquals(updatedTrade.getBuyQuantity(),10);
	}

	@Test
	public void validateTradeTest() {
		//given
		Trade trade = new Trade("TradeAccountNew", "TypeNew",10);
		//when
		assertDoesNotThrow(() -> tradeService.validateTrade(trade));

		//then
		Trade tradeIdByAccountAndType = tradeRepository.findIdByAccountAndTypeAndBuyQuantity("TradeAccountNew", "TypeNew", 10);
		Optional<Trade> tradeId = tradeRepository.findById(tradeIdByAccountAndType.getId());
		assertTrue(tradeId.isPresent());
	}
}
