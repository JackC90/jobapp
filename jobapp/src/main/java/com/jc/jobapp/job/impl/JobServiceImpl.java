package com.jc.jobapp.job.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.jc.jobapp.job.Job;
import com.jc.jobapp.job.JobRepository;
import com.jc.jobapp.job.JobService;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;
    private Long nextId = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public Job createJob(@RequestBody Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
        return job;
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse( null);
    }

    @Override
    public Job updateJob(Long id, Job input) {
        Optional<Job> jobOptional = jobRepository.findById(id);

        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(input.getTitle());
            job.setDescription(input.getDescription());
            job.setMinSalary(input.getMinSalary());
            job.setMaxSalary(input.getMaxSalary());
            job.setLocation(input.getLocation());
            jobRepository.save(job);
            return job;
        }
        return null;
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
}
