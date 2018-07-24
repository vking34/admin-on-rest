import React from 'react';
import { jsonServerRestClient, Admin, Resource, Delete } from 'admin-on-rest';

import { PostList } from './resources/posts';
import {DepartmentCreate, DepartmentEdit, DepartmentList} from "./resources/departments";
import myApiRestClient from './clientSide/restClient';
import authClient from "./clientSide/authClient";



const App = () => (
    <Admin restClient={myApiRestClient} authClient={authClient} >
        {permissions => [
            <Resource name="departments" list={DepartmentList} create={DepartmentCreate} edit={ permissions === 'ROLE_ADMIN' ? DepartmentEdit : null} remove={ permissions === 'ROLE_ADMIN' ? Delete : null} permissions={permissions} />
        ]
        }
    </Admin>
);

export default App;
