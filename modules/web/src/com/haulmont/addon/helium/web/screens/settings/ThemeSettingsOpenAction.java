/*
 * Copyright (c) 2008-2020 Haulmont.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.haulmont.addon.helium.web.screens.settings;

import com.haulmont.addon.helium.web.theme.HeliumThemeVariantsManager;
import com.haulmont.cuba.core.global.BeanLocator;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.sys.BeanLocatorAware;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.config.MenuItem;
import com.haulmont.cuba.gui.config.MenuItemRunnable;
import com.haulmont.cuba.gui.screen.FrameOwner;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

import static com.haulmont.cuba.gui.screen.UiControllerUtils.getScreenContext;

public class ThemeSettingsOpenAction implements MenuItemRunnable, BeanLocatorAware {

    protected BeanLocator beanLocator;

    @Override
    public void run(FrameOwner origin, MenuItem menuItem) {
        HeliumThemeVariantsManager variantsManager = beanLocator.get(HeliumThemeVariantsManager.NAME);

        List<String> appThemeModeList = variantsManager.getAppThemeModeList();
        List<String> appThemeSizeList = variantsManager.getAppThemeSizeList();
        if (CollectionUtils.isEmpty(appThemeModeList)
                && CollectionUtils.isEmpty(appThemeSizeList)) {
            Messages messages = beanLocator.get(Messages.NAME);

            Notifications notifications = getScreenContext(origin).getNotifications();
            notifications.create()
                    .withCaption(messages.getMessage(ThemeSettingsOpenAction.class, "noSettingsAvailable"))
                    .withType(Notifications.NotificationType.WARNING)
                    .show();
        } else {
            getScreenContext(origin).getScreens()
                    .create(ThemeSettingsScreen.class)
                    .show();
        }
    }

    @Override
    public void setBeanLocator(BeanLocator beanLocator) {
        this.beanLocator = beanLocator;
    }
}
