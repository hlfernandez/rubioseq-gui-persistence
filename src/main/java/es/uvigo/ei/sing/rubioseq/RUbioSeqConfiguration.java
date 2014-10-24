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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author hlfernandez
 *
 */
@Entity
public class RUbioSeqConfiguration {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false, length=150)
	private String rubioseqCommand;

	@Column(nullable = false, length=250)
	private String privateDatastoresRootDirectory;
	
	@Column(nullable = false)
	private boolean createPrivateDatastoresOnUserRegistration;

	public String getRubioseqCommand() {
		return rubioseqCommand;
	}

	public void setRubioseqCommand(String rubioseqCommand) {
		this.rubioseqCommand = rubioseqCommand;
	}
	
	public String getPrivateDatastoresRootDirectory() {
		return privateDatastoresRootDirectory;
	}
	
	public void setPrivateDatastoresRootDirectory(
			String privateDatastoresRootDirectory) {
		this.privateDatastoresRootDirectory = privateDatastoresRootDirectory;
	}
	
	public boolean isCreatePrivateDatastoresOnUserRegistration() {
		return createPrivateDatastoresOnUserRegistration;
	}
	
	public void setCreatePrivateDatastoresOnUserRegistration(
			boolean createPrivateDatastoresOnUserRegistration) {
		this.createPrivateDatastoresOnUserRegistration = createPrivateDatastoresOnUserRegistration;
	}
}
