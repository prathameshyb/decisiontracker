package com.prathamesh.decisiontracker.service;

import com.prathamesh.decisiontracker.constants.DecisionConstants;
import com.prathamesh.decisiontracker.constants.TagConstants;
import com.prathamesh.decisiontracker.dto.*;
import com.prathamesh.decisiontracker.entities.Decision;
import com.prathamesh.decisiontracker.entities.Tag;
import com.prathamesh.decisiontracker.exception.DuplicateEntryException;
import com.prathamesh.decisiontracker.exception.ResourceNotFoundException;
import com.prathamesh.decisiontracker.repository.DecisionRepository;
import com.prathamesh.decisiontracker.repository.TagRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DecisionServiceImpl implements DecisionService {

    @Autowired
    private final DecisionRepository decisionRepository;

    private final DecisionMapper decisionMapper;

    private final TagService tagService;

    private final TagRepository tagRepository;

    private final TagMapper tagMapper;

//    @PersistenceContext
//    EntityManager entityManager;

    public DecisionServiceImpl(DecisionRepository decisionRepository, DecisionMapper decisionMapper, TagService tagService, TagRepository tagRepository, TagMapper tagMapper) {
        this.decisionRepository = decisionRepository;
        this.decisionMapper = decisionMapper;
        this.tagService = tagService;
        this.tagRepository = tagRepository;
        this.tagMapper = tagMapper;
    }

    @Override
    public List<DecisionDTO> getDecisions() {
        List<Decision> decisions = decisionRepository.findAll();
        return decisions.stream().map(decisionMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public DecisionDTO getDecisionById(int decisionId) throws Exception {
        Decision decision = decisionRepository.findById(decisionId).orElseThrow(() -> new ResourceNotFoundException("Decision", decisionId));
        return decisionMapper.toDTO(decision);
    }

    @Override
    public void addDecisions(CreateDecisionDTO createDecisionDTO) throws Exception {
        Decision decision = decisionMapper.toEntity(createDecisionDTO);
        if (decisionRepository.existsByTitle(decision.getTitle())) {
            throw new DuplicateEntryException("Title must be unique");
        }
        decisionRepository.save(decision);
    }

    @Override
    public void updateDecision(UpdateDecisionDTO updateDecisionDTO) throws Exception {
        Decision decision = decisionMapper.toEntity(updateDecisionDTO);
        decisionRepository.save(decision);
    }

    @Override
    public void deleteDecision(Integer decisionId) throws Exception {
        Decision decision = decisionRepository.findById(decisionId)
                .orElseThrow(() -> new ResourceNotFoundException("Decision", decisionId));
        decisionRepository.delete(decision);
    }

    @Override
    public void setDecisionTags(Integer decisionId, List<Integer>tagIds) throws Exception {
    Decision decision = decisionRepository.findById(decisionId).orElseThrow(() -> new ResourceNotFoundException("Decision", decisionId));
    List<Tag>decisionTags = new ArrayList<>();
    for(Integer tagId: tagIds){
        if(tagService.getTagById(tagId) == null){
            throw new ResourceNotFoundException("Tag", tagId);
        }
        decisionTags.add(tagRepository.findById(tagId).orElseThrow(() -> new ResourceNotFoundException("Tag", tagId)));
    }
    decision.setDecisionTags(decisionTags);
    decisionRepository.save(decision);
    }

    @Override
    public List<TagDTO> getDecisionTags(Integer decisionId) {
        Decision decision = decisionRepository.findById(decisionId).orElseThrow(() -> new ResourceNotFoundException("Decision", decisionId));
        return decision.getDecisionTags().stream().map(tagMapper::toDTO).toList();
    }

    @Override
    public List<BestDecisionDTO> getBestDecisions() {
        return decisionRepository.getBestDecisions(DecisionConstants.MINIMUM_SCORE_FOR_BEST_DECISION).stream().map(decisionMapper::toBestDecisionDTO).collect(Collectors.toList());
    }

    @Override
    public List<DecisionDueReviewDTO> getDecisionsDueReview() {
        List<DecisionDueReviewDTO> decisionsOverDueForReview = new ArrayList<>();
        List<Object[]> decisionsReviewDetails = decisionRepository.getDecisionReviewDetails();
        for(Object[] decisionDetails : decisionsReviewDetails) {
            if (decisionDetails[3] != null) {
                LocalDate decisionDate =
                        ((Date) decisionDetails[3])
                                .toInstant()
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate();

                LocalDate reviewDate =
                        decisionDate.plusDays((Integer) decisionDetails[4]);

                if (reviewDate.isBefore(LocalDate.now())) {
                    int differenceOfDays = (int)ChronoUnit.DAYS.between(reviewDate, LocalDate.now());
                    decisionsOverDueForReview.add(decisionMapper.toDecisionDueReviewDTO(decisionDetails, differenceOfDays));
                }
            }
        }
        return decisionsOverDueForReview;

    }


}
