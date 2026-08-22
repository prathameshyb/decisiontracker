package com.prathamesh.decisiontracker.mvc;

import com.prathamesh.decisiontracker.dto.*;
import com.prathamesh.decisiontracker.exception.ResourceNotFoundException;
import com.prathamesh.decisiontracker.service.DecisionService;
import com.prathamesh.decisiontracker.service.TagService;
import com.prathamesh.decisiontracker.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DecisionTrackerRESTController {

    @Autowired
    private final DecisionService decisionService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final TagService tagService;

    private SecurityContext context;

    private Authentication auth;


    public DecisionTrackerRESTController(DecisionService decisionService, UserService userService, TagService tagService){
        this.decisionService = decisionService;
        this.userService = userService;
        this.tagService = tagService;
    }

    public void checkAdminAccess(){
        context = SecurityContextHolder.getContext();
        auth = context.getAuthentication();
        SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
        if (auth != null && !auth.getAuthorities().contains(adminAuthority) ) {
            throw new AccessDeniedException("Action denied for this admin");
        }
    }

    @GetMapping("/users")
    List<UserDTO> getUsers(){
        checkAdminAccess();
        return userService.getUsers();

    }

    @PostMapping("/user")
    @ResponseStatus(HttpStatus.CREATED)
    public void newUser(@RequestBody CreateUserDTO newUserDTO) throws Exception {
        checkAdminAccess();
        userService.addUser(newUserDTO);
    }

    @PutMapping("/user")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@RequestBody UpdateUserDTO updateUserDTO) throws Exception {
        checkAdminAccess();
        userService.updateUser(updateUserDTO);
    }

    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUsers(@RequestBody List<Integer>userIds) throws Exception{
        checkAdminAccess();
        for(Integer userId : userIds){
            userService.deleteUser(userId);
        }
    }

    @GetMapping("/decisions")
    List<DecisionDTO> getDecisions(){
        return decisionService.getDecisions();
    }

    @PostMapping("/decision")
    @ResponseStatus(HttpStatus.CREATED)
    public void newDecision(@RequestBody CreateDecisionDTO newDecisionDTO) throws Exception {
        decisionService.addDecisions(newDecisionDTO);
    }

    @PutMapping("/decision")
    @ResponseStatus(HttpStatus.OK)
    public void updateDecision(@RequestBody UpdateDecisionDTO updateDecisionDTO) throws Exception{
        decisionService.updateDecision(updateDecisionDTO);
    }

    @DeleteMapping("/decisions")
    @ResponseStatus(HttpStatus.OK)
    public void deleteDecisions(@RequestBody List<Integer> decisionIds) throws Exception{
        for(Integer decisionId : decisionIds){
            decisionService.deleteDecision(decisionId);
        }
    }

    @GetMapping("/tags")
    List<TagDTO> getTags(){
        return tagService.getTags();
    }

    @PostMapping("/tag")
    @ResponseStatus(HttpStatus.CREATED)
    public void newTag(@RequestBody CreateTagDTO newTagDTO) throws Exception {
        tagService.addTag(newTagDTO);
    }

    @PutMapping("/tag")
    @ResponseStatus(HttpStatus.OK)
    public void updateTag(@RequestBody UpdateTagDTO updateTagDTO) throws Exception {
        tagService.updateTag(updateTagDTO);
    }

    @DeleteMapping("/tags")
    @ResponseStatus(HttpStatus.OK)
    public void deleteTags(@RequestBody List<Integer> tagIds) throws Exception {
        for(Integer tagId : tagIds) {
            tagService.deleteTag(tagId);
        }
    }

    @Transactional
    @PostMapping("/userDecisions/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void setUserDecisions(@PathVariable(name = "userId", required = true)Integer userId,@RequestBody List<Integer> decisionIds) throws Exception{
        if(userService.getUserById(userId)==null){
            throw new ResourceNotFoundException("User", userId);
        }
        userService.setUserDecisions(userId, decisionIds);
    }

    @GetMapping("/userDecisions/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DecisionDTO> getUserDecisions(@PathVariable(name = "userId", required = true)Integer userId) throws Exception{
        if(userService.getUserById(userId)==null){
            throw new ResourceNotFoundException("User", userId);
        }
        return userService.getUserDecisions(userId);
    }

    @PostMapping("/decisionTags/{decisionId}")
    @ResponseStatus(HttpStatus.OK)
    public void setDecisionTags(@PathVariable (name = "decisionId", required = true)Integer decisionId, @RequestBody List<Integer> tagIds) throws Exception{
        if(decisionService.getDecisionById(decisionId) == null){
            throw new ResourceNotFoundException("Decision", decisionId);
        }
        decisionService.setDecisionTags(decisionId, tagIds);
    }

    @GetMapping("/decisionTags/{decisionId}")
    @ResponseStatus(HttpStatus.OK)
    public List<TagDTO> getDecisionTags(@PathVariable(name = "decisionId", required = true)Integer decisionId) throws Exception{
        if(decisionService.getDecisionById(decisionId)==null){
            throw new ResourceNotFoundException("Decision", decisionId);
        }
        return decisionService.getDecisionTags(decisionId);
    }

    @GetMapping("/tagDecisions/{tagId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DecisionDTO> getTagDecisions(@PathVariable(name = "tagId", required = true)Integer tagId) throws Exception{
        if(tagService.getTagById(tagId) == null){
            throw new ResourceNotFoundException("Tag", tagId);
        }
        return tagService.getTagDecisions(tagId);
    }

    @GetMapping("/analytics/best-decisions")
    @ResponseStatus(HttpStatus.OK)
    public List<BestDecisionDTO> getBestDecisions(){
        return decisionService.getBestDecisions();
    }

    @GetMapping("/analytics/top-tags")
    @ResponseStatus(HttpStatus.OK)
    public List<TopTagDTO> getTopTags(){
         return tagService.getTopTags();
    }

    @GetMapping("/analytics/average-score-by-tag")
    @ResponseStatus(HttpStatus.OK)
    public List<TagScoreDTO> getAvgScorePerTag(){
        return tagService.getAverageScorePerTag();
    }

    @GetMapping("/analytics/decisions-due-review")
    @ResponseStatus(HttpStatus.OK)
    public List<DecisionDueReviewDTO> getDecisionsDueReview(){
        return decisionService.getDecisionsDueReview();
    }
}
