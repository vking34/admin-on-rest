import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import RaisedButton from 'material-ui/RaisedButton';
import FlatButton from 'material-ui/FlatButton';
import { push as pushAction } from 'react-router-redux';
import { refreshView, showNotification as showNotificationAction } from 'admin-on-rest';

import ContentSave from 'material-ui/svg-icons/content/save';

class UserCreateButton extends Component {

    handleClick = () => {
        const { push, record, showNotification, refreshView } = this.props;
        console.log(this.props);

        fetch(`/users`,
            {
                method: 'POST',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type' : 'application/json',
                    'Authorization' : 'Bearer ' + localStorage.getItem("token")
                },
                body: JSON.stringify(record)
            })
            .then(resp => resp.json())
            .then((resp) => {
                if(resp.status === true){
                    showNotification('Updated');
                    push('/users');
                }
                else {
                    showNotification('Error', 'warning');
                }
            });
    };

    render() {
        return (
            <RaisedButton
                icon={
                    <ContentSave />
                }
                label="Create User"
                onClick={this.handleClick}
                style={{
                    margin: '10px 24px',
                    position: 'relative',
                }}
            />
        );
    }
}

UserCreateButton.propTypes = {
    push: PropTypes.func,
    record: PropTypes.object,
    showNotification: PropTypes.func,
};

export default connect(null, {
    push: pushAction,
    showNotification: showNotificationAction,
})(UserCreateButton);
