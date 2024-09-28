package com.fs.pos.relationalmanager.service;

import com.fs.pos.relationalmanager.entities.Investor;
import com.fs.pos.relationalmanager.repository.InvestorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvestorService {
    @Autowired
    private InvestorRepository investorRepository;

    // Create a new investor
    public Investor createInvestor(Investor investor) {
        //refactor to use user_id
        // logic: user login --> user_id --> save investor with user_id
        return investorRepository.save(investor);
    }

    // Get an investor by ID
    public Optional<Investor> getInvestorById(Long id) {
        return investorRepository.findById(id);
    }

    // Get all investors
    public List<Investor> getAllInvestors(Long userId) {
        // If user is Agent, show only investor belong to this Agent
        // user.type = 'Agent' --> getByUserId(1)


        // If user is Coach, show all investors belong to team
        // user.type = 'Coach' --> find team_id --> find UserIds
        // user.type = 'Coach' --> getByUserIds([1,2,3\)

        // If user is Admin, show all investors
        return investorRepository.findAll();
    }

    // Update an investor
    public Investor updateInvestor(Long id, Investor investorDetails) {
        Optional<Investor> optionalInvestor = investorRepository.findById(id);
        if (optionalInvestor.isPresent()) {
            Investor investor = optionalInvestor.get();
            investor.setName(investorDetails.getName());
            investor.setEntityNo(investorDetails.getEntityNo());
            investor.setPhone(investorDetails.getPhone());
            investor.setType(investorDetails.getType());
            return investorRepository.save(investor);
        } else {
            throw new RuntimeException("Investor not found with id " + id);
        }
    }

    // Delete an investor
    public void deleteInvestor(Long id) {
        investorRepository.deleteById(id);
    }

    // Get all investors by user ID
    public List<Investor> getInvestorsByUserId(Long userId) {
        return investorRepository.findByUserId(userId);
    }
}