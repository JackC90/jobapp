package com.jc.jobapp.review;

import org.springframework.web.bind.annotation.RestController;

import com.jc.jobapp.company.Company;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<List<Review>>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        Review resultReview = reviewService.addReview(companyId, review);

        if (resultReview != null) {
            return new ResponseEntity<Review>(resultReview, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<Review>(resultReview, HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        return new ResponseEntity<Review>(reviewService.getReviewById(companyId, reviewId), HttpStatus.OK);
    }
    
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> updateReview(
        @PathVariable Long companyId, 
        @PathVariable Long reviewId, 
        @RequestBody Review review
    ) {
        Review resultReview = reviewService.updateReview(companyId, reviewId, review);

        if (resultReview != null) {
            return new ResponseEntity<Review>(resultReview, HttpStatus.OK);
        } else {
            return new ResponseEntity<Review>(resultReview, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Boolean> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean review = reviewService.deleteReview(companyId, reviewId);

        if (!review) {
            return new ResponseEntity<Boolean>(HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
    }
}
