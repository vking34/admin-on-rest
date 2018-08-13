import React, { Component } from 'react';
import { Toolbar } from 'admin-on-rest';
import UserCreateButton from "./userCreateButton";

const UserCreateToolbar = props =>
    <Toolbar {...props}>
        <UserCreateButton />
    </Toolbar>;

module.exports = UserCreateToolbar;