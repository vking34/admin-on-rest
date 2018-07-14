var redux = require('redux');
import editDepartment from './editDepartment';
import editText from './editText';

var reducers = redux.combineReducers({
    departments: editDepartment,
    text : editText
});

module.exports = reducers;