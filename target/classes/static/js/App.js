import React from 'react';
import { jsonServerRestClient, Admin, Resource, Delete } from 'admin-on-rest';
import myApiRestClient from './clientSide/restClient';
import authClient from "./clientSide/authClient";
import {UserCreate, UserEdit, UserList} from "./resources/users";
import UsersIcon from "material-ui/svg-icons/social/people";
import PagesIcon from "material-ui/svg-icons/social/pages";
import AccountIcon from "material-ui/svg-icons/social/person";
import StoreIcon from "material-ui/svg-icons/image/collections"
import {
    BizwebStoreCreate,
    BizwebStoreEdit,
    BizwebStoreList,
    BizwebStoreShow,
    SupportBizwebStoreList
} from "./resources/bizwebStores";
import {PageCreate, PageEdit, PageList} from "./resources/pages";
import {AccountCreate, AccountEdit, AccountList} from "./resources/accounts";
import Login from "./Login";

const App = () => (
    <Admin
        title="Sapo Social Admin"
        restClient={myApiRestClient}
        authClient={authClient}
        loginPage={Login}
    >
        {permissions => [
            permissions === 'ROLE_ROOT'
                ? <Resource name="users" list={UserList} create={UserCreate} edit={UserEdit} remove={Delete} icon={UsersIcon} />
                : <Resource name="bizweb-stores" list={permissions === 'ROLE_ADMIN' ? BizwebStoreList : SupportBizwebStoreList} remove={Delete} edit={BizwebStoreEdit} icon={StoreIcon} />,
            permissions !== 'ROLE_ROOT'
                ? <Resource name="pages" list={PageList} edit={PageEdit} remove={Delete} create={PageCreate} icon={PagesIcon} />
                : null,
            permissions !== 'ROLE_ROOT'
                ? <Resource name="accounts" list={AccountList} edit={AccountEdit} remove={Delete} create={AccountCreate} icon={AccountIcon} />
                : null
        ]
        }
    </Admin>
);

export default App;
