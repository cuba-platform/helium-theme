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
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.Route;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.components.data.table.ContainerTableItems;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.DataComponents;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.web.app.UserSettingsTools;
import com.vaadin.server.Page;
import org.apache.commons.collections4.CollectionUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Route("helium-theme-settings")
@UiController("helium_ThemeSettingsScreen")
@UiDescriptor("theme-settings-screen.xml")
public class ThemeSettingsScreen extends Screen {

    protected static final int SAMPLE_DATA_SIZE = 10;

    @Inject
    protected RadioButtonGroup<String> modeField;
    @Inject
    protected RadioButtonGroup<String> sizeField;

    @Inject
    protected GroupBoxLayout previewBox;
    @Inject
    protected ScrollBoxLayout innerPreviewBox;

    @Inject
    private Table<User> table;
    @Inject
    private LookupField<String> requiredLookupField;
    @Inject
    private LookupField<String> lookupField;
    @Inject
    private RadioButtonGroup<String> radioButtonGroup;
    @Inject
    private CheckBoxGroup<String> checkBoxGroup;

    @Inject
    protected HeliumThemeVariantsManager variantsManager;

    @Inject
    private Metadata metadata;
    @Inject
    private DataComponents dataComponents;
    @Inject
    protected UserSettingsTools userSettingsTools;
    @Inject
    private MessageBundle messageBundle;
    @Inject
    private Notifications notifications;

    protected String appWindowTheme;
    protected boolean settingsAvailable;

    @Subscribe
    public void onInit(InitEvent event) {
        checkSettingsAvailable();

        if (settingsAvailable) {
            appWindowTheme = userSettingsTools.loadAppWindowTheme();

            initModeField();
            initSizeField();
            initTableSample();
            initOptions();
        }
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if (!settingsAvailable) {
            notifications.create()
                    .withCaption(messageBundle.getMessage("noSettingsAvailable"))
                    .withType(Notifications.NotificationType.WARNING)
                    .show();

            close(WINDOW_CLOSE_ACTION);
        }
    }

    protected void checkSettingsAvailable() {
        List<String> appThemeModeList = variantsManager.getAppThemeModeList();
        List<String> appThemeSizeList = variantsManager.getAppThemeSizeList();

        settingsAvailable = CollectionUtils.isNotEmpty(appThemeModeList)
                || CollectionUtils.isNotEmpty(appThemeSizeList);
    }

    protected void initTableSample() {
        CollectionContainer<User> container = dataComponents.createCollectionContainer(User.class);
        container.setItems(generateUsersSampleData());
        table.setItems(new ContainerTableItems<>(container));
    }

    protected List<User> generateUsersSampleData() {
        List<User> users = new ArrayList<>(SAMPLE_DATA_SIZE);
        for (int i = 0; i < SAMPLE_DATA_SIZE; i++) {
            users.add(createUser(i));
        }
        return users;
    }

    protected User createUser(int index) {
        User user = metadata.create(User.class);
        user.setLogin("user" + index);
        user.setName("User " + index);
        user.setActive(index % 2 == 0);

        return user;
    }

    protected void initOptions() {
        List<String> options = generateSampleOptions();
        checkBoxGroup.setOptionsList(options);
        radioButtonGroup.setOptionsList(options);
        lookupField.setOptionsList(options);
        requiredLookupField.setOptionsList(options);
    }

    protected List<String> generateSampleOptions() {
        return Arrays.asList("Option 1", "Options 2", "Option 3");
    }

    protected void initModeField() {
        modeField.setOptionsList(variantsManager.getAppThemeModeList());
        modeField.setValue(variantsManager.loadUserAppThemeModeSetting());
    }

    protected void initSizeField() {
        sizeField.setOptionsList(variantsManager.getAppThemeSizeList());
        sizeField.setValue(variantsManager.loadUserAppThemeSizeSetting());
    }

    @Subscribe("applyBtn")
    public void onApplyBtnClick(Button.ClickEvent event) {
        applyThemeMode();
        applyThemeSize();

        Page.getCurrent().reload();
    }

    protected void applyThemeMode() {
        String mode = modeField.getValue();
        variantsManager.setUserAppThemeMode(mode);
    }

    protected void applyThemeSize() {
        String size = sizeField.getValue();
        variantsManager.setUserAppThemeSize(size);
    }

    @Subscribe("modeField")
    public void onModeFieldValueChange(HasValue.ValueChangeEvent<String> event) {
        previewBox.setStyleName(appWindowTheme + " " + modeField.getValue());
    }

    @Subscribe("sizeField")
    public void onSizeFieldValueChange(HasValue.ValueChangeEvent<String> event) {
        innerPreviewBox.setStyleName(sizeField.getValue());
    }
}