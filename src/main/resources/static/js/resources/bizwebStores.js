import React from 'react';
import { CardActions } from 'material-ui/Card';
import FlatButton from 'material-ui/FlatButton';
import {
    TabbedForm,
    FormTab,
    ThumbnailField,
    ReferenceManyField,
    RefreshButton,
    ListButton,
    Show,
    SimpleShowLayout,
    List, Edit, Create, Datagrid, BooleanInput, BooleanField, TextField, ReferenceField, NumberField, NumberInput, EditButton, DeleteButton, DisabledInput, LongTextInput, ReferenceInput, required, SelectInput, SimpleForm, TextInput, Filter } from 'admin-on-rest';

const cardActionStyle = {
    zIndex: 2,
    display: 'inline-block',
    float: 'right',
};

export const BizwebStoreList = (props) => (
    <List {...props} filters={<BizwebStoreFilter/>}>
        <Datagrid>
            {/*<ReferenceField label="Alias" source="alias" reference="bizweb-stores">*/}
            <TextField source="alias" />
            {/*</ReferenceField>*/}
            <TextField label="created-on" source="createdOn" />
            {/*<NumberField source="hasUpdate" />*/}
            <TextField label="api-access-token" source="apiAccessToken" />
            <EditButton />
            <DeleteButton />
        </Datagrid>
    </List>
);

export const SupportBizwebStoreList = (props) => (
    <List {...props} filters={<BizwebStoreFilter/>}>
        <Datagrid>
            {/*<ReferenceField label="Alias" source="alias" reference="bizweb-stores">*/}
            <TextField source="alias" />
            {/*</ReferenceField>*/}
            <TextField source="createdOn" />
            <NumberField source="hasUpdate" />
            <EditButton />
            <DeleteButton />
        </Datagrid>
    </List>
);

const BizwebStoreFilter = (props) => (
  <Filter {...props}>
      <TextInput label="Search by Alias" source="q" alwaysOn />
  </Filter>
);

const PostShowActions = ({ basePath, data }) => (
    <CardActions style={cardActionStyle}>
        <EditButton basePath={basePath} record={data} />
        <ListButton basePath={basePath} />
        <DeleteButton basePath={basePath} record={data} />
        <RefreshButton />
    </CardActions>
);

export const BizwebStoreShow = (props) => {
    <Show title="Store Information" actions={<PostShowActions />} {...props}>
        <SimpleShowLayout>
            <TextField label="Alias" source="alias" />
            <TextField label="API Access Token" source="apiAccessToken" />
        </SimpleShowLayout>
    </Show>
};

const BizwebStoreTitle = ( {record}) => {
    return <span>Edit store: { record ? `${record.alias}` : ''} </span>
};

export const BizwebStoreEdit = (props) => (
  <Edit title={<BizwebStoreTitle/>} {...props}>
    <TabbedForm>
        <FormTab label="Basic Info">
            <DisabledInput label="Alias" source="alias" />
            <TextInput label="Bizweb Channels" source="bwChannels" />
        </FormTab>

        <FormTab label="Pages">
            <ReferenceManyField addLabel={false} reference="pages" target="bizweb-stores">
                <Datagrid>
                    <TextField source="name"/>
                    <TextField source="facebookPageId" />
                    <EditButton />
                </Datagrid>
            </ReferenceManyField>
        </FormTab>

    </TabbedForm>
  </Edit>
);

export const BizwebStoreCreate = (props) => {
    <Create {...props} redirect="list" >
        <SimpleForm>
            <TextInput label="Alias" source="alias" />
        </SimpleForm>
    </Create>
};