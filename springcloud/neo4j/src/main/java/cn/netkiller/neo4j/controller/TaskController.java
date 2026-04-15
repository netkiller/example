package cn.netkiller.neo4j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.netkiller.neo4j.domain.Task;
import cn.netkiller.neo4j.repositories.TaskRepository;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	TaskRepository taskRepository;

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
	@Transactional
	public Task saveTask(@RequestBody Task taskInfo) {
		Task task = taskRepository.save(taskInfo);
		return task;
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public Task create(@PathVariable String name) { 
		Task task = taskRepository.findByTaskName(name); 
		return task; 
　　}
}