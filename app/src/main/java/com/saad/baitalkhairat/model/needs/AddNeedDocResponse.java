package com.saad.baitalkhairat.model.needs;

import com.google.gson.annotations.SerializedName;
import com.saad.baitalkhairat.model.File;

import java.io.Serializable;

public class AddNeedDocResponse implements Serializable {

    @SerializedName("file")
    private File file;

    public File getFile() {
        return file;
    }
}