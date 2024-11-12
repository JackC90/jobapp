package com.jc.jobapp.review.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jc.jobapp.review.Review;
import com.jc.jobapp.review.ReviewRepository;
import com.jc.jobapp.review.ReviewService;
import com.jc.jobapp.company.Company;
import com.jc.jobapp.company.CompanyService;


@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Review addReview(Long companyId, Review review) {
        Company company = companyService.getCompanyById(companyId)
        if (company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return review;
        }
        return null;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream()
            .filter(review -> review.getId().equals(reviewId))
            .findFirst()
            .orElse(null);
    }

    @Override
    public Review updateReview(Long companyId, Long reviewId, Review review) {
        if (companyService.getCompanyById(reviewId) != null) {
            review.setCompany(companyService.getCompanyById(companyId));
            review.setId(reviewId);
            reviewRepository.save(review);
            return review;
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if (companyService.getCompanyById(reviewId) != null && reviewRepository.existsById(reviewId)) {
            Review review = reviewRepository.findById(reviewId).orElse(null);
            Company company = review.getCompany();
            review.setCompany(null);
            company.getReviews().remove(review);
            companyService.updateCompany(companyId, company);
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
