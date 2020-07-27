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

import com.haulmont.cuba.core.config.Config;
import com.haulmont.cuba.core.config.Property;
import com.haulmont.cuba.core.config.Source;
import com.haulmont.cuba.core.config.SourceType;
import com.haulmont.cuba.core.config.defaults.Default;
import com.haulmont.cuba.core.config.type.Factory;
import com.haulmont.cuba.core.config.type.StringListStringify;
import com.haulmont.cuba.core.config.type.StringListTypeFactory;
import com.haulmont.cuba.core.config.type.Stringify;
import com.haulmont.cuba.security.app.UserSettingService;

import java.util.List;

/**
 * Helium theme configuration parameters interface.
 */
@Source(type = SourceType.APP)
public interface HeliumThemeConfig extends Config {

    /**
     * @return the list of available theme modes, i.e. color presets
     */
    @Property("cuba.theme.helium.modes")
    @Factory(factory = StringListTypeFactory.class)
    @Stringify(stringify = StringListStringify.class)
    @Default("light|dark")
    List<String> getModes();

    /**
     * Returns the name of color preset that has no additional style class name.
     * <p>
     * <strong>Note:</strong> can’t be changed without corresponding changes in styles.
     *
     * @return the name of color preset that has no additional style class name
     */
    @Property("cuba.theme.helium.defaultMode")
    @Default("light")
    String getDefaultMode();

    /**
     * Returns the name of color preset to be used if no other settings are available.
     * <p>
     * Either cookie or user settings obtained from {@link UserSettingService}
     * have precedence over this value.
     *
     * @return the name of color preset to be used if no other settings are available
     */
    @Property("cuba.theme.helium.defaultModeToUse")
    String getDefaultModeToUse();

    /**
     * @return the list of available theme size presets
     */
    @Property("cuba.theme.helium.sizes")
    @Factory(factory = StringListTypeFactory.class)
    @Stringify(stringify = StringListStringify.class)
    @Default("small|medium|large")
    List<String> getSizes();

    /**
     * Returns the name of size preset that has no additional style class name.
     * <p>
     * <strong>Note:</strong> can’t be changed without corresponding changes in styles.
     *
     * @return the name of size preset that has no additional style class name
     */
    @Property("cuba.theme.helium.defaultSize")
    @Default("medium")
    String getDefaultSize();

    /**
     * Returns the name of size preset to be used if no other settings are available.
     * <p>
     * Either cookie or user settings obtained from {@link UserSettingService}
     * have precedence over this value.
     *
     * @return the name of size preset to be used if no other settings are available
     */
    @Property("cuba.theme.helium.defaultSizeToUse")
    String getDefaultSizeToUse();
}
