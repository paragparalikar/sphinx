package com.sphinx.workflow.task.execution;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sphinx.workflow.task.TaskExecution;

@Repository
public interface TaskExecutorRepository extends JpaRepository<TaskExecution, Long> {

}
