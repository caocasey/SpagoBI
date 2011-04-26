/**

SpagoBI - The Business Intelligence Free Platform

Copyright (C) 2005-2009 Engineering Ingegneria Informatica S.p.A.

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

**/
package it.eng.spagobi.analiticalmodel.documentsbrowser.service;

import it.eng.spago.error.EMFUserError;
import it.eng.spagobi.analiticalmodel.document.x.AbstractSpagoBIAction;
import it.eng.spagobi.commons.dao.DAOFactory;
import it.eng.spagobi.commons.serializer.SerializationException;
import it.eng.spagobi.commons.serializer.SerializerFactory;
import it.eng.spagobi.tools.objmetadata.bo.ObjMetadata;
import it.eng.spagobi.tools.objmetadata.dao.IObjMetadataDAO;
import it.eng.spagobi.utilities.service.JSONSuccess;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetCategoriesList extends AbstractSpagoBIAction{
	
	private static Logger logger = Logger.getLogger(GetCategoriesList.class);
	@Override
	public void doService() {
		logger.debug("IN");
		try {
			JSONArray toReturn = new JSONArray();
			IObjMetadataDAO metadataDAO = DAOFactory.getObjMetadataDAO();
			List results = metadataDAO.loadAllObjMetadata();
			ArrayList objects = new ArrayList();
			for (int i = 0; i < results.size(); i++) {
				// look for binary content mimetype
				ObjMetadata metadata = (ObjMetadata)results.get(i);			
				objects.add(metadata);
			}
			toReturn = (JSONArray) SerializerFactory.getSerializer("application/json").serialize(objects, null);
			writeBackToClient( new JSONSuccess( toReturn ) ); 
		} catch (EMFUserError e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		} catch (SerializationException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		
		logger.debug("OUT");
		
	}
}
