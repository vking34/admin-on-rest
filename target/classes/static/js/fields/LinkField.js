import React from 'react';

class LinkField extends React.Component{

    render(){
        return(
            <a href={`#/${this.props.resource}/${this.props.id}`}>
                {this.props.id}
            </a>
        );
    }
}

const StoreLinkField = ({ record = {}}) =>
    <LinkField resource="bizweb-stores" id={record.id} />;
StoreLinkField.defaultProps = { label: 'ID'};

const PageLinkField = ({ record = {}}) =>
    <LinkField resource="pages" id={record.id} />;
PageLinkField.defaultProps = { label: 'ID'};

const AccountLinkField = ({ record = {}}) =>
    <LinkField resource="accounts" id={record.id} />;
AccountLinkField.defaultProps = {label: 'ID'};

module.exports = LinkField;
module.exports = StoreLinkField;
module.exports = PageLinkField;
module.exports = AccountLinkField;