import React from 'react';
import { List, Datagrid, TextField, ReferenceField } from 'admin-on-rest';

export const DepartmentList = (props) => (
    <List {...props}>
        <Datagrid>
            <TextField source="department_id" />
            <TextField source="title" />
            <TextField source="sub_title" />
        </Datagrid>
    </List>

);