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

package com.haulmont.addon.helium.web.screens.sandbox;

import com.haulmont.cuba.gui.Route;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;

import javax.inject.Inject;

@UiController("helium_Sandbox")
@UiDescriptor("sandbox.xml")
@Route("sandbox")
@LoadDataBeforeShow
public class Sandbox extends Screen {
    /*@Inject
    private DataGrid<User> dataGrid;

    @Subscribe
    public void onInit(InitEvent event) {
        for (Component component : getWindow().getComponents()) {
            if (component instanceof CheckBox) {
                ((CheckBox) component).addValueChangeListener(this::updateTableStyle);
            }
        }
    }

    private void updateTableStyle(HasValue.ValueChangeEvent<Boolean> e) {
        String id = e.getComponent().getId();
        assert id != null;

        Object value = e.getValue();
        if (Boolean.TRUE.equals(value)) {
            dataGrid.addStyleName(prepareStyleName(id));
        } else {
            dataGrid.removeStyleName(prepareStyleName(id));
        }
    }

    private String prepareStyleName(String stylename) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stylename.length(); i++) {
            char c = stylename.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("-").append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }*/

    /*@Inject
    private CheckBox enabledSwitch;
    @Inject
    private CheckBox editableSwitch;
    @Inject
    private VBoxLayout componentsBox;

    @Subscribe
    public void onInit(InitEvent event) {
        editableSwitch.setValue(true);
        enabledSwitch.setValue(true);
    }

    @Subscribe("editableSwitch")
    public void onEditableSwitchValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        componentsBox.getComponents().stream()
                .filter(component ->
                        component instanceof Component.Editable)
                .forEach(component ->
                        ((Component.Editable) component).setEditable(Boolean.TRUE.equals(event.getValue())));
    }

    @Subscribe("enabledSwitch")
    public void onEnabledSwitchValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        componentsBox.getComponents()
                .forEach(component ->
                        component.setEnabled(Boolean.TRUE.equals(event.getValue())));
    }*/



    /*@Inject
    private RadioButtonGroup<String> sizeGroup;

    @Inject
    private TextField<String> textFieldRO;
    @Inject
    private TextField<String> textFieldD;

    @Inject
    private LookupField<RoleType> lookupFieldRO;
    @Inject
    private LookupField<RoleType> lookupFieldD;

    @Inject
    private OptionsList optionsListRO;
    @Inject
    private OptionsList optionsListD;

    @Inject
    private PickerField<User> pickerFieldRO;
    @Inject
    private PickerField<User> pickerFieldD;

    @Inject
    private LookupPickerField<User> lookupPickerFieldRO;
    @Inject
    private LookupPickerField<User> lookupPickerFieldD;

    @Inject
    private CheckBox checkBoxRO2;
    @Inject
    private CheckBox checkBoxD2;

    @Inject
    private RadioButtonGroup<RoleType> radioButtonGroupD;
    @Inject
    private RadioButtonGroup<RoleType> radioButtonGroupRO;

    @Inject
    private TokenList<User> tokenListRO;
    @Inject
    private TokenList<User> tokenListR1;
    @Inject
    private TokenList<User> tokenListR2;
    @Inject
    private TokenList<User> tokenListR3;

    @Inject
    private Table<User> table;

    @Inject
    private DataGrid<User> dataGrid;

    @Inject
    private TabSheet tabSheet;
    @Inject
    private FlowBoxLayout tabSheetStylesBox;

    @Inject
    private Tree<Group> tree;

    @Inject
    private SourceCodeEditor codeEditor;
    @Inject
    private SourceCodeEditor codeEditorD;
    @Inject
    private SourceCodeEditor codeEditorRO;
    @Inject
    private CheckBox highlightActiveLineCheck;
    @Inject
    private CheckBox printMarginCheck;
    @Inject
    private CheckBox showGutterCheck;

    @Inject
    private ProgressBar progressBarP;
    @Inject
    private ProgressBar progressBar;

    @Inject
    private CollectionContainer<User> usersDc;
    @Inject
    private Dialogs dialogs;
    @Inject
    private Notifications notifications;
    @Inject
    private UiComponents uiComponents;
    @Inject
    private HeliumThemeVariantsManager themeVariantsManager;

    @Inject
    private Calendar<Date> monthCalendar;
    @Inject
    private Calendar<Date> weekCalendar;
    @Inject
    private Calendar<Date> dayCalendar;

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        initSizes();

        textFieldRO.setValue("Value");
        textFieldD.setValue("Value");

        lookupFieldRO.setValue(RoleType.DENYING);
        lookupFieldD.setValue(RoleType.DENYING);

        checkBoxRO2.setValue(true);
        checkBoxD2.setValue(true);

        User user = usersDc.getItems().get(0);

        pickerFieldRO.setValue(user);
        pickerFieldD.setValue(user);

        lookupPickerFieldRO.setValue(user);
        lookupPickerFieldD.setValue(user);

        radioButtonGroupRO.setValue(RoleType.DENYING);
        radioButtonGroupD.setValue(RoleType.DENYING);

        tokenListRO.setValue(usersDc.getItems());
        tokenListR1.addValidator(getBeanLocator().get(NotEmptyValidator.NAME));
        tokenListR2.addValidator(getBeanLocator().get(NotEmptyValidator.NAME));
        tokenListR3.addValidator(getBeanLocator().get(NotEmptyValidator.NAME));
        try {
            tokenListR1.validate();
        } catch (Exception ignored) {
        }
        try {
            tokenListR3.validate();
        } catch (Exception ignored) {
        }
        try {
            tokenListR2.validate();
        } catch (Exception ignored) {
        }

        tabSheetStylesBox.getComponents().stream()
                .filter(component -> component instanceof CheckBox)
                .map(component -> ((CheckBox) component))
                .forEach(checkBox -> {
                    checkBox.addValueChangeListener(this::changeTableStyle);
                });

        tree.expandTree();

        highlightActiveLineCheck.setValue(codeEditor.isHighlightActiveLine());
        printMarginCheck.setValue(codeEditor.isShowPrintMargin());
        showGutterCheck.setValue(codeEditor.isShowGutter());

        codeEditorRO.setValue("highlightActiveLineCheck.setValue(codeEditor.isHighlightActiveLine());");
        codeEditorD.setValue("highlightActiveLineCheck.setValue(codeEditor.isHighlightActiveLine());");

        progressBar.setValue(0.5);
        progressBarP.setValue(0.5);

        optionsListRO.setValue(RoleType.DENYING);
        optionsListD.setValue(RoleType.DENYING);

        initCalendar();

        initListEditor();
    }

    @Inject
    private GroupBoxLayout listEditorBox;

    private void initListEditor() {
        ListEditor listEditor = uiComponents.create(ListEditor.NAME);
        listEditor.setWidth("250px");
        listEditor.setClearButtonVisible(true);
        listEditorBox.add(listEditor);

        listEditor = uiComponents.create(ListEditor.NAME);
        listEditor.setWidth("250px");
        listEditor.setEditable(false);
        listEditor.setCaption("Read-only");
        listEditorBox.add(listEditor);

        listEditor = uiComponents.create(ListEditor.NAME);
        listEditor.setWidth("250px");
        listEditor.setEnabled(false);
        listEditor.setCaption("Disabled");
        listEditorBox.add(listEditor);

        listEditor = uiComponents.create(ListEditor.NAME);
        listEditor.setWidth("250px");
        listEditor.setRequired(true);
        listEditor.setCaption("Required");
        listEditorBox.add(listEditor);


        listEditor = uiComponents.create(ListEditor.NAME);
        listEditor.setWidth("250px");
        listEditor.addStyleName("large");
        listEditor.setCaption("LARGE");
        listEditorBox.add(listEditor);

        listEditor = uiComponents.create(ListEditor.NAME);
        listEditor.setWidth("250px");
        listEditor.addStyleName("medium");
        listEditor.setCaption("Medium");
        listEditorBox.add(listEditor);

        listEditor = uiComponents.create(ListEditor.NAME);
        listEditor.setWidth("250px");
        listEditor.addStyleName("small");
        listEditor.setCaption("small");
        listEditorBox.add(listEditor);
    }

    private void initCalendar() {
        ListCalendarEventProvider eventProvider = new ListCalendarEventProvider();

        SimpleCalendarEvent<Date> calendarEvent1 = new SimpleCalendarEvent<>();
        calendarEvent1.setCaption("Event 1");
        calendarEvent1.setDescription("Description 1");
        calendarEvent1.setStart(new Date());
        calendarEvent1.setEnd(DateUtils.addHours(new Date(), 2));
        eventProvider.addEvent(calendarEvent1);

        SimpleCalendarEvent<Date> calendarEvent2 = new SimpleCalendarEvent<>();
        calendarEvent2.setCaption("Event 2");
        calendarEvent2.setDescription("Description 2");
        calendarEvent2.setStart(DateUtils.addDays(new Date(), -2));
        calendarEvent2.setEnd(DateUtils.addHours(DateUtils.addDays(new Date(), -2), 2));
        eventProvider.addEvent(calendarEvent2);

        SimpleCalendarEvent<Date> calendarEvent3 = new SimpleCalendarEvent<>();
        calendarEvent3.setCaption("Event 3");
        calendarEvent3.setDescription("Description 3");
        calendarEvent3.setStart(DateUtils.addDays(new Date(), -2));
        calendarEvent3.setEnd(DateUtils.addHours(DateUtils.addDays(new Date(), -2), 2));
        calendarEvent3.setAllDay(true);
        eventProvider.addEvent(calendarEvent3);

        monthCalendar.setEventProvider(eventProvider);
        weekCalendar.setEventProvider(eventProvider);
        dayCalendar.setEventProvider(eventProvider);

        monthCalendar.addRangeSelectListener(dateCalendarRangeSelectEvent -> {

        });

        weekCalendar.addRangeSelectListener(dateCalendarRangeSelectEvent -> {

        });

        dayCalendar.addRangeSelectListener(dateCalendarRangeSelectEvent -> {

        });
    }

    private void changeTableStyle(HasValue.ValueChangeEvent<Boolean> e) {
        String id = e.getComponent().getId();
        Boolean checked = e.getValue();
        if (checked != null) {
            if (checked) {
                tabSheet.addStyleName(prepareStyleName(id));
            } else {
                tabSheet.removeStyleName(prepareStyleName(id));
            }
        }
    }

    private String prepareStyleName(String stylename) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < stylename.length(); i++) {
            char c = stylename.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("-").append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    @Install(to = "dataGrid", subject = "detailsGenerator")
    protected Component dataGridDetailsGenerator(User user) {
        VBoxLayout mainLayout = uiComponents.create(VBoxLayout.class);
        mainLayout.setWidth("100%");
        mainLayout.setHeight("200px");

        return mainLayout;
    }

    @Subscribe("showDetailsBtn")
    public void onShowDetailsBtnClick(Button.ClickEvent event) {
        User singleSelected = dataGrid.getSingleSelected();
        if (singleSelected != null) {
            dataGrid.setDetailsVisible(singleSelected, true);
        }
    }

    @Subscribe("closeDetailsBtn")
    public void onCloseDetailsBtnClick(Button.ClickEvent event) {
        User singleSelected = dataGrid.getSingleSelected();
        if (singleSelected != null) {
            dataGrid.setDetailsVisible(singleSelected, false);
        }
    }

    @Subscribe("showMessageDialogBtn")
    public void onShowMessageDialogBtnClick(Button.ClickEvent event) {
        dialogs.createMessageDialog()
                .withCaption("Confirmation")
                .withMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Et sollicitudin quam massa id enim et. Purus parturient pretium arcu quis vitae feugiat sit quis. Sem dictum vel nisi, cursus purus nibh fermentum tortor. Ultrices scelerisque orci, ullamcorper imperdiet orci bibendum a, aliquet. Purus mauris vitae odio fermentum semper diam commodo quis. Pulvinar nulla duis adipiscing nunc eu laoreet laoreet. Ornare sodales donec malesuada id eu arcu lectus ipsum scelerisque.")
                .withType(Dialogs.MessageType.CONFIRMATION)
                .show();
    }

    @Subscribe("showOptionDialogBtn")
    public void onShowOptionDialogBtnClick(Button.ClickEvent event) {
        dialogs.createOptionDialog()
                .withCaption("Title")
                .withMessage("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Et sollicitudin quam massa id enim et. Purus parturient pretium arcu quis vitae feugiat sit quis. Sem dictum vel nisi, cursus purus nibh fermentum tortor. Ultrices scelerisque orci, ullamcorper imperdiet orci bibendum a, aliquet. Purus mauris vitae odio fermentum semper diam commodo quis. Pulvinar nulla duis adipiscing nunc eu laoreet laoreet. Ornare sodales donec malesuada id eu arcu lectus ipsum scelerisque.")
                .withType(Dialogs.MessageType.CONFIRMATION)
                .withActions(
                        new DialogAction(DialogAction.Type.OK)
                                .withHandler(e ->
                                        notifications.create()
                                                .withCaption("OK pressed")
                                                .show()
                                ),

                        new DialogAction(DialogAction.Type.CANCEL))
                .show();
    }

    @Subscribe("showInputDialogBtn")
    public void onShowInputDialogBtnClick(Button.ClickEvent event) {
        dialogs.createInputDialog(this)
                .withCaption("Enter values")
                .withParameters(
                        InputParameter.stringParameter("name")
                                .withCaption("Name").withRequired(true),
                        InputParameter.doubleParameter("quantity")
                                .withCaption("Quantity").withDefaultValue(1.0),
                        InputParameter.enumParameter("roleType", RoleType.class)
                                .withCaption("Role Type")
                )
                .withActions(DialogActions.OK_CANCEL)
                .show();
    }

    @Subscribe("showTrayBtn")
    public void onShowTrayBtnClick(Button.ClickEvent event) {
        notifications.create()
                .withCaption("Tray notification")
//                .withDescription("Hi there! I’m a CUBA’s “tray” message")
                .withType(Notifications.NotificationType.TRAY)
                .withHideDelayMs(-1)
                .show();
    }

    @Subscribe("showHumanizedBtn")
    public void onShowHumanizedBtnClick(Button.ClickEvent event) {
        notifications.create()
                .withCaption("Humanized notification")
//                .withDescription("Hi there! I’m a CUBA’s “humanized” message")
                .withType(Notifications.NotificationType.HUMANIZED)
                .withHideDelayMs(-1)
                .show();
    }

    @Subscribe("showWarningBtn")
    public void onShowWarningBtnClick(Button.ClickEvent event) {
        notifications.create()
                .withCaption("Warning notification")
//                .withDescription("Hi there! I’m a CUBA’s “warning” message")
                .withType(Notifications.NotificationType.WARNING)
                .withContentMode(ContentMode.HTML)
                .show();
    }

    @Subscribe("showErrorBtn")
    public void onShowErrorBtnClick(Button.ClickEvent event) {
        notifications.create()
                .withCaption("Error notification")
//                .withDescription("Hi there! I’m a CUBA’s “error” message")
                .withType(Notifications.NotificationType.ERROR)
                .withContentMode(ContentMode.HTML)
                .show();
    }

    @Subscribe("showSystemBtn")
    public void onShowSystemBtnClick(Button.ClickEvent event) {
        notifications.create()
                .withCaption("System notification")
//                .withDescription("Hi there! I’m a CUBA’s “system” message")
                .withType(Notifications.NotificationType.SYSTEM)
                .withContentMode(ContentMode.HTML)
                .show();
    }

    private void initSizes() {
        sizeGroup.setOptionsList(themeVariantsManager.getAppThemeSizeList());
        sizeGroup.setValue(themeVariantsManager.loadUserAppThemeSizeSetting());

        sizeGroup.addValueChangeListener(event ->
                getWindow().setStyleName(event.getValue()));
    }

    @Subscribe("highlightActiveLineCheck")
    protected void onHighlightActiveLineCheckValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        if (event.getValue() != null) {
            codeEditor.setHighlightActiveLine(event.getValue());
        }
    }

    @Subscribe("printMarginCheck")
    protected void onPrintMarginCheckValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        if (event.getValue() != null) {
            codeEditor.setShowPrintMargin(event.getValue());
        }
    }

    @Subscribe("showGutterCheck")
    protected void onShowGutterCheckValueChange(HasValue.ValueChangeEvent<Boolean> event) {
        if (event.getValue() != null) {
            codeEditor.setShowGutter(event.getValue());
        }
    }*/
}