import React from 'react';
import { RefreshButton, ListButton, Show, SimpleShowLayout, List, Edit, Create, Datagrid, BooleanInput, BooleanField, TextField, ReferenceField, NumberField, NumberInput, EditButton, DeleteButton, DisabledInput, LongTextInput, ReferenceInput, required, SelectInput, SimpleForm, TextInput, Filter } from 'admin-on-rest';

export const PageList = (props) => (
    <List {...props} filters={<PageFilter/>}>
        <Datagrid>
            <TextField source="name"/>
            <TextField label="FB Page ID" source="facebookPageId"/>
            <BooleanField label="is used?" source="used" />
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
        <SimpleForm>
            <DisabledInput label="FB Page ID" source="facebookPageId" />
            <TextInput label="Name" source="name" />
        </SimpleForm>
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