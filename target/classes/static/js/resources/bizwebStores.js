import React from 'react';
import { CardActions } from 'material-ui/Card';
import {
    TabbedForm,
    FormTab,
    ThumbnailField,
    ReferenceManyField,
    RefreshButton,
    ListButton,
    Show,
    SimpleShowLayout,
    List,
    Edit,
    Create,
    Datagrid,
    BooleanInput,
    BooleanField,
    TextField,
    ReferenceField,
    NumberField,
    NumberInput,
    EditButton,
    DeleteButton,
    DisabledInput,
    LongTextInput,
    ReferenceInput,
    required,
    SelectInput,
    SimpleForm,
    TextInput,
    Filter,
    DateInput,
    SelectArrayInput,
    FunctionField,
    RichTextField,
    DateField
} from 'admin-on-rest';
import {dateFormatter, dateParser} from "../functions/dateConverter";
import AccessToken from "../components/tokenField";
import PagesField from "../fields/PagesField";
import DeleteAccountButton from "../components/deleteAccountFromStore";
import DeletePageButton from "../components/deletePageFromStore";

const cardActionStyle = {
    zIndex: 2,
    display: 'inline-block',
    float: 'right',
};

export const BizwebStoreList = (props) => (
    <List {...props} filters={<BizwebStoreFilter/>}>
        {permissions =>
            <Datagrid>
                <TextField label="ID" source="id"/>
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

const PostShowActions = ({ basePath, data }) => (
    <CardActions style={cardActionStyle}>
        <EditButton basePath={basePath} record={data} />
        <ListButton basePath={basePath} />
        <DeleteButton basePath={basePath} record={data} />
        <RefreshButton />
    </CardActions>
);

export const BizwebStoreShow = (props) => {
    <Show title="Store Information" actions={<PostShowActions />} {...props}>
        <SimpleShowLayout>
            <TextField label="Alias" source="alias" />
            <TextField label="API Access Token" source="apiAccessToken" />
        </SimpleShowLayout>
    </Show>
};


const BizwebStoreTitle = ( {record}) => {
    return <span>Edit store: { record ? `${record.alias}` : ''} </span>
};

const AccessTokenField = ({ record = {}} ) =>
    <AccessToken token={record.fbUserAccessToken} />;
AccessTokenField.defaultProps= { label: 'User Access Token'};

export const BizwebStoreEdit = (props) => (
  <Edit title={<BizwebStoreTitle/>} {...props}>
      { permissions =>
        <TabbedForm>
            <FormTab label="Basic_Info">
                <TextField label="ID" source="id" />
                <TextInput label="Alias" source="alias" />
                { permissions === 'ROLE_ADMIN' && <TextField label="API-Access-Token" source="apiAccessToken" />}
                <TextField label='FB User ID' source="fbUserId" />
                { permissions === 'ROLE_ADMIN' && <AccessTokenField source="fbUserAccessToken" />}
                <FunctionField label="Current-Pages" render={record => `${record.pageIds !== null ? record.pageIds.length : 0} / ${record.packageInfo.pageLimit}`} />
                <FunctionField label="Current-Accounts" render={record => `${record.accountIds !== null ? record.accountIds.length : 0} / ${record.packageInfo.adminLimit}`} />
                {/*<TextField label="Created On" source="createdOn" format={dateFormatter} parse={dateParser} />*/}
                {/*<TextField label="Modified On" source="modifiedOn" format={dateFormatter} parse={dateParser} />*/}
                {/*<TextField label="Expiration Time Channel" source="expirationTimeChannel" format={dateFormatter} parse={dateParser} />*/}
                <TextField label="Created On" source="createdOn" />
                <TextField label="Modified On" source="modifiedOn" />
                <TextField label="Expiration Time Channel" source="expirationTimeChannel" />
                <TextField label="Bizweb Channels" source="bwChannels" />
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
                        <TextField label="ID" source="id"/>
                        <TextField source="name"/>
                        <TextField label="FB-Page-ID" source="facebookPageId" />
                        <BooleanField label="Used" source="used" />
                        <EditButton />
                        { permissions === 'ROLE_ADMIN' && <DeletePageButton />}
                    </Datagrid>
                </ReferenceManyField>
            </FormTab>

            <FormTab label="Accounts">
                <ReferenceManyField addLabel={false} reference="accounts" target="bizweb-stores">
                    <Datagrid>
                        <TextField label="Account ID" source="id"/>
                        <TextField label="Account Name" source="accountName"/>
                        <PagesField />
                        <EditButton />
                        { permissions === 'ROLE_ADMIN' && <DeleteAccountButton />}
                    </Datagrid>
                </ReferenceManyField>
            </FormTab>

        </TabbedForm>
      }
  </Edit>
);

// export const BizwebStoreCreate = (props) => {
//     <Create {...props} redirect="list" >
//         <SimpleForm>
//             <TextInput label="Alias" source="alias" />
//         </SimpleForm>
//     </Create>
// };