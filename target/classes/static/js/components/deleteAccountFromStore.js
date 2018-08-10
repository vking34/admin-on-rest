import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import RaisedButton from 'material-ui/RaisedButton';
import { refreshView, showNotification as showNotificationAction } from 'admin-on-rest';
import { push as pushAction } from 'react-router-redux';

class DeleteAccountButton extends Component {

    handleClick = () => {

        const { push, record, showNotification, refreshView } = this.props;
        console.log(record);

        fetch(`/bizweb-stores/${record.parent}/accounts/${record.id}`,
            {
                method: 'DELETE',
                headers: {
                    'Accept': 'application/json',
                    'Content-Type' : 'application/json',
                    'Authorization' : 'Bearer ' + localStorage.getItem("token")
                }
            })
            .then(resp => resp.json())
            .then((resp) => {
                if(resp.status === true){
                    showNotification('Deleted Account');
                    // push(`/bizweb-stores/${record.parent}`);
                    refreshView();
                }
                else {
                    showNotification('Cant delete');
                }
            });

    };

    render() {
        return <RaisedButton label="Delete" onClick={this.handleClick} />;
    }
}

DeleteAccountButton.propTypes = {
    push: PropTypes.func,
    record: PropTypes.object,
    showNotification: PropTypes.func,
};

export default connect(null, {
    showNotification: showNotificationAction,
    push: pushAction,
    refreshView: refreshView
})(DeleteAccountButton);