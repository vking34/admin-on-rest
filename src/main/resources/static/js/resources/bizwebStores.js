import React from 'react';
import { CardActions } from 'material-ui/Card';
import {
    TabbedForm,
    FormTab,
    ReferenceManyField,
    List,
    Edit,
    Create,
    Datagrid,
    BooleanField,
    TextField,
    NumberInput,
    EditButton,
    DeleteButton,
    DisabledInput,
    required,
    SimpleForm,
    TextInput,
    Filter,
    SelectArrayInput,
    FunctionField
} from 'admin-on-rest';
import AccessToken from "../fields/TokenField";
import PagesField from "../fields/PagesField";
import ChannelsField from "../fields/ChannelsField";
import DeleteAccountButton from "../components/deleteAccountFromStore";
import DeletePageButton from "../components/deletePageFromStore";
import StoreLinkField from "../fields/LinkField";
import PageLinkField from "../fields/LinkField";
import AccountLinkField from "../fields/LinkField";

export const BizwebStoreList = (props) => (
    <List {...props} filters={<BizwebStoreFilter/>}>
        {permissions =>
            <Datagrid>
                <StoreLinkField/>
                <TextField source="alias"/>
                { permissions === 'ROLE_ADMIN' && <TextField label="api-access-token" source="apiAccessToken"/>}
                <EditButton/>
                { permissions === 'ROLE_ADMIN' && <DeleteButton/> }
            </Datagrid>
        }
    </List>
);

const BizwebStoreFilter = (props) => (
  <Filter {...props}>
      <TextInput label="Search by Alias" source="q" alwaysOn />
  </Filter>
);

export const BizwebStoreEdit = (props) => (
  <Edit title={<BizwebStoreTitle/>} {...props} >
      { permissions =>
        <TabbedForm redirect={false}>
            <FormTab label="Basic_Info">
                <TextField label="ID" source="id" />
                <TextInput label="Alias" source="alias" />
                { permissions === 'ROLE_ADMIN' && <TextField label="API Access Token" source="apiAccessToken" />}
                <TextField label='FB User ID' source="fbUserId" />
                { permissions === 'ROLE_ADMIN' && <AccessTokenField source="fbUserAccessToken" />}
                <FunctionField label="Current Pages" render={record => `${record.pageIds !== null ? record.pageIds.length : 0} / ${record.packageInfo.pageLimit}`} />
                <FunctionField label="Current Accounts" render={record => `${record.accountIds !== null ? record.accountIds.length : 0} / ${record.packageInfo.adminLimit}`} />
                <TextField label="Created On" source="createdOn" />
                <TextField label="Modified On" source="modifiedOn" />
                <TextField label="Expiration Time Channel" source="expirationTimeChannel" />
                {/*<TextField label="Bizweb Channels" source="bwChannels" />*/}
                <ChannelsField />
            </FormTab>

            <FormTab label="Package_Info" >
                <TextInput label="Package Name" source="packageInfo.packageName" />
                <NumberInput label="Pages Limit" source="packageInfo.pageLimit" />
                <NumberInput label="Admins Limit" source="packageInfo.adminLimit"/>
                <SelectArrayInput label="Scopes" source="packageInfo.scopes" choices={[
                    {id : 'comment', name: 'Comment'},
                    {id : 'inbox', name: 'Inbox'},
                    {id : 'product', name: 'Product'},
                    {id : 'setting', name: 'Setting'}
                ]} />
            </FormTab>

            <FormTab label="Pages">
                <ReferenceManyField addLabel={false} reference="pages" target="bizweb-stores">
                    <Datagrid>
                        <PageLinkField />
                        <TextField source="name"/>
                        <TextField label="FB-Page-ID" source="facebookPageId" />
                        <BooleanField label="Used" source="used" />
                        { permissions === 'ROLE_ADMIN' && <DeletePageButton />}
                    </Datagrid>
                </ReferenceManyField>
            </FormTab>

            <FormTab label="Accounts">
                <ReferenceManyField addLabel={false} reference="accounts" target="bizweb-stores">
                    <Datagrid>
                        <AccountLinkField />
                        <TextField label="Account Name" source="accountName"/>
                        <PagesField />
                        { permissions === 'ROLE_ADMIN' && <DeleteAccountButton />}
                    </Datagrid>
                </ReferenceManyField>
            </FormTab>

        </TabbedForm>
      }
  </Edit>
);

const BizwebStoreTitle = ( {record}) => {
    return <span>Edit store: { record ? `${record.alias}` : ''} </span>
};

const AccessTokenField = ({ record = {}} ) =>
    <AccessToken token={record.fbUserAccessToken} />;
AccessTokenField.defaultProps= { label: 'User Access Token'};


// export const BizwebStoreCreate = (props) => {
//     <Create {...props} redirect="list" >
//         <SimpleForm>
//             <TextInput label="Alias" source="alias" />
//         </SimpleForm>
//     </Create>
// };