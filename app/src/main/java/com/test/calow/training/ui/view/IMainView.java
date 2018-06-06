package com.test.calow.training.ui.view;

import com.test.calow.training.ui.model.DynamicEntity;

import java.util.List;

/**
 * Created by calow on 18/5/18.
 */

public interface IMainView {
    void update2loadData(int loadType, List<DynamicEntity> datas);
}
