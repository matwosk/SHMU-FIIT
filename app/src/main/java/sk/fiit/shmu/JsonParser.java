package sk.fiit.shmu;

import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser {

    private static String example = "{\"station_info\":{\"name\":\"Bratislava (letisko)\",\"lon\":17.200,\"lat\":48.172},\"weather\":[{\"hour\":\"00\",\"temp\":6.7,\"precipitation\":0.0},{\"hour\":\"02\",\"temp\":6.7,\"precipitation\":0.0},{\"hour\":\"04\",\"temp\":6.7,\"precipitation\":0.0},{\"hour\":\"06\",\"temp\":6.7,\"precipitation\":0.0},{\"hour\":\"08\",\"temp\":6.7,\"precipitation\":0.0},{\"hour\":\"10\",\"temp\":6.7,\"precipitation\":0.0},{\"hour\":\"12\",\"temp\":6.7,\"precipitation\":0.0},{\"hour\":\"14\",\"temp\":6.7,\"precipitation\":0.0},{\"hour\":\"16\",\"temp\":6.7,\"precipitation\":0.0},{\"hour\":\"18\",\"temp\":6.7,\"precipitation\":0.0},{\"hour\":\"20\",\"temp\":6.7,\"precipitation\":0.0},{\"hour\":\"22\",\"temp\":6.7,\"precipitation\":0.0}]}";

    public static JSONObject getJsonObject() throws JSONException {

        JSONObject object;
        object = new JSONObject(example);

        return object;
    }

    public static String getStationName(JSONObject object) throws JSONException {
        JSONObject stationInfo = object.getJSONObject("station_info");

        return stationInfo.getString("name");
    }

}
