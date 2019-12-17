/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */

package cordova.plugins.screenorientation;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import java.util.*;

public class CDVOrientation extends CordovaPlugin {
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        if (action.equals("screenOrientation")) {
            return routeScreenOrientation(args, callbackContext);
        }

        return false;
    }

    private static Map<String, Integer> orientations = new HashMap<String, Integer>() {{
        put("any", ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);
        put("portrait-primary", ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        put("portrait-secondary", ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
        put("landscape-primary", ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        put("landscape-secondary", ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
        put("portrait", ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
        put("landscape", ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);
    }};

    private boolean routeScreenOrientation(JSONArray args, CallbackContext callbackContext) {
        String typeOfOrientation = args.optString(1);
        Activity activity = cordova.getActivity();

        Integer orientation = orientations.get(typeOfOrientation);
        if (orientation != null) {
            activity.setRequestedOrientation(orientation);
        }

        callbackContext.success();
        return true;
    }
}
