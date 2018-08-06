import React from 'react';
import {
    TabbedForm,
    FormTab,
    RefreshButton,
    ReferenceManyField,
    ListButton,
    Show,
    SimpleShowLayout,
    List,
    Edit,
    Create,
    Datagrid,
    BooleanInput,
    BooleanField,
    TextField,
    ReferenceField,
    NumberField,
    NumberInput,
    EditButton,
    DeleteButton,
    DisabledInput,
    LongTextInput,
    ReferenceInput,
    required,
    SelectInput,
    SimpleForm,
    TextInput,
    Filter,
    DateInput,
    FunctionField
} from 'admin-on-rest';

import {dateFormatter, dateParser} from "../functions/dateConverter";

export const PageList = (props) => (
    <List {...props} filters={<PageFilter/>}>
        <Datagrid>
            <TextField label="ID" source="id"/>
            <TextField source="name"/>
            <TextField label="FB Page ID" source="facebookPageId"/>
            <EditButton />
            <DeleteButton />
        </Datagrid>
    </List>
);

const PageFilter = (props) => (
    <Filter {...props}>
        <TextInput label="Search by name" source="q" alwaysOn />
    </Filter>
);

const PageTitle = ( {record}) => {
    return <span>Edit page: { record ? `${record.name}` : ''} </span>
};

export const PageEdit = (props) => (
    <Edit title={<PageTitle/>} {...props}>
        <TabbedForm >
            <FormTab label="Basic Info" >
                <TextField label="ID" source="id"/>
                <TextField label="FB Page ID" source="facebookPageId" />
                <TextInput label="Name" source="name" />
                <DateInput label="Created On" source="createdOn" format={dateFormatter} parse={dateParser} />
                {/*<DateInput label="Modified On" source="modifiedOn" format={dateFormatter} parse={dateParser} />*/}
                <TextField label="Modified On" source="modifiedOn" />
            </FormTab>

            <FormTab label="account-maps">

                    {/*<TextField label="ID" source="accountMaps.accountId"/>*/}
                    {/*<TextField label="Access-Token" source="accountMaps.accessToken"/>*/}
                    <ReferenceManyField addLabel={false} reference="accounts" target="pages">
                        <Datagrid>
                            {/*<FunctionField label="ID" render={ record => `${record.}`} />*/}
                            <TextField label="ID" source="id" />
                            <TextField source="name"/>
                            <TextField label="Page-Acces-Token" source="access_token" />
                            <EditButton />
                            <DeleteButton />
                        </Datagrid>
                    </ReferenceManyField>
            </FormTab>
        </TabbedForm>
    </Edit>
);

export const PageCreate = (props) => {
    <Create {...props} redirect="list" >
        <SimpleForm>
            <TextInput label="Name" source="name" />
            <TextInput label="FB Page ID" source="facebookPageId" />
        </SimpleForm>
    </Create>
};