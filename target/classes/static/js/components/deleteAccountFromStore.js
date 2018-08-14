import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { refreshView, showNotification as showNotificationAction } from 'admin-on-rest';
import { push as pushAction } from 'react-router-redux';
import ActionDelete from 'material-ui/svg-icons/action/delete';
import FlatButton from 'material-ui/FlatButton';

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
        return <FlatButton
            secondary
            label="Delete"
            icon={<ActionDelete />}
            onClick={this.handleClick}
            style={{ overflow: 'inherit' }}
        />;
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