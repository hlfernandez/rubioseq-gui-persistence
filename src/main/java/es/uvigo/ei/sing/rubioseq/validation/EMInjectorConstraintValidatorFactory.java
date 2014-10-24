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
package es.uvigo.ei.sing.rubioseq.validation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorFactory;

/**
 * This class is a simple ConstraintValidationFactory that injects an entity manager when crating ConstraintValidation objects (see validation.xml)
 * Those objects should have a field of type EntityManager annotated with @Inject (CDI, see JSR 346)
 * This class is intended for environments where the CDI provider is not available (such as testing) 
 * This class works with a set of Thread local entity managers that should be provided manually just before you create the EntityManager
 * 
 * @author lipido
 *
 */
public class EMInjectorConstraintValidatorFactory implements ConstraintValidatorFactory {

	private static ThreadLocal<EntityManager> entityManagers = new ThreadLocal<EntityManager>();

	public static void setThreadLocalEntityManager(EntityManager em){
		entityManagers.set(em);
	}
	
	public EMInjectorConstraintValidatorFactory(){
	}
	public <T extends ConstraintValidator<?, ?>> T getInstance(Class<T> constraintValidatorClass) {
		try {
			T validator = constraintValidatorClass.newInstance();
			for (Field f : constraintValidatorClass.getDeclaredFields()){
				for (Annotation a : f.getAnnotations()){
					if (a.annotationType().equals(Inject.class) && EntityManager.class.isAssignableFrom(f.getType())){
						f.set(validator, entityManagers.get());
					}
				}
			}
			return validator;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public void releaseInstance(ConstraintValidator<?, ?> constraintValidator) {
		//nothing
	}

}
