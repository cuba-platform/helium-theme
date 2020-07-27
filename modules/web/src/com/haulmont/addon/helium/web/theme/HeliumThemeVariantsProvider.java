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
import com.haulmont.cuba.web.theme.ThemeVariantsProvider;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component(ThemeVariantsProvider.NAME)
public class HeliumThemeVariantsProvider implements ThemeVariantsProvider {

    @Inject
    protected HeliumThemeVariantsManager variantsManager;

    @Override
    public List<String> getThemeVariants() {
        List<String> variants = new ArrayList<>(2);

        String themeMode = getThemeMode();
        if (!Strings.isNullOrEmpty(themeMode)) {
            variants.add(themeMode);
        }

        String themeSize = getThemeSize();
        if (!Strings.isNullOrEmpty(themeSize)) {
            variants.add(themeSize);
        }

        return variants;
    }

    @Nullable
    protected String getThemeMode() {
        String themeMode = variantsManager.getUserAppThemeMode();
        if (Strings.isNullOrEmpty(themeMode)) {
            themeMode = variantsManager.getDefaultAppThemeModeToUse();
        }

        return Objects.equals(themeMode, variantsManager.getDefaultAppThemeMode())
                ? null          // Don't add the default value to the result style class names list
                : themeMode;
    }

    @Nullable
    protected String getThemeSize() {
        String themeSize = variantsManager.getUserAppThemeSize();
        if (Strings.isNullOrEmpty(themeSize)) {
            themeSize = variantsManager.getDefaultAppThemeSizeToUse();
        }

        return Objects.equals(themeSize, variantsManager.getDefaultAppThemeSize())
                ? null          // Don't add the default value to the result style class names list
                : themeSize;
    }
}
