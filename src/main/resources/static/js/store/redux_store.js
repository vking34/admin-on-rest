var redux = require('redux');

// var defaultState = {
//     arr: ['Android', 'iOs'],
//     isAdding: false
// };

// var reducer = (state = defaultState, action) => {
//
//     switch (action.type){
//
//         case 'TOGGLE_IS_ADDING':
//             return {...state, isAdding: !state.isAdding};
//
//         case 'ADD_ITEM':
//             return {...state, arr: [...state.arr, action.item]};
//
//         case 'REMOVE_ITEM':
//             return {...state, arr: state.arr.filter((e, i) => i != action.index)};
//
//         default:
//             return state;
//     }
//
// };

import arrReducer from '../reducers/arrReducer';
import addingReducer from '../reducers/addingReducers';

var reducer = redux.combineReducers({
    arr: arrReducer,
    isAdding: addingReducer
});

var store = redux.createStore(reducer);

store.subscribe(() => {
    console.log(store.getState())
    }
);

store.dispatch({type: 'TOGGLE_IS_ADDING'});


store.dispatch({type: 'ADD_ITEM', item: 'NodeJS'});


store.dispatch({type: 'REMOVE_ITEM', index: 1});

module.exports = store;