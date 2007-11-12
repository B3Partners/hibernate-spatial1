/**
 * $Id$
 *
 * This file is part of Hibernate Spatial, an extension to the 
 * hibernate ORM solution for geographic data. 
 *  
 * Copyright © 2007 Geovise BVBA
 * Copyright © 2007 K.U. Leuven LRD, Spatial Applications Division, Belgium
 *
 * This work was partially supported by the European Commission, 
 * under the 6th Framework Programme, contract IST-2-004688-STP.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * For more information, visit: http://www.hibernatespatial.org/
 */
package org.hibernatespatial.test.model;

// Generated Aug 9, 2006 9:35:48 PM by Hibernate Tools 3.2.0.beta6a

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;

/**
 * Spatialtest generated by hbm2java
 */
public class PolygonEntity implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;

	private long id;

	private String name;

	private Polygon geometry;

	// Constructors

	/** default constructor */
	public PolygonEntity() {
	}

	/** minimal constructor */
	public PolygonEntity(long id) {
		this.id = id;
	}

	/** full constructor */
	public PolygonEntity(long id, String name, Geometry geom) {
		this.id = id;
		this.name = name;
		this.geometry = (Polygon) geom;
	}

	// Property accessors
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Geometry getGeometry() {
		return this.geometry;
	}

	public void setGeometry(Geometry geom) {
		this.geometry = (Polygon) geom;
	}

}
