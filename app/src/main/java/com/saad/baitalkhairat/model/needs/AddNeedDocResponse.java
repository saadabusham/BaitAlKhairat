package com.saad.baitalkhairat.model.needs;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.File;

public class AddNeedDocResponse {

    @SerializedName("file")
    private File file;

    public File getFile() {
        return file;
    }
}