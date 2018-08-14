import React from 'react';
import {
    List,
    Edit,
    Create,
    Datagrid,
    BooleanInput,
    BooleanField,
    TextField,
    EditButton,
    DeleteButton,
    DisabledInput,
    required,
    SimpleForm,
    TextInput,
    Filter
} from 'admin-on-rest';
// import UserCreateToolbar from "../components/userCreateToolbar";
import LinkField from "../fields/LinkField";

export const UserList = (props) => (
    <List {...props} filters={<UsersFilter />}>
        <Datagrid>
            <UserLinkField />
            <TextField source="username" />
            <BooleanField source="admin" />
            <BooleanField source="active" />
            <EditButton />
            <DeleteButton />
        </Datagrid>
    </List>
);

const UsersFilter = (props) => (
    <Filter {...props}>
        <TextInput label="Search by Username" source="q" alwaysOn />
    </Filter>
);


export const UserEdit = (props) => (
    <Edit title={<UserTitle />} {...props}>
        <SimpleForm >
            <TextField source="id" />
            <TextField label="Username" source="username" />
            <TextInput label="Password" source="password" type="password" />
            <BooleanInput source="admin" />
            <BooleanInput source="active" />
        </SimpleForm>
    </Edit>
);

const UserTitle = ({ record }) => {
    return <span>Edit user: { record ? `${record.username}` : '' }</span>
};

export const UserCreate = (props) => (
    <Create {...props}>
        <SimpleForm redirect="list" >
            <TextInput label="Username" source="username" />
            <TextInput label="Password" source="password" type="password" />
            <BooleanInput label="is Admin?" source="admin" defaultValue={false} />
            <BooleanInput label="Active?" source="active" defaultValue={false} />
        </SimpleForm>
    </Create>
);

const UserLinkField = ({ record = {}}) =>
    <LinkField channel="users" id={record.id} />;
UserLinkField.defaultProps = {label: 'ID'};