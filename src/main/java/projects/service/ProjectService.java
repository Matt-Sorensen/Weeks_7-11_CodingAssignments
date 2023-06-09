package projects.service;

import recipes.exception.DbException;

import java.util.List;
import java.util.NoSuchElementException;

import projects.dao.ProjectDao;
import projects.entity.Project;

public class ProjectService {
	// Initialize the project DAO to perform database operations.
	private ProjectDao projectDao = new ProjectDao();

	/*
	 * This method calls the project DAO to get all project details, including
	 * materials, steps, and categories. If the project ID is invalid, it throws an
	 * exception.
	 * 
	 * @param projectId The project ID.
	 * 
	 * @return A Project object if successful.
	 * 
	 * @throws NoSuchElementException Thrown if the project with the given ID does
	 * not exist.
	 */

	public Project fetchProjectById(Integer projectId) {
		return projectDao.fetchProjectById(projectId).orElseThrow(
				() -> new NoSuchElementException("Project with project ID=" + projectId + " does not exist."));
	}

	/**
	 * This method simply calls the DAO class to insert a project row.
	 * 
	 * @param project The {@link Project} object.
	 * @return The project object with the newly generated primary key value.
	 */
	public Project addProject(Project project) {
		return projectDao.insertProject(project);
	}

	/**
	 * This method calls the project DAO to retrieve all project rows without
	 * accompanying details (materials, steps, and categories.).
	 * 
	 * @return A list of project records.
	 */
	public List<Project> fetchAllProjects() {
		return projectDao.fetchAllProjects();
	}

	// This method modifies the details of a project in the database. If the project
	// does not exist, it throws a DbException.
	public void modifyProjectDetails(Project project) {
		if (!projectDao.modifyProjectDetails(project)) {
			throw new DbException("Project with ID=" + project.getProjectId() + " does not exist.");
		}
	}

	/**
	 * This method deletes a project from the database by its ID. If the project
	 * does not exist, it throws a DbException.
	 * 
	 * @param projectId
	 */
	public void deleteProject(Integer projectId) {
		if (!projectDao.deleteProject(projectId)) {
			throw new DbException("Project with ID=" + projectId + " does not exist.");
		}
	}
}
