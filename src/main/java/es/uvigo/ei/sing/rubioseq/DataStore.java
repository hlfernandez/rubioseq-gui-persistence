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
public class DataStore {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false, length=100, unique=true)
	private String name;
	
	@Column(nullable = false, length=100)
	private String path;
	
	@Column(nullable = false)
	private DataStoreMode mode;
	
	@Column(nullable = false)
	private DataStoreType type;
	
	@ManyToOne(fetch=FetchType.LAZY, optional=true)
	private User user;
	
	public DataStore(){
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public DataStoreMode getMode() {
		return mode;
	}

	public void setMode(DataStoreMode mode) {
		this.mode = mode;
	}

	public DataStoreType getType() {
		return type;
	}

	public void setType(DataStoreType type) {
		this.type = type;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof DataStore))
			return false;

		DataStore other = (DataStore) o;
		if(this.name == null  || other.name == null){
			return false;
		}
		return this.name.equals(other.name);
	}

	@Override
	public int hashCode() {
		if(this.name == null) return 0;
		else return this.name.hashCode();
	}
}
