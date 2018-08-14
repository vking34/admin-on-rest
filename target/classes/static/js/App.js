import React from 'react';
import { Admin, Resource, Delete } from 'admin-on-rest';
import myApiRestClient from './clientSide/restClient';
import authClient from "./clientSide/authClient";
import { UserCreate, UserEdit, UserList} from "./resources/users";
import UsersIcon from "material-ui/svg-icons/social/people";
import AccountIcon from "material-ui/svg-icons/social/person";
import PagesIcon from "./icons/facebook";
import StoreIcon from "./icons/store";
import {
    BizwebStoreEdit,
    BizwebStoreList,
} from "./resources/bizwebStores";
import { PageEdit, PageList } from "./resources/pages";
import { AccountEdit, AccountList} from "./resources/accounts";
import Login from "./Login";
import Dashboard from "./components/Dashboard";

const App = () => (
    <Admin
        title="Sapo Social Admin"
        dashboard={Dashboard}
        restClient={myApiRestClient}
        authClient={authClient}
        loginPage={Login}
    >
        {permissions => [
            permissions === 'ROLE_ROOT'
                ? <Resource name="users" list={UserList} create={UserCreate} edit={UserEdit} remove={Delete} icon={UsersIcon} />
                : <Resource name="bizweb-stores" list={BizwebStoreList} remove={Delete} edit={BizwebStoreEdit} icon={StoreIcon} />,
            permissions !== 'ROLE_ROOT'
                ? <Resource name="pages" list={PageList} edit={PageEdit} remove={Delete} icon={PagesIcon} />
                : null,
            permissions !== 'ROLE_ROOT'
                ? <Resource name="accounts" list={AccountList} edit={AccountEdit} remove={Delete} icon={AccountIcon} />
                : null
        ]
        }
    </Admin>
);

export default App;
