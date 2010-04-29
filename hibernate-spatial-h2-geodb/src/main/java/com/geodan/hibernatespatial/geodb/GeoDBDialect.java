/**
 * Copyright 2010 Geodan IT b.v.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.geodan.hibernatespatial.geodb;

import org.hibernate.Hibernate;
import org.hibernate.dialect.H2Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.CustomType;
import org.hibernate.usertype.UserType;
import org.hibernatespatial.SpatialDialect;
import org.hibernatespatial.SpatialRelation;

/**
 * Extends the H2Dialect by also including information on spatial functions.
 * 
 * @author janb
 */
public class GeoDBDialect extends H2Dialect implements SpatialDialect {

	/*	
		Contents of GeoDB's spatial registration script (geodb.sql):
		
		CREATE ALIAS CreateSpatialIndex for "geodb.GeoDB.CreateSpatialIndex"
		CREATE ALIAS DropSpatialIndex for "geodb.GeoDB.DropSpatialIndex"
		CREATE ALIAS EnvelopeAsText for "geodb.GeoDB.EnvelopeAsText"
		CREATE ALIAS GeometryType for "geodb.GeoDB.GeometryType"
		CREATE ALIAS ST_Area FOR "geodb.GeoDB.ST_Area"
		CREATE ALIAS ST_AsEWKB FOR "geodb.GeoDB.ST_AsEWKB"
		CREATE ALIAS ST_AsEWKT FOR "geodb.GeoDB.ST_AsEWKT"
		CREATE ALIAS ST_AsHexEWKB FOR "geodb.GeoDB.ST_AsHexEWKB"
		CREATE ALIAS ST_AsText FOR "geodb.GeoDB.ST_AsText"
		CREATE ALIAS ST_BBOX FOR "geodb.GeoDB.ST_BBox"
		CREATE ALIAS ST_Buffer FOR "geodb.GeoDB.ST_Buffer" 
		CREATE ALIAS ST_Centroid FOR "geodb.GeoDB.ST_Centroid"
		CREATE ALIAS ST_Crosses FOR "geodb.GeoDB.ST_Crosses"
		CREATE ALIAS ST_Contains FOR "geodb.GeoDB.ST_Contains"
		CREATE ALIAS ST_DWithin FOR "geodb.GeoDB.ST_DWithin"
		CREATE ALIAS ST_Disjoint FOR "geodb.GeoDB.ST_Disjoint"
		CREATE ALIAS ST_Envelope FOR "geodb.GeoDB.ST_Envelope"
		CREATE ALIAS ST_Equals FOR "geodb.GeoDB.ST_Equals"
		CREATE ALIAS ST_GeoHash FOR "geodb.GeoDB.ST_GeoHash"
		CREATE ALIAS ST_GeomFromEWKB FOR "geodb.GeoDB.ST_GeomFromEWKB"
		CREATE ALIAS ST_GeomFromEWKT FOR "geodb.GeoDB.ST_GeomFromEWKT"
		CREATE ALIAS ST_GeomFromText FOR "geodb.GeoDB.ST_GeomFromText"
		CREATE ALIAS ST_GeomFromWKB FOR "geodb.GeoDB.ST_GeomFromWKB"
		CREATE ALIAS ST_Intersects FOR "geodb.GeoDB.ST_Intersects"
		CREATE ALIAS ST_IsEmpty FOR "geodb.GeoDB.ST_IsEmpty"
		CREATE ALIAS ST_IsSimple FOR "geodb.GeoDB.ST_IsSimple"
		CREATE ALIAS ST_IsValid FOR "geodb.GeoDB.ST_IsValid"
		CREATE ALIAS ST_MakeBox2D FOR "geodb.GeoDB.ST_MakeBox2D"
		CREATE ALIAS ST_Overlaps FOR "geodb.GeoDB.ST_Overlaps"
		CREATE ALIAS ST_SRID FOR "geodb.GeoDB.ST_SRID"
		CREATE ALIAS ST_SetSRID FOR "geodb.GeoDB.ST_SetSRID"
		CREATE ALIAS ST_Simplify FOR "geodb.GeoDB.ST_Simplify"
		CREATE ALIAS ST_Touches FOR "geodb.GeoDB.ST_Touches"
		CREATE ALIAS ST_Within FOR "geodb.GeoDB.ST_Within"
		CREATE ALIAS Version FOR "geodb.GeoDB.Version"
	 */
	
	/**
	 * Constructor. Registers OGC simple feature functions (see 
	 * http://portal.opengeospatial.org/files/?artifact_id=829 for details).
	 * 
	 * Note for the registerfunction method: it registers non-standard database
	 * functions: first argument is the internal (OGC standard) function name, 
	 * second the name as it occurs in the spatial dialect
	 */
	public GeoDBDialect() {
		super();
		
		// Register Geometry column type
		registerColumnType(java.sql.Types.BLOB, "GEOM");
		
		// Register functions that operate on spatial types		
// NOT YET AVAILABLE IN GEODB
//		registerFunction("dimension", new StandardSQLFunction("dimension",
//				Hibernate.INTEGER));
		registerFunction("geometrytype", new StandardSQLFunction(
				"GeometryType", Hibernate.STRING));
		registerFunction("srid", new StandardSQLFunction("ST_SRID",
				Hibernate.INTEGER));
		registerFunction("envelope", new StandardSQLFunction("ST_Envelope",
				new CustomType(GeoDBGeometryUserType.class, null)));
		registerFunction("astext", new StandardSQLFunction("ST_AsText",
				Hibernate.STRING));
		registerFunction("asbinary", new StandardSQLFunction("ST_AsEWKB",
				Hibernate.BINARY));
		registerFunction("isempty", new StandardSQLFunction("ST_IsEmpty",
				Hibernate.BOOLEAN));
		registerFunction("issimple", new StandardSQLFunction("ST_IsSimple",
				Hibernate.BOOLEAN));
// NOT YET AVAILABLE IN GEODB		
//		registerFunction("boundary", new StandardSQLFunction("boundary",
//				new CustomType(GeoDBGeometryUserType.class, null)));

		// Register functions for spatial relation constructs
		registerFunction("overlaps", new StandardSQLFunction("ST_Overlaps",
				Hibernate.BOOLEAN));
		registerFunction("intersects", new StandardSQLFunction("ST_Intersects",
				Hibernate.BOOLEAN));
		registerFunction("equals", new StandardSQLFunction("ST_Equals",
				Hibernate.BOOLEAN));
		registerFunction("contains", new StandardSQLFunction("ST_Contains",
				Hibernate.BOOLEAN));
		registerFunction("crosses", new StandardSQLFunction("ST_Crosses",
				Hibernate.BOOLEAN));
		registerFunction("disjoint", new StandardSQLFunction("ST_Disjoint",
				Hibernate.BOOLEAN));
		registerFunction("touches", new StandardSQLFunction("ST_Touches",
				Hibernate.BOOLEAN));
		registerFunction("within", new StandardSQLFunction("ST_Within",
				Hibernate.BOOLEAN));
// NOT YET AVAILABLE IN GEODB			
//		registerFunction("relate", new StandardSQLFunction("relate",
//				Hibernate.BOOLEAN));

		// register the spatial analysis functions
// NOT YET AVAILABLE IN GEODB		
//		registerFunction("distance", new StandardSQLFunction("distance",
//				Hibernate.DOUBLE));
		registerFunction("buffer", new StandardSQLFunction("ST_Buffer",
				new CustomType(GeoDBGeometryUserType.class, null)));
// NOT YET AVAILABLE IN GEODB			
//		registerFunction("convexhull", new StandardSQLFunction("convexhull",
//				new CustomType(GeoDBGeometryUserType.class, null)));
//		registerFunction("difference", new StandardSQLFunction("difference",
//				new CustomType(GeoDBGeometryUserType.class, null)));
//		registerFunction("intersection", new StandardSQLFunction(
//				"intersection", new CustomType(GeoDBGeometryUserType.class, null)));
//		registerFunction("symdifference",
//				new StandardSQLFunction("symdifference", new CustomType(
//						GeoDBGeometryUserType.class, null)));
//		registerFunction("geomunion", new StandardSQLFunction("geomunion",
//				new CustomType(GeoDBGeometryUserType.class, null)));
		
		//register Spatial Aggregate funciton
// NOT YET AVAILABLE IN GEODB			
//		registerFunction("extent", new StandardSQLFunction("extent", 
//				new CustomType(GeoDBGeometryUserType.class, null)));
	}
	
	/* (non-Javadoc)
	 * @see org.hibernatespatial.SpatialDialect#getGeometryUserType()
	 */
	public UserType getGeometryUserType() {
		return new GeoDBGeometryUserType();
	}

	/* (non-Javadoc)
	 * @see org.hibernatespatial.SpatialDialect#getSpatialAggregateSQL(java.lang.String, int)
	 */
	public String getSpatialAggregateSQL(String columnName, int aggregation) {
		switch (aggregation) {
// NOT YET AVAILABLE IN GEODB	
//		case SpatialAggregate.EXTENT:
//			StringBuilder stbuf = new StringBuilder();
//			stbuf.append("extent(").append(columnName).append(")");
//			return stbuf.toString();
		default:
			throw new IllegalArgumentException("Aggregations of type "
					+ aggregation + " are not supported by this dialect");
		}
	}

	/* (non-Javadoc)
	 * @see org.hibernatespatial.SpatialDialect#getSpatialFilterExpression(java.lang.String)
	 */
	public String getSpatialFilterExpression(String columnName) {
		return "(" + columnName + " && ? ) ";
	}

	/* (non-Javadoc)
	 * @see org.hibernatespatial.SpatialDialect#getSpatialRelateSQL(java.lang.String, int, boolean)
	 */
	public String getSpatialRelateSQL(String columnName, int spatialRelation,
			boolean hasFilter) {
		switch (spatialRelation) {
		case SpatialRelation.WITHIN:
			return " ST_Within(" + columnName + ", ?)";
		case SpatialRelation.CONTAINS:
			return " ST_Contains(" + columnName + ", ?)";
		case SpatialRelation.CROSSES:
			return " ST_Crosses(" + columnName + ", ?)";
		case SpatialRelation.OVERLAPS:
			return " ST_Overlaps(" + columnName + ", ?)";
		case SpatialRelation.DISJOINT:
			return " ST_Disjoint(" + columnName + ", ?)";
		case SpatialRelation.INTERSECTS:
			return " ST_Intersects(" + columnName	+ ", ?)";
		case SpatialRelation.TOUCHES:
			return  " ST_Touches(" + columnName + ", ?)";
		case SpatialRelation.EQUALS:
			return " ST_Equals(" + columnName + ", ?)";
		default:
			throw new IllegalArgumentException(
					"Spatial relation is not known by this dialect");
		}
	}

	/* (non-Javadoc)
	 * @see org.hibernatespatial.SpatialDialect#getDbGeometryTypeName()
	 */
	public String getDbGeometryTypeName() {
		return "GEOM";
	}

	/* (non-Javadoc)
	 * @see org.hibernatespatial.SpatialDialect#isTwoPhaseFiltering()
	 */
	public boolean isTwoPhaseFiltering() {
		return false;
	}

}
