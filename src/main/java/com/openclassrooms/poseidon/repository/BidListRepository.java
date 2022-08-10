package com.openclassrooms.poseidon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.poseidon.model.BidList;

@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {
	BidList findBidListById(int id);
	BidList findBidListIdByAccount(String account);
}
