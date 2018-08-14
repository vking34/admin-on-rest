import React from 'react';
import {
    TabbedForm,
    FormTab,
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
    Filter,
    ReferenceManyField
} from 'admin-on-rest';
import AccessToken from "../fields/TokenGridField";
import PageLinkField from "../fields/LinkField";

export const PageList = (props) => (
    <List {...props} filters={<PageFilter/>}>
        {permissions =>
            <Datagrid>
                <PageLinkField/>
                <TextField source="name"/>
                <TextField label="FB_Page_ID" source="facebookPageId"/>
                <EditButton/>
                {/*{ permissions === 'ROLE_ADMIN' && <DeleteButton/>}*/}
            </Datagrid>
        }
    </List>
);

const PageFilter = (props) => (
    <Filter {...props}>
        <TextInput label="Search by Name" source="q" alwaysOn />
    </Filter>
);

export const PageEdit = (props) => (
    <Edit title={<PageTitle/>} {...props}>
        {permissions =>
            <TabbedForm>
                <FormTab label="Basic_Info">
                    <TextField label="ID" source="id"/>
                    <TextField label="FB Page ID" source="facebookPageId"/>
                    <TextField label="Name" source="name"/>
                    <TextField label="Created On" source="createdOn"/>
                    <TextField label="Modified On" source="modifiedOn"/>
                    {/*<DateInput label="Created On" source="createdOn" format={dateFormatter} parse={dateParser} />*/}
                    {/*<DateInput label="Modified On" source="modifiedOn" format={dateFormatter} parse={dateParser} />*/}
                </FormTab>

                <FormTab label="account_maps">
                    <ReferenceManyField addLabel={false} reference="accounts" target="pages">
                        <Datagrid>
                            <TextField label="ID" source="id"/>
                            <TextField source="name"/>
                            { permissions === 'ROLE_ADMIN' && <AccessTokenField source="access_token"/>}
                            <EditButton/>
                            {/*{ permissions === 'ROLE_ADMIN' && <DeleteAccountButton/>}*/}
                        </Datagrid>
                    </ReferenceManyField>
                </FormTab>
            </TabbedForm>
        }
    </Edit>
);

const PageTitle = ( {record}) => {
    return <span>Edit page: { record ? `${record.name}` : ''} </span>
};

const AccessTokenField = ({ record = {} }) =>
    <AccessToken token={record.access_token}/>;
AccessTokenField.defaultProps = { label: 'FB-Access-Token'};

