import React from 'react';
import { List, Edit, Create, Datagrid, TextField, ReferenceField, NumberField, NumberInput, EditButton, DeleteButton, DisabledInput, LongTextInput, ReferenceInput, required, SelectInput, SimpleForm, TextInput } from 'admin-on-rest';

export const DepartmentList = (props) => (
    <List {...props}>
        <Datagrid>

            <NumberField source="department_id" />
            <TextField source="title" />
            <TextField source="sub_title" />
            <NumberField source="numOfEmployee" />
            <EditButton />
            <DeleteButton />
        </Datagrid>
    </List>
);

const DepartmentTitle = ({ record }) => {
    return <span>Department {record ? `"${record.title}"` : ''}</span>
};

export const DepartmentEdit = (props) => (
  <Edit title={<DepartmentTitle />} {...props}>
  <SimpleForm>
      <DisabledInput source="department_id" />
      <TextInput source="title" />
      <TextInput source="sub_title" />
      <DisabledInput source="numOfEmployee" />
  </SimpleForm>
  </Edit>
);


const defaultNumOfEmployee = 0;

export const DepartmentCreate = (props) => (
  <Create {...props}>
      <SimpleForm redirect="list" >
          <NumberInput label="Department ID" source="department_id" />
          <TextInput source="title" />
          <TextInput source="sub_title" />
          <NumberInput label="Number of Employees" source="numOfEmployee" defaultValue={defaultNumOfEmployee} />
      </SimpleForm>
  </Create>
);