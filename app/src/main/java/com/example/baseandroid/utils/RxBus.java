package com.example.baseandroid.utils;

import static java.lang.annotation.RetentionPolicy.SOURCE;

import android.util.SparseArray;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

import java.lang.annotation.Retention;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;

public class RxBus {

    private static SparseArray<BehaviorSubject<Object>> bBehaviorMap = new SparseArray<>();
    private static SparseArray<PublishSubject<Object>> sSubjectMap = new SparseArray<>();
    private static Map<Object, CompositeDisposable> sSubscriptionsMap = new HashMap<>();
    public static final int TAG_HIDE_WIDGET_NO_PERMISSION = 100001;
    public static final int TAG_CLICK_GO_TO_SET = 100002;
    public static final int TAG_WIDGET_TITLE_SCREEN = 100003;
    public static final int TAG_CLICK_TYPE_LIST_HOME = 100004;
    public static final int TAG_TITLE_LIST_FILE_ACTIVITY = 100005;
    public static final int TAG_UPDATE_LANGUAGE_NOW = 100006;
    public static final int TAG_CHANGE_DARK_MODE = 100007;
    public static final int TAG_SEARCH_TEXT = 100008;
    public static final int TAG_RENAME_SUCCESS = 100009;
    public static final int TAG_RADIO_BUTTON_IS_CHECKED = 1000010;
    public static final int TAG_CLICK_SELECT_ALL = 100011;
    public static final int TAG_CLICK_STAR_FAVOURITE = 100012;
    public static final int TAG_DELETE_ONE_FILE_SUCCESS = 100013;
    public static final int TAG_DELETE_MULTI_FILE_SUCCESS = 100014;
    public static final int SHOW_POPUP_NOT_INTERNET = 10001000;



    @Retention(SOURCE)
    @IntDef(value = {
            TAG_HIDE_WIDGET_NO_PERMISSION,
            TAG_CLICK_GO_TO_SET,
            TAG_WIDGET_TITLE_SCREEN,
            TAG_CLICK_TYPE_LIST_HOME,
            TAG_TITLE_LIST_FILE_ACTIVITY,
            TAG_UPDATE_LANGUAGE_NOW,
            TAG_CHANGE_DARK_MODE,
            TAG_SEARCH_TEXT,
            TAG_RENAME_SUCCESS,
            TAG_RADIO_BUTTON_IS_CHECKED,
            TAG_CLICK_SELECT_ALL,
            TAG_CLICK_STAR_FAVOURITE,
            TAG_DELETE_ONE_FILE_SUCCESS,
            TAG_DELETE_MULTI_FILE_SUCCESS,
            SHOW_POPUP_NOT_INTERNET


    })
    @interface Subject {

    }

    private RxBus() {
        // hidden constructor
    }

    /**
     * Get the subject or create it if it's not already in memory.
     */
    @NonNull
    private static PublishSubject<Object> getSubject(@Subject int subjectKey) {
        PublishSubject<Object> subject = sSubjectMap.get(subjectKey);
        if (subject == null) {
            subject = PublishSubject.create();
            sSubjectMap.put(subjectKey, subject);
        }

        return subject;
    }


    @NonNull
    private static BehaviorSubject<Object> getBehaviorSubject(@Subject int subjectKey) {
        BehaviorSubject<Object> subject = bBehaviorMap.get(subjectKey);
        if (subject == null) {
            subject = BehaviorSubject.create();
            bBehaviorMap.put(subjectKey, subject);
        }

        return subject;
    }


    /**
     * Get the CompositeDisposable or create it if it's not already in memory.
     */
    @NonNull
    private static CompositeDisposable getCompositeDisposable(@NonNull Object object) {
        CompositeDisposable compositeDisposable = sSubscriptionsMap.get(object);
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
            sSubscriptionsMap.put(object, compositeDisposable);
        }

        return compositeDisposable;
    }

    /**
     * Subscribe to the specified subject and listen for updates on that subject. Pass in an object to associate
     * your registration with, so that you can unsubscribe later.
     * <br/><br/>
     * <b>Note:</b> Make sure to call {@link RxBus#unregister(Object)} to avoid memory leaks.
     */
    public static void subscribe(@Subject int subject, @NonNull Object lifecycle, @NonNull Consumer<Object> action) {
        Disposable disposable = getSubject(subject).subscribe(action);
        getCompositeDisposable(lifecycle).add(disposable);
    }

    public static void subscribeOnMainThread(@Subject int subject, @NonNull Object lifecycle, @NonNull Consumer<Object> action) {
        Disposable disposable = getSubject(subject)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action);
        getCompositeDisposable(lifecycle).add(disposable);
    }

    public static void subscribeBehavior(@Subject int subject, @NonNull Object lifecycle, @NonNull Consumer<Object> action) {
        Disposable disposable = getBehaviorSubject(subject).subscribe(action);
        getCompositeDisposable(lifecycle).add(disposable);
    }

    /**
     * Unregisters this object from the bus, removing all subscriptions.
     * This should be called when the object is going to go out of memory.
     */
    public static void unregister(@NonNull Object lifecycle) {
        //We have to remove the composition from the map, because once you dispose it can't be used anymore
        CompositeDisposable compositeDisposable = sSubscriptionsMap.remove(lifecycle);
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }

    /**
     * Publish an object to the specified subject for all subscribers of that subject.
     */
    public static void publish(@Subject int subject, @NonNull Object message) {
        getSubject(subject).onNext(message);
    }

    public static void publishBehavior(@Subject int subject, @NonNull Object message) {
        getBehaviorSubject(subject).onNext(message);
    }

}
