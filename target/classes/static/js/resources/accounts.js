import React from 'react';
import {
    TabbedForm,
    FormTab,
    ReferenceManyField,
    List,
    Edit,
    Create,
    Datagrid,
    TextField,
    EditButton,
    DeleteButton,
    DisabledInput,
    required,
    SimpleForm,
    TextInput,
    Filter
} from 'admin-on-rest';
import {dateFormatter, dateParser} from "../functions/dateConverter";
import MsgList from "../components/msgList";
import CustomerList from "../components/customerList";
import DeletePageButton from "../components/deletePageFromAccount";
import AccessToken from "../fields/TokenField";
import AccountLinkField from "../fields/LinkField";

export const AccountList = (props) => (
    <List {...props} filters={<AccountFilter />}>
        <Datagrid>
            {/*<TextField label="ID" source="id"/>*/}
            <AccountLinkField />
            <TextField source="name"/>
            <TextField label="FB-User-ID" source="facebookUserId" />
            <EditButton />
            {/*<DeleteButton />*/}
        </Datagrid>
    </List>
);

const AccountFilter = (props) => (
    <Filter {...props}>
        <TextInput label="Search by Name" source="q" alwaysOn />
    </Filter>
);

export const AccountEdit = (props) => (
    <Edit title={<AccountTitle />} {...props}>
        {permissions =>
            <TabbedForm>
                <FormTab label="Basic Info">
                    <TextField label="ID" source="id"/>
                    <TextField label="Name" source="name"/>
                    <TextField label="FB-User-ID" source="facebookUserId"/>
                    { permissions === 'ROLE_ADMIN' && <AccessTokenField source="accessToken"/>}
                    <TextField label="Created On" source="createdOn"/>
                    <TextField label="Modified On" source="modifiedOn"/>
                    {/*<DateInput label="Created On" source="createdOn" format={dateFormatter} parse={dateParser} />*/}
                    {/*<DateInput label="Modified On" source="modifiedOn" format={dateFormatter} parse={dateParser} />*/}
                </FormTab>

                <FormTab label="Page_Maps">
                    <ReferenceManyField addLabel={false} reference="pages" target="accounts">
                        <Datagrid>
                            <TextField label="ID" source="id"/>
                            <TextField source="name"/>
                            <TextField label="FB-Page-ID" source="facebookPageId"/>
                            <EditButton/>
                            <DeletePageButton/>
                        </Datagrid>
                    </ReferenceManyField>
                </FormTab>

                <FormTab label="Bizweb_CustomerMaps">
                    <BizwebCustomerMapField source="bizwebCustomerMaps"/>
                </FormTab>

                <FormTab label="Messenger_Platform_IDs">
                    <MessengerPlatformIdField source="messengerPlatformIds"/>
                </FormTab>

            </TabbedForm>
        }
    </Edit>
);

const AccountTitle = ( {record}) => {
    return <span>Edit account: { record ? `${record.name}` : ''} </span>
};

const BizwebCustomerMapField = ({ record = {}} ) =>
    <CustomerList customers={record.bizwebCustomerMaps ? record.bizwebCustomerMaps : null} />;
BizwebCustomerMapField.defaultProps= { label: 'Bizweb Customer Maps'};

const MessengerPlatformIdField = ({ record = {}} ) =>
    <MsgList list={record.messengerPlatformIds} /> ;
MessengerPlatformIdField.defaultProps= { label: 'Messenger Platform IDs'};

const AccessTokenField = ({ record = {}} ) =>
    <AccessToken token={record.accessToken} />;
AccessTokenField.defaultProps= { label: 'Access Token'};

// export const AccountCreate = (props) => {
//     <Create {...props} redirect="list" >
//         <SimpleForm>
//             <TextInput label="Name" source="name" />
//
//         </SimpleForm>
//     </Create>
// };