package com.jc.jobapp.job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    Job createJob(Job job);
    Job getJobById(Long id);
    Job updateJob(Long id, Job input);
    Job deleteJobById(Long id);
}
