// in src/authClient.js
import { AUTH_LOGOUT, AUTH_LOGIN, AUTH_ERROR, AUTH_CHECK, AUTH_GET_PERMISSIONS } from 'admin-on-rest';
import decodeJwt from 'jwt-decode';

export default (type, params) => {
    if (type === AUTH_LOGIN) {
        const { username, password } = params;
        const request = new Request('http://localhost/login', {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'text/plain'
            },
            body: JSON.stringify({ username, password })
        });
        return fetch(request)
            .then(response => {
                if (response.status < 200 || response.status >= 300) {
                    throw new Error(response.statusText);
                }
                return response.json();
            })
            .then(({ token }) => {
                const decodedToken = decodeJwt(token);
                console.log(decodedToken);
                localStorage.setItem('token', token);
                localStorage.setItem('role', decodedToken.jti);
            });
    }

    if (type === AUTH_LOGOUT) {
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        return Promise.resolve();
    }

    if (type === AUTH_ERROR) {
        const status  = params.message.status;
        if (status === 401 || status === 403 || status === 500 ) {
            localStorage.removeItem('token');
            return Promise.reject();
        }
        return Promise.resolve();
    }

    if (type === AUTH_CHECK) {
        return localStorage.getItem('token') ? Promise.resolve() : Promise.reject();
    }

    if (type === AUTH_GET_PERMISSIONS) {
        const role = localStorage.getItem('role');
        return Promise.resolve(role);
    }

    return Promise.reject('Unkown method');
}