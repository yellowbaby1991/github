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
package app.yellow.rx_mvp_sample.base.mvp;

import android.support.annotation.NonNull;

import app.yellow.rx_mvp_sample.data.Repository;
import rx.subscriptions.CompositeSubscription;

public class BasePresenterImpl<T extends BaseView> implements BasePresenter {

    @NonNull
    protected T mView;

    @NonNull
    protected CompositeSubscription mSubscriptions;

    @NonNull
    protected Repository mRepository;

    public BasePresenterImpl(@NonNull Repository repository, T view) {
        mRepository = repository;
        mSubscriptions = new CompositeSubscription();
        mView = view;
        mView.setPresenter(this);
    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mView.hideLoading();
        mSubscriptions.clear();
    }
}