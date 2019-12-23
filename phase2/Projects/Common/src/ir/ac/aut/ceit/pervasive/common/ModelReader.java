package ir.ac.aut.ceit.pervasive.common;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Map;

/**
 * Provides a static method to read model data stored in a 'raw' resource as a
 * serialised map.
 * 
 * @author Atefeh Zareh
 */
public class ModelReader {

	@SuppressWarnings("unchecked")
	public static Map<Float[], String> getModel(final Context context,
			final int res) {
		InputStream is = null;
		try {
			is = context.getResources().openRawResource(res);
			Map<Float[], String> model = (Map<Float[], String>) new ObjectInputStream(
					is).readObject();
		
//			String data="";
//			for (Float[] key : model.keySet()) {
//				data += model.get(key);
//				for (Float float1 : key) {
//					data += ",";
//					data += float1;
//				}
//				data += "\n";
//			}
//			System.out.println(data);
			
			return model;
		} catch (Exception ex) {
			Log.e("ModelReader", "Unable to load model", ex);
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException ex) {
					// Don't care
				}
			}
		}

		return null;
	}

}
