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

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.zettafantasy.showmethemoney.ActivityUtils;
import com.zettafantasy.showmethemoney.R;

/**
 * Displays an add or edit money entry screen.
 */
public class AddEditMoneyEntryActivity extends AppCompatActivity {

    public static final int REQUEST_ADD_TASK = 1;

    private AddEditMoneyEntryPresenter mAddEditMoneyEntryPresenter;
    private AddEditMoneyEntryFragment addEditMoneyEntryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addentry_act);

        // Set up the toolbar.
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        addEditMoneyEntryFragment =
                (AddEditMoneyEntryFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame); //TODO contentFrame id가 여러개인데 현재 activity에서 찾아주는건가? 따로 infalte해주지 않아도 되는건가?

        long entryId = getIntent().getLongExtra(AddEditMoneyEntryFragment.ARGUMENT_EDIT_ENTRY_ID, -1);

        if (addEditMoneyEntryFragment == null) {
            addEditMoneyEntryFragment = AddEditMoneyEntryFragment.newInstance();
//
//            if (getIntent().hasExtra(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID)) {
//                actionBar.setTitle(R.string.edit_task);
//                Bundle bundle = new Bundle();
//                bundle.putString(AddEditTaskFragment.ARGUMENT_EDIT_TASK_ID, taskId); //TODO EDIT_TASK_ID는 용도가 뭐지?
//                addEditTaskFragment.setArguments(bundle);
//            } else {
//                actionBar.setTitle(R.string.add_task);
//            }
//
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    addEditMoneyEntryFragment, R.id.contentFrame);
//        }
//
//        boolean shouldLoadDataFromRepo = true;
//
//        // Prevent the presenter from loading data from the repository if this is a config change.
//        if (savedInstanceState != null) {
//            // Data might not have loaded when the config change happen, so we saved the state.
//            shouldLoadDataFromRepo = savedInstanceState.getBoolean(SHOULD_LOAD_DATA_FROM_REPO_KEY);
//        }
//
            // Create the presenter
            mAddEditMoneyEntryPresenter = new AddEditMoneyEntryPresenter(
                    addEditMoneyEntryFragment,
                    entryId);
            //taskId,
            //Injection.provideTasksRepository(getApplicationContext()),
            //addEditTaskFragment,
            //shouldLoadDataFromRepo);

            addEditMoneyEntryFragment.setPresenter(mAddEditMoneyEntryPresenter);
        }
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            addEditMoneyEntryFragment.onSaveButtonClick();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
