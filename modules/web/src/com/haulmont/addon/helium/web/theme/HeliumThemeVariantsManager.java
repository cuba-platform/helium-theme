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

package com.haulmont.addon.helium.web.theme;

import com.google.common.base.Strings;
import com.haulmont.cuba.core.global.ClientType;
import com.haulmont.cuba.core.global.GlobalConfig;
import com.haulmont.cuba.security.app.UserSettingService;
import com.haulmont.cuba.web.sys.AppCookies;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.List;

@Component(HeliumThemeVariantsManager.NAME)
public class HeliumThemeVariantsManager {
    public static final String NAME = "helium_HeliumThemeVariantsManager";

    protected static final String THEME_SIZE_USER_SETTING_NAME = "heliumThemeSize";
    protected static final String THEME_MODE_USER_SETTING_NAME = "heliumThemeMode";

    protected static final String THEME_MODE_COOKIE_PREFIX = "HELIUM_THEME_MODE_";
    protected static final String THEME_SIZE_COOKIE_PREFIX = "HELIUM_THEME_SIZE_";

    @Inject
    protected GlobalConfig globalConfig;

    @Inject
    protected UserSettingService userSettingService;

    @Inject
    protected HeliumThemeConfig heliumThemeConfig;

    protected AppCookies cookies;

    public HeliumThemeVariantsManager() {
        cookies = new AppCookies();
    }

    @Nullable
    public String getUserAppThemeMode() {
        return getCookieValue(THEME_MODE_COOKIE_PREFIX);
    }

    public void setUserAppThemeMode(String themeMode) {
        addCookie(THEME_MODE_COOKIE_PREFIX, themeMode);
        saveUserSetting(THEME_MODE_USER_SETTING_NAME, themeMode);
    }

    @Nullable
    public String loadUserAppThemeModeSetting() {
        return loadUserSetting(THEME_MODE_USER_SETTING_NAME);
    }

    public String loadUserAppThemeModeSettingOrDefault() {
        String mode = loadUserAppThemeModeSetting();
        return Strings.isNullOrEmpty(mode) ? getDefaultAppThemeModeToUse() : mode;
    }

    public String getDefaultAppThemeMode() {
        return heliumThemeConfig.getDefaultMode();
    }

    public String getDefaultAppThemeModeToUse() {
        String defaultModeToUse = heliumThemeConfig.getDefaultModeToUse();
        return Strings.isNullOrEmpty(defaultModeToUse) ? getDefaultAppThemeMode() : defaultModeToUse;
    }

    public List<String> getAppThemeModeList() {
        return heliumThemeConfig.getModes();
    }

    @Nullable
    public String getUserAppThemeSize() {
        return getCookieValue(THEME_SIZE_COOKIE_PREFIX);
    }

    public void setUserAppThemeSize(String themeSize) {
        addCookie(THEME_SIZE_COOKIE_PREFIX, themeSize);
        saveUserSetting(THEME_SIZE_USER_SETTING_NAME, themeSize);
    }

    @Nullable
    public String loadUserAppThemeSizeSetting() {
        return loadUserSetting(THEME_SIZE_USER_SETTING_NAME);
    }

    public String loadUserAppThemeSizeSettingOrDefault() {
        String size = loadUserAppThemeSizeSetting();
        return Strings.isNullOrEmpty(size) ? getDefaultAppThemeSizeToUse() : size;
    }

    public String getDefaultAppThemeSize() {
        return heliumThemeConfig.getDefaultSize();
    }

    public String getDefaultAppThemeSizeToUse() {
        String defaultSizeToUse = heliumThemeConfig.getDefaultSizeToUse();
        return Strings.isNullOrEmpty(defaultSizeToUse) ? getDefaultAppThemeSize() : defaultSizeToUse;
    }

    public List<String> getAppThemeSizeList() {
        return heliumThemeConfig.getSizes();
    }

    protected void addCookie(String name, String value) {
        cookies.addCookie(getFullCookieName(name), value);
    }

    @Nullable
    protected String getCookieValue(String name) {
        return cookies.getCookieValue(getFullCookieName(name));
    }

    protected void removeCookie(String name) {
        cookies.removeCookie(getFullCookieName(name));
    }

    protected String getFullCookieName(String prefix) {
        return prefix + globalConfig.getWebContextName();
    }

    protected void saveUserSetting(String name, String value) {
        userSettingService.saveSetting(ClientType.WEB, name, value);
    }

    @Nullable
    protected String loadUserSetting(String name) {
        return userSettingService.loadSetting(ClientType.WEB, name);
    }

    protected void removeUserSetting(String name) {
        userSettingService.deleteSettings(ClientType.WEB, name);
    }
}
