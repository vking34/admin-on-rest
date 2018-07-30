import React from 'react';
import { jsonServerRestClient, Admin, Resource, Delete } from 'admin-on-rest';
//
// import { PostList } from './resources/examples/posts';
// import {DepartmentCreate, DepartmentEdit, DepartmentList} from "./resources/examples/departments";
import myApiRestClient from './clientSide/restClient';
import authClient from "./clientSide/authClient";
import {UserCreate, UserEdit, UserList} from "./resources/users";
import UsersIcon from "material-ui/svg-icons/social/people";
import {
    BizwebStoreCreate,
    BizwebStoreEdit,
    BizwebStoreList,
    BizwebStoreShow,
    SupportBizwebStoreList
} from "./resources/bizwebStores";
import {PageCreate, PageEdit, PageList} from "./resources/pages";

// {permissions => [
//     permissions === 'ROLE_ROOT'
//         ? <Resource name="users" list={UserList} create={UserCreate} edit={UserEdit} remove={Delete} />
//         : <Resource name="departments" list={DepartmentList} />
// ]
// }
//
// <Resource name="users" list={UserList} create={UserCreate} edit={UserEdit} remove={Delete} icon={UsersIcon} />

const App = () => (
    <Admin title="Sapo Social Admin" restClient={myApiRestClient} authClient={authClient} >
        {permissions => [
            permissions === 'ROLE_ROOT'
                ? <Resource name="users" list={UserList} create={UserCreate} edit={UserEdit} remove={Delete} icon={UsersIcon} />
                : <Resource name="bizweb-stores" list={permissions === 'ROLE_ADMIN' ? BizwebStoreList : SupportBizwebStoreList} create={BizwebStoreCreate} remove={Delete} edit={BizwebStoreEdit} />,
            permissions !== 'ROLE_ROOT'
                ? <Resource name="pages" list={PageList} edit={PageEdit} remove={Delete} create={PageCreate} />
                : null
        ]
        }
    </Admin>
);

export default App;
