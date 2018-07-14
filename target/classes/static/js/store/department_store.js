var redux = require('redux');
var reducers = require('../reducers/reducers');

import thunkMiddleware from 'redux-thunk';
import  { createLogger } from 'redux-logger';
import { applyMiddleware } from 'redux';

const loggerMiddleware = createLogger();

const store = redux.createStore(reducers,
    applyMiddleware(
        thunkMiddleware,
        loggerMiddleware));

module.exports = store;