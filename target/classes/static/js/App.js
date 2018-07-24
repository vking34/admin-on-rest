import React from 'react';
import { jsonServerRestClient, Admin, Resource, Delete } from 'admin-on-rest';

import { PostList } from './resources/posts';
import {DepartmentCreate, DepartmentEdit, DepartmentList} from "./resources/departments";
import myApiRestClient from './clientSide/restClient';
import authClient from "./clientSide/authClient";
import {UserCreate, UserEdit, UserList} from "./resources/users";
import users_icon from "../img/users_icon.svg";

// {permissions => [
//     permissions === 'ROLE_ROOT'
//         ? <Resource name="users" list={UserList} create={UserCreate} edit={UserEdit} remove={Delete} />
//         : <Resource name="departments" list={DepartmentList} />
// ]
// }

const App = () => (
    <Admin restClient={myApiRestClient} authClient={authClient} >
        <Resource name="users" list={UserList} create={UserCreate} edit={UserEdit} remove={Delete} icon={users_icon} />
    </Admin>
);

export default App;
