package com.yandex.auriwield.yandextranslate.domain.interactors.impl;

import com.yandex.auriwield.yandextranslate.domain.executor.Executor;
import com.yandex.auriwield.yandextranslate.domain.executor.MainThread;
import com.yandex.auriwield.yandextranslate.domain.interactors.IGetDirectionInteractor;
import com.yandex.auriwield.yandextranslate.domain.interactors.base.AbstractInteractor;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Direction;
import com.yandex.auriwield.yandextranslate.domain.model.translate.Language;
import com.yandex.auriwield.yandextranslate.domain.repository.TranslationRepository;

import static com.yandex.auriwield.yandextranslate.App.UI_CODE;

/**
 * @author g.zaripov
 */
public class GetDirectionInteractor extends AbstractInteractor implements IGetDirectionInteractor {
    private TranslationRepository mTranslationRepository;
    private IGetDirectionInteractor.Callback mCallback;

    public GetDirectionInteractor(Executor threadExecutor,
                                  MainThread mainThread,
                                  TranslationRepository translationRepository,
                                  IGetDirectionInteractor.Callback callback) {
        super(threadExecutor, mainThread);
        mTranslationRepository = translationRepository;
        mCallback = callback;
    }


    @Override
    public void run() {
        try {
            Direction direction = mTranslationRepository.getLastDirection();

            if (direction == null) {
                Language source = mTranslationRepository.getLanguageByCode(UI_CODE);

                if (source == null) {
                    mMainThread.post(new Runnable() {
                        @Override
                        public void run() {
                            mCallback.onReceiveDirectionError();
                        }
                    });
                    return;
                }

                Language target = Language.EN.equals(source) ? Language.RU : Language.EN;

                direction = new Direction(source, target);

                mTranslationRepository.saveDirection(direction);

            }

            final Direction finalDirection = direction;

            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onReceiveDirection(finalDirection);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}