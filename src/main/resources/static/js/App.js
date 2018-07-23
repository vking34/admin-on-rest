import React from 'react';
import { jsonServerRestClient, Admin, Resource } from 'admin-on-rest';

import { PostList } from './resources/posts';
import { DepartmentList} from "./resources/departments";
import myApiRestClient from './clientSide/restClient';
import authClient from "./authClient";



const App = () => (
    <Admin restClient={myApiRestClient} authClient={authClient} >
        <Resource name="departments" list={DepartmentList} />
    </Admin>
);

export default App;
