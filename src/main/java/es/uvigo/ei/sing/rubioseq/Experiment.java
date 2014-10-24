/*
	This file is part of RUbioSeq-GUI.

	RUbioSeq-GUI is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.

	RUbioSeq-GUI is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
	GNU General Public License for more details.

	You should have received a copy of the GNU General Public License
	along with RUbioSeq-GUI.  If not, see <http://www.gnu.org/licenses/>.
*/
package es.uvigo.ei.sing.rubioseq;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author hlfernandez
 *
 */
@Entity
public class Experiment {
	
	public static final String LOG_FILE = "output.log";
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=false)
	private User user;
	
	@Column(nullable = false)
	private long created = System.currentTimeMillis();
	
	@Column(nullable = false)
	private ExperimentType type;
	
	@Column(nullable = false)
	private Integer executionLevel;
	
	@Column(nullable = false)
	private ExperimentStatus status;
	
	@Column(nullable = false, columnDefinition="VARCHAR(300)")
	private String workingDirectory;
	
	public Experiment(){
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public ExperimentType getType() {
		return type;
	}

	public void setType(ExperimentType type) {
		this.type = type;
	}
	
	public Integer getExecutionLevel() {
		return executionLevel;
	}
	
	public void setExecutionLevel(Integer executionLevel) {
		this.executionLevel = executionLevel;
	}

	public ExperimentStatus getStatus() {
		return status;
	}

	public void setStatus(ExperimentStatus status) {
		this.status = status;
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}
	
	public boolean isMonitorizableExperiment(){
		return this.status.equals(ExperimentStatus.Running);
	}
}
