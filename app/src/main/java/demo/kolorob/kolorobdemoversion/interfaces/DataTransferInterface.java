package demo.kolorob.kolorobdemoversion.interfaces;

import java.util.ArrayList;

import demo.kolorob.kolorobdemoversion.model.CommonModel;

/**
 * Created by shamima.yasmin on 12/5/2017.
 */

public interface DataTransferFragment <ModelType extends CommonModel>{
    void sendDataList(ArrayList <ModelType> list);
}
