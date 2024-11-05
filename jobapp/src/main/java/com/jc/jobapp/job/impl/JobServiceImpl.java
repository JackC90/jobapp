package com.jc.jobapp.job.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.jc.jobapp.job.Job;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.stereotype.Service;

import com.jc.jobapp.job.JobService;

@Service
public class JobServiceImpl implements JobService {
    private List<Job> jobs = new ArrayList<Job>();
    private Long nextId = 1L;

    @Override
    public List<Job> findAll() {
        return jobs;
    }

    @Override
    public Job createJob(@RequestBody Job job) {
        job.setId(nextId++);
        jobs.add(job);
        return job;
    }

    @Override
    public Job getJobById(Long id) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                return job;
            }
        }
        return null;
    }

    @Override
    public Job updateJob(Long id, Job input) {
        for (Job job : jobs) {
            if (job.getId().equals(id)) {
                job.setTitle(input.getTitle());
                job.setDescription(input.getDescription());
                job.setMinSalary(input.getMinSalary());
                job.setMaxSalary(input.getMaxSalary());
                job.setLocation(input.getLocation());
                return job;
            }
        }
        return null;
    }

    @Override
    public Job deleteJobById(Long id) {
        Iterator<Job> iterator = jobs.iterator();

        while (iterator.hasNext()) {
            Job job = iterator.next();
            if (job.getId().equals(id)) {
                iterator.remove();
                return job;
            }
        }

        return null;
    }
}
