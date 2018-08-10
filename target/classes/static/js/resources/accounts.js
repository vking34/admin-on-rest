import React from 'react';
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
    DateInput
} from 'admin-on-rest';
import {dateFormatter, dateParser} from "../functions/dateConverter";
import MsgList from "../components/msgList";
import CustomerList from "../components/customerList";
import DeletePageButton from "../components/deletePageFromAccount";

export const AccountList = (props) => (
    <List {...props} filters={<AccountFilter />}>
        <Datagrid>
            <TextField label="ID" source="id"/>
            <TextField source="name"/>
            <TextField label="FB-User-ID" source="facebookUserId" />
            <EditButton />
            <DeleteButton />
        </Datagrid>
    </List>
);

const AccountFilter = (props) => (
    <Filter {...props}>
        <TextInput label="Search by Name" source="q" alwaysOn />
    </Filter>
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

export const AccountEdit = (props) => (
    <Edit title={<AccountTitle />} {...props}>
        {perrmissions =>
            <TabbedForm>
                <FormTab label="Basic Info">
                    <TextField label="ID" source="id"/>
                    <TextField label="Name" source="name"/>
                    <TextField label="FB-User-ID" source="facebookUserId"/>
                    { permissions === 'ROLE_ADMIN' && <TextField label="access-token" source="accessToken"/>}
                    <TextField label="Created On" source="createdOn"/>
                    <TextField label="Modified On" source="modifiedOn"/>
                    {/*<DateInput label="Created On" source="createdOn" format={dateFormatter} parse={dateParser} />*/}
                    {/*<DateInput label="Modified On" source="modifiedOn" format={dateFormatter} parse={dateParser} />*/}
                </FormTab>

                <FormTab label="Page Maps">
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

                <FormTab label="Bizweb-CustomerMaps">
                    <BizwebCustomerMapField source="bizwebCustomerMaps"/>
                </FormTab>

                <FormTab label="Messenger-Platform-IDs">
                    <MessengerPlatformIdField source="messengerPlatformIds"/>
                </FormTab>

            </TabbedForm>
        }
    </Edit>
);

// export const AccountCreate = (props) => {
//     <Create {...props} redirect="list" >
//         <SimpleForm>
//             <TextInput label="Name" source="name" />
//
//         </SimpleForm>
//     </Create>
// };