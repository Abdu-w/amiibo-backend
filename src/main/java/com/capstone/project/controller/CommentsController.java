package com.capstone.project.controller;


import com.capstone.project.model.Comments;
import com.capstone.project.model.Games;
import com.capstone.project.repository.CommentsRepository;
import com.capstone.project.Services.ResourceException;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api_v2/" )
public class CommentsController {

    @Autowired
    private CommentsRepository commentsRepository;

    @GetMapping("/comment")
    public List<Comments> getAllComments(Model model){
        return this.commentsRepository.findAll();

    }

    @GetMapping("/comment/{id}")
    public ResponseEntity<Comments> getCommentsId(@PathVariable(value = "id") Long commentId)
            throws ResourceException {
        Comments comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new ResourceException("Comments not found for this id ::" +
                        commentId));
        return ResponseEntity.ok().body(comment);
    }


    @PostMapping("/comment")
    public Comments CreateComments(@Valid @RequestBody Comments comment) {
        return commentsRepository.save(comment);
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<Comments> updateComments(@PathVariable(value = "id") Long commentId,
                                             @Valid @RequestBody Comments commentDetails)
            throws ResourceException {
        Comments comments = commentsRepository.findById(commentId)
                .orElseThrow(()-> new ResourceException("comment not found for this id :: " + commentId));

        comments.setComments(commentDetails.getComments());

        final Comments updatedComments = commentsRepository.save(comments);
        return ResponseEntity.ok(updatedComments);
    }


    @DeleteMapping("/comments/{id}")
    public Map<String, Boolean> deletedComments(@PathVariable(value = "id") Long commentId)
            throws ResourceException {
        Comments comment = commentsRepository.findById(commentId)
                .orElseThrow(()-> new ResourceException("comment not found for this id :: " + commentId));

        commentsRepository.delete(comment);
        Map<String, Boolean> response = new HashMap<>();

        response.put("deleted comment", Boolean.TRUE);

        return response;

    }
}