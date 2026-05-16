package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.dto.DecisionDTO;
import com.prathamesh.decisiontracker.dto.DecisionMapper;
import com.prathamesh.decisiontracker.entities.Decision;
import com.prathamesh.decisiontracker.exception.DuplicateEntryException;
import com.prathamesh.decisiontracker.exception.ResourceNotFoundException;
import com.prathamesh.decisiontracker.repository.DecisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DecisionServiceImpl implements DecisionService {

    @Autowired
    private final DecisionRepository decisionRepository;

    private final DecisionMapper decisionMapper;

    public DecisionServiceImpl(DecisionRepository decisionRepository, DecisionMapper decisionMapper) {
        this.decisionRepository = decisionRepository;
        this.decisionMapper = decisionMapper;
    }

    @Override
    public List<DecisionDTO> getDecisions() {
        List<Decision> decisions = decisionRepository.findAll();
        return decisions.stream().map(decisionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public void addDecisions(DecisionDTO decisionDTO) throws Exception {
        Decision decision = decisionMapper.toEntity(decisionDTO);
        if (decisionRepository.existsByTitle(decision.getTitle())) {
            throw new DuplicateEntryException("Title must be unique");
        }
        decisionRepository.save(decision);
    }

    @Override
    public void updateDecision(DecisionDTO decisionDTO) throws Exception {
        Decision updateDecision = decisionRepository.findById(decisionDTO.getDecisionId())
                .orElseThrow(() -> new ResourceNotFoundException("Decision", decisionDTO.getDecisionId()));
        Decision decision = decisionMapper.toEntity(decisionDTO);
        decision.setDecisionId(decisionDTO.getDecisionId());
        decisionRepository.save(decision);
    }

    @Override
    public void deleteDecision(Integer decisionId) throws Exception {
        Decision decision = decisionRepository.findById(decisionId)
                .orElseThrow(() -> new ResourceNotFoundException("Decision", decisionId));
        decisionRepository.delete(decision);
    }
}
