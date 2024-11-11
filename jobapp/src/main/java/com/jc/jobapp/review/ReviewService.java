package com.jc.jobapp.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    Review addReview(Long companyId, Review review);
    Review getReviewById(Long companyId, Long reviewId);
    Review updateReview(Long companyId, Long reviewId, Review review);
    boolean deleteReview(Long companyId, Long reviewId);
}
