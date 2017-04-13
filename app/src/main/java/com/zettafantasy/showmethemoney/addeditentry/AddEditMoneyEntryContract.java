/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.zettafantasy.showmethemoney.addeditentry;

import com.zettafantasy.showmethemoney.BasePresenter;
import com.zettafantasy.showmethemoney.BaseView;
import com.zettafantasy.showmethemoney.entity.MoneyEntry;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface AddEditMoneyEntryContract {

    interface View extends BaseView<Presenter> {
        void onDateChanged(long date); //TODO 어떤 타입으로 저장해야 좋은지 모르겠음(후보 long)

        void launchDatePicker();

        void setType(MoneyEntry.Type type);

        void setSubType(int subType);

        void launchSubtypePicker();

        void onMemoChanged(String memo);

        void onAmountChanged(long amount);

        void showEntryList(); //TODO 저장 후 목록을 보는 메소드

        void showEmptyAmountError();

        void onSaveButtonClick();
    }

    interface Presenter extends BasePresenter {
        void setAmount(long amount);

        void setMemo(String memeo);

        void setDate(long date);
        
        void saveEntry();

        void populateEntry();
    }
}
