package cn.netkiller.neo4j.repositories;

import java.util.Collection;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.query.Param;

import cn.netkiller.neo4j.domain.Task;

public interface TaskRepository extends GraphRepository<Task> {

	Task findByTaskName(@Param("taskName") String taskName);

	@Query("MATCH (t:Task) WHERE t.taskName =~ ('(?i).*'+{taskName}+'.*') RETURN t")
	Collection<Task> findByNameContaining(@Param("taskName") String taskName);

	Task save(Task taskInfo);
}
