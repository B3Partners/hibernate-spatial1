/*
 * $Id:$
 *
 * This file is part of Hibernate Spatial, an extension to the
 * hibernate ORM solution for geographic data.
 *
 * Copyright © 2007-2010 Geovise BVBA
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

package org.hibernatespatial.test;

/**
 * <code>SQLExpressoinTemplate</code>s generate database-specific
 * SQL statements for a given <code>TestGeometry</code> instance.
 *
 * @author Karel Maesen, Geovise BVBA
 */
public interface SQLExpressionTemplate {

    /**
     * Returns an insert SQL statement for the specified <code>TestGeometry</code>
     *
     * @param testGeometry
     * @return an insert SQL for testGeometry
     */
    public String toInsertSql(TestGeometry testGeometry);

}
